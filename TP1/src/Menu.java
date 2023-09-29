
import java.util.*;
import java.io.*;
import static leitura.IORegistro.*;
import leitura.Ufo;

public class Menu {
//--> ATRIBUTOS	
	private Scanner scan;
	
//--> CONSTRUTOR
	public Menu () {
		scan = new Scanner(System.in);
	}
	
//--> METODOS
	public void displayMenu () {
		int x = 0;
		int id = 0;
		Menu.clearConsole();
		do {
			System.out.println("1 - Carregar CSV");
			System.out.println("2 - Pesquisar Id");
			System.out.println("4 - Deletar Id");
			System.out.println("0 - Encerrar programa");
			x = scan.nextInt();
			switch (x) {
				case 0:
						Menu.clearConsole();
						System.out.println("PROGRAMA ENCERRADO");
						break;
				case 1:
					try {
						System.out.println("Carregndo arquivo...");
						readFile();
						Menu.clearConsole();
						System.out.println("Arquivo carregado");
					} catch (IOException e) {
						Menu.clearConsole();
						System.out.println("ERRO NA LEITURA DO ARQUIVO");
					}
					break;
				case 2:
					try {
						Menu.clearConsole();
						System.out.print("Inserir ID: ");
						id = scan.nextInt();
						Ufo ufo = searchId(id);
						Menu.clearConsole();
						if (ufo == null)
							System.out.println("Nao foi possivel encontrar registro com id: " + id);
						else
							ufo.printUfo();					
					}catch (IOException e) {
						
					}
					break;
				case 4:
					try {
						Menu.clearConsole();
						System.out.print("Inserir ID: ");
						id = scan.nextInt();
						Menu.clearConsole();
						System.out.println(deleteId(id) ? id + " - ID Deletado" : "nao foi possivel deletar o id");
					}catch (IOException e) {
						
					}
					break;
				default:
					System.out.println();
					Menu.clearConsole();
					System.out.println("Opcao invalida");
					break;

			}
		} while(x != 0);
	}

	private final static void clearConsole() {
		System.out.print("\033[H\033[2J");  
		System.out.flush();  
	}
}//END_MENU
