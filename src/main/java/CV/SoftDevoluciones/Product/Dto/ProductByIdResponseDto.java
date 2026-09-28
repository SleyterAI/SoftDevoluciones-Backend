package CV.SoftDevoluciones.Product.Dto;

import CV.SoftDevoluciones.Category.Entity.Category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductByIdResponseDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String imageUrl;
    private Boolean visible;
    private Category category;
}
