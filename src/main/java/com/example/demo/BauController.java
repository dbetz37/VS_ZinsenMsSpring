package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/baukapital")
public class BauController {
    @GetMapping
    public int berechneMonatGet(
            @RequestParam double kreditbetrag,
            @RequestParam double zinssatz,
            @RequestParam double rueckzahlung) {
        return berechneLaufzeitInMonaten(kreditbetrag, zinssatz, rueckzahlung);
    }

    @PostMapping
    public Collection<Double> berechneMonatPost(@RequestBody Map<String, String> parameter) {
        double kreditbetrag = Double.parseDouble(parameter.get("kreditbetrag"));
        double zinssatz = Double.parseDouble(parameter.get("zinssatz"));
        double rueckzahlung = Double.parseDouble(parameter.get("rueckzahlung"));
        return rueckzahlungsplan(kreditbetrag, zinssatz, rueckzahlung);
    }


    int berechneLaufzeitInMonaten(double kreditbetrag, double zinssatz, double rueckzahlung){
        double restBetrag = kreditbetrag;
        int monate = 0;
        double zinsen;
        double tilgung;
        double richtigerzinssatz = zinssatz * 0.01;
        while(restBetrag > 0){
            zinsen = restBetrag*richtigerzinssatz;
            tilgung = rueckzahlung - zinsen;
            if(restBetrag-tilgung > 0){
                restBetrag -= tilgung;
                monate++;
            } else {
                restBetrag = 0;
            }

        }
        return monate;
    }
    /*
    public Collection<Professor> find(
            @RequestParam(required = false) String vorname,
            @RequestParam(required = false) String nachname) {
        Collection<Professor> professorenCollection = professoren.values();
        Stream<Professor> stream = professorenCollection.stream();
        if (vorname!=null) {
            stream = stream.filter(professor -> professor.vorname.equals(vorname));
        }
        if (nachname!=null) {
            stream = stream.filter(professor -> professor.nachname.equals(nachname));
        }
        return stream.collect(Collectors.toList());
    }

     */

    public Collection<Double> rueckzahlungsplan (double kreditbetrag, double zinssatz, double rueckzahlung){

        ArrayList a = new ArrayList<Double>();

        double restBetrag = kreditbetrag;

        a.add(restBetrag);

        //planCollection.add(5.0);

        while(restBetrag > 0){

            double richtigerzinssatz = zinssatz * 0.01;
            double zinsen = restBetrag*richtigerzinssatz;
            double tilgung = rueckzahlung - zinsen;

            if(restBetrag-tilgung > 0){
                restBetrag = restBetrag - tilgung;
                restBetrag = Math.round(100.0 * restBetrag) / 100.0;
                a.add(restBetrag);
            } else {
                restBetrag = 0.0;
                a.add(restBetrag);
            }
        }
        Collection<Double> planCollection = a;
        Stream<Double> stream = planCollection.stream();
        return stream.collect(Collectors.toList());
    }
}
