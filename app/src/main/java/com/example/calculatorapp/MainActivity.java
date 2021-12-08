package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button numberOneButton = findViewById(R.id.numberOneButton);
        numberOneButton.setOnClickListener((this));

        Button numberTwoButton = findViewById(R.id.numberTwoButton);
        numberTwoButton.setOnClickListener((this));

        Button numberThreeButton = findViewById(R.id.numberThreeButton);
        numberThreeButton.setOnClickListener((this));

        Button numberFourButton = findViewById(R.id.numberFourButton);
        numberFourButton.setOnClickListener((this));

        Button numberFiveButton = findViewById(R.id.numberFiveButton);
        numberFiveButton.setOnClickListener((this));

        Button numberSixButton = findViewById(R.id.numberSixButton);
        numberSixButton.setOnClickListener((this));

        Button numberSevenButton = findViewById(R.id.numberSevenButton);
        numberSevenButton.setOnClickListener((this));

        Button numberEightButton = findViewById(R.id.numberEightButton);
        numberEightButton.setOnClickListener((this));

        Button numberNineButton = findViewById(R.id.numberNineButton);
        numberNineButton.setOnClickListener((this));

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView resultTextView = findViewById(R.id.resultTextView);
        switch (v.getId()) {
            case R.id.numberOneButton:
                resultTextView.setText(resultTextView.getText() + "1");
                break;
            case R.id.numberTwoButton:
                resultTextView.setText(resultTextView.getText() + "2");
                break;
            case R.id.numberThreeButton:
                resultTextView.setText(resultTextView.getText() + "3");
                break;
            case R.id.numberFourButton:
                resultTextView.setText(resultTextView.getText() + "4");
                break;
            case R.id.numberFiveButton:
                resultTextView.setText(resultTextView.getText() + "5");
                break;
            case R.id.numberSixButton:
                resultTextView.setText(resultTextView.getText() + "6");
                break;
            case R.id.numberSevenButton:
                resultTextView.setText(resultTextView.getText() + "7");
                break;
            case R.id.numberEightButton:
                resultTextView.setText(resultTextView.getText() + "8");
                break;
            case R.id.numberNineButton:
                resultTextView.setText(resultTextView.getText() + "9");
                break;
            case R.id.resetButton:
                resultTextView.setText("");
                break;
            default:
                resultTextView.setText("ERROR");
                break;
        }
    }
}