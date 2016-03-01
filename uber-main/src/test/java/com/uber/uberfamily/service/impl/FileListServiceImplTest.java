package com.uber.uberfamily.service.impl;

import org.junit.Test;

import java.io.File;

/**
 * @Project uber
 * @Package com.uber.uberfamily.service.impl
 * @Description //TODO
 * @Date 16/3/1
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class FileListServiceImplTest {


    @Test
    public void test() {
        String fileName = "未命名.mp4";
        String file1 = fileName.substring(0, fileName.lastIndexOf(".") - 1);
        System.out.println(file1);
    }
}