package com.ejemplo.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ejemplo.DemoApplication;

@ExtendWith(MockitoExtension.class)
public class DemoApplicationTest {

	@Test
	public void main() {
		DemoApplication.main(new String[] {});
	}
}
