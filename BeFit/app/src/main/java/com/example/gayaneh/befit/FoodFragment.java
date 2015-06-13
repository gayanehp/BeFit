package com.example.gayaneh.befit;

import android.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class FoodFragment extends Fragment {



    Button search,cancel,add;
    EditText foodText;
    CheckBox ckBreakfast,ckLunch,ckDinner;
    private ProgressBar progress;
    boolean isBreakfast,isLunch,isDinner = false;
    Bundle args,nutritionArgs = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food, container, false);
        args = getArguments();
        if (args != null)
        {
            nutritionArgs = new Bundle();
            String[] foodSpecs= (String[]) args.getSerializable("foodSpecs");
            String foodName=foodSpecs[0];
            String foodBrand = foodSpecs[1];
            String foodServing = foodSpecs[3];
            String foodSize = foodSpecs[2];
            String calories = foodSpecs[4];
            nutritionArgs.putSerializable("foodName",foodName);
            nutritionArgs.putSerializable("foodBrand",foodBrand);
            nutritionArgs.putSerializable("foodServing",foodServing);
            nutritionArgs.putSerializable("foodSize",foodSize);
            nutritionArgs.putSerializable("calories",calories);
            EditText foodText = (EditText) view.findViewById(R.id.editText2);
            foodText.setText(foodName);
            EditText brandText = (EditText) view.findViewById(R.id.editText4);
            brandText.setText(foodBrand);
            EditText servingText = (EditText) view.findViewById(R.id.editText5);
            servingText.setText(foodSize+" "+foodServing);
            EditText caloriesText = (EditText) view.findViewById(R.id.editText3);
            caloriesText.setText(calories);
        }
         return view;
    }





    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progress = (ProgressBar) view.findViewById(R.id.progressBar);
        foodText = (EditText) view.findViewById(R.id.editText);
        search = (Button) view.findViewById(R.id.search_button);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // start search task
                Log.i("TAG", "search clicked");
                Context c = getActivity().getBaseContext();
                startLoadTask(c);


            }
        });

        ckBreakfast = (CheckBox) view.findViewById(R.id.checkBoxBreakfast);
        ckBreakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBreakfast=true;
            }
        });

        ckLunch = (CheckBox) view.findViewById(R.id.checkBoxLunch);
        ckLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLunch=true;
            }
        });

        ckDinner = (CheckBox) view.findViewById(R.id.checkBoxDinner);
        ckDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDinner=true;
            }
        });




        ckLunch = (CheckBox) view.findViewById(R.id.checkBoxLunch);
        ckDinner = (CheckBox) view.findViewById(R.id.checkBoxDinner);
        add = (Button) view.findViewById(R.id.buttonAddFood);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = getActivity().getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                MainActivity mainActivity = (MainActivity) getActivity();
                Fragment nutritionFragment = mainActivity.getFragmentManager().findFragmentById(R.layout.fragment_nutrition);


                nutritionArgs.putSerializable("isBreakfast", isBreakfast);
                nutritionArgs.putSerializable("isLunch", isLunch);
                nutritionArgs.putSerializable("isDinner",isDinner);
                nutritionFragment.setArguments(nutritionArgs);
                ft.replace(R.id.container, nutritionFragment);
                ft.commit();

            }
        });



    }

    public void startLoadTask(Context c){
        if (isOnline()) {

            FindFood task = new FindFood();
            task.execute();
        } else {
            Toast.makeText(c, "Not online", Toast.LENGTH_LONG).show();
        }
    }


    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    public void showList( ArrayList<BeFitFood> foods) {
        FragmentManager fm = getActivity().getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ArrayList<String>  rows = new ArrayList<String>();
        for( BeFitFood f:foods ){
            String item = f.getItem() + ";" + f.getBrand()+ ";" + f.getServing_qty() + ";" +
                          f.getServing_unit()+ ";"+ f.getCalories();
            rows.add(item);
        }
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.hideSoftKeyboard();
        FoodListFragment foodListFragment = new FoodListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("rows", rows);
        foodListFragment.setArguments(bundle);
        ft.replace(R.id.container, foodListFragment);
        ft.commit();
    }


    private class FindFood extends AsyncTask<String, Long, Long> {
        HttpURLConnection connection = null;
        ArrayList<BeFitFood> foods;

        @Override
        protected void onPreExecute() {
            progress.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Long... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Long doInBackground(String... strings) {
            //https://api.nutritionix.com/v1_1/search/cheddar%20cheese?
            // fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories%2Cnf_total_fat&appId=[YOURID]&appKey=[YOURKEY]
            String foodToSearch;
            foodToSearch= foodText.getText().toString().replaceAll(" ","%20"); // replace whitespace with the code
            String dataString = "https://api.nutritionix.com/v1_1/search/" +
                                 foodToSearch +
                    "?fields=item_name%2Citem_id%2Cbrand_name%2Cnf_calories&appId=5c9c19fb&appKey=1a3226d6c6dc1e0cdf5fb42d72aa481e";

           Log.i("TAG", dataString);
            try {
                URL dataUrl = new URL(dataString);
                connection = (HttpURLConnection) dataUrl.openConnection();
                connection.connect();
                int status = connection.getResponseCode();
                Log.d("TAG", "status " + status);
                //if it is successful
                if (status == 200) {
                    InputStream is = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String responseString;
                    StringBuilder sb = new StringBuilder();

                    while ((responseString = reader.readLine()) != null) {
                        sb = sb.append(responseString);
                    }
                    String foodData = sb.toString();

                    foods = BeFitFood.makeFoodList(foodData);
                    Log.i("TAG", foodData);

                    return 0l;
                } else {
                    return 1l;
                }
            } catch (MalformedURLException e) {
                Log.i("TAG", "Malformed Url");
                e.printStackTrace();
                return 1l;
            } catch (IOException e) {
                e.printStackTrace();
                return 1l;
            } catch (JSONException e) {
                e.printStackTrace();
                return 1l;
            } finally {
                if (connection != null)
                    connection.disconnect();
            }

        }


        @Override
        protected void onPostExecute(Long result) {
            if (result != 1l) {
              showList(foods);

            } else {
                Toast.makeText(getActivity().getApplicationContext(), "AsyncTask didn't complete", Toast.LENGTH_LONG).show();
            }
            progress.setVisibility(View.GONE);
        }
    }



}
