package com.ps;

import com.uber.uberfamily.model.BaseCard;
import com.uber.uberfamily.service.BaseCardService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Project uber
 * @Package com.ps
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@RunWith(CustomJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class MybatisTest {

    private static final Logger logger = Logger.getLogger(MybatisTest.class);
    @Autowired
    private BaseCardService baseCardService;


    @Test
    public void test1() {
        BaseCard baseCard = baseCardService.getById(57L);
        logger.info(baseCard);
    }

    @Test
    public void test2() {
        logger.info(baseCardService.getPage(null, 1, 10));
    }


    @Test
    public void test3() {
//        TestGenric gen = new TestGenric();
//        Class clazz = gen.getClass();
//        Type genType = gen.getClass().getGenericSuperclass();
//        if (!(genType instanceof ParameterizedType)) {
//            logger.info(clazz.getSimpleName()
//                    + "'s superclass not ParameterizedType");
//        } else {
//            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
//            for (Type param : params) {
//                Class c = (Class) param;
//                logger.info(c.getName());
//            }
//        }
        Type[] types = baseCardService.getClass().getGenericInterfaces();
        for (Type genType : types) {
            if (!(genType instanceof ParameterizedType)) {
                logger.info(baseCardService.getClass().getSimpleName()
                        + "'s superclass not ParameterizedType");
            }
        }
    }
}
