package pl.codecity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.MessageCodesResolver;
import pl.codecity.helper.UserCreateHelper;
import pl.codecity.model.User;
import pl.codecity.repository.UserRepository;
import pl.codecity.utility.AuthorizedUser;

import java.time.LocalDateTime;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private MessageCodesResolver messageCodesResolver;
    private PlatformTransactionManager transactionManager;

    @Autowired
    public UserService(UserRepository userRepository, MessageCodesResolver messageCodesResolver, PlatformTransactionManager transactionManager) {
        this.userRepository = userRepository;
        this.messageCodesResolver = messageCodesResolver;
        this.transactionManager = transactionManager;
    }

    @CacheEvict(allEntries = true)
    public User createUser(UserCreateHelper helper, AuthorizedUser authorizedUser){
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        user.setFirstName(helper.getFirstName());
        user.setLastName(helper.getLastName());
        user.setNickName(helper.getNickName());
        user.setEmail(helper.getEmail());
        user.setPassword(helper.getPassword());

        user.setCreatedAt(now);
        user.setCreatedBy(authorizedUser.toString());

        user.setUpdatedAt(now);
        user.setUpdatedBy(authorizedUser.toString());
        return userRepository.saveAndFlush(user);
    }
}
