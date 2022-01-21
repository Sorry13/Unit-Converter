package com.example.unitconvertersm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unitconvertersm.R;

public class WeightConverterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextValue;
    Button buttonCalculateResult;
    Spinner spinnerConvertWeightFrom;
    Spinner spinnerConvertWeightTo;

    public static final String WEIGHT_KEY_INPUT_VALUE = "value input";
    public static final String WEIGHT_KEY_OUTPUT_VALUE = "value result";
    public static final String WEIGHT_KEY_INPUT_UNIT = "unit input";
    public static final String WEIGHT_KEY_OUTPUT_UNIT = "unit result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        this.spinnerConvertWeightFrom = findViewById(R.id.weightConvertFromSpinner);
        this.spinnerConvertWeightTo = findViewById(R.id.weightConvertToSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.WeightUnitsSpinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConvertWeightTo.setAdapter(adapter);
        spinnerConvertWeightFrom.setAdapter(adapter);

        this.editTextValue = findViewById(R.id.weightValueEditText);
        editTextValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.buttonCalculateResult = findViewById(R.id.buttonCalculateResult);
        buttonCalculateResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String weightUnitFrom;
        String weightUnitTo;
        String inputValue;
        Intent intent;

        inputValue = editTextValue.getText().toString();

        if(inputValue.trim().isEmpty()){
            Toast.makeText(this, R.string.empty_input, Toast.LENGTH_LONG).show();
        }
        else {
            weightUnitFrom = spinnerConvertWeightFrom.getSelectedItem().toString();
            weightUnitTo = spinnerConvertWeightTo.getSelectedItem().toString();

            Double result = CalculateWeight(weightUnitFrom, weightUnitTo, inputValue);
            String resultAsString = result.toString();

            intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra(WEIGHT_KEY_INPUT_VALUE, inputValue);
            intent.putExtra(WEIGHT_KEY_OUTPUT_VALUE, resultAsString);
            intent.putExtra(WEIGHT_KEY_INPUT_UNIT, weightUnitFrom);
            intent.putExtra(WEIGHT_KEY_OUTPUT_UNIT, weightUnitTo);

            this.startActivity(intent);
        }
    }

    public Double CalculateWeight(String weightUnitFrom, String weightUnitTo, String value) {
        Double inputValue = Double.parseDouble(value);
        Double resultValue = null;

        switch (weightUnitFrom) {
            case "Gram":
                switch (weightUnitTo) {
                    case "Gram":
                        resultValue = inputValue;
                        break;
                    case "Kilogram":
                        resultValue = inputValue / 1000;
                        break;
                    case "Ton":
                        resultValue = inputValue / 1000000;
                        break;
                    case "Pound":
                        resultValue = inputValue / 453.59;
                        break;
                }
                break;
            case "Kilogram":
                switch (weightUnitTo) {
                    case "Gram":
                        resultValue = inputValue * 1000;
                        break;
                    case "Kilogram":
                        resultValue = inputValue;
                        break;
                    case "Ton":
                        resultValue = inputValue/1000;
                        break;
                    case "Pound":
                        resultValue = inputValue / 0.45359;
                        break;
                }
                break;
            case "Ton":
                switch (weightUnitTo) {
                    case "Gram":
                        resultValue = inputValue * 1000000;
                        break;
                    case "Kilogram":
                        resultValue = inputValue * 1000;
                        break;
                    case "Ton":
                        resultValue = inputValue;
                        break;
                    case "Pound":
                        resultValue = inputValue / 0.45359;
                        break;
                }
                break;
            case "Pound":
                switch (weightUnitTo) {
                    case "Gram":
                        resultValue = inputValue * 453.59;
                        break;
                    case "Kilogram":
                        resultValue = inputValue / 0.45359;
                        break;
                    case "Ton":
                        resultValue = inputValue * 0.00045359;
                        break;
                    case "Pound":
                        resultValue = inputValue;
                        break;
                }
                break;
        }
        return resultValue;
    }
}