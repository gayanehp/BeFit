package com.example.gayaneh.befit;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;


@SuppressWarnings("deprecation")
public class MainActivity extends Activity implements ActionBar.TabListener {

    Fragment profile,nutrition,exercise;

    ActionBar.Tab firstTab,secondTab,thirdTab;
    Button search;


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
        getMenuInflater().inflate(R.menu.menu_search, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



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

//    public void startLoadTask(Context c){
//        if (isOnline()) {
//            LoadPhotos task = new LoadPhotos();
//            task.execute();
//        } else {
//            Toast.makeText(c, "Not online", Toast.LENGTH_LONG).show();
//        }
//    }

    public boolean isOnline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

/** Source internet:
 * http://stackoverflow.com/questions/18977187/how-to-hide-soft-keyboard-when-activity-starts
 */

    /**
     * Hides the soft keyboard
     */
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * Shows the soft keyboard
     */
    public void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }



}
