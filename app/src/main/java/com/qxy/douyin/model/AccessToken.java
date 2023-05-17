package com.qxy.douyin.model;

public class AccessToken {


    private DataDTO data;
    private String message;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        @Override
        public String toString() {
            return "DataDTO{" +
                    "access_token='" + access_token + '\'' +
                    ", description='" + description + '\'' +
                    ", error_code='" + error_code + '\'' +
                    ", expires_in='" + expires_in + '\'' +
                    ", open_id='" + open_id + '\'' +
                    ", refresh_expires_in='" + refresh_expires_in + '\'' +
                    ", refresh_token='" + refresh_token + '\'' +
                    ", scope='" + scope + '\'' +
                    '}';
        }

        private String access_token;
        private String description;
        private String error_code;
        private String expires_in;
        private String open_id;
        private String refresh_expires_in;
        private String refresh_token;
        private String scope;

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

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getRefresh_expires_in() {
            return refresh_expires_in;
        }

        public void setRefresh_expires_in(String refresh_expires_in) {
            this.refresh_expires_in = refresh_expires_in;
        }

        public String getRefresh_token() {
            return refresh_token;
        }

        public void setRefresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
        }

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
