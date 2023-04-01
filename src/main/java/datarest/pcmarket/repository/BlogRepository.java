package datarest.pcmarket.repository;

import datarest.pcmarket.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
}
