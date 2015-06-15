package com.example.gayaneh.befit;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class NutritionFragment extends Fragment {

    public NutritionFragment() {
    }

    Button food,cancel,save;
    Fragment foodFragment,foodListFragment;
    Spinner breakfastSpinner,lunchSpinner,dinnerSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =   inflater.inflate(R.layout.fragment_nutrition, container, false);



        food = (Button) view.findViewById(R.id.buttonFood);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               foodFragment = new FoodFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, foodFragment);
                // firstTab.setRetainInstance(true);
                ft.commit();
            }
        });
        DatabaseHelper dbHelper= new DatabaseHelper( getActivity().getApplicationContext());
        Cursor cursor =dbHelper.getAllRows();
        getActivity().startManagingCursor(cursor);
        int foodCount = cursor.getCount();
        String foodName;
        int isBreakfast,isLunch,isDinner;
        List<String> breakfastList = new ArrayList<String>(); //= {"Spanish Omelette","Oatmeal","Ham Sandwich"};
        List<String> lunchList = new ArrayList<String>();  //= {"Chicken Salad","Fish and Chips","Tomato Soup"};
        List<String> dinnerList = new  ArrayList<String>(); // = {"Kale Salad","Steak","Sushi Roll"};
        cursor.moveToFirst();
        for (int i= 1; i<=foodCount; i++ ){
            foodName = cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.ITEM))+
                              "-" +cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.BRAND));
            isBreakfast = cursor.getInt((cursor.getColumnIndexOrThrow(Contract.FoodEntry.BREAKFAST)));
            isLunch = cursor.getInt((cursor.getColumnIndexOrThrow(Contract.FoodEntry.LUNCH)));
            isDinner = cursor.getInt((cursor.getColumnIndexOrThrow(Contract.FoodEntry.DINNER)));

            if (isBreakfast == 1 ){
                breakfastList.add(foodName);
            }
            if (isLunch == 1 ){
                lunchList.add(foodName);
            }
            if (isDinner == 1){
                dinnerList.add(foodName);
            }
            cursor.moveToNext();

        }

        //title.setText(titleText);

        breakfastSpinner = (Spinner) view.findViewById(R.id.spinnerBreakfast);
        lunchSpinner = (Spinner) view.findViewById(R.id.spinnerLunch);
        dinnerSpinner = (Spinner) view.findViewById(R.id.spinnerDinner);
        ArrayAdapter<String> adapterBreakfast = new ArrayAdapter<String>
                                    (this.getActivity(),android.R.layout.simple_spinner_item,breakfastList);
        adapterBreakfast.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        breakfastSpinner.setAdapter(adapterBreakfast);
        ArrayAdapter<String> adapterLunch = new ArrayAdapter<>
                (this.getActivity(), android.R.layout.simple_spinner_item, lunchList);
        adapterLunch.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        lunchSpinner.setAdapter(adapterLunch);
        ArrayAdapter<String> adapterDinner = new ArrayAdapter<String>
                (this.getActivity(),android.R.layout.simple_spinner_item,dinnerList);
        adapterDinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        dinnerSpinner.setAdapter(adapterDinner);
        return view;
    }
}
