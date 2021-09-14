package com.nujabness.notify.coinbase;

import javax.mail.MessagingException;

public interface ICoinbaseService {

     void getDataFromCoinbase(String urlCoinbaseApi, double purchaseThreshold, String cryptoMonnaie) throws MessagingException;
}
