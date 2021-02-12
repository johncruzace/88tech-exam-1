package johncruz.tech.exam.model.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import johncruz.tech.exam.util.serializer.LocalDateDeserializer;
import johncruz.tech.exam.util.serializer.LocalDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "currency")
@JsonIgnoreProperties({"rates"})
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String baseCurrency;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "baseCurrency")
    @JsonManagedReference(value = "currency-rate")
    private Set<Rates> rates;

    @Column
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDate date;

    public Currency(String baseCurrency, Set<Rates> rates, LocalDate date) {
        this.baseCurrency = baseCurrency;
        this.rates = rates;
        this.date = date;
    }

    public Currency() {
    }

    public long getId() {
        return id;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Set<Rates> getRates() {
        return rates;
    }

    public void setRates(Set<Rates> rates) {
        this.rates = rates;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
