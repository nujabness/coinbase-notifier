package com.nujabness.notify.scrapping;

import com.nujabness.notify.coinbase.ICoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class CoinbaseScrapping {

    @Autowired ICoinbaseService coinbaseService;

    @Value("${url.coinbase.btc}") String urlCoinbaseApiBTC;
    @Value("${url.coinbase.eth}") String urlCoinbaseApiETH;
    @Value("${url.coinbase.lpt}") String urlCoinbaseApiLPT;

    @Value("${purchase.thresholf.btc}") double purchaseThresholfBTC;
    @Value("${purchase.thresholf.eth}") double purchaseThresholfETH;
    @Value("${purchase.thresholf.lpt}") double purchaseThresholfLPT;


    @Scheduled(fixedDelay = 10000)
    public void getCryptoData() {
        try {
            coinbaseService.getDataFromCoinbase(urlCoinbaseApiBTC, purchaseThresholfBTC, "BTC");
            coinbaseService.getDataFromCoinbase(urlCoinbaseApiETH, purchaseThresholfETH, "ETH");
            coinbaseService.getDataFromCoinbase(urlCoinbaseApiLPT, purchaseThresholfLPT, "LPT");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

