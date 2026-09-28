package CV.SoftDevoluciones.Category.Service.Interface;


import CV.SoftDevoluciones.Category.Dto.CategoryRequestDto;
import CV.SoftDevoluciones.Category.Dto.CategoryResponseDto;
import CV.SoftDevoluciones.Category.Entity.Category;

import java.util.List;

public interface ICategoryService {
    //Create
    Category createCategory(CategoryRequestDto categoryRequestDto);

    //Read
    List<CategoryResponseDto> getAllCategory();
    Category getCategoryById(Long id);

    //Update
    Category updateCategory(Long id, CategoryRequestDto categoryRequestDto);

    //Delete
    void deleteCategory(Long id);
}
