package com.jhonfre.proyectofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button loggin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loggin = (Button)findViewById(R.id.logginButton);
        loggin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.facebook);
            }
        });
    }
}
