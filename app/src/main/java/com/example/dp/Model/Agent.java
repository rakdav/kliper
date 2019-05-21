package com.example.dp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Agent implements Serializable {

    @SerializedName("agent")
    @Expose
    private Agent_ agent;

    public Agent_ getAgent() {
        return agent;
    }

    public void setAgent(Agent_ agent) {
        this.agent = agent;
    }




    public static class Agent_ {

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("group_name")
        @Expose
        private String groupName;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("mobile_phone")
        @Expose
        private String mobilePhone;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("position")
        @Expose
        private String position;
        @SerializedName("role")
        @Expose
        private Integer role;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public Integer getRole() {
            return role;
        }

        public void setRole(Integer role) {
            this.role = role;
        }
    }
}