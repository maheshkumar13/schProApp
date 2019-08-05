package scaits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.CastBO;


public interface CastRepository extends JpaRepository<CastBO, Long> {

	
	
}
