package johncruz.tech.exam.controller;

import johncruz.tech.exam.model.response.CurrencyResponse;
import johncruz.tech.exam.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rates")
public class RatesController {


    @Autowired
    CurrencyService currencyService;

    @GetMapping("/")
    public ResponseEntity<List<CurrencyResponse>> retrieveAllCurrency(){
        return ResponseEntity.ok().body(currencyService.retrieveAllCurrency());
    }

    @GetMapping(value = {"/{currency}/","/{currency}"})
    public ResponseEntity<CurrencyResponse> retrieveByCurrency(
            @PathVariable(name = "currency") String currency){
        return ResponseEntity.ok().body(currencyService.retrieveByCurrency(currency));
    }

    @GetMapping(value = {"/{currency}/{dateFilter}","/{currency}/{dateFilter}/"})
    public ResponseEntity<CurrencyResponse> retrieveByCurrencyAndDate(
            @PathVariable(name = "currency") String currency,
            @PathVariable(name = "dateFilter")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate date){
        return ResponseEntity.ok().body(currencyService.retrieveByCurrencyAndDateGenerated(currency,date));
    }

}
