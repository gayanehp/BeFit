package com.example.gayaneh.befit;

import android.provider.BaseColumns;

/**
 * Created by Gayaneh on 5/31/2015.
 */
public class Contract {

    public static final String DATABASE_NAME="BeFit.db";

    /**
     * "item_id","item_name","brand_name","nf_calories",'nf_serving_size_qty","nf_serving_size_unit"
     */

    public static final class FoodEntry implements BaseColumns {


        FoodEntry fe = new FoodEntry();

        public static final String TABLE_NAME = "food_entry";

       // public static final String ITEM_ID = "item_id";

        public static final String ITEM = "item_name";

        public static final String BRAND = "brand_name";

        public static final String UNIT = "nf_serving_size_unit";

        public static final String QUANTITY = "nf_serving_size_qty";

        public static final String CALORIES = "nf_calories";

        public static final String BREAKFAST = "breakfast";

        public static final String LUNCH = "lunch";

        public static final String DINNER="dinner";

    }

    public static final class CalendarEntry implements BaseColumns{
        CalendarEntry ce = new CalendarEntry();

        public static final String TABLE_NAME = "calendar_entry";

        public static final String CDATE = "calendar_date";

        public static final String BREAKFAST = "breakfast";

        public static final String BCALORIES = "breakfast_calories";

        public static final String LUNCH = "lunch";

        public static final String LCALORIES = "lunch_calories";

        public static final String DINNER = "dinner";

        public static final String DCALORIES = "dinner_calories";



    }
}
