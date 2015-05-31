package com.example.gayaneh.befit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;



@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements ActionBar.TabListener {

    Fragment profile,nutrition,exercise;
    ActionBar.Tab firstTab,secondTab,thirdTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        actionBar.addTab(
                actionBar.newTab()
                        .setText("Profile")
                        .setTabListener(this));

        actionBar.addTab(
                actionBar.newTab()
                        .setText("Activities")
                        .setTabListener(this));

        actionBar.addTab(
                actionBar.newTab()
                        .setText("Nutrition")
                        .setTabListener(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragTrans) {

        profile = new ProfileFragment();
        nutrition = new NutritionFragment();
        exercise = new ExerciseFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        int i = tab.getPosition();
        switch (i){
            case 0:{
                ft.replace(R.id.container, profile);
                // firstTab.setRetainInstance(true);
                ft.commit();
                break;
            }

            case 1:{
                ft.replace(R.id.container, exercise);
                //  secondTab.setRetainInstance(true);
                ft.commit();
                break;
            }
            case 2:{
                ft.replace(R.id.container, nutrition);
                //  secondTab.setRetainInstance(true);
                ft.commit();
                break;
            }
            default: {
                ft.replace(R.id.container, profile);
                ft.commit();
            }
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_main, container, false);
            return rootView;
        }
    }
}
