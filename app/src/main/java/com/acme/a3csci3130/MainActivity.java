package com.acme.a3csci3130;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Main Activity
 * The actvity lists the data stored in the Firebase database
 * and provides an entrance to CreateContactAcitivity
 * @author  Haofan Hou
 * @version 1.0
 * @since   2018-03-06
 */
public class MainActivity extends Activity {


    private ListView businessListView;
    private FirebaseListAdapter<Business> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get the app wide shared variables
        MyApplicationData appData = (MyApplicationData)getApplication();

        //Set-up Firebase
        appData.firebaseDBInstance = FirebaseDatabase.getInstance();
        appData.firebaseReference = appData.firebaseDBInstance.getReference("businesses");

        //Get the reference to the UI contents
        businessListView = (ListView) findViewById(R.id.listView);

        //Set up the List View
       firebaseAdapter = new FirebaseListAdapter<Business>(this, Business.class,
                android.R.layout.simple_list_item_1, appData.firebaseReference) {
            @Override
            protected void populateView(View v, Business model, int position) {
                TextView BusinessName = (TextView)v.findViewById(android.R.id.text1);
                BusinessName.setText(model.name);
            }
        };
        businessListView.setAdapter(firebaseAdapter);
        businessListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Business b = (Business) firebaseAdapter.getItem(position);
                showDetailView(b);
            }
        });
    }

    /**
     * This is a clicklisener of the button 'SubmitButton'
     * Clicking the button will reach CreateContactAcitivity
     * @param v SubmitButton
     */
    public void createBusinessButton(View v)
    {
        Intent intent=new Intent(this, CreateContactAcitivity.class);
        startActivity(intent);
    }

    /**
     * This method leads the user to the DetailViewActivity
     * which contains the detailed information of the selected business
     * @param b the selected business
     */
    private void showDetailView(Business b)
    {
        Intent intent = new Intent(this, DetailViewActivity.class);
        intent.putExtra("Business", b);
        startActivity(intent);
    }



}
