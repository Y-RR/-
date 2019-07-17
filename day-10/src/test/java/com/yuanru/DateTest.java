package com.yuanru;

import java.util.GregorianCalendar;

import org.junit.Test;

public class DateTest {

	@Test
	public void test1(){
		assertEquals(19,DateUtil.getAge(GregorianCalendar(2000,2,9)));
	}
}
