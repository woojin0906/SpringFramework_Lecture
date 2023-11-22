package inhatc.spring.shop.dto;

import inhatc.spring.shop.Entity.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemImgDto {

    private Long id;

    private String imgName;  // 이미지 파일명

    private String oriImgName;  // 이미지 원본 파일명

    private String imgUrl;  // 이미지 URL

    private String repImgYn;    // 대표 이미지 여부

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto entityToDto(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
