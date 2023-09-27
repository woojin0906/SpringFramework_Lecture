package inhatc.spring.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shop.Entity.Item;
import inhatc.spring.shop.Entity.QItem;
import inhatc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;
import java.time.LocalDateTime;
import java.util.List;

import static inhatc.spring.shop.Entity.QItem.item;

@SpringBootTest
public class ItemRepositoryReportTest {

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    public void createItemList() {
        for(int i=10; i<=100; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
    }

    public void createItemList2() {
        for(int i=1; i<=5; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
        for(int i=6; i<=10; i++) {
            Item item = Item.builder()
                    .itemNm("테스트 상품" + i)
                    .price(10000 + i)
                    .stockNumber(100 + i)
                    .itemDetail("테스트 상품 상세 설명" + i)
                    .itemSellStatus(ItemSellStatus.SOLD_OUT)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("재고량과 이름으로 검색 테스트")
    public void findByStockNumberAndItemNmTest() {
        createItemList();

        itemRepository.findByStockNumberGreaterThanEqualAndItemNmLike(160, "%5%")
                .forEach((item -> System.out.println(item)));
    }

    @Test
    @DisplayName("JPQL 테스트")
    public void findByStockNumberAndItemNmJPQLTest() {
        createItemList();

        itemRepository.findByStockNumberAndItemNmJPQL()
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("Native 테스트")
    public void findByStockNumberAndItemNmNativeTest() {
        createItemList();

        itemRepository.findByStockNumberAndItemNmNative()
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("querydsl 테스트")
    public void querydslTest() {
        createItemList();
        JPAQueryFactory factory = new JPAQueryFactory(em);

        factory.selectFrom(item)
                .where(item.stockNumber.goe(160))
                .where(item.itemNm.like("%5%"))
                .fetch()
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("querydsl 테스트2")
    public void querydslTest2() {
        createItemList2();

        BooleanBuilder builder = new BooleanBuilder();
        String itemDetail = "테스트";
        int price = 10004;
        String itemSellStatus = "SELL";

        QItem item = QItem.item;

        builder.and(item.itemDetail.like("%" + itemDetail + "%"));
        builder.and(item.price. gt(price));

        if(StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
            builder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }
        Pageable pageable = PageRequest.of(1, 5);

        Page<Item> page = itemRepository.findAll(builder, pageable);
        List<Item> content =  page.getContent();
        content.stream().forEach((e) -> System.out.println(e));
    }
}
