package datarest.pcmarket.repository;

import datarest.pcmarket.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews,Integer> {
}
