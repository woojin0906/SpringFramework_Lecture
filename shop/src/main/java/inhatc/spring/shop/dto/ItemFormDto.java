package inhatc.spring.shop.dto;

import inhatc.spring.shop.Entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 항목입니다.")
    private String itemNm;  // 상품명

    @NotNull(message = "가격은 필수 항목입니다.")
    private int price;  // 가격

    @NotNull(message = "재고 수량은 필수 항목입니다.")
    private int stockNumber;    // 재고 수량

    @NotBlank(message = "상품 상세 설명은 필수 항목입니다.")
    private String itemDetail;  // 상품 상세 설명

    @NotBlank(message = "상품 판매 상태는 필수 항목입니다.")
    private String itemSellStatus;  // 상품 판매 상태

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem() {
        Item item = modelMapper.map(this, Item.class);

        return item;
    }

    public static ItemFormDto entityToDto(Item item) {
        ItemFormDto itemFormDto = modelMapper.map(item, ItemFormDto.class);

        return itemFormDto;
    }
}
