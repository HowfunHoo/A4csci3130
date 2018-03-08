package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


/**
 * Activity that creates new data and stores it into Firebase database
 * @author  Haofan Hou
 * @version 1.0
 * @since   2018-03-06
 */

public class CreateContactAcitivity extends Activity {

    private Button submitButton;
    private EditText etName, etBusiness, etAddress, etProvince;
    private MyApplicationData appState;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        etName = (EditText) findViewById(R.id.name);
        etBusiness = (EditText) findViewById(R.id.business);
        etAddress = (EditText) findViewById(R.id.address);
        etProvince = (EditText) findViewById(R.id.province);

    }

    /**
     * This is a clicklisener of the button 'submitButton'
     * This method create the data in the Firebase.
     * @param v submitButton
     */
    public void submitInfoButton(View v) {

//        String bno = appState.firebaseReference.push().getKey();

        //generate a 9-digit random number as the business number
        String bno = Long.toString(generateID(9));
        System.out.println(bno);
        String name = etName.getText().toString();
        String business = etBusiness.getText().toString();
        String address = etAddress.getText().toString();
        String province = etProvince.getText().toString();

        Business b  = new Business(bno, name, business, address, province);

        appState.firebaseReference.child(bno).setValue(b);

        Toast.makeText(appState, "Created Successfully!",Toast.LENGTH_SHORT).show();


        finish();

    }

    /**
     * This method returns a random number depends on the length.
     * This method is used to generate the business number of each business
     * @param length the length of the required random number
     */
    public static long generateID(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }


}
