package com.example.unitconvertersm.activities;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unitconvertersm.R;

public class BMICalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextHeightValue;
    EditText editTextWeightValue;
    Button buttonCalculateResult;
    TextView editTextBMIResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator_converter);

        this.editTextHeightValue = findViewById(R.id.BMICalculatorHeightEditText);
        this.editTextWeightValue = findViewById(R.id.BMICalculatorWeightEditText);
        editTextHeightValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextWeightValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.buttonCalculateResult = findViewById(R.id.buttonCalculateBMIResult);
        buttonCalculateResult.setOnClickListener(this);
        this.editTextBMIResult = findViewById(R.id.BMIResultEditText);
    }

    @Override
    public void onClick(View v) {
        String height;
        String weight;
        double result;

        height = editTextHeightValue.getText().toString();
        weight = editTextWeightValue.getText().toString();

        if(height.trim().isEmpty() || weight.trim().isEmpty()){
            Toast.makeText(this, R.string.empty_input, Toast.LENGTH_LONG).show();
        }
        else {
            result = (Double.parseDouble(weight) / Math.pow(Double.parseDouble(height) / 100, 2));
            editTextBMIResult.setText(String.valueOf(result));
        }
    }
}