package com.mr.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;

/**
 * @author LiangYongjie
 * @date 2019-01-06
 */
@Configuration
public class TransactionConfig {

    /**
     * 事务超时时间
     */
    private static final int TX_METHOD_TIMEOUT = 5;


    /**
     * 写事务的方法名
     */
    private static final String[] REQUIRED_METHOD = {
        "add*",
        "save*",
        "insert*",
        "update*",
        "delete*",
        "change*",
        "remove*",
        "cancel*",
        "generate*",
        "login",
        "close"
    };

    /**
     * 读事务的方法名
     */
    private static final String[] READ_ONLY_METHOD = {
        "get*",
        "count*",
        "find*",
        "query*",
        "select*",
        "list*",
    };


    @Bean("txInterceptor")
    public TransactionInterceptor txAdvice(PlatformTransactionManager transactionManager) {
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        RuleBasedTransactionAttribute readOnly = this.readOnlyTx();
        RuleBasedTransactionAttribute required = this.requiredTx();

        for (String methodName : READ_ONLY_METHOD) {
            source.addTransactionalMethod(methodName, readOnly);
        }

        for (String methodName : REQUIRED_METHOD) {
            source.addTransactionalMethod(methodName, required);
        }

        return new TransactionInterceptor(transactionManager, source);
    }

    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setProxyTargetClass(true);
        creator.setBeanNames("*ServiceImpl");
        creator.setInterceptorNames("txInterceptor");

        return creator;
    }

    /**
     * 写事务
     *
     * @return 写事务规则
     */
    private RuleBasedTransactionAttribute requiredTx() {
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        required.setTimeout(TX_METHOD_TIMEOUT);
        required.setReadOnly(false);

        return required;
    }

    /**
     * 只读事务
     *
     * @return 只读事务规则
     */
    private RuleBasedTransactionAttribute readOnlyTx() {
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        readOnly.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        readOnly.setReadOnly(true);

        return readOnly;
    }

}
