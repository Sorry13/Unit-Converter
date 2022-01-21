package com.example.unitconvertersm.activities;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unitconvertersm.api.CurrencyService;
import com.example.unitconvertersm.R;
import com.example.unitconvertersm.api.RatesContainer;
import com.example.unitconvertersm.api.RetrofitInstance;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyConverterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextValue;
    Button buttonCalculateResult;
    Spinner spinnerConvertCurrencyTo;
    TextView textCurrencyResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        this.spinnerConvertCurrencyTo = findViewById(R.id.currencyConvertToSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.CurrencyUnitsSpinner, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerConvertCurrencyTo.setAdapter(adapter);

        this.editTextValue = findViewById(R.id.currencyValueEditText);
        editTextValue.setInputType(InputType.TYPE_CLASS_NUMBER);
        this.textCurrencyResult = findViewById(R.id.currencyResultEditText);
        this.buttonCalculateResult = findViewById(R.id.buttonCalculateCurrencyResult);
        buttonCalculateResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (editTextValue.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, R.string.empty_input, Toast.LENGTH_LONG).show();
        } else {
            CurrencyService currencyService = RetrofitInstance.getRetrofitInstance().create(CurrencyService.class);
            Call<RatesContainer> call = currencyService.getExchangeCurrency(spinnerConvertCurrencyTo.getSelectedItem().toString());
            call.enqueue(new Callback<RatesContainer>() {
                @Override
                public void onResponse(Call<RatesContainer> call, Response<RatesContainer> response) {
                    double currency = Double.valueOf(editTextValue.getText().toString());

                    double rate = Double.valueOf(response.body().getCurrencyList().get(0).getMid());
                    double result = currency / rate;
                    textCurrencyResult.setText(String.valueOf(result));

                    Snackbar.make(findViewById(R.id.main_view), R.string.api_succes,
                            Snackbar.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<RatesContainer> call, Throwable t) {
                    Snackbar.make(findViewById(R.id.main_view), R.string.api_fail,
                            Snackbar.LENGTH_LONG).show();
                }
            });
        }


    }
}