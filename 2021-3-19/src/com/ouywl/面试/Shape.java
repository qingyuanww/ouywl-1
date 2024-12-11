package com.ouywl.面试;

/**
 * @description:
 * @author: oywl
 * @time: 2022-9-14 21:42
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected String type;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
