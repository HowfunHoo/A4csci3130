package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class DetailViewActivity extends Activity {

    private EditText etName, etBusiness, etAddress, etProvince;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        appState = ((MyApplicationData) getApplicationContext());

        receivedBusinessInfo = (Business) getIntent().getSerializableExtra("Business");

        etName = (EditText) findViewById(R.id.name);
        etBusiness = (EditText) findViewById(R.id.business);
        etAddress = (EditText) findViewById(R.id.address);
        etProvince = (EditText) findViewById(R.id.province);

        if(receivedBusinessInfo != null){
            etName.setText(receivedBusinessInfo.name);
            etBusiness.setText(receivedBusinessInfo.business);
            etAddress.setText(receivedBusinessInfo.address);
            etProvince.setText(receivedBusinessInfo.province);
        }
    }

    /**
     * This is a clicklisener of the button 'updateButton'
     * This method updates the data in the Firebase.
     * @param v updateButton
     */
    public void updateBusiness(View v){
        String bno = receivedBusinessInfo.bno;
        String name = etName.getText().toString();
        String business = etBusiness.getText().toString();
        String address = etAddress.getText().toString();
        String province = etProvince.getText().toString();

        Business b = new Business(bno, name, business, address, province);
        Map<String, Object> businessValues = b.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(bno, businessValues);

        appState.firebaseReference.updateChildren(childUpdates);

        Toast.makeText(appState, "Updated Successfully!",Toast.LENGTH_SHORT).show();

        finish();
    }

    /**
     * This is a clicklisener of the button 'deleteButton'
     * This method deletes the data from Firebase.
     * @param v deleteButton
     */
    public void eraseBusiness(View v)
    {
        String bno = receivedBusinessInfo.bno;
        appState.firebaseReference.child(bno).removeValue();

        Toast.makeText(appState, "Deleted Successfully!",Toast.LENGTH_SHORT).show();

        finish();
    }
}
