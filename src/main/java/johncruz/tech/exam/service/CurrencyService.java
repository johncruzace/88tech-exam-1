package johncruz.tech.exam.service;

import johncruz.tech.exam.model.database.BaseCurrency;
import johncruz.tech.exam.model.database.Currency;
import johncruz.tech.exam.model.database.Rates;
import johncruz.tech.exam.model.repository.BaseCurrencyRepository;
import johncruz.tech.exam.model.repository.CurrencyRepository;
import johncruz.tech.exam.model.repository.RatesRepository;
import johncruz.tech.exam.model.request.CurrencyRequest;
import johncruz.tech.exam.model.response.CurrencyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    private static final String ROOT_URL = "https://api.exchangeratesapi.io/latest";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    static RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private BaseCurrencyRepository baseCurrencyRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private RatesRepository ratesRepository;

    @Scheduled(cron = "*/5 * * * * ?")
    public synchronized void fetchLatestExchangeRate() {

        List<BaseCurrency> currencyList = baseCurrencyRepository.findAll();

        for (BaseCurrency currency : currencyList) {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(ROOT_URL);

            uriBuilder.queryParam("base", currency.getCurrencyName());

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

            ResponseEntity<CurrencyRequest> result = restTemplate
                    .exchange(uriBuilder.toUriString(), HttpMethod.GET, entity, CurrencyRequest.class);
            if (result.getStatusCode().is2xxSuccessful()) persistCurrency(Objects.requireNonNull(result.getBody()));

        }
    }

    private void persistCurrency(CurrencyRequest exchangeRate) {

        Currency currency;
        Optional<Currency> currencyOptional = currencyRepository.findByBaseCurrency(exchangeRate.getBase());
        currency = currencyOptional.orElseGet(Currency::new);
        currency.setBaseCurrency(exchangeRate.getBase());
        currency.setDate(exchangeRate.getDate());
        currencyRepository.save(currency);
        Timestamp timestamp =  new Timestamp(System.currentTimeMillis());

        for (String key : exchangeRate.getRates().keySet()) {
            ratesRepository.save(
                    new Rates(key,
                            exchangeRate.getRates().get(key),
                            LocalDate.now(),
                            timestamp,
                            currency
                    ));
        }
    }

    public List<CurrencyResponse> retrieveAllCurrency(){
        List<CurrencyResponse> returnList = new ArrayList<CurrencyResponse>();
        for(Currency currency: currencyRepository.findAll()){
            returnList.add(cleanObjectMap(currency));
        }
        return returnList;
    }


    public CurrencyResponse retrieveByCurrency(String currencyName){
        return cleanObjectMap(retrieveCurrencyByName(currencyName));
    }

    public CurrencyResponse retrieveByCurrencyAndDateGenerated(String currencyName, LocalDate generatedDate){
        return cleanObjectMapSelectedDate(retrieveCurrencyByName(currencyName), generatedDate);
    }

    private CurrencyResponse cleanObjectMap(Currency currency){
        Comparator<Rates> comparator = Comparator.comparing( Rates::getTimestamp );
        Rates maxRateValue = currency.getRates().stream().max(comparator).get();

        CurrencyResponse response = new CurrencyResponse();
        response.setCurrencyName(currency.getBaseCurrency());
        response.setLatestDateGenerated(maxRateValue.getDateGenerated());
        List<Rates> returnRates = currency.getRates().stream()
                .filter(rates -> rates.getTimestamp().equals(maxRateValue.getTimestamp()))
                .collect(Collectors.toList());
        response.setRatesList(returnRates);
        return response;
    }

    private CurrencyResponse cleanObjectMapSelectedDate(Currency currency, LocalDate generatedDate){
        Comparator<Rates> comparator = Comparator.comparing( Rates::getTimestamp );
        Rates maxRateValue = currency.getRates().stream().max(comparator).get();

        CurrencyResponse response = new CurrencyResponse();
        response.setCurrencyName(currency.getBaseCurrency());
        response.setLatestDateGenerated(maxRateValue.getDateGenerated());
        List<Rates> filteredRatesByGeneratedDate = currency.getRates().stream()
                .filter(rates -> rates.getDateGenerated().equals(generatedDate))
                .collect(Collectors.toList());
        List<Rates> returnRates = filteredRatesByGeneratedDate.stream()
                .filter(rates -> rates.getTimestamp().equals(maxRateValue.getTimestamp()))
                .collect(Collectors.toList());
        response.setRatesList(returnRates);
        return response;
    }


    private Currency retrieveCurrencyByName (String currencyName) throws EntityNotFoundException{
        Optional<Currency> currencyOption = currencyRepository.findByBaseCurrency(currencyName);
        if(currencyOption.isPresent()){
            return currencyOption.get();
        } else {
            throw new EntityNotFoundException("Currency name does not exist");
        }
    }
}
