package com.qxy.douyin.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class UserInfo {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
    @Entity(tableName = "userinfo")
    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "avatar='" + avatar + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    ", description='" + description + '\'' +
                    ", e_account_role='" + e_account_role + '\'' +
                    ", error_code='" + error_code + '\'' +
                    ", gender='" + gender + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", open_id='" + open_id + '\'' +
                    ", province='" + province + '\'' +
                    ", union_id='" + union_id + '\'' +
                    '}';
        }

        @ColumnInfo(name="avatar",typeAffinity = ColumnInfo.TEXT)
        private String avatar;
        @ColumnInfo(name="city",typeAffinity = ColumnInfo.TEXT)
        private String city;
        @ColumnInfo(name="country",typeAffinity = ColumnInfo.TEXT)
        private String country;
        @ColumnInfo(name="description",typeAffinity = ColumnInfo.TEXT)
        private String description;
        @ColumnInfo(name="e_account_role",typeAffinity = ColumnInfo.TEXT)
        private String e_account_role;
        @ColumnInfo(name="error_code",typeAffinity = ColumnInfo.TEXT)
        private String error_code;
        @ColumnInfo(name="gender",typeAffinity = ColumnInfo.TEXT)
        private String gender;
        @ColumnInfo(name="nickname",typeAffinity = ColumnInfo.TEXT)
        private String nickname;
        @PrimaryKey
        @NonNull
        @ColumnInfo(name="openid",typeAffinity = ColumnInfo.TEXT)
        private String open_id;
        @ColumnInfo(name="province",typeAffinity = ColumnInfo.TEXT)
        private String province;
        @ColumnInfo(name="union_id",typeAffinity = ColumnInfo.TEXT)
        private String union_id;

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

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getE_account_role() {
            return e_account_role;
        }

        public void setE_account_role(String e_account_role) {
            this.e_account_role = e_account_role;
        }

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getUnion_id() {
            return union_id;
        }

        public void setUnion_id(String union_id) {
            this.union_id = union_id;
        }
    }
}
