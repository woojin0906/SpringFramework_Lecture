package inhatc.spring.shop.repository;

import inhatc.spring.shop.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThanOrderByPriceDesc(int price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByDetail(@Param("itemDetail") String itemDetail);    // JPQL

    @Query(value = "select * from Item i where i.item_Detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByDetailNative(@Param("itemDetail") String itemDetail);    // Native

    // 4주차 과제 - 2023-09-20
    List<Item> findByStockNumberGreaterThanEqualAndItemNmLike(int stockNumber, String itemNm);

    @Query("select i from Item i where i.stockNumber >= 160 and i.itemNm like '%5%'")
    List<Item> findByStockNumberAndItemNmJPQL();

    @Query(value = "select * from Item i where i.stock_number >= 160 and i.item_nm like '%5%'", nativeQuery = true)
    List<Item> findByStockNumberAndItemNmNative();

}
