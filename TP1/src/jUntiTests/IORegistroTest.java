package jUntiTests;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import leitura.IORegistro;

public class IORegistroTest {

	@Test
	public void testReadFile () {
		try {
			IORegistro.readFile();
		} catch (IOException e) {
			System.out.println("EXCESSAO IO");
		}
	}

}
