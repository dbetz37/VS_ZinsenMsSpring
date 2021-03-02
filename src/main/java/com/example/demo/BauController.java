package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/baukapital")
public class BauController {
    public int berechneLaufzeitInMonaten(double kreditbetrag, double zinssatz, double rueckzahlung){
        double restBetrag = kreditbetrag;
        int monate = 0;
        while(restBetrag > 0){
            double zinsen = restBetrag*zinssatz;
            double tilgung = rueckzahlung - zinsen;
            if(restBetrag-tilgung > 0){
                restBetrag = restBetrag - tilgung;
            } else {
                restBetrag = 0;
            }
            monate++;
        }
        return monate;
    }

    public Collection<Double> rueckzahlungsplan (double kreditbetrag, double zinssatz, double rueckzahlung){
        double restBetrag = kreditbetrag;
        Collection<Double> tilgungsliste = null;
        while(restBetrag > 0){
            double zinsen = restBetrag*zinssatz;
            double tilgung = rueckzahlung - zinsen;
            if(restBetrag-tilgung > 0){
                restBetrag = restBetrag - tilgung;
                tilgungsliste.add(restBetrag);
            } else {
                restBetrag = 0;
                tilgungsliste.add(restBetrag);
            }
        }
        return tilgungsliste;
    }
}
