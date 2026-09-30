package CV.SoftDevoluciones.Return.Repository;

import CV.SoftDevoluciones.Return.Entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReturnRepository extends JpaRepository<Return, Long> {
   /* List<ProductResponseDto> findByCategory_NameAndVisible(String categoryName, Boolean visible);

    List<ProductResponseDto> findByCategory_Name(String categoryName);

    List<ProductResponseDto> findByVisible(Boolean visible);*/

    List<Return> findByOrderUserEmail(String email);
}
