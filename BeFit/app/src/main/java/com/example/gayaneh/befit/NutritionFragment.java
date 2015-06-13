package com.example.gayaneh.befit;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


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

        String[] breakfastList = {"Spanish Omelette","Oatmeal","Ham Sandwich"};
        String[] lunchList = {"Chicken Salad","Fish and Chips","Tomato Soup"};
        String[] dinnerList = {"Kale Salad","Steak","Sushi Roll"};
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
