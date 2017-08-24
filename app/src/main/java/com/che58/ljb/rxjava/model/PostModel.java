package com.che58.ljb.rxjava.model;

@SuppressWarnings("ALL")
public class PostModel {

    private int code;
    private String message;
    private XgoEntity entity;

    public PostModel() {
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

    private class XgoEntity {

        private Data data;

        public XgoEntity() {
            super();
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        private class Data {

            private String xxx;

            public Data() {
                super();
            }

            public String getXxx() {
                return xxx;
            }

            public void setXxx(String xxx) {
                this.xxx = xxx;
            }

            @Override
            public String toString() {
                return "Data{" +
                        "xxx='" + xxx + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "XgoEntity{" +
                    "data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetModel{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}
