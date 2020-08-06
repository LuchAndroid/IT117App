package com.example.it117app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Randomizer extends AppCompatActivity {

    EditText txtName, txtAmount, txtBet1, txtBet2, txtBet3, txtBet4, txtBet5, txtBet6, txtRandom1, txtRandom2, txtRandom3, txtRandom4, txtRandom5, txtRandom6;
    Button btnPlaceBet, btnShow, btnStart;
    TextView lblResult;

    Random rnd = new Random();
    InputFilterMinMaxInteger numFilter;
    final Handler myHandler = new Handler();

    long startTime;
    int ctr = 0;
    final double jackpot = 50000000;
    Integer num;

    //Arrays
    int[] lottoBet = new int[6], lottoResult = new int[6];
    EditText[] arrRandoms, arrBets;

    // ArrayLists
    ArrayList<Integer> arrLottoBalls;
    ArrayList<Integer> arrCount;
    ArrayList<String> arrPlayers;
    ArrayList<String> arrMessages;
    ArrayList<int[]> arrLottoBets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);

        // Initialize ArrayList and Arrays
        arrPlayers = new ArrayList<>();
        arrCount = new ArrayList<>();
        arrMessages = new ArrayList<>();
        arrLottoBets = new ArrayList<>();
        arrLottoBalls = new ArrayList<>();
        generateLottoNumbers();

        // Initialize Filter
        numFilter = new InputFilterMinMaxInteger(1,58);

        // Initialize
        txtName = findViewById(R.id.txtName);
        txtAmount = findViewById(R.id.txtAmount);
        txtBet1 = findViewById(R.id.txtBet1);
        txtBet2 = findViewById(R.id.txtBet2);
        txtBet3 = findViewById(R.id.txtBet3);
        txtBet4 = findViewById(R.id.txtBet4);
        txtBet5 = findViewById(R.id.txtBet5);
        txtBet6 = findViewById(R.id.txtBet6);
        txtRandom1 = findViewById(R.id.txtRandom1);
        txtRandom2 = findViewById(R.id.txtRandom2);
        txtRandom3 = findViewById(R.id.txtRandom3);
        txtRandom4 = findViewById(R.id.txtRandom4);
        txtRandom5 = findViewById(R.id.txtRandom5);
        txtRandom6 = findViewById(R.id.txtRandom6);
        btnPlaceBet = findViewById(R.id.btnPlaceBet);
        btnStart = findViewById(R.id.btnStart);
        btnShow = findViewById(R.id.btnShow);
        lblResult = findViewById(R.id.lblResult);
        arrRandoms = new EditText[] {txtRandom1, txtRandom2, txtRandom3, txtRandom4, txtRandom5, txtRandom6};
        arrBets = new EditText[] {txtBet1, txtBet2, txtBet3, txtBet4, txtBet5, txtBet6};
        // Set Filters
        for (int i = 0; i < arrBets.length; i++) {
            arrBets[i].setFilters(new InputFilter[]{ numFilter });
            arrBets[i].setOnFocusChangeListener((v, hasFocus) -> {
                EditText et = (EditText) v;
                String input = et.getText().toString();
                if (!input.isEmpty() && !isUnique(Integer.parseInt(input))) {
                    et.setText("");
                }
                getLottoBets();
            });
        }

        // Set Action Listener to Buttons
        btnPlaceBet.setOnClickListener(v -> {
            for (int i = 0; i < arrBets.length; i++) {
                if(arrBets[i].hasFocus()){
                    arrBets[i].clearFocus();
                }
            }
            if(isUnique(0)){
                // Add LottoBets to ArrayList
                arrLottoBets.add(lottoBet.clone());
                // Add Player to ArrayList
                arrPlayers.add(txtName.getText().toString());
                // Other Effects
                txtName.setText("");
                for(EditText et: arrBets) et.setText("");
            }else{
                MsgBox("One or more lotto numbers is/are 0. Choose from 1-58 only.");
            }
        });

        btnStart.setOnClickListener(v -> {
            // Clear first the lotto result
            for(EditText et: arrRandoms){
                et.setText("");
            }
            startTime = System.currentTimeMillis();
            btnStart.setEnabled(false);
            setRandomInteger(arrRandoms[ctr]);
        });

        btnShow.setOnClickListener(v -> {
            String message = "";
            for(int i = 0; i < arrPlayers.size(); i++){
                message += arrPlayers.get(i) +" - "+Arrays.toString(arrLottoBets.get(i))+"\n";
            }
            DisplayDialog.openDialog("List of Players", message, getSupportFragmentManager(), "");
        });
    }

    private void getLottoBets(){
        lottoBet[0] = tryParse(txtBet1);
        lottoBet[1] = tryParse(txtBet2);
        lottoBet[2] = tryParse(txtBet3);
        lottoBet[3] = tryParse(txtBet4);
        lottoBet[4] = tryParse(txtBet5);
        lottoBet[5] = tryParse(txtBet6);
    }

    private void setRandomInteger(EditText et) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (System.currentTimeMillis() - startTime < 1000) {
                    myHandler.postDelayed(this, 100);
                    num = arrLottoBalls.get(randomNumber(0,arrLottoBalls.size()-1));
                    et.setText(num+"");
                }else{
                    myHandler.removeCallbacks(this);
                    arrLottoBalls.remove(num);
                    ctr++;
                    if(!btnStart.isEnabled()){
                        displayResult();
                    }
                }
            }
        };
        myHandler.post(runnable);
    }

    private int randomNumber(int min, int max){
        return rnd.nextInt(max + 1 - min) + min;
    }

    private void displayResult(){
        btnStart.setEnabled(true);
        if(ctr < arrRandoms.length){
            btnStart.performClick();
        }else{
            // Get Results
            lottoResult[0] = Integer.parseInt(txtRandom1.getText().toString());
            lottoResult[1] = Integer.parseInt(txtRandom2.getText().toString());
            lottoResult[2] = Integer.parseInt(txtRandom3.getText().toString());
            lottoResult[3] = Integer.parseInt(txtRandom4.getText().toString());
            lottoResult[4] = Integer.parseInt(txtRandom5.getText().toString());
            lottoResult[5] = Integer.parseInt(txtRandom6.getText().toString());
            // Check each player if they won
            for(int i=0; i < arrPlayers.size(); i++){
                int count = 0;
                String temp = "";
                for (int j = 0; j < arrLottoBets.get(0).length; j++){
                    for(int k = 0; k < lottoResult.length; k++){
                        if(arrLottoBets.get(i)[j] == lottoResult[k]){
                            count++;
                            arrCount.add(count);
                            temp+=arrLottoBets.get(i)[j]+" ";
                        }
                    }
                }
                arrMessages.add(arrPlayers.get(i)+" - "+count+" matches "+(temp.isEmpty()?"":"("+temp.trim()+")"));
            }
            getPrizes(6, 100);
            getPrizes(5, 50);
            getPrizes(4, 20);
            getPrizes(3, 0);
            DisplayDialog.openDialog("Lotto Result", createAnnouncement(), getSupportFragmentManager(), "");

            // Reset Everything After Lotto Draw
            ctr = 0;
            generateLottoNumbers();
        }
    }

    private void getPrizes(int numMatches, double percent){
        // Check for Jackpot
        int ctr = 0;
        double prize = 0;
        String strIndexes="";
        for (int i = 0; i < arrCount.size(); i++) {
            if(arrCount.get(i) == numMatches){
                ctr++;
                strIndexes += i+" ";
            }
        }
        if(ctr != 0){
            if(percent != 0){
                prize = jackpot * (percent/100) / ctr;
            }else{
                prize = 5000;
            }
            for(String str: strIndexes.split(" ")){
                int i = Integer.parseInt(str);
                String temp = arrMessages.get(i);
                arrMessages.set(i, String.format("%s [%,3.2f]", temp,prize));
            }
        }
    }

    private void generateLottoNumbers(){
        for (int i = 1; i <= 58; i++){
            arrLottoBalls.add(i);
        }
    }

    private String createAnnouncement(){
        String message = "";
        for(String str: arrMessages){
            message+=str+"\n";
        }
        arrCount.clear();
        arrMessages.clear();
        return message;
    }

    private void MsgBox(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private boolean isUnique(int num){
        for (int i = 0; i < lottoBet.length; i++) {
            if (lottoBet[i] == num) return false;
        }
        return true;
    }

    private int tryParse(EditText et){
        try {
            return Integer.parseInt(et.getText().toString());
        }catch (Exception e){
            return 0;
        }
    }
}