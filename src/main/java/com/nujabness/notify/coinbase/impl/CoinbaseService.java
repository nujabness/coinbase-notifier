package com.nujabness.notify.coinbase.impl;

import com.nujabness.notify.mail.IEmailsenderService;
import com.nujabness.notify.beans.ResultObjectWrapperCoinBase;
import com.nujabness.notify.coinbase.ICoinbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;

@Service
public class CoinbaseService implements ICoinbaseService {

    @Value("${user.mail.to}") private String destinataire;

    @Autowired private IEmailsenderService emailsenderService;
    private RestTemplate coinbaseRestService = new RestTemplate();

    public void getDataFromCoinbase(String urlCoinbaseApi, double purchaseThreshold, String cryptoMonnaie) throws MessagingException {
        ResultObjectWrapperCoinBase resultObjectWrapperCoinBase = this.coinbaseRestService.getForObject(urlCoinbaseApi, ResultObjectWrapperCoinBase.class);
        double price = Double.parseDouble(resultObjectWrapperCoinBase.getPayload().getAmount());
        if (price < purchaseThreshold){
            this.triggerMail(cryptoMonnaie);
        }
    }

    public void triggerMail(String cryptoMonnaie) throws javax.mail.MessagingException {
        emailsenderService.sendEmailWithAttachment(destinataire,
                "STONKS " + cryptoMonnaie,
                "MEEEC GO SUR " + cryptoMonnaie,
                "C:\\Users\\melas\\Pictures\\gif\\amazing.gif");

    }
}
