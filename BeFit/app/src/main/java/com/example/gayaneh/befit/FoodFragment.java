package com.example.gayaneh.befit;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class FoodFragment extends Fragment {

    public FoodFragment() {
    }

    Button food,cancel,save;
    Spinner spinnerSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        String[] measures = {"cup", "plate", "bottle", "tbsp", "bar", "piece", "serving", "roll", "grams", "roll", "oz", "portion"};

        spinnerSize = (Spinner) view.findViewById(R.id.spinnerMeasures);
        ArrayAdapter<String> adapterMeasure = new ArrayAdapter<String>
                (this.getActivity(),android.R.layout.simple_spinner_item,measures);
        adapterMeasure.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinnerSize.setAdapter(adapterMeasure);
        return view;
    }
}
