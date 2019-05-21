package com.example.dp.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class House implements Serializable {
    private String area;
    private String city_title;
    private String creation_date;
    private String currency;
    private String date;
    private String deal;
    private String description;
    private boolean display_price;
    private String district_title;
    private String floor;
    private String ground_area;
    private boolean hot;
    @PrimaryKey
    private int id;
    private String kitchen_area;
    private String title;
    private String latitude;
    private String longitude;
    private String live_area;
    private String lock;
    private String picture_path;
    private String price_per_meter;
    private String price_total;
    private String room_area;
    private String rooms;
    private String super_type;
    private String type;
    private String user_title;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public House(String area, String city_title, String creation_date, String currency, String date, String deal, String description, boolean display_price, String district_title, String floor, String ground_area, boolean hot, int id, String kitchen_area, String title, String latitude, String longitude, String live_area, String lock, String picture_path, String price_per_meter, String price_total, String room_area, String rooms, String super_type, String type, String user_title) {
        this.area = area;
        this.city_title = city_title;
        this.creation_date = creation_date;
        this.currency = currency;
        this.date = date;
        this.deal = deal;
        this.description = description;
        this.display_price = display_price;
        this.district_title = district_title;
        this.floor = floor;
        this.ground_area = ground_area;
        this.hot = hot;
        this.id = id;
        this.kitchen_area = kitchen_area;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
        this.live_area = live_area;
        this.lock = lock;
        this.picture_path = picture_path;
        this.price_per_meter = price_per_meter;
        this.price_total = price_total;
        this.room_area = room_area;
        this.rooms = rooms;
        this.super_type = super_type;
        this.type = type;
        this.user_title = user_title;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity_title() {
        return city_title;
    }

    public void setCity_title(String city_title) {
        this.city_title = city_title;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeal() {
        return deal;
    }

    public void setDeal(String deal) {
        this.deal = deal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisplay_price() {
        return display_price;
    }

    public void setDisplay_price(boolean display_price) {
        this.display_price = display_price;
    }

    public String getDistrict_title() {
        return district_title;
    }

    public void setDistrict_title(String district_title) {
        this.district_title = district_title;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getGround_area() {
        return ground_area;
    }

    public void setGround_area(String ground_area) {
        this.ground_area = ground_area;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKitchen_area() {
        return kitchen_area;
    }

    public void setKitchen_area(String kitchen_area) {
        this.kitchen_area = kitchen_area;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLive_area() {
        return live_area;
    }

    public void setLive_area(String live_area) {
        this.live_area = live_area;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    public String getPicture_path() {
        return picture_path;
    }

    public void setPicture_path(String picture_path) {
        this.picture_path = picture_path;
    }

    public String getPrice_per_meter() {
        return price_per_meter;
    }

    public void setPrice_per_meter(String price_per_meter) {
        this.price_per_meter = price_per_meter;
    }

    public String getPrice_total() {
        return price_total;
    }

    public void setPrice_total(String price_total) {
        this.price_total = price_total;
    }

    public String getRoom_area() {
        return room_area;
    }

    public void setRoom_area(String room_area) {
        this.room_area = room_area;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getSuper_type() {
        return super_type;
    }

    public void setSuper_type(String super_type) {
        this.super_type = super_type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser_title() {
        return user_title;
    }

    public void setUser_title(String user_title) {
        this.user_title = user_title;
    }

}