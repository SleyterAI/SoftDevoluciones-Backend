package CV.SoftDevoluciones.Category.Service;


import CV.SoftDevoluciones.Category.Dto.CategoryRequestDto;
import CV.SoftDevoluciones.Category.Dto.CategoryResponseDto;
import CV.SoftDevoluciones.Category.Entity.Category;
import CV.SoftDevoluciones.Category.Mapper.CategoryMapper;
import CV.SoftDevoluciones.Category.Repository.CategoryRepository;
import CV.SoftDevoluciones.Category.Service.Interface.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category createCategory(CategoryRequestDto categoryRequestDto) {
        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .build();
        return categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponseDto> getAllCategory() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryDto)
                .toList();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public Category updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
    }
}
