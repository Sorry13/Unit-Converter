package com.example.unitconvertersm.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CurrencyService {
    @GET("api/exchangerates/rates/A/{code}/?format=json")
    Call<RatesContainer> getExchangeCurrency(@Path("code") String currency);
}
