package leitura;

import java.io.*;

public class IORegistro {
//--> ATRIBUTOS
	private static final String FPATH = "./archive/ufoFormatado.csv";
	private static final String FBPATH = "./archive/ufoByte.bin";
	private static List list = new List();

//--> CONSTRUTOR
	private IORegistro() {
		throw new AssertionError(); // classe nao deve ser instanciada em momento algum, por isso erro e n excessao
	}

//--> METODOS
	public static void readFile() throws IOException {
		//System.out.println(FPATH);
		RandomAccessFile cf = new RandomAccessFile(FBPATH, "rw");
		cf.writeInt(0);
		cf.close();
		try (FileReader fr = new FileReader(FPATH); BufferedReader reader = new BufferedReader(fr)) { // try-with-resources

			String registro;
			while ((registro = reader.readLine()) != null && registro.length() != 0) {
				Ufo ufo = IORegistro.parseString(registro);

				byte[] arrayb = ParseToByte.parseUfoToByte(ufo);
				try {
					list.insert(arrayb);
				} catch (Exception e) {
					IORegistro.writeToFile();
					try {
						list.insert(arrayb);
					} catch (Exception x) {
						System.out.println("weird");
					}
				}
			}
		} catch (IOException e) {
			throw new IOException();
		}
	}

	private static void writeToFile() {
		try (FileOutputStream out = new FileOutputStream(FBPATH, true)) {
			byte[] id_temp = new byte[4];
			while (!list.isEmpty()) {
				byte[] temp = list.remove();
				for (int i = 0; i < 4; i++) {
					id_temp[i] = temp[i+5];
					
				}
				out.write(temp);
			}
			RandomAccessFile ra = new RandomAccessFile(FBPATH, "rw");
			ra.write(id_temp);
			ra.close();
		} catch (IOException e) {
			System.out.println("erro");
		}
	}

	private static Ufo parseString(String registro) {
		String[] temp = new String[9];
		Ufo ufo = null;

		temp = registro.split("\",\"");

		temp[0] = temp[0].substring(1);
		if (temp[8].length() == 0 || temp[8].length() == 1)
			temp[8] = null;
		else
			temp[8] = temp[8].substring(0, temp[8].length() - 1);
		for (int i = 1; i < 8; i++) {
			if (temp[i].length() == 0)
				temp[i] = null;
		}
		ufo = new Ufo(temp);
		return ufo;
	}

//--> SEARCH ID
	public static Ufo searchId (int id) throws FileNotFoundException, IOException {
		byte[] registro = new byte[1000000];
		FileInputStream file = new FileInputStream(FBPATH);
		DataInputStream ds = new DataInputStream(file);
		ds.skipBytes(4);
		int size_r;
		while (ds.available() > 0) {
			byte lapide = ds.readByte();
			size_r = ds.readInt();	
			if (lapide == 1) {
				ds.skipBytes(size_r-4);
				System.out.println("DELETADO ID: " + id);
			}else if (ds.readInt() == id) {
				ds.read(registro, 0, size_r-4);
				Ufo ufo = ParseToByte.parseByteToString(id, registro, size_r-4);
				ds.close();
				file.close();
				return ufo;
			} else {
				ds.skipBytes(size_r-4);
			}
		}
		ds.close();
		file.close();
		return null;
	}
//--> DELETE ID
	//randomaccessfile nao retorna o int correto. comparar bytes
	public static boolean deleteId (int id) throws FileNotFoundException, IOException {
		return false;
	}
}// END_IOREGISTRO
