package johncruz.net88.exam.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import johncruz.net88.exam.model.database.Rates;
import org.apache.tomcat.jni.Local;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class CurrencyResponse {

    private String currencyName;
    private LocalDate dateGenerated;
    private List<Rates> ratesList;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public LocalDate getGeneratedDate() {
        return dateGenerated;
    }

    public void setGeneratedDate(LocalDate generatedDate) {
        this.dateGenerated = generatedDate;
    }

    public List<Rates> getRatesList() {
        return ratesList;
    }

    public void setRatesList(List<Rates> ratesList) {
        this.ratesList = ratesList;
    }
}
