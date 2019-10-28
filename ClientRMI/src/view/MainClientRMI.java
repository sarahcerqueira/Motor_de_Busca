package view;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import model.InterfaceServerRMI;

/** Classe que implementa o método principal do Cliente RMI. Faz a conexão com o servidor RMI, e a interface entre usuário sistema.
 * 
 */
public class MainClientRMI {
	
	private static String opcao;
	private static Scanner scanner;
	private static InterfaceServerRMI servidor;

	public static void main(String[] args) {
		
		MainClientRMI main = new MainClientRMI();
		
		try {
			servidor = (InterfaceServerRMI) Naming.lookup("rmi://127.0.0.1/ServerRMI");
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
						
						if(servidor.userIsAdmin(username)) {
							main.opcoesAdmin(username);
						} else {
							main.opcoesUsuario(username);}
						
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
							
							if(servidor.userIsAdmin(username)) {
								main.opcoesAdmin(username);
							} else {
								main.opcoesUsuario(username);}
							
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
	
	public void opcoesUsuario(String username){
		
	
			while(true) {
				
				switch(opcao) {
				case("0"):
					return;
				
				case("1"):
					break;
				
				case("2"):
					break;
				
				default:
					System.out.println("Selecione 0 a qualquer momento para sair.\n"
							+ "Escolha sua opção: \n"
							+ "1 - Fazer busca \n"
							+ "2 - Ver histórico de navegação \n");
				}
				
				opcao =	scanner.nextLine();

			}
		
	}
	
	public void opcoesAdmin(String username) {
		
		try {
			if(servidor.userHasNotification(username)) {
				System.out.println(servidor.getUserNotification(username));
				servidor.removeUserNotification(username);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while(true) {
					
					switch(opcao) {
					case("0"):
						return;
					
					case("1"):
						break;
					
					case("2"):
						break;
					
					case("3"):
						break;
					
					case("4"):
						break;
					
					case("5"):
						break;
					
					case("6"):
						System.out.println("***************************** ADICIONAR ADMINISTRADOR *****************************");
						System.out.print("Digite o username: ");
						username = scanner.nextLine();
						
						try {
							if(servidor.changeUserPermission(username)) {
								System.out.println("O usuário "+ username + " agora é um administrador\n");
								
							} else {
								System.out.println("Erro o usuario "+ username+ " não existe\n");
							}
							
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						break;
					
					case("7"):
						break;
					
					default:
						System.out.println("Selecione 0 a qualquer momento para sair.\n"
								+ "Escolha sua opção: \n"
								+ "1 - Fazer busca \n"
								+ "2 - Ver histórico de navegação \n"
								+ "3 - Adicionar URL\n"
								+ "4 - 10 páginas mais pesquisadas\n"
								+ "5 - 10 pesquisas mais comuns\n"
								+ "6 - Adicionar administrador\n"
								+ "7 - Servidores Multicast Ativos\n");
					}
					
					opcao =	scanner.nextLine();
		
				}
		
		
	}


}
