package com.example.it117app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtHello;
    Button btnHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtHello=findViewById(R.id.textView);
        btnHello=findViewById(R.id.button);

        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = txtHello.getText()+"";
                Toast.makeText(getApplicationContext(), "You typed : "+str, Toast.LENGTH_LONG).show();
            }
        });
    }
}