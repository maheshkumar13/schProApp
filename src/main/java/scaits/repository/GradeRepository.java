package scaits.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.common.GradeBO;


public interface GradeRepository extends JpaRepository<GradeBO, Long> {

	
	
}
