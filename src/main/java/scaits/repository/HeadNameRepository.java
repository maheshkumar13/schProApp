package scaits.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import scaits.bo.student.HeadNamesBO;


public interface HeadNameRepository extends JpaRepository<HeadNamesBO, Long> {

	List<HeadNamesBO> findByHeadName(String string);

	
	
}
