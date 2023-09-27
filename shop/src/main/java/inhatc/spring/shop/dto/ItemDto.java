package inhatc.spring.shop.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {

    private Long id;

    private String itemNm;  // 상품명

    private int price;  // 가격

    private int stockNumber;  // 재고 수량

    private String itemDetail;  // 상품 상세 설명

    private String itemSellStatus;  // 상품 판매 상태

    private LocalDateTime regTime;  // 등록 시간

    private LocalDateTime updateTime;  // 수정 시간

}
