/*

package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
@RestController
@RequestMapping("/zinsen")
public class ZinsrechnerController {
    // GET: Verzinst kapital mit einem jährlichen zinssatz (Prozentwert) über jahre
    @GetMapping
    public double berechneGet(
            @RequestParam double kapital,
            @RequestParam double zinssatz,
            @RequestParam int jahre) {
        return berechneZinsen(kapital, zinssatz, jahre);
    }
    @PostMapping
    public double berechnePost(@RequestBody Map<String, String> parameter) {
        double kapital = Double.parseDouble(parameter.get("kapital"));
        double zinssatz = Double.parseDouble(parameter.get("zinssatz"));
        int jahre = Integer.parseInt(parameter.get("jahre"));
        return berechneZinsen(kapital, zinssatz, jahre);
    }
    // Berechnung: Verzinst kapital mit einem jährlichen zinssatz (Prozentwert) über jahre
    double berechneZinsen(double kapital, double zinssatz, int jahre) {
        double endkapital = kapital;
        for (int i = 1; i <= jahre; i++) {
            endkapital *= (1 + zinssatz / 100);
        }
        return endkapital;
    }


}

 */