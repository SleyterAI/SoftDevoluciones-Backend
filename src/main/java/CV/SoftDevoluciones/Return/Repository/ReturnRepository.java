package CV.SoftDevoluciones.Return.Repository;

import CV.SoftDevoluciones.Return.Entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReturnRepository extends
        JpaRepository<Return, Long>,
        JpaSpecificationExecutor<Return> {

    List<Return> findByOrderUserEmail(String email);
}
