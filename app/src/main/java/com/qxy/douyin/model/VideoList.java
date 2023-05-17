package com.qxy.douyin.model;

import java.util.List;

public class VideoList {

    private ExtraBean extra;
    private DataBean data;

    @Override
    public String toString() {
        return "VideoList{" +
                "extra=" + extra +
                ", data=" + data +
                '}';
    }

    public ExtraBean getExtra() {
        return extra;
    }

    public void setExtra(ExtraBean extra) {
        this.extra = extra;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ExtraBean {
        private int error_code;
        private String description;
        private int sub_error_code;
        private String sub_description;
        private String logid;
        private long now;

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
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "error_code=" + error_code +
                    ", description='" + description + '\'' +
                    ", has_more=" + has_more +
                    ", list=" + list +
                    ", cursor=" + cursor +
                    '}';
        }

        private int error_code;
        private String description;
        private boolean has_more;
        private List<ListBean> list;
        private long cursor;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public long getCursor() {
            return cursor;
        }

        public void setCursor(int cursor) {
            this.cursor = cursor;
        }

        public static class ListBean {
            private String title;
            private boolean is_top;
            private int create_time;
            private boolean is_reviewed;
            private int video_status;
            private String share_url;
            private String item_id;
            private String video_id;
            private int media_type;
            private String cover;
            private StatisticsBean statistics;

            @Override
            public String toString() {
                return "ListBean{" +
                        "title='" + title + '\'' +
                        ", is_top=" + is_top +
                        ", create_time=" + create_time +
                        ", is_reviewed=" + is_reviewed +
                        ", video_status=" + video_status +
                        ", share_url='" + share_url + '\'' +
                        ", item_id='" + item_id + '\'' +
                        ", video_id='" + video_id + '\'' +
                        ", media_type=" + media_type +
                        ", cover='" + cover + '\'' +
                        ", statistics=" + statistics +
                        '}';
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public boolean isIs_top() {
                return is_top;
            }

            public void setIs_top(boolean is_top) {
                this.is_top = is_top;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public boolean isIs_reviewed() {
                return is_reviewed;
            }

            public void setIs_reviewed(boolean is_reviewed) {
                this.is_reviewed = is_reviewed;
            }

            public int getVideo_status() {
                return video_status;
            }

            public void setVideo_status(int video_status) {
                this.video_status = video_status;
            }

            public String getShare_url() {
                return share_url;
            }

            public void setShare_url(String share_url) {
                this.share_url = share_url;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getVideo_id() {
                return video_id;
            }

            public void setVideo_id(String video_id) {
                this.video_id = video_id;
            }

            public int getMedia_type() {
                return media_type;
            }

            public void setMedia_type(int media_type) {
                this.media_type = media_type;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public StatisticsBean getStatistics() {
                return statistics;
            }

            public void setStatistics(StatisticsBean statistics) {
                this.statistics = statistics;
            }

            public static class StatisticsBean {
                private int forward_count;
                private int comment_count;
                private int digg_count;
                private int download_count;
                private int play_count;
                private int share_count;

                @Override
                public String toString() {
                    return "StatisticsBean{" +
                            "forward_count=" + forward_count +
                            ", comment_count=" + comment_count +
                            ", digg_count=" + digg_count +
                            ", download_count=" + download_count +
                            ", play_count=" + play_count +
                            ", share_count=" + share_count +
                            '}';
                }

                public int getForward_count() {
                    return forward_count;
                }

                public void setForward_count(int forward_count) {
                    this.forward_count = forward_count;
                }

                public int getComment_count() {
                    return comment_count;
                }

                public void setComment_count(int comment_count) {
                    this.comment_count = comment_count;
                }

                public int getDigg_count() {
                    return digg_count;
                }

                public void setDigg_count(int digg_count) {
                    this.digg_count = digg_count;
                }

                public int getDownload_count() {
                    return download_count;
                }

                public void setDownload_count(int download_count) {
                    this.download_count = download_count;
                }

                public int getPlay_count() {
                    return play_count;
                }

                public void setPlay_count(int play_count) {
                    this.play_count = play_count;
                }

                public int getShare_count() {
                    return share_count;
                }

                public void setShare_count(int share_count) {
                    this.share_count = share_count;
                }
            }
        }
    }
}
