package com.ejemplo.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.ejemplo.controller.PricesController;
import com.ejemplo.entity.InditextPrice;
import com.ejemplo.exceptions.PricesRepoException;
import com.ejemplo.repository.PricesRepo;
import com.ejemplo.service.PricesService;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

	@InjectMocks
	PricesController pricesController;

	@Mock
	private PricesRepo pricesRepo;

	@Mock
	private PricesService pricesService;
	
	
	


	//http://localhost:8080/listPrice?applyDate=2020-12-14%2010.00.00&productId=35455&brandID=1

	@Test
	void PruebaPricesControllerOK() throws Exception {
		String pDate = "2020-06-14 10.00.00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		Date startDate = df.parse(pDate);

		String productId = "35455";
		Integer brandID = 1;

		ResponseEntity<List<InditextPrice>> listPrices = pricesController.getPricesToApply(startDate, productId,
				brandID);
		assertNotNull(listPrices);
	    assertThat(listPrices.getBody().size()).isGreaterThanOrEqualTo(0);

	}

	@Test
	void PruebaPricesControllerKO() throws Exception {
		Date anyDate = new Date();
		when(pricesService.getPricesToApply(anyDate,"asdas",0)).thenThrow(PricesRepoException.class);
		ResponseEntity<List<InditextPrice>> listPrices = pricesController.getPricesToApply(anyDate,"asdas",0);
		assertNull(listPrices.getBody());
	}

}
