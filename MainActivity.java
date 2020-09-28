package com.example.gslc2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView clickHere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickHere = findViewById(R.id.registerLink);
        clickHere.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerLink:

                // finish activity agar tidak back ke halaman Main setelah masuk halaman Regist
                MainActivity.this.finish();

                // pindah activity/page
                Intent i = new Intent(this, RegisterActivity.class);
                startActivity(i);
                break;

        }
    }
}