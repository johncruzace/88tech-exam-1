package johncruz.net88.exam.model.repository;

import johncruz.net88.exam.model.database.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatesRepository extends JpaRepository<Rates,Long> {
}
