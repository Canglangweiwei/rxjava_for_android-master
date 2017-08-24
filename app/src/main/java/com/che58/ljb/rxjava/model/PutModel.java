package com.che58.ljb.rxjava.model;

import java.util.List;

@SuppressWarnings("ALL")
public class PutModel {

    private int code;
    private String message;
    private XgoEntity entity;

    public PutModel() {
        super();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public XgoEntity getEntity() {
        return entity;
    }

    public void setEntity(XgoEntity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "PutModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }

    private class XgoEntity {

        private List<Data> data;

        public XgoEntity() {
            super();
        }

        public List<Data> getData() {
            return data;
        }

        public void setData(List<Data> data) {
            this.data = data;
        }

        private class Data {

        }

        @Override
        public String toString() {
            return "XgoEntity{" +
                    "data=" + data +
                    '}';
        }
    }
}
