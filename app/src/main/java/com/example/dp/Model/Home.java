package com.example.dp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Home implements Serializable {

    @SerializedName("property")
    @Expose
    private Property property;

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }


    public static class Parameter {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("unit")
        @Expose
        private Object unit;
        @SerializedName("value")
        @Expose
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getUnit() {
            return unit;
        }

        public void setUnit(Object unit) {
            this.unit = unit;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }


    public static class Property {

        @SerializedName("agent_id")
        @Expose
        private Integer agentId;
        @SerializedName("cadastral_number")
        @Expose
        private String cadastralNumber;
        @SerializedName("city_id")
        @Expose
        private Integer cityId;
        @SerializedName("country_id")
        @Expose
        private Integer countryId;
        @SerializedName("district_id")
        @Expose
        private Integer districtId;
        @SerializedName("hidden")
        @Expose
        private Boolean hidden;
        @SerializedName("hot")
        @Expose
        private Boolean hot;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("lock")
        @Expose
        private String lock;
        @SerializedName("longitude")
        @Expose
        private String longitude;
        @SerializedName("meta_description")
        @Expose
        private String metaDescription;
        @SerializedName("meta_keywords")
        @Expose
        private String metaKeywords;
        @SerializedName("meta_title")
        @Expose
        private String metaTitle;
        @SerializedName("metro_id")
        @Expose
        private Integer metroId;
        @SerializedName("owner_id")
        @Expose
        private Integer ownerId;
        @SerializedName("parameters")
        @Expose
        private List<Parameter> parameters = null;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("zoom")
        @Expose
        private Integer zoom;

        public Integer getAgentId() {
            return agentId;
        }

        public void setAgentId(Integer agentId) {
            this.agentId = agentId;
        }

        public String getCadastralNumber() {
            return cadastralNumber;
        }

        public void setCadastralNumber(String cadastralNumber) {
            this.cadastralNumber = cadastralNumber;
        }

        public Integer getCityId() {
            return cityId;
        }

        public void setCityId(Integer cityId) {
            this.cityId = cityId;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public Integer getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Integer districtId) {
            this.districtId = districtId;
        }

        public Boolean getHidden() {
            return hidden;
        }

        public void setHidden(Boolean hidden) {
            this.hidden = hidden;
        }

        public Boolean getHot() {
            return hot;
        }

        public void setHot(Boolean hot) {
            this.hot = hot;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLock() {
            return lock;
        }

        public void setLock(String lock) {
            this.lock = lock;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getMetaDescription() {
            return metaDescription;
        }

        public void setMetaDescription(String metaDescription) {
            this.metaDescription = metaDescription;
        }

        public String getMetaKeywords() {
            return metaKeywords;
        }

        public void setMetaKeywords(String metaKeywords) {
            this.metaKeywords = metaKeywords;
        }

        public String getMetaTitle() {
            return metaTitle;
        }

        public void setMetaTitle(String metaTitle) {
            this.metaTitle = metaTitle;
        }

        public Integer getMetroId() {
            return metroId;
        }

        public void setMetroId(Integer metroId) {
            this.metroId = metroId;
        }

        public Integer getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(Integer ownerId) {
            this.ownerId = ownerId;
        }

        public List<Parameter> getParameters() {
            return parameters;
        }

        public void setParameters(List<Parameter> parameters) {
            this.parameters = parameters;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getZoom() {
            return zoom;
        }

        public void setZoom(Integer zoom) {
            this.zoom = zoom;
        }
    }
}

