package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String bno = appState.firebaseReference.push().getKey();
        String name = etName.getText().toString();
        String business = etBusiness.getText().toString();
        String address = etAddress.getText().toString();
        String province = etProvince.getText().toString();

        Business b  = new Business(bno, name, business, address, province);

        appState.firebaseReference.child(bno).setValue(b);

        Toast.makeText(appState, "Created Successfully!",Toast.LENGTH_SHORT).show();


        finish();

    }
}
