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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class NutritionFragment extends Fragment {

    public NutritionFragment() {
    }

    Button food,cancel,save;
    Fragment foodFragment,foodListFragment;
    Spinner breakfastSpinner,lunchSpinner,dinnerSpinner;
    EditText servingB,servingL,servingD;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =   inflater.inflate(R.layout.fragment_nutrition, container, false);

        breakfastSpinner = (Spinner) view.findViewById(R.id.spinnerBreakfast);
        lunchSpinner = (Spinner) view.findViewById(R.id.spinnerLunch);
        dinnerSpinner = (Spinner) view.findViewById(R.id.spinnerDinner);

          servingB = (EditText) view.findViewById(R.id.editText6);
          servingL = (EditText) view.findViewById(R.id.editText7);
          servingD = (EditText) view.findViewById(R.id.editText8);
          servingB.setText("1");
          servingL.setText("1");
          servingD.setText("1");
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
        List<String> breakfastList = new ArrayList<String>();
        List<String> lunchList = new ArrayList<String>();
        List<String> dinnerList = new  ArrayList<String>();
        cursor.moveToFirst();
        for (int i= 1; i<=foodCount; i++ ){
            foodName = cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.ITEM))+
                              ";" +cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.BRAND))+
                              ";" + cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.CALORIES));
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

        save = (Button) view.findViewById(R.id.save);
        final EditText dateText = (EditText) view.findViewById(R.id.editDate);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                if (dateText.getText().toString().equals("")){
                    //notify user to enter a date
                    Toast.makeText(getActivity().getApplicationContext(), "Date fields is empty. Cannot Save.", Toast.LENGTH_LONG).show();

                }
                else{
                    String breakfast= (String) breakfastSpinner.getSelectedItem();
                    String lunch = (String) lunchSpinner.getSelectedItem();
                    String dinner = (String) dinnerSpinner.getSelectedItem();
                    String[] breakfastSplit = breakfast.split(";");
                    String[] lunchSplit = lunch.split(";");
                    String[] dinnerSplit = dinner.split(";");
                    int servingIntB = 1 ;
                    if (!(servingB.getText().toString().equals(""))){
                        servingIntB = Integer.parseInt( servingB.getText().toString());
                    }
                    int servingIntL = 1 ;
                    if (!(servingL.getText().toString().equals(""))){
                        servingIntL = Integer.parseInt( servingL.getText().toString());
                    }

                    int servingIntD = 1 ;
                    if (!(servingD.getText().toString().equals(""))){
                        servingIntD = Integer.parseInt( servingD.getText().toString());
                    }
                    Integer TotalCalories =
                            Integer.parseInt(breakfastSplit[2])* servingIntB+
                            Integer.parseInt(lunchSplit[2])*servingIntL +
                            Integer.parseInt(dinnerSplit[2])*servingIntD;

                    Toast.makeText(getActivity().getApplicationContext(),
                            "Total calories for the date: " + TotalCalories.toString()+ " will be saved.", Toast.LENGTH_LONG).show();


                }

            }
        });



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
