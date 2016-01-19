package com.uber.uberfamily.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Project showMeTheMoney
 * @Package com.enmotech.service
 * @Description
 * @Date 15/7/2
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Service
@Scope(value = "singleton")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public @interface MyBatisService {
    String value() default "";
}
