package nl.gerimedica.repository;

import java.util.Optional;
import nl.gerimedica.entity.SymptomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymptomRepository extends JpaRepository<SymptomEntity, Long> {
    Optional<SymptomEntity> findByCode(String code);
}
