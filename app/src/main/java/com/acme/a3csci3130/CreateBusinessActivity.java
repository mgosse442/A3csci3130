package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Class that creates new businesses and stores them in the database
 */
public class CreateBusinessActivity extends Activity {

    private Button submitButton;
    private EditText numberField, nameField, addressField;
    private Spinner businessField, provinceField;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_acitivity);
        //Get the app wide shared variables
        appState = ((MyApplicationData) getApplicationContext());

        submitButton = (Button) findViewById(R.id.submitButton);
        numberField = (EditText) findViewById(R.id.number);
        nameField = (EditText) findViewById(R.id.name);
        addressField = (EditText) findViewById(R.id.address);
        businessField = (Spinner) findViewById(R.id.business);
        provinceField = (Spinner) findViewById(R.id.province);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.businesses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        businessField.setAdapter(adapter);

        adapter = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceField.setAdapter(adapter);
    }

    public void submitInfoButton(View v) {
        //each entry needs a unique ID
        String UID = appState.firebaseReference.push().getKey();
        String number = numberField.getText().toString();
        String name = nameField.getText().toString();
        String addr = addressField.getText().toString();
        String busi = businessField.getSelectedItem().toString();
        String prov = provinceField.getSelectedItem().toString();
        Business business = new Business(UID, number, name, busi, addr, prov);

        appState.firebaseReference.child(UID).setValue(business);

        finish();

    }
}
