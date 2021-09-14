package com.nujabness.notify.beans;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultObjectWrapperCoinBase {

    @JsonProperty(value="data")
    private CoinPrice payload;
}