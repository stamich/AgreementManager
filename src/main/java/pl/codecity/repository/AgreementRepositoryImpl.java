package pl.codecity.repository;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import pl.codecity.helper.AgreementSearchHelper;
import pl.codecity.model.Agreement;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class AgreementRepositoryImpl implements AgreementRepositoryCustom {

    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Agreement> search(AgreementSearchHelper helper, Pageable pageable) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Agreement.class)
                .get();

        @SuppressWarnings("rawtypes")
        BooleanJunction<BooleanJunction> junction = queryBuilder.bool();
        junction.must(queryBuilder.all().createQuery());

        if(StringUtils.hasText(helper.getKeyword())){
            Analyzer analyzer = fullTextEntityManager.getSearchFactory().getAnalyzer("synonyms");
            String[] fields = new String[] { "name" };
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
            parser.setDefaultOperator(QueryParser.Operator.AND);
            Query query = null;
            try {
                query = parser.parse(helper.getKeyword());
            }
            catch (ParseException pe1){
                try {
                    query = parser.parse(QueryParser.escape(helper.getKeyword()));
                }
                catch (ParseException pe2){
                    throw new RuntimeException(pe2);
                }
            }
            junction.must(query);
        }

        if(StringUtils.hasText(helper.getAmount().toString())){
            junction.must(queryBuilder.keyword().onField("amount").matching(helper.getAmount().toString()).createQuery());
        }
        Query searchQuery = junction.createQuery();
        Session session = (Session) entityManager.getDelegate();
        Criteria criteria = session.createCriteria(Agreement.class);

//        CriteriaBuilder builder = getSession().getCriteriaBuilder();
//        CriteriaQuery<T> query = builder.createQuery(Agreement.class);

        Sort sort = new Sort(new SortField("sortName", SortField.Type.DOUBLE));

        FullTextQuery persistenceQuery = fullTextEntityManager.createFullTextQuery(searchQuery, Agreement.class).setCriteriaQuery(criteria).setSort(sort);
        persistenceQuery.setFirstResult((int) pageable.getOffset());
        persistenceQuery.setMaxResults(pageable.getPageSize());

        int resultSize = persistenceQuery.getResultSize();

        @SuppressWarnings("unchecked")
        List<Agreement> results = persistenceQuery.getResultList();
        return new PageImpl<>(results, pageable, resultSize);
    }
}
