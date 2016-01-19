package com.uber.uberfamily.context;

/**
 * @Project uber
 * @Package com.uber.uberfamily.context
 * @Description //TODO
 * @Date 16/1/20
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class SystemContext {
    /**
     * 系统启动目录，基于物理路径
     */
    public static String SYSTEM_HOME;
    /**
     * 系统资源上传目录
     */
    public final static String UPLOAD_PATH = "uploads";

    /**
     * 系统资源上传目录，基于物理路径
     */
    public static String getUploadPath() {
        return SYSTEM_HOME + UPLOAD_PATH;
    }
}
