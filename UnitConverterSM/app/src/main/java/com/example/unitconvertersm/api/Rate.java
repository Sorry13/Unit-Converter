package com.example.unitconvertersm.api;

import com.google.gson.annotations.SerializedName;

public class Rate {
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    @SerializedName("no")
    private String no;
    @SerializedName("effectiveDate")
    private String effectiveDate;
    @SerializedName("mid")
    private String mid;
}
