package com.example.user.intentim_ex;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity implements OnClickListener {
    Button sendBTN2, callBTN2, smsBTN2;
    TextView outputTV2;
    EditText inputET2;
    static final int REQUEST_CODE= 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        sendBTN2 = (Button)findViewById(R.id.sendBTN2);
        smsBTN2 = (Button)findViewById(R.id.smsBTN2);
        callBTN2 = (Button) findViewById(R.id.callBTN2);
        outputTV2 = (TextView) findViewById(R.id.outputTV2);
        inputET2 = (EditText) findViewById(R.id.inputET2);
        outputTV2.setText(getIntent().getStringExtra("message"));

        sendBTN2.setOnClickListener(this);
        callBTN2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(SubActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:0148165055")));
            }
        });
        smsBTN2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "12346556";  // The number on which you want to send SMS
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent data = new Intent();
        data.putExtra("message",inputET2.getText().toString());
        setResult(RESULT_OK,data);
        finish();
    }



}

