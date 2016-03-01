package com.uber.uberfamily.framework.resolver;

import com.uber.uberfamily.listener.ProcessListener;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Project uber
 * @Package com.uber.uberfamily.framework.resolver
 * @Description // 文件上传类....加入了上传进度监听.
 * @Date 16/2/25
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class ProcessCommonsMultipartResolver extends CommonsMultipartResolver {

    private HttpServletRequest request;

    @Override
    public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {
        this.request = request;
        return super.resolveMultipart(request);
    }


    /**
     * Initialize the underlying {@code org.apache.commons.fileupload.servlet.ServletFileUpload}
     * instance. Can be overridden to use a custom subclass, e.g. for testing purposes.
     *
     * @param fileItemFactory the Commons FileItemFactory to use
     * @return the new ServletFileUpload instance
     */
    @Override
    protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {
        ServletFileUpload upload = new ServletFileUpload(fileItemFactory);
        upload.setSizeMax(-1);
        if (null != request) {
            HttpSession session = request.getSession();
            ProcessListener processListener = new ProcessListener(session);
            upload.setProgressListener(processListener);
        }
        return upload;
    }

    /**
     * Parse the given servlet request, resolving its multipart elements.
     *
     * @param request the request to parse
     * @return the parsing result
     * @throws MultipartException if multipart resolution failed.
     */
    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        HttpSession session = request.getSession();
        String encoding = "UTF-8";
        FileUpload fileUpload = prepareFileUpload(encoding);
        ProcessListener processListener = new ProcessListener(session);
        fileUpload.setProgressListener(processListener);
        try {
            List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        } catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart servlet request", ex);
        }
    }
}
