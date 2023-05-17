package com.qxy.douyin.model;

import java.util.List;

public class Fans {
    @Override
    public String toString() {
        return "Fans{" +
                "data=" + data +
                ", extra=" + extra +
                '}';
    }

    private DataBean data;
    private ExtraBean extra;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public static class DataBean {
        private List<ListBean> list;
        private int total;
        private int cursor;
        private int error_code;
        private String description;
        private boolean has_more;

        @Override
        public String toString() {
            return "DataBean{" +
                    "list=" + list +
                    ", total=" + total +
                    ", cursor=" + cursor +
                    ", error_code=" + error_code +
                    ", description='" + description + '\'' +
                    ", has_more=" + has_more +
                    '}';
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getCursor() {
            return cursor;
        }

        public void setCursor(int cursor) {
            this.cursor = cursor;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public static class ListBean {
            private String open_id;
            private String union_id;
            private String nickname;
            private String avatar;
            private String city;
            private String province;
            private String country;
            private int gender;

            @Override
            public String toString() {
                return "ListBean{" +
                        "open_id='" + open_id + '\'' +
                        ", union_id='" + union_id + '\'' +
                        ", nickname='" + nickname + '\'' +
                        ", avatar='" + avatar + '\'' +
                        ", city='" + city + '\'' +
                        ", province='" + province + '\'' +
                        ", country='" + country + '\'' +
                        ", gender=" + gender +
                        '}';
            }

            public String getOpen_id() {
                return open_id;
            }

            public void setOpen_id(String open_id) {
                this.open_id = open_id;
            }

            public String getUnion_id() {
                return union_id;
            }

            public void setUnion_id(String union_id) {
                this.union_id = union_id;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }
        }
    }

    public static class ExtraBean {
        private String sub_description;
        private String logid;
        private long now;
        private int error_code;
        private String description;
        private int sub_error_code;

        public String getSub_description() {
            return sub_description;
        }

        public void setSub_description(String sub_description) {
            this.sub_description = sub_description;
        }

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
        }

        public long getNow() {
            return now;
        }

        public void setNow(long now) {
            this.now = now;
        }

        public int getError_code() {
            return error_code;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getSub_error_code() {
            return sub_error_code;
        }

        public void setSub_error_code(int sub_error_code) {
            this.sub_error_code = sub_error_code;
        }
    }
}
