package com.example.gayaneh.befit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gayaneh on 5/31/2015.
 */
public class BeFitFood {

    private String id,
            name,brand,servingUnit;
    private Integer servingQty,calories;

    private Boolean breakfast,lunch,dinner;

    public BeFitFood (JSONObject jsonFood) throws JSONException{
     //   this.id = (String) jsonFood.optString("item_id");
        this.name = (String) jsonFood.optString("item_name");
        this.brand = (String) jsonFood.optString("brand_name");
        this.calories = (Integer) jsonFood.optInt("nf_calories");
        this.servingQty = (Integer) jsonFood.optInt("nf_serving_size_qty");
        this.servingUnit = (String) jsonFood.optString("nf_serving_size_unit");
        this.breakfast = false;
        this.lunch = false;
        this.dinner = false;

    }

    public BeFitFood (){
        this.breakfast = false;
        this.lunch = false;
        this.dinner = false;
    }


    public static ArrayList<BeFitFood> makeFoodList(String foodData) throws JSONException {
        ArrayList<BeFitFood> befitFoods = new ArrayList<>();
        JSONObject data = new JSONObject(foodData);
     //   JSONObject hits = data.optJSONObject("hits");
        JSONArray foodArray = data.optJSONArray("hits");

        for (int i = 0; i < foodArray.length(); i++) {
            JSONObject food = (JSONObject) foodArray.get(i);
            JSONObject foodSpec = (JSONObject) food.optJSONObject("fields");
            BeFitFood currentFood = new BeFitFood(foodSpec);
            befitFoods.add(currentFood);
        }
        return befitFoods;
    }

//    public String getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getServingUnit() {
        return servingUnit;
    }

    public Integer getServingQty() {
        return servingQty;
    }

    public Integer getCalories() {
        return calories;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setServingUnit(String serving_unit) {
        this.servingUnit = serving_unit;
    }

    public void setServingQty(Integer serving_qty) {
        this.servingQty = serving_qty;
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
