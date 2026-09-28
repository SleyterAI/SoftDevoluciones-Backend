package CV.SoftDevoluciones.Category.Mapper;

import CV.SoftDevoluciones.Category.Dto.CategoryResponseDto;
import CV.SoftDevoluciones.Category.Entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponseDto toCategoryDto(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
