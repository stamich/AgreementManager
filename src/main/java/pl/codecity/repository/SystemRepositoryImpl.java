package pl.codecity.repository;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.BooleanJunction;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import pl.codecity.helper.SystemSearchHelper;
import pl.codecity.model.System;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class SystemRepositoryImpl implements SystemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<System> search(SystemSearchHelper helper, Pageable pageable) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(System.class).get();

        BooleanJunction<BooleanJunction> junction = queryBuilder.bool();
        junction.must(queryBuilder.all().createQuery());

        if (StringUtils.hasText(helper.getKeyword())){
            Analyzer analyzer = fullTextEntityManager.getSearchFactory().getAnalyzer("system_synonyms");
            String[] fields = new String[]{ "system_name" };
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
            parser.setDefaultOperator(QueryParser.Operator.AND);
            Query query = null;
            try {
                query = parser.parse(helper.getKeyword());
            }
            catch (ParseException e1){
                try {
                    query = parser.parse(QueryParser.escape(helper.getKeyword()));
                }
                catch (ParseException e2){
                    throw new RuntimeException(e2);
                }
            }
        }
        return null;
    }
}
