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
public interface CurrencyService {
    void fetchLatestExchangeRate();

    List<CurrencyResponse> retrieveAllCurrency();

    CurrencyResponse retrieveByCurrency(String currencyName);

    CurrencyResponse retrieveByCurrencyAndDateGenerated(String currencyName, LocalDate generatedDate);

}
