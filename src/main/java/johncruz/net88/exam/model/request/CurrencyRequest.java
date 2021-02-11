package johncruz.net88.exam.model.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public class CurrencyRequest {
    Map<String, BigDecimal> rates;
    String base;
    LocalDate date;

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
