package com.ejemplo.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ejemplo.entity.InditextPrice;
import com.ejemplo.repository.PricesRepo;
import com.ejemplo.service.PricesService;


@ExtendWith(MockitoExtension.class) 
@DataJpaTest
class PricesServiceTest {


	@InjectMocks
	private PricesService pricesService;

	@Mock
	private PricesRepo pricesRepo;
	

	@Test
	public void test2() throws Exception
	{
		
		String pDate = "2020-06-14 20.00.00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
		Date startDate = df.parse(pDate);

		String productId = "35455";
		Integer brandID = 1;
		
        InditextPrice price = new InditextPrice();

        //When 
        when(pricesRepo.findPricesToApply(startDate,productId,brandID)).thenReturn(List.of(price));

        List<InditextPrice> prices = pricesService.getPricesToApply(startDate, productId, brandID);

        //Then
        assertEquals(List.of(price), prices);
        assertThat(prices).isNotEmpty();
        verify(this.pricesRepo).findPricesToApply(startDate,productId,brandID);
	}
}
