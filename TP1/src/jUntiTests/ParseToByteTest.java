package jUntiTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import leitura.ParseToByte;

class ParseToByteTest {

	@Test
	void test() {
		char[] c_array = {'C', 'A'};
		try {
			ParseToByte.intToByte(10);
			ParseToByte.stringToByte("Avestruz");
			ParseToByte.charArrayToByte(c_array);
			ParseToByte.stringToByte("2019-12-12T18:43:00");
			ParseToByte.stringToByte("Occurred : 12/12/2019 18:43  (Entered as : 12/12/19 18:43) Reported:");
			ParseToByte.stringToByte("http://www.nuforc.org/webreports/151/S151739.html");
			ParseToByte.stringToByte("Sangue de jesus tem poder oxala");
			ParseToByte.floatToByte(4f);
		} catch (IOException e) {
			
		}
	}

}
