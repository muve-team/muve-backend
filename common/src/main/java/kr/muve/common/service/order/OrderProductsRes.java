package kr.muve.common.service.order;

import kr.muve.common.domain.order.product.OrderProductJpaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductsRes {

    private Long orderProductId;
    private String koreanName;
    private String englishName;
    private String brandKoreanName;
    private String brandEnglishName;
    private String imageUrl;
    private Long price;
    private Integer count;

    public static OrderProductsRes from(OrderProductJpaEntity orderProduct) {
        return new OrderProductsRes(orderProduct.getId(), orderProduct.getProductJpaEntity().getKoreanName(), orderProduct.getProductJpaEntity().getEnglishName(),
                orderProduct.getProductJpaEntity().getBrandKoreanName(), orderProduct.getProductJpaEntity().getBrandEnglishName(),
                orderProduct.getProductJpaEntity().getImageUrl(), orderProduct.getPrice(), orderProduct.getCount());
    }
}
