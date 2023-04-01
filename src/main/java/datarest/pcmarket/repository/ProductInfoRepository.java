package datarest.pcmarket.repository;

import datarest.pcmarket.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,Integer> {
}
