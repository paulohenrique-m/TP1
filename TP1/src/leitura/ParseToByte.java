package leitura;

import java.io.*;

// class to parse a variable into chosen type byte 
class ParseToByte {
//--> ATRIBUTOS

//--> CONSTRUTOR
	private ParseToByte () {
		throw new AssertionError();
	}

	public static byte[] intToByte (int num) throws IOException {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    DataOutputStream dos = new DataOutputStream(bos);
	    dos.writeInt(num);
	    dos.flush();
	    return bos.toByteArray();
	}
	public static byte[] stringToByte (String str) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		if (str == null || str.length() == 0) {
			dos.writeInt(0);
		} else {
			int n = str.length();
			dos.writeInt(n);
			dos.writeBytes(str);
			dos.flush();
		}
		return bos.toByteArray();
	}
	public static byte[] charArrayToByte (char[] c_array) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		if (c_array == null) {
			dos.write(0);
		} else {
			dos.writeBytes(String.valueOf(c_array));
			dos.flush();
		}
		return bos.toByteArray();
	}
	public static byte[] floatToByte (float num) throws IOException {
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 DataOutputStream dos = new DataOutputStream(bos);
		 dos.writeFloat(num);
		 dos.flush();
		 return bos.toByteArray();
	}
	// parsing the object UFO with all his components into byte 
	protected static byte[] parseUfoToByte (Ufo ufo) {
		ByteArrayOutputStream temp = new ByteArrayOutputStream();
		ByteArrayOutputStream resp = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(resp);
		try {
			temp.write(intToByte(ufo.getId()));
			temp.write(stringToByte(ufo.getCity()));
			temp.write(charArrayToByte(ufo.getState()));
			temp.write(stringToByte(ufo.getDate()));
			temp.write(stringToByte(ufo.getStats()));
			temp.write(stringToByte(ufo.getReportLink()));
			temp.write(stringToByte(ufo.getText()));
			temp.write(floatToByte(ufo.getCityLatitude()));
			temp.write(floatToByte(ufo.getCityLongitude()));
			dos.write(0);
			dos.writeInt(temp.size());
			dos.write(temp.toByteArray());
			dos.flush();
			dos.close();
			temp.close();
		} catch (IOException e) {
			System.out.println("erro parse ufo");
			return null;
		}
		return resp.toByteArray();
	}
	protected static Ufo parseByteToString (int id, byte[] b_array, int size_b_array) {
		int off, len;
		off = len = 0;
		byte aux;
		ByteArrayInputStream bis = new ByteArrayInputStream(b_array, 0,size_b_array);
		DataInputStream ds = new DataInputStream(bis);
		Ufo ufo = new Ufo();
		ufo.setId(id);
		try {
		//--> CITY
			len = ds.readInt();
			off += 4;
			ufo.setCity(byteToString(b_array, off, len));
			off += len;
			ds.skipBytes(len);
		//--> STATE
			aux = ds.readByte();
			if (aux == 0) {
				ufo.setState(null);
				off += 1;
			} else {
				ufo.setState(byteToString(b_array, off, 2));
				off += 2;
				ds.skipBytes(1);
			}
		//--> DATE
			len = ds.readInt();
			off += 4;
			ufo.setDate(byteToString(b_array, off, len));
			off += len;
			ds.skipBytes(len);
		//--> STATS
			len = ds.readInt();
			off += 4;
			ufo.setStats(byteToString(b_array, off, len));
			off += len;
			ds.skipBytes(len);
		//--> REPORT_LINK
			len = ds.readInt();
			off += 4;
			ufo.setReportLink(byteToString(b_array, off, len));
			off += len;
			ds.skipBytes(len);
		//--> TEXT
			len = ds.readInt();
			off += 4;
			ufo.setText(byteToString(b_array, off, len));
			off += len;
			ds.skipBytes(len);
		//--> LATITUDE LONGITUDE	
			ufo.setCityLatitude(ds.readFloat());
			ufo.setCityLongitude(ds.readFloat());
		//--> CLOSE	
			bis.close();
			ds.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do registro");
			return null;
		}
		return ufo;
	}
	private static String byteToString (byte[] b_array, int off, int len) {
		if (len == 0)
			return null;
		else
			return new String(b_array, off, len);
	}
}
