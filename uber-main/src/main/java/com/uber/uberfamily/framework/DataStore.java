package com.uber.uberfamily.framework;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project uber
 * @Package com.uber.uberfamily.framework
 * @Description //TODO
 * @Date 16/2/5
 * @USER saxisuer
 * @COMPANY ENMOTECH
 */
public class DataStore<T> implements Serializable {

    private long total;

    private List<T> rows = new ArrayList<T>();

    public DataStore(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public DataStore() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
