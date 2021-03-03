package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/baukapital")
public class BauController {
    @GetMapping
    public int berechneGet(
            @RequestParam double kreditbetrag,
            @RequestParam double zinssatz,
            @RequestParam double rueckzahlung) {
        return berechneLaufzeitInMonaten(kreditbetrag, zinssatz, rueckzahlung);
    }
    @PostMapping
    public int berechnePost(@RequestBody Map<String, String> parameter) {
        double kreditbetrag = Double.parseDouble(parameter.get("kreditbetrag"));
        double zinssatz = Double.parseDouble(parameter.get("zinssatz"));
        double rueckzahlung = Double.parseDouble(parameter.get("ruekzahlung"));
        return berechneLaufzeitInMonaten(kreditbetrag, zinssatz, rueckzahlung);
    }
    int berechneLaufzeitInMonaten(double kreditbetrag, double zinssatz, double rueckzahlung){
        double restBetrag = kreditbetrag;
        int monate = 0;
        double zinsen;
        double tilgung;

        while(restBetrag > 0){
            zinsen = restBetrag*zinssatz;
            tilgung = rueckzahlung - zinsen;
            if(restBetrag-tilgung > 0){
                restBetrag -= tilgung;
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
