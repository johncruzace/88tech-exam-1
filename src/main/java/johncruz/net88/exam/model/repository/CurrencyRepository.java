package johncruz.net88.exam.model.repository;

import johncruz.net88.exam.model.database.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Long> {

    Optional<Currency> findByBaseCurrency(String currencyName);

}
