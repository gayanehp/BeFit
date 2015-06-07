package com.example.gayaneh.befit;

import android.provider.BaseColumns;

/**
 * Created by Gayaneh on 5/31/2015.
 */
public class Contract {

    public static final String DATABASE_NAME="BeFit.db";

    /**
     * "item_name","brand_name","upc"
     */

    public static final class FoodEntry implements BaseColumns {


        FoodEntry fe = new FoodEntry();

        public static final String TABLE_NAME = "food_entry";

        public static final String TYPE = "item_type";

        public static final String ITEM = "item_name";

        public static final String BRAND = "brand_name";

        public static final String UNIT = "upc";

        public static final String CALORIES = "nf_calories";

        public static final String BREAKFAST = "breakfast";

        public static final String LUNCH = "lunch";

        public static final String DINNER="dinner";

    }
}
