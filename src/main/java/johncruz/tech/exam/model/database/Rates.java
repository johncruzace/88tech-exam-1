package johncruz.tech.exam.model.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import johncruz.tech.exam.util.serializer.LocalDateDeserializer;
import johncruz.tech.exam.util.serializer.LocalDateSerializer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "exchange_rates")
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String currency;

    @Column
    private BigDecimal rate;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDate dateGenerated;

    @Column
    @JsonIgnore
    private Timestamp timestamp;

    @JoinColumn(name = "currency_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference(value = "currency-rate")
    private Currency baseCurrency;

    public Rates() {
    }

    public Rates(String currency, BigDecimal rate, LocalDate dateGenerated, Timestamp timestamp, Currency baseCurrency) {
        this.currency = currency;
        this.rate = rate;
        this.dateGenerated = dateGenerated;
        this.timestamp = timestamp;
        this.baseCurrency = baseCurrency;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDate getDateGenerated() {
        return dateGenerated;
    }

    public void setDateGenerated(LocalDate dateGenerated) {
        this.dateGenerated = dateGenerated;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
}
