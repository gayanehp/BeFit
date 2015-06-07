package com.example.gayaneh.befit;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gayaneh on 5/31/2015.
 */
public class BeFitFood {

    private String id,item,brand;
    private Integer type,upc,calories;
    private Boolean breakfast,lunch,dinner;

    public BeFitFood (JSONObject jsonFood) throws JSONException{
        this.id = (String) jsonFood.optString("item_id");
        this.item = (String) jsonFood.optString("item_name");
        this.brand = (String) jsonFood.optString("brand_name");
        this.type = (Integer)jsonFood.optInt("item_type");
        this.upc =(Integer) jsonFood.optInt("upc");
        this.calories = (Integer) jsonFood.optInt("nc_calories");
        this.breakfast = false;
        this.lunch = false;
        this.dinner = false;

    }

    public String getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getType() {
        return type;
    }

    public Integer getUpc() {
        return upc;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setUpc(Integer upc) {
        this.upc = upc;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Boolean getIsBreakfast() {
        return breakfast;
    }

    public Boolean getIsLunch() {
        return lunch;
    }

    public Boolean getIsDinner() {
        return dinner;
    }

    public void setIsBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public void setIsLunch(Boolean lunch) {
        this.lunch = lunch;
    }

    public void setIsDinner(Boolean dinner) {
        this.dinner = dinner;
    }
}
