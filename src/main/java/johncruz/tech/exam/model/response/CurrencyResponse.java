package johncruz.tech.exam.model.response;

import johncruz.tech.exam.model.database.Rates;

import java.time.LocalDate;
import java.util.List;

public class CurrencyResponse {

    private String currencyName;
    private LocalDate latestDateGenerated;
    private List<Rates> ratesList;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public List<Rates> getRatesList() {
        return ratesList;
    }

    public void setRatesList(List<Rates> ratesList) {
        this.ratesList = ratesList;
    }

    public LocalDate getLatestDateGenerated() {
        return latestDateGenerated;
    }

    public void setLatestDateGenerated(LocalDate latestDateGenerated) {
        this.latestDateGenerated = latestDateGenerated;
    }
}
