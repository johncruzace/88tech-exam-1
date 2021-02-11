package johncruz.net88.exam.controller;

import johncruz.net88.exam.model.database.Currency;
import johncruz.net88.exam.model.response.CurrencyResponse;
import johncruz.net88.exam.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

}
