package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int temp = 0;
    String lastButtonPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button numberOneButton = findViewById(R.id.numberOneButton);
        numberOneButton.setOnClickListener(this);

        Button numberTwoButton = findViewById(R.id.numberTwoButton);
        numberTwoButton.setOnClickListener(this);

        Button numberThreeButton = findViewById(R.id.numberThreeButton);
        numberThreeButton.setOnClickListener(this);

        Button numberFourButton = findViewById(R.id.numberFourButton);
        numberFourButton.setOnClickListener(this);

        Button numberFiveButton = findViewById(R.id.numberFiveButton);
        numberFiveButton.setOnClickListener(this);

        Button numberSixButton = findViewById(R.id.numberSixButton);
        numberSixButton.setOnClickListener(this);

        Button numberSevenButton = findViewById(R.id.numberSevenButton);
        numberSevenButton.setOnClickListener(this);

        Button numberEightButton = findViewById(R.id.numberEightButton);
        numberEightButton.setOnClickListener(this);

        Button numberNineButton = findViewById(R.id.numberNineButton);
        numberNineButton.setOnClickListener(this);

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        Button plusButton = findViewById(R.id.plusButton);
        plusButton.setOnClickListener(this);

        Button minusButton = findViewById(R.id.minusButton);
        minusButton.setOnClickListener(this);

        Button multiplyButton = findViewById(R.id.multiplyButton);
        multiplyButton.setOnClickListener(this);

        Button divisionButton = findViewById(R.id.divisionButton);
        divisionButton.setOnClickListener(this);

        Button equalsButton = findViewById(R.id.equalsButton);
        equalsButton.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        TextView resultTextView = findViewById(R.id.resultTextView);
        // TODO сделвть нормально через eval
        switch (v.getId()) {
            case R.id.numberOneButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("1").toString());
                break;
            case R.id.numberTwoButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("2").toString());
                break;
            case R.id.numberThreeButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("3").toString());
                break;
            case R.id.numberFourButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("4").toString());
                break;
            case R.id.numberFiveButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("5").toString());
                break;
            case R.id.numberSixButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("6").toString());
                break;
            case R.id.numberSevenButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("7").toString());
                break;
            case R.id.numberEightButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("8").toString());
                break;
            case R.id.numberNineButton:
                resultTextView.setText(new StringBuilder().append(resultTextView.getText()).append("9").toString());
                break;
            case R.id.resetButton:
                resultTextView.setText("");
                temp = 0;
                lastButtonPressed = "";
                break;
            case R.id.plusButton:
                temp += Integer.parseInt(resultTextView.getText().toString());
                resultTextView.setText("");
                lastButtonPressed = "plus";
                break;
            case R.id.minusButton:
                temp += Integer.parseInt(resultTextView.getText().toString());
                resultTextView.setText("");
                lastButtonPressed = "minus";
                break;
            case R.id.multiplyButton:
                if (temp == 0) {
                    temp = Integer.parseInt(resultTextView.getText().toString());
                } else {
                    temp *= Integer.parseInt(resultTextView.getText().toString());
                }
                resultTextView.setText("");
                lastButtonPressed = "multiply";
                break;
            case R.id.divisionButton:
                temp = Integer.parseInt(resultTextView.getText().toString());
                resultTextView.setText("");
                lastButtonPressed = "divide";
                break;
            case R.id.equalsButton:
                switch (lastButtonPressed) {
                    case "plus":
                        resultTextView.setText(String.valueOf(Integer.parseInt(resultTextView.getText().toString()) + temp));
                        break;
                    case "minus":
                        resultTextView.setText(String.valueOf(temp - Integer.parseInt(resultTextView.getText().toString())));
                        break;
                    case "multiply":
                        resultTextView.setText(String.valueOf(Integer.parseInt(resultTextView.getText().toString()) * temp));
                        break;
                    case "divide":
                        resultTextView.setText(String.valueOf(temp / Integer.parseInt(resultTextView.getText().toString())));
                        break;
                }

            default:
                //resultTextView.setText(R.string.errorMessage);
                break;
        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            // | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}