package CV.SoftDevoluciones.Return.Repository;

import CV.SoftDevoluciones.Product.Dto.ProductResponseDto;
import CV.SoftDevoluciones.Product.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRepository extends JpaRepository<Product, Long> {
    List<ProductResponseDto> findByCategory_NameAndVisible(String categoryName, Boolean visible);

    List<ProductResponseDto> findByCategory_Name(String categoryName);

    List<ProductResponseDto> findByVisible(Boolean visible);

    Long find
}
