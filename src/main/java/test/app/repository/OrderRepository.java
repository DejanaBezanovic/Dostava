package test.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.app.model.Narudzbina;

@Repository
public interface OrderRepository extends JpaRepository<Narudzbina, Long> {
	
	@Query("SELECT o FROM Narudzbina o WHERE" +
			"(:address = NULL OR o.adress LIKE :address) AND " + 
			"(:deliveryId = NULL OR o.delivery.id = :deliveryId)")
	Page<Narudzbina> search(@Param("address") String address, @Param("deliveryId") Long deliveryId, Pageable pageable);
}
