package com.example.it117app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class BudgetBuddy extends AppCompatActivity {
    CheckBox chkBeauty, chkIntelligent, chkSmart, chkRich, chkPoor;
    String hold = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_buddy);

        // Create connection to the layout components
        chkBeauty = findViewById(R.id.chkBeautiful);
        chkIntelligent = findViewById(R.id.chkIntelligent);
        chkSmart = findViewById(R.id.chkSmart);
        chkRich = findViewById(R.id.chkRich);
        chkPoor = findViewById(R.id.chkPoor);
    }

    public void onClickCheck(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.chkBeautiful:
                if (checked) {
                    hold += chkBeauty.getText().toString() + "\n";
                }
                break;
            case R.id.chkIntelligent:
                if (checked) {
                    hold += chkBeauty.getText().toString() + "\n";
                }
                break;
            case R.id.chkSmart:
                if (checked) {
                    hold += chkBeauty.getText().toString() + "\n";
                }
                break;
            case R.id.chkRich:
                if (checked) {
                    hold += chkBeauty.getText().toString() + "\n";
                }
                break;
            case R.id.chkPoor:
                if (checked) {
                    hold += chkBeauty.getText().toString() + "\n";
                }
                break;
        }
    }
}