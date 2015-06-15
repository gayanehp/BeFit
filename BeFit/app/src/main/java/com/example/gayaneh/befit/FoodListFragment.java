package com.example.gayaneh.befit;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Gayaneh on 6/11/2015.
 */
public class FoodListFragment extends Fragment implements AdapterView.OnItemClickListener{

    ArrayAdapter<String> foodAdapter;


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
       String foodSelected = parent.getAdapter().getItem(position).toString();
       String[] foodSpecs= foodSelected.split(";");
        FoodFragment foodFragment= new FoodFragment();
     //   MainActivity mainActivity= (MainActivity) getActivity();
        Bundle bundle = new Bundle();
        bundle.putSerializable("foodSpecs", foodSpecs);
        foodFragment.setArguments(bundle);
        ft.replace(R.id.container, foodFragment);
        ft.commit();



    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_list, container, false);

        Bundle bundle = new Bundle();
        bundle = getArguments();
        ArrayList<String> rows = new ArrayList<String>();
        if (bundle!=null) {
            rows = (ArrayList<String>) bundle.getSerializable("rows");
        }
        ListView listView;
        listView =(ListView)view.findViewById(R.id.listView);
        foodAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.row,R.id.food_text,rows);
        listView.setAdapter(foodAdapter);
        listView.setOnItemClickListener(this);

        return view;


    }
}
