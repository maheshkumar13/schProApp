package scaits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.common.SchoolBO;


public interface SchoolRepository extends JpaRepository<SchoolBO, Long> {

	
	
}
