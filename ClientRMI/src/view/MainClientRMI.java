package view;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;

import model.InterfaceServerRMI;

public class MainClientRMI {
	
	private static String opcao;
	private static Scanner scanner;
	//private static InterfaceServerRMI servidor;

	public static void main(String[] args) {
		
		MainClientRMI main = new MainClientRMI();
		
		try {
			InterfaceServerRMI servidor = (InterfaceServerRMI) Naming.lookup("rmi://127.0.0.1/ServerRMI");
			opcao = "0";
			scanner = new Scanner(System.in);
			String username, password;
			
			
			while(true){
				
				switch(opcao) {
				
				case ("0"): 
					System.out.println("***************************** UCBUSCA *****************************");
					System.out.println("Escolha sua opção: \n"
							+ "1 - Login \n"
							+ "2 - Registrar \n"
							+ "3 - Fazer busca");
						opcao =	scanner.nextLine();
					break;
					
				case("1"):
					System.out.println("***************************** LOGIN *****************************");
					System.out.print("Login: ");
					username = scanner.nextLine();
					System.out.print("Password: ");
					password = scanner.nextLine();
					
				
					if(servidor.login(username, password)){
						System.out.println("\nBem-vindo ao UCBusca "+ username);
						main.opcoesUsuario();
					} else {
						System.out.println("\nSenha ou usuario incorreto :(");
						opcao = "0";
						scanner.hasNextLine();
					}
					
				break;
				
				case("2"):
					System.out.println("***************************** REGISTRY *****************************");
					
					while(opcao.equals("2")) {
						System.out.print("Username: ");
						username = scanner.nextLine();
						System.out.print("Password: ");   
						password = scanner.nextLine();
						
						if(servidor.registerUser(username, password)) {
							System.out.println("\nObrigada por se registrar no UCBusca *-* \n");
							main.opcoesUsuario();							
						}else{
							System.out.println("\nNão foi possível cadastrar o username: "+ username+
									" :( \nTente novamente com um username diferente.\n");
							opcao = "2";
						}
					
				}
											
					
				break;
				
				default:
					opcao = "0";
				}
			}

			
		} catch (NotBoundException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void opcoesUsuario(){
		
		System.out.println("Selecione 0 a qualquer momento para sair.\n"
				+ "Escolha sua opção: \n"
				+ "1 - Fazer busca \n"
				+ "2 - Ver histórico de navegação \n"
				+ "3 - Fazer busca\n");
		
		opcao =	scanner.nextLine();
		
	}
	
	public void opcoesAdmin() {
		
		System.out.println("Selecione 0 a qualquer momento para sair.\n"
				+ "Escolha sua opção: \n"
				+ "1 - Fazer busca \n"
				+ "2 - Ver histórico de navegação \n"
				+ "3 - Adicionar URL\n"
				+ "4 - 10 páginas mais pesquisadas\n"
				+ "5 - 10 pesquisas mais comuns\n"
				+ "6 - Adicionar administrador\n"
				+ "7 - Servidores Multicast Ativos\n");
		
		opcao =	scanner.nextLine();
	}


}
