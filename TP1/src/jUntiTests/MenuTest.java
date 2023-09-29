package jUntiTests;
import static org.junit.Assert.*;
import  org.junit.*;

import org.junit.Test;

import Menu;

public class MenuTest {
	public static Menu menu;
	@Before	
	public void test() {
		menu = new Menu();
	}
	@Test
	public void menuTest() {
		menu.displayMenu();
	}
	

}
