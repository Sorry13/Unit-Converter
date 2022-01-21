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

public class MemoryConverterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextValue;
    Button buttonCalculateResult;
    Spinner spinnerConvertMemoryFrom;
    Spinner spinnerConvertMemoryTo;

    public static final String MEMORY_KEY_INPUT_VALUE = "value input";
    public static final String MEMORY_KEY_OUTPUT_VALUE = "value result";
    public static final String MEMORY_KEY_INPUT_UNIT = "unit input";
    public static final String MEMORY_KEY_OUTPUT_UNIT = "unit result";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_converter);

        this.spinnerConvertMemoryFrom = findViewById(R.id.memoryConvertFromSpinner);
        this.spinnerConvertMemoryTo = findViewById(R.id.memoryConvertToSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.MemoryUnitsSpinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConvertMemoryTo.setAdapter(adapter);
        spinnerConvertMemoryFrom.setAdapter(adapter);

        this.editTextValue = findViewById(R.id.memoryValueEditText);
        editTextValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.buttonCalculateResult = findViewById(R.id.buttonCalculateResult);
        buttonCalculateResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String memoryUnitFrom;
        String memoryUnitTo;
        String inputValue;
        Intent intent;


        memoryUnitFrom = spinnerConvertMemoryFrom.getSelectedItem().toString();
        memoryUnitTo = spinnerConvertMemoryTo.getSelectedItem().toString();
        inputValue = editTextValue.getText().toString();
        if(inputValue.trim().isEmpty()){
            Toast.makeText(this, R.string.empty_input, Toast.LENGTH_LONG).show();
        } else {
            Double result = CalculateMemory(memoryUnitFrom, memoryUnitTo, inputValue);
            String resultAsString = result.toString();

            intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra(MEMORY_KEY_INPUT_VALUE, inputValue);
            intent.putExtra(MEMORY_KEY_OUTPUT_VALUE, resultAsString);
            intent.putExtra(MEMORY_KEY_INPUT_UNIT, memoryUnitFrom);
            intent.putExtra(MEMORY_KEY_OUTPUT_UNIT, memoryUnitTo);

            this.startActivity(intent);
        }
    }

    public Double CalculateMemory(String memoryUnitFrom, String memoryUnitTo, String value){
        Double inputValue = Double.parseDouble(value);
        Double resultValue = null;

        switch(memoryUnitFrom){
            case "byte":
                switch(memoryUnitTo){
                    case "byte":
                        resultValue = inputValue;
                        break;
                    case "kilobyte":
                        resultValue = inputValue / 1024;
                        break;
                    case "megabyte":
                        resultValue = inputValue / 1048576;
                        break;
                    case "gigabyte":
                        resultValue = inputValue / 1073741824;
                        break;
                    case "terabyte":
                        resultValue = inputValue / 1099511627776.0;
                        break;
                }
                break;
            case "kilobyte":
                switch(memoryUnitTo){
                    case "byte":
                        resultValue = inputValue * 1024;
                        break;
                    case "kilobyte":
                        resultValue = inputValue;
                        break;
                    case "megabyte":
                        resultValue = inputValue / 1024;
                        break;
                    case "gigabyte":
                        resultValue = inputValue / 1048576;
                        break;
                    case "terabyte":
                        resultValue = inputValue / 1073741824;
                        break;
                }
                break;
            case "megabyte":
                switch(memoryUnitTo){
                    case "byte":
                        resultValue = inputValue * 1048576;
                        break;
                    case "kilobyte":
                        resultValue = inputValue * 1024;
                        break;
                    case "megabyte":
                        resultValue = inputValue;
                        break;
                    case "gigabyte":
                        resultValue = inputValue / 1024;
                        break;
                    case "terabyte":
                        resultValue = inputValue / 1048576;
                        break;
                }
                break;
            case "gigabyte":
                switch(memoryUnitTo){
                    case "byte":
                        resultValue = inputValue * 1073741824;
                        break;
                    case "kilobyte":
                        resultValue = inputValue * 1048576;
                        break;
                    case "megabyte":
                        resultValue = inputValue * 1024;
                        break;
                    case "gigabyte":
                        resultValue = inputValue;
                        break;
                    case "terabyte":
                        resultValue = inputValue / 1024;
                        break;
                }
                break;
            case "terabyte":
                switch(memoryUnitTo){
                    case "byte":
                        resultValue = inputValue * 1099511627776.0;
                        break;
                    case "kilobyte":
                        resultValue = inputValue * 1073741824;
                        break;
                    case "megabyte":
                        resultValue = inputValue * 1048576;
                        break;
                    case "gigabyte":
                        resultValue = inputValue * 1024;
                        break;
                    case "terabyte":
                        resultValue = inputValue;
                        break;
                }
                break;
        }
        return resultValue;
    }
}