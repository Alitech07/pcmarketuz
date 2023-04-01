package datarest.pcmarket.repository;

import datarest.pcmarket.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
}
