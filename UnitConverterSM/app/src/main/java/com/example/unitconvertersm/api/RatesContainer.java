package com.example.unitconvertersm.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RatesContainer {
    public List<Rate> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Rate> currencyList) {
        this.currencyList = currencyList;
    }

    @SerializedName("rates")
    private List<Rate> currencyList;

}
