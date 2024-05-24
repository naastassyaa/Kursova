package api.repository;

import api.models.BmpData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BmpDataRepository extends JpaRepository<BmpData,Integer> {
}
