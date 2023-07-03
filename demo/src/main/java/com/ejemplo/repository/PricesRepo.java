package com.ejemplo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ejemplo.entity.InditextPrice;

@Repository
public interface PricesRepo extends JpaRepository<InditextPrice, Long>  {
	
	/** 
	 * Creates and launch a query to H2 prices having the following parameters
	 * @param applicationDate
	 * @param productId
	 * @param brandID
	 * @return list of products given certain searching criteria as Applying price date, a productID and a brandId
	 */
	@Query("Select p.productId, p.brandID, p.priceList, p.startDate, p.endDate, p.price FROM InditextPrice p "
			+ "WHERE p.startDate<=:applyDate AND p.endDate>=:applyDate AND p.brandID=:brandID AND "
			+ "p.productId=:productId AND p.priority IN "
			+ "("
			+ "  Select  MAX(p.priority) "
			+ "   FROM InditextPrice p WHERE p.startDate<=:applyDate AND p.endDate>=:applyDate and "
			+ "     p.productId=:productId AND p.brandID=:brandID GROUP BY p.productId, p.brandID"
			+ ") ")
	public List<InditextPrice> findPricesToApply(@Param("applyDate") Date applicationDate,
			@Param("productId")  String productId, @Param("brandID")  Integer brandID);

}
