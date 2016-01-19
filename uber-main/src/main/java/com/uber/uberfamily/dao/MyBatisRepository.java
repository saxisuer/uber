package com.uber.uberfamily.dao;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Project uber
 * @Package com.uber.uberfamily.dao
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
@Target(ElementType.TYPE)
public @interface MyBatisRepository {
    String value() default "";
}