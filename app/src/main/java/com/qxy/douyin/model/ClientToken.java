package com.qxy.douyin.model;

public class ClientToken {


    private DataBean data;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private String access_token;
        private String description;
        private String error_code;
        private String expires_in;

        @Override
        public String toString() {
            return "DataBean{" +
                    "access_token='" + access_token + '\'' +
                    ", description='" + description + '\'' +
                    ", error_code='" + error_code + '\'' +
                    ", expires_in='" + expires_in + '\'' +
                    '}';
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
            this.expires_in = expires_in;
        }
    }
}
