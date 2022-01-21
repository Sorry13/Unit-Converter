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

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TemperatureConverterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextValue;
    Button buttonCalculateResult;
    Spinner spinnerConvertTemperatureFrom;
    Spinner spinnerConvertTemperatureTo;

    public static final String TEMP_KEY_INPUT_VALUE = "value input";
    public static final String TEMP_KEY_OUTPUT_VALUE = "value result";
    public static final String TEMP_KEY_INPUT_UNIT = "unit input";
    public static final String TEMP_KEY_OUTPUT_UNIT = "unit result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        this.spinnerConvertTemperatureFrom = findViewById(R.id.temperatureConvertFromSpinner);
        this.spinnerConvertTemperatureTo = findViewById(R.id.temperatureConvertToSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.TemperatureUnitsSpinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConvertTemperatureTo.setAdapter(adapter);
        spinnerConvertTemperatureFrom.setAdapter(adapter);

        this.editTextValue = findViewById(R.id.temperatureValueEditText);
        editTextValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.buttonCalculateResult = findViewById(R.id.buttonCalculateResult);
        buttonCalculateResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String temperatureUnitFrom;
        String temperatureUnitTo;
        String inputValue;
        Intent intent;

        temperatureUnitFrom = spinnerConvertTemperatureFrom.getSelectedItem().toString();
        temperatureUnitTo = spinnerConvertTemperatureTo.getSelectedItem().toString();
        inputValue = editTextValue.getText().toString();

        if (inputValue.trim().isEmpty()) {
            Toast.makeText(this, R.string.empty_input, Toast.LENGTH_LONG).show();
        } else {

            Double result = CalculateTemperature(temperatureUnitFrom, temperatureUnitTo, inputValue);
            result = round(result, 2);
            String resultAsString = result.toString();

            intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra(TEMP_KEY_INPUT_VALUE, inputValue);
            intent.putExtra(TEMP_KEY_OUTPUT_VALUE, resultAsString);
            intent.putExtra(TEMP_KEY_INPUT_UNIT, temperatureUnitFrom);
            intent.putExtra(TEMP_KEY_OUTPUT_UNIT, temperatureUnitTo);

            this.startActivity(intent);
        }
    }


    public Double CalculateTemperature(String temperatureUnitFrom, String temperatureUnitTo, String value){
        Double inputValue = Double.parseDouble(value);
        Double resultValue = null;

        switch(temperatureUnitFrom){
            case "Celsius":
                switch(temperatureUnitTo){
                    case "Celsius":
                        resultValue = inputValue;
                        break;
                    case "Kelvin":
                        resultValue = inputValue + 273.15;
                        break;
                    case "Fahrenheit":
                        resultValue = (inputValue*9/5) + 32;
                        break;
            }
            break;
            case "Kelvin":
                switch(temperatureUnitTo){
                    case "Celsius":
                        resultValue = inputValue - 273.15;
                        break;
                    case "Kelvin":
                        resultValue = inputValue;
                        break;
                    case "Fahrenheit":
                        resultValue = (inputValue - 273.15) * 9/5 + 32;
                        break;
                }
                break;
            case "Fahrenheit":
                switch(temperatureUnitTo){
                    case "Celsius":
                        resultValue = (inputValue - 32) * 5/9;
                        break;
                    case "Kelvin":
                        resultValue = (inputValue - 32) * 5/9 + 273.15;
                        break;
                    case "Fahrenheit":
                        resultValue = inputValue;
                        break;
                }
                break;
        }
        return resultValue;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}