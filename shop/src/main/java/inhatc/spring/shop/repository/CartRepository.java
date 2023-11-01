package inhatc.spring.shop.repository;

import inhatc.spring.shop.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
