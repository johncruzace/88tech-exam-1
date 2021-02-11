package johncruz.net88.exam.model.repository;

import johncruz.net88.exam.model.database.BaseCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCurrencyRepository extends JpaRepository<BaseCurrency, Long> {
}
