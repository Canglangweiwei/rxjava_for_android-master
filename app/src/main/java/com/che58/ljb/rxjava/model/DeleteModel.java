package com.che58.ljb.rxjava.model;

@SuppressWarnings("ALL")
public class DeleteModel {

    private int code;
    private String message;
    private XgoEntity entity;

    public DeleteModel() {
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

            private int id;
            private String name;
            private String value;
            private Links links;

            public Data() {
                super();
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public Links getLinks() {
                return links;
            }

            public void setLinks(Links links) {
                this.links = links;
            }

            private class Links {

                private String rel;
                private String href;

                public Links() {
                    super();
                }

                public String getRel() {
                    return rel;
                }

                public void setRel(String rel) {
                    this.rel = rel;
                }

                public String getHref() {
                    return href;
                }

                public void setHref(String href) {
                    this.href = href;
                }
            }
        }
    }
}
