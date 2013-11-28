package br.com.biblioteca.app.model;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

public class EmprestimoTest {

	@Test
	public void testNewInstance() {
		LocalDate date = new LocalDate();
		LocalDate date2 = date.plusDays(5);
		System.out.println(date);
		System.out.println(date2);
	}

}
