package org.springrain.frame.util;

import java.io.Serializable;
import java.util.Map;

public class RequestDatas<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Page page;
    private T data;
    private Map map;


    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
