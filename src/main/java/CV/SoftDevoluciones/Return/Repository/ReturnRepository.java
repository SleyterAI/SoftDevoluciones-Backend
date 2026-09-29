package CV.SoftDevoluciones.Return.Repository;

import CV.SoftDevoluciones.Order.Entity.Order;
import CV.SoftDevoluciones.Product.Dto.ProductResponseDto;
import CV.SoftDevoluciones.Product.Entity.Product;
import CV.SoftDevoluciones.Return.Entity.ReturnDetail;
import CV.SoftDevoluciones.Return.Entity.ReturnRequest;
import CV.SoftDevoluciones.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReturnRepository extends JpaRepository<ReturnRequest, Long> {
    List<ProductResponseDto> findByCategory_NameAndVisible(String categoryName, Boolean visible);

    List<ProductResponseDto> findByCategory_Name(String categoryName);

    List<ProductResponseDto> findByVisible(Boolean visible);

    List<ReturnDetail> find
}
