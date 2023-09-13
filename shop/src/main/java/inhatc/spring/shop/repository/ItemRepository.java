package inhatc.spring.shop.repository;

import inhatc.spring.shop.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
