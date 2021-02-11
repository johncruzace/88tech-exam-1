package johncruz.net88.exam.model.database;

import javax.persistence.*;

@Entity
@Table(name = "base_currency")
public class BaseCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "currency_name")
    String currencyName;

    public BaseCurrency(String currencyName) {
        this.currencyName = currencyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public BaseCurrency() {
    }
}
