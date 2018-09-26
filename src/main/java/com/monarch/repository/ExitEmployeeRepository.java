package com.monarch.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.ExitEmployeeDetail;



public interface ExitEmployeeRepository extends JpaRepository<ExitEmployeeDetail, Long> {

	
	
	
	@Query("select pd from ExitEmployeeDetail pd WHERE("
			+ "DATE(pd.applieddate) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.username LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.reason LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.idcard LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.deskkey LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.otherKey LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.material LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.companyvehicle LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.otherEquipment LIKE CONCAT('%',:searchterm,'%'))")
	public Page<ExitEmployeeDetail> findBySearchTerm(Pageable pageable,@Param("searchterm") String searchterm);
	
	
	@Query("select eed from ExitEmployeeDetail eed")
	public Page<ExitEmployeeDetail> findBySearchTerm(Pageable pageable);

	
	
	
	
	
}
