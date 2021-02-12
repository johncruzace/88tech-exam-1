package johncruz.tech.exam.model.repository;

import johncruz.tech.exam.model.database.BaseCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCurrencyRepository extends JpaRepository<BaseCurrency, Long> {
}
