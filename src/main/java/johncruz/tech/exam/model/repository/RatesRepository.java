package johncruz.tech.exam.model.repository;

import johncruz.tech.exam.model.database.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatesRepository extends JpaRepository<Rates,Long> {
}
