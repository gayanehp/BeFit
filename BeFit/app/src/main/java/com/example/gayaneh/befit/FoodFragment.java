package com.example.gayaneh.befit;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.view.KeyEvent.ACTION_DOWN;


public class FoodFragment extends Fragment {

    public FoodFragment() {
    }

    Button search,cancel,save;
    EditText text;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        String[] measures = {"cup", "plate", "bottle", "tbsp", "bar", "piece", "serving", "roll", "grams", "oz", "portion"};


        ArrayAdapter<String> adapterMeasure = new ArrayAdapter<String>
                (this.getActivity(),android.R.layout.simple_spinner_item,measures);
        adapterMeasure.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);


        return view;
    }


    public boolean onSearchRequested (){
        text= (EditText) this.getActivity().findViewById(R.id.editText);

        Toast.makeText(text.getContext(), text.getText(), Toast.LENGTH_SHORT).show();

        return true;
    }


}
