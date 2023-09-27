package inhatc.spring.shop.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import inhatc.spring.shop.Entity.Item;
import static inhatc.spring.shop.Entity.QItem.item;
import inhatc.spring.shop.Entity.QItem;
import inhatc.spring.shop.constant.ItemSellStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
// @Transactional -> 이거 걸어두면 db 저장 안됨
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @PersistenceContext  // @Autowired 써도 됨
    EntityManager em;

    @Test
    @DisplayName("상품 생성 테스트")
    public void createItemTest() {
        Item item = Item.builder()
                .itemNm("테스트 상품")
                .price(10000)
                .stockNumber(100)
                .itemDetail("테스트 상품 상세 설명")
                .itemSellStatus(ItemSellStatus.SELL)
                .regTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        System.out.println(">>>>>>>>>>>>> item : " + item);

        Item savedItem = itemRepository.save(item);

        System.out.println(">>>>>>>>>>>>> savedItem : " + savedItem);
    }

    public void createItemList() {
         for(int i=1; i<=10; i++) {
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

    @Test
    @DisplayName("상품 이름 검색 테스트")
    public void findByItemNmTest() {
        createItemList();

        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        // 일반적인 형태
        for (Item item : itemList) {
            System.out.println(item);
        }
        // 람다식
        itemList.forEach((item) -> System.out.println(item));
        // 위와 동일
        itemList.forEach(System.out::println);
        // 동일
        itemRepository.findByItemNm("테스트 상품1").forEach(System.out::println);
    }

     @Test
     @DisplayName("OR 테스트")
     public void findByItemNmOrItemDetailTest() {
         createItemList();

         List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품 2", "테스트 사움 사에 설명 불과");

            itemList.forEach(System.out::println);
     }

     @Test
     @DisplayName("OrderBy 테스트")
     public void findByPriceLessThanOrderByPriceDescTest() {
        createItemList();

        itemRepository.findByPriceLessThanOrderByPriceDesc(10005)
                .forEach((item -> System.out.println(item)));
     }

     @Test
     @DisplayName("JPQL 테스트")
     public void findByDetailTest() {
        createItemList();

        itemRepository.findByDetail("1")
                .forEach(System.out::println);
     }

    @Test
    @DisplayName("Native 테스트")
    public void findByDetailNativeTest() {
        createItemList();

        itemRepository.findByDetailNative("1")
                .forEach(System.out::println);
    }


    @Test
    @DisplayName("Querydsl 조회 테스트")
    public void queryDslTest() {
        createItemList();
        JPAQueryFactory query = new JPAQueryFactory(em);
        
        List<Item> itemList = query.selectFrom(item)
                .where(item.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(item.itemDetail.like("%" + "1" + "%"))
                .orderBy(item.price.desc())
                .fetch();

        itemList.forEach((item -> System.out.println(item)));
    }
}