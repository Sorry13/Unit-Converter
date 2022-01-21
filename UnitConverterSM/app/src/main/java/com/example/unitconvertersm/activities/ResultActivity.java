package com.example.unitconvertersm.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unitconvertersm.R;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textUnitToConvert;
    TextView textUnitConverted;
    TextView textValueToConvert;
    TextView textValueConverted;
    Button buttonReturnToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        this.textUnitToConvert = findViewById(R.id.textUnitToConvert);
        this.textUnitConverted = findViewById(R.id.textConvertedUnit);
        this.textValueToConvert = findViewById(R.id.textValueToConvert);
        this.textValueConverted = findViewById(R.id.textConvertedValue);
        this.buttonReturnToMenu = findViewById(R.id.buttonHome);
        buttonReturnToMenu.setOnClickListener(this);

        Intent intent = this.getIntent();

        if(intent.hasExtra(TemperatureConverterActivity.TEMP_KEY_INPUT_VALUE)){
            textUnitToConvert.setText(intent.getStringExtra(TemperatureConverterActivity.TEMP_KEY_INPUT_UNIT));
            textValueToConvert.setText(intent.getStringExtra(TemperatureConverterActivity.TEMP_KEY_INPUT_VALUE));

            textUnitConverted.setText(intent.getStringExtra(TemperatureConverterActivity.TEMP_KEY_OUTPUT_UNIT));
            textValueConverted.setText(intent.getStringExtra(TemperatureConverterActivity.TEMP_KEY_OUTPUT_VALUE));
        }

        if(intent.hasExtra(WeightConverterActivity.WEIGHT_KEY_INPUT_VALUE)){
            textUnitToConvert.setText(intent.getStringExtra(WeightConverterActivity.WEIGHT_KEY_INPUT_UNIT));
            textValueToConvert.setText(intent.getStringExtra(WeightConverterActivity.WEIGHT_KEY_INPUT_VALUE));

            textUnitConverted.setText(intent.getStringExtra(WeightConverterActivity.WEIGHT_KEY_OUTPUT_UNIT));
            textValueConverted.setText(intent.getStringExtra(WeightConverterActivity.WEIGHT_KEY_OUTPUT_VALUE));
        }

        if(intent.hasExtra(TimeConverterActivity.TIME_KEY_INPUT_VALUE)){
            textUnitToConvert.setText(intent.getStringExtra(TimeConverterActivity.TIME_KEY_INPUT_UNIT));
            textValueToConvert.setText(intent.getStringExtra(TimeConverterActivity.TIME_KEY_INPUT_VALUE));

            textUnitConverted.setText(intent.getStringExtra(TimeConverterActivity.TIME_KEY_OUTPUT_UNIT));
            textValueConverted.setText(intent.getStringExtra(TimeConverterActivity.TIME_KEY_OUTPUT_VALUE));
        }

        if(intent.hasExtra(MemoryConverterActivity.MEMORY_KEY_INPUT_VALUE)){
            textUnitToConvert.setText(intent.getStringExtra(MemoryConverterActivity.MEMORY_KEY_INPUT_UNIT));
            textValueToConvert.setText(intent.getStringExtra(MemoryConverterActivity.MEMORY_KEY_INPUT_VALUE));

            textUnitConverted.setText(intent.getStringExtra(MemoryConverterActivity.MEMORY_KEY_OUTPUT_UNIT));
            textValueConverted.setText(intent.getStringExtra(MemoryConverterActivity.MEMORY_KEY_OUTPUT_VALUE));
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        this.startActivity(intent);
    }
}