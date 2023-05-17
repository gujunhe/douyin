package com.qxy.douyin.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.qxy.douyin.db.ListConverters;

import java.util.List;

public class RankVersion {

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
        private String cursor;
        private String description;
        private String error_code;
        private String has_more;
        private List<ListBean> list;

        public String getCursor() {
            return cursor;
        }

        public void setCursor(String cursor) {
            this.cursor = cursor;
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

        public String getHas_more() {
            return has_more;
        }

        public void setHas_more(String has_more) {
            this.has_more = has_more;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
        @Entity(tableName = "rankversion")
        public static class ListBean {
            @Override
            public String toString() {
                return "ListBean{" +
                        "active_time='" + active_time + '\'' +
                        ", end_time='" + end_time + '\'' +
                        ", start_time='" + start_time + '\'' +
                        ", type='" + type + '\'' +
                        ", version='" + version + '\'' +
                        '}';
            }

            @ColumnInfo(name = "active_time")
            private String active_time;
            @ColumnInfo(name = "end_time")
            private String end_time;
            @ColumnInfo(name = "start_time")
            private String start_time;
            @ColumnInfo(name = "type")
            private String type;
            @PrimaryKey
            @NonNull
            @ColumnInfo(name = "version")
            private String version;

            public String getActive_time() {
                return active_time;
            }

            public void setActive_time(String active_time) {
                this.active_time = active_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }
        }
    }

    public static class ExtraBean {
        private String description;
        private String error_code;
        private String logid;
        private String now;
        private String sub_description;
        private String sub_error_code;

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

        public String getLogid() {
            return logid;
        }

        public void setLogid(String logid) {
            this.logid = logid;
        }

        public String getNow() {
            return now;
        }

        public void setNow(String now) {
            this.now = now;
        }

        public String getSub_description() {
            return sub_description;
        }

        public void setSub_description(String sub_description) {
            this.sub_description = sub_description;
        }

        public String getSub_error_code() {
            return sub_error_code;
        }

        public void setSub_error_code(String sub_error_code) {
            this.sub_error_code = sub_error_code;
        }
    }
}
