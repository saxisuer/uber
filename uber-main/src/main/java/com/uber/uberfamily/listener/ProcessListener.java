package com.uber.uberfamily.listener;

import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

/**
 * @Project uber
 * @Package com.uber.uberfamily.listener
 * @Description //实现文件上传时时百分比监听
 * @Date 16/2/25
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class ProcessListener implements ProgressListener {

    private HttpSession httpSession;
    private double megaBytes = -1;

    public ProcessListener(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public ProcessListener() {
    }

    /**
     * Updates the listeners status information.
     *
     * @param pBytesRead     The total number of bytes, which have been read
     *                       so far.
     * @param pContentLength The total number of bytes, which are being
     *                       read. May be -1, if this number is unknown.
     * @param pItems         The number of the field, which is currently being
     */
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        if (megaBytes == (double) pBytesRead) {
            return;
        }
        megaBytes = (double) pBytesRead;
        if (pContentLength != -1) {
            int percent = (int) (((float) (double) pBytesRead / (float) (pContentLength)) * 100);
            httpSession.setAttribute("percent", percent + "%");
        }
    }
}
