package com.uber.uberfamily.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Project uber
 * @Package com.uber.uberfamily.controller
 * @Description //TODO
 * @Date 16/4/3
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
@Controller
@RequestMapping(value = "/api")
public class UberApiController {

    private static final Logger logger = Logger.getLogger(UberApiController.class);

    @RequestMapping(value = "/getCode")
    public void getCode(String code) {
        logger.info(code);
    }
}
