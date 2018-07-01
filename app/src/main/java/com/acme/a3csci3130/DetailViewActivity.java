package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * Class that updates and erases existing businesses
 */
public class DetailViewActivity extends Activity {

    private EditText numberField, nameField, addressField;
    private Spinner businessField, provinceField;
    Business receivedBusinessInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedBusinessInfo = (Business)getIntent().getSerializableExtra("Business");
        appState = ((MyApplicationData) getApplicationContext());

        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        businessField = (Spinner) findViewById(R.id.business);
        provinceField = (Spinner) findViewById(R.id.province);

        if(receivedBusinessInfo != null){
            numberField.setText(receivedBusinessInfo.number);
            nameField.setText(receivedBusinessInfo.name);
            addressField.setText(receivedBusinessInfo.address);

            //fill and set spinners
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.businesses, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            businessField.setAdapter(adapter);

            int pos = adapter.getPosition(receivedBusinessInfo.business);
            businessField.getItemAtPosition(pos);

            adapter = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            provinceField.setAdapter(adapter);

            pos = adapter.getPosition(receivedBusinessInfo.province);
            provinceField.getItemAtPosition(pos);
        }
    }

    public void updateBusiness(View v){
        String UID = receivedBusinessInfo.UID;
        String number = numberField.getText().toString();
        String name = nameField.getText().toString();
        String address = addressField.getText().toString();
        String primaryBusiness = businessField.getSelectedItem().toString();
        String province = provinceField.getSelectedItem().toString();
        Business business = new Business(UID, number, name, primaryBusiness, address, province);
        appState.firebaseReference.child(UID).setValue(business);
    }

    public void eraseBusiness(View v)
    {
        String UID = receivedBusinessInfo.UID;
        appState.firebaseReference.child(UID).setValue(null);
    }
}
