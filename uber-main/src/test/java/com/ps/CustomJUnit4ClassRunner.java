package com.ps;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

import java.io.FileNotFoundException;

/**
 * @Project showMeTheMoney
 * @Package PACKAGE_NAME
 * @Description
 * @Date 15/6/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class CustomJUnit4ClassRunner extends SpringJUnit4ClassRunner {

    static {
        try {
            Log4jConfigurer.initLogging("classpath:log.properties");
        } catch (FileNotFoundException ex) {
            System.err.println("Cannot Initialize log4j");
        }
    }

    public CustomJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }


}
