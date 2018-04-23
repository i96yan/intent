package com.example.user.intentim_ex;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button sendBTN, callBTN, smsBTN;
    TextView outputTV;
    EditText inputET;
    static final int REQUEST_CODE= 17915;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendBTN = (Button)findViewById(R.id.sendBTN2);
        callBTN = (Button) findViewById(R.id.callBTN);
        outputTV = (TextView) findViewById(R.id.outputTV2);
        inputET = (EditText) findViewById(R.id.inputET2);
        smsBTN = (Button)findViewById(R.id.smsBTN);

        sendBTN.setOnClickListener(this);
        callBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0148165055")));
            }
        });
        smsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "12346556";  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);
        intent.putExtra("message", inputET.getText().toString());
        startActivityForResult(intent, REQUEST_CODE);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            outputTV.setText(data.getStringExtra("message"));
        }


    }
}
