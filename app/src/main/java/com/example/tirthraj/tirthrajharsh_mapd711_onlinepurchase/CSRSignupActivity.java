package com.example.tirthraj.tirthrajharsh_mapd711_onlinepurchase;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CSRSignupActivity extends AppCompatActivity {

    private static final String tables[]={"CSR"};
    //
    private static final String tableCreatorString[] =
            {"CREATE TABLE CSR (employeeId INTEGER PRIMARY KEY AUTOINCREMENT , userName TEXT, password TEXT, firstname TEXT, lastname, TEXT);"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csrsignup);

        final DatabaseManager db = new DatabaseManager(this);
        //db.createDatabase(getApplicationContext());
        db.dbInitialize( tables,tableCreatorString);

        final String fields[] = {"employeeId","userName","password","firstname","lastname"};
        final String record[] = new String[5];
        // Handle Save button
        final Button btnSignup = (Button) findViewById(R.id.Register);

        final EditText uname = (EditText) findViewById(R.id.editTextUsername);
        final EditText pwd = (EditText) findViewById(R.id.editTextPassword);
        final EditText fname = (EditText) findViewById(R.id.editTextFirstname);
        final EditText lname = (EditText) findViewById(R.id.editTextLastname);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                record[1] = uname.getText().toString();
                record[2] = pwd.getText().toString();
                record[3] = fname.getText().toString();
                record[4] = lname.getText().toString();

                Log.d("CSR: ", record[1]);
                //populate the row with some values
                ContentValues values = new ContentValues();
                //for (int i=1;i<record.length;i++)
                //values.put(fields[i],record[i]);
                //add the row to the database
                db.addRecord(values, "CSR", fields,record);
            }
        });
    }
}
