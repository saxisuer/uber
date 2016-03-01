package com.uber.uberfamily.framework.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project uber
 * @Package com.uber.uberfamily.framework.util
 * @Description
 * @Date 16/2/26
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class CustomerPropertiesLoader implements BeanFactoryPostProcessor, BeanNameAware {

    private static final Logger logger = Logger.getLogger(CustomerPropertiesLoader.class);

    /**
     * 当前对象在spring中的实例名称
     */
    private String beanName;

    /**
     * 配置文件的路径
     */
    private List<String> locations = new ArrayList<String>();

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    /**
     * 在Spring启动时优先调用
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactoryToProcess) throws BeansException {
        if (this.locations.isEmpty()) {
            BeanDefinition bean = beanFactoryToProcess.getBeanDefinition(this.beanName);
            MutablePropertyValues pvs = bean.getPropertyValues();
            PropertyValue[] pvArray = pvs.getPropertyValues();
            for (PropertyValue pv : pvArray) {
                Object newVal = pv.getValue();
                resolveValue(newVal);
            }
            loadProperties();
        } else {
            loadProperties();
        }
    }

    /**
     * 加载配置文件
     */
    private void loadProperties() {
        PathMatchingResourcePatternResolver pr = new PathMatchingResourcePatternResolver();
        if (null != this.locations && this.locations.size() > 0) {
            for (String path : this.locations) {
                try {
                    Resource resource = pr.getResource(path);
                    PropertiestUtil.pro.load(resource.getInputStream());
                    logger.info("load [" + path + "] success!");
                } catch (Exception e) {
                    logger.error("load [" + path + "] failure!", e);
                }
            }
        }
    }

    protected void resolveValue(Object value) {
        if (value instanceof List<?>) {
            for (Object o : (List<?>) value) {
                resolveValue(o);
            }
        } else if (value instanceof TypedStringValue) {
            TypedStringValue typedStringValue = (TypedStringValue) value;
            String stringValue = typedStringValue.getValue();
            this.locations.add(stringValue);
        } else if (value instanceof String) {
            this.locations.add((String) value);
        }
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }
}
