package com.qxy.douyin.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.qxy.douyin.db.ListConverters;

import java.util.List;

public class RankItem {

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
        private String active_time;
        private String description;
        private String error_code;
        private List<ListBean> list;

        public String getActive_time() {
            return active_time;
        }

        public void setActive_time(String active_time) {
            this.active_time = active_time;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }
        @Entity(tableName = "rankitem")
        @TypeConverters(ListConverters.class)
        public static class ListBean {
            @ColumnInfo(name = "actors")
            private List<String> actors;
            @ColumnInfo(name= "areas")
            private List<String> areas;
            @ColumnInfo(name= "directors")
            private List<String> directors;
            @ColumnInfo(name= "discussion_hot")
            private String discussion_hot;
            @ColumnInfo(name= "hot")
            private String hot;
            @PrimaryKey
            @NonNull
            @ColumnInfo(name= "id")
            private String id;
            @ColumnInfo(name= "influence_hot")
            private String influence_hot;
            @ColumnInfo(name= "maoyan_id")
            private String maoyan_id;
            @ColumnInfo(name= "name")
            private String name;
            @ColumnInfo(name= "name_en")
            private String name_en;
            @ColumnInfo(name= "poster")
            private String poster;
            @ColumnInfo(name= "release_date")
            private String release_date;
            @ColumnInfo(name= "search_hot")
            private String search_hot;
            @ColumnInfo(name= "tags")
            private List<String> tags;
            @ColumnInfo(name= "topic_hot")
            private String topic_hot;
            @ColumnInfo(name= "type")
            private String type;

            public List<String> getActors() {
                return actors;
            }

            public void setActors(List<String> actors) {
                this.actors = actors;
            }

            public List<String> getAreas() {
                return areas;
            }

            public void setAreas(List<String> areas) {
                this.areas = areas;
            }

            public List<String> getDirectors() {
                return directors;
            }

            public void setDirectors(List<String> directors) {
                this.directors = directors;
            }

            public String getDiscussion_hot() {
                return discussion_hot;
            }

            public void setDiscussion_hot(String discussion_hot) {
                this.discussion_hot = discussion_hot;
            }

            public String getHot() {
                return hot;
            }

            public void setHot(String hot) {
                this.hot = hot;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInfluence_hot() {
                return influence_hot;
            }

            public void setInfluence_hot(String influence_hot) {
                this.influence_hot = influence_hot;
            }

            public String getMaoyan_id() {
                return maoyan_id;
            }

            public void setMaoyan_id(String maoyan_id) {
                this.maoyan_id = maoyan_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getPoster() {
                return poster;
            }

            public void setPoster(String poster) {
                this.poster = poster;
            }

            public String getRelease_date() {
                return release_date;
            }

            public void setRelease_date(String release_date) {
                this.release_date = release_date;
            }

            public String getSearch_hot() {
                return search_hot;
            }

            public void setSearch_hot(String search_hot) {
                this.search_hot = search_hot;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public String getTopic_hot() {
                return topic_hot;
            }

            public void setTopic_hot(String topic_hot) {
                this.topic_hot = topic_hot;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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
