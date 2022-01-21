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

public class TimeConverterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextValue;
    Button buttonCalculateResult;
    Spinner spinnerConvertTimeFrom;
    Spinner spinnerConvertTimeTo;

    public static final String TIME_KEY_INPUT_VALUE = "value input";
    public static final String TIME_KEY_OUTPUT_VALUE = "value result";
    public static final String TIME_KEY_INPUT_UNIT = "unit input";
    public static final String TIME_KEY_OUTPUT_UNIT = "unit result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_converter);

        this.spinnerConvertTimeFrom = findViewById(R.id.timeConvertFromSpinner);
        this.spinnerConvertTimeTo = findViewById(R.id.timeConvertToSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.TimeUnitsSpinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConvertTimeTo.setAdapter(adapter);
        spinnerConvertTimeFrom.setAdapter(adapter);

        this.editTextValue = findViewById(R.id.timeValueEditText);
        editTextValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.buttonCalculateResult = findViewById(R.id.buttonCalculateResult);
        buttonCalculateResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String timeUnitFrom;
        String timeUnitTo;
        String inputValue;
        Intent intent;

        timeUnitFrom = spinnerConvertTimeFrom.getSelectedItem().toString();
        timeUnitTo = spinnerConvertTimeTo.getSelectedItem().toString();
        inputValue = editTextValue.getText().toString();
        if (inputValue.trim().isEmpty()) {
            Toast.makeText(this, R.string.empty_input, Toast.LENGTH_LONG).show();
        } else {

            Double result = CalculateTime(timeUnitFrom, timeUnitTo, inputValue);
            String resultAsString = result.toString();

            intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra(TIME_KEY_INPUT_VALUE, inputValue);
            intent.putExtra(TIME_KEY_OUTPUT_VALUE, resultAsString);
            intent.putExtra(TIME_KEY_INPUT_UNIT, timeUnitFrom);
            intent.putExtra(TIME_KEY_OUTPUT_UNIT, timeUnitTo);

            this.startActivity(intent);
        }
    }

    public Double CalculateTime(String timeUnitFrom, String timeUnitTo, String value) {
        Double inputValue = Double.parseDouble(value);
        Double resultValue = null;

        switch (timeUnitFrom) {
            case "Second":
                switch (timeUnitTo) {
                    case "Second":
                        resultValue = inputValue;
                        break;
                    case "Minute":
                        resultValue = inputValue / 60;
                        break;
                    case "Hour":
                        resultValue = inputValue / 3600;
                        break;
                    case "Day":
                        resultValue = inputValue / 86400;
                        break;
                    case "Week":
                        resultValue = inputValue / 604800;
                        break;
                }
                break;
            case "Minute":
                switch (timeUnitTo) {
                    case "Second":
                        resultValue = inputValue * 60;
                        break;
                    case "Minute":
                        resultValue = inputValue;
                        break;
                    case "Hour":
                        resultValue = inputValue / 60;
                        break;
                    case "Day":
                        resultValue = inputValue / 1440;
                        break;
                    case "Week":
                        resultValue = inputValue / 10080;
                        break;
                }
                break;
            case "Hour":
                switch (timeUnitTo) {
                    case "Second":
                        resultValue = inputValue * 3600;
                        break;
                    case "Minute":
                        resultValue = inputValue * 60;
                        break;
                    case "Hour":
                        resultValue = inputValue;
                        break;
                    case "Day":
                        resultValue = inputValue / 24;
                        break;
                    case "Week":
                        resultValue = inputValue / 168;
                        break;
                }
                break;
            case "Day":
                switch (timeUnitTo) {
                    case "Second":
                        resultValue = inputValue * 86400;
                        break;
                    case "Minute":
                        resultValue = inputValue * 1440;
                        break;
                    case "Hour":
                        resultValue = inputValue / 24;
                        break;
                    case "Day":
                        resultValue = inputValue;
                        break;
                    case "Week":
                        resultValue = inputValue / 7;
                        break;
                }
                break;
            case "Week":
                switch (timeUnitTo) {
                    case "Second":
                        resultValue = inputValue * 604800;
                        break;
                    case "Minute":
                        resultValue = inputValue * 10080;
                        break;
                    case "Hour":
                        resultValue = inputValue * 168;
                        break;
                    case "Day":
                        resultValue = inputValue * 7;
                        break;
                    case "Week":
                        resultValue = inputValue;
                        break;
                }
                break;
        }
        return resultValue;
    }
}