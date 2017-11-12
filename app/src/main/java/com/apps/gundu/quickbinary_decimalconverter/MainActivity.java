package com.apps.gundu.quickbinary_decimalconverter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.graphics.Color.RED;


public class MainActivity extends AppCompatActivity {

    //initialize value of all bits to be zero
    String[] digit = {"0", "0", "0", "0", "0", "0", "0"};
    //decimal add up values
    String[] decimalAddups = {"", "", "", "", "", "", ""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void binaryButton(View view) {
        String tag = view.getTag().toString();

        Button bit = determineButton(tag);

        if (Integer.parseInt(tag) < 7) {
            String tagDecimal = Integer.toString(Integer.parseInt(tag) + 10);

            Button bitDecimal = determineButton(tagDecimal);

            if (bit.getText() == "1") {
                bit.setText("0");
                bit.setTextColor(Color.BLACK);
                bitDecimal.setTextColor(Color.BLACK);
            } else {
                bit.setText("1");
                bit.setTextColor(RED);
                bitDecimal.setTextColor(RED);  //Color.parseColor("#07998C")
            }

            binaryGenerator(bit);
            decimalGenerator(bit);
            decimalValueGenerator();
        } else {
            String tagBinary = Integer.toString(Integer.parseInt(tag) - 10); //tag of binary column

            Button bitBinary = determineButton(tagBinary);

            if (bit.getCurrentTextColor() == Color.BLACK) {
                bitBinary.performClick();

            } else {
                bitBinary.performClick();

            }

        }


    }

    public Button determineButton(String tag) {
        String btnID = "bin" + tag;
        int resID = getResources().getIdentifier(btnID, "id", getPackageName());
        Button bit = (Button) findViewById(resID);

        return bit;
    }

    public void binaryGenerator(Button bit) {
        int position = Integer.parseInt(bit.getTag().toString());

        if (bit.getText() == "0") {
            digit[position] = "0";
        } else {
            digit[position] = "1";
        }

        String binary = "";
        for (int counter = 6; counter >= 0; counter--) {
            binary += digit[counter];
        }
        TextView textView = (TextView) findViewById(R.id.binaryNumber);
        textView.setText(binary);
    }

    public void decimalGenerator(Button bit) {
        int position = Integer.parseInt(bit.getTag().toString());

        String decimalValue = Integer.toString((int) Math.pow(2, position));

        String string = "";


        if (bit.getText() == "1") {
            decimalAddups[position] = decimalValue;
        } else {
            decimalAddups[position] = "";
        }

        boolean plusSign = false;

        for (int counter = 6; counter >= 0; counter--) {
            if (decimalAddups[counter] != "") {

                if(plusSign == true && decimalAddups[counter].charAt(0)!='+') {
                    decimalAddups[counter] = "+" + decimalAddups[counter];
                }
                else if(plusSign==false ){
                    if(decimalAddups[counter].charAt(0)=='+') {
                        decimalAddups[counter] = decimalAddups[counter].substring(1);
                    }
                }

                plusSign=true;

            }

        }

        for (int counter = 6; counter >= 0; counter--) {
            string += decimalAddups[counter];
        }

        if (string == "") {
            string = "0";
        }

        TextView textView = (TextView) findViewById(R.id.decimalIntermediate);
        textView.setText(string);
    }

    public void decimalValueGenerator()
    {
        int decimal=0;
        for(int counter=6;counter>=0;counter--)
        {
            Button bit = determineButton(Integer.toString(counter));
            int zeroOrOne = Integer.parseInt(bit.getText().toString());
            decimal += Math.pow(2,counter)*zeroOrOne;
        }

        TextView textView = (TextView)findViewById(R.id.decimalFinal);
        textView.setText(Integer.toString(decimal));
    }
}
