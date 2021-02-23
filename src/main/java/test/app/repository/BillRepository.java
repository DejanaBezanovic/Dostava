package test.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.app.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
