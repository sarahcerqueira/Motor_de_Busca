package view;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import model.InterfaceServerRMI;

/** Classe que implementa o método principal do Cliente RMI. Faz a conexão com o servidor RMI, e a interface entre usuário sistema.
 * 
 */
public class MainClientRMI {
	
	private static String opcao;
	private static Scanner scanner;
	private static InterfaceServerRMI servidor;
	private String username;

	public static void main(String[] args) {
		
		MainClientRMI main = new MainClientRMI();
		
		try {
			servidor = (InterfaceServerRMI) Naming.lookup("server");;
			opcao = "0";
			scanner = new Scanner(System.in);		
			
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
					main.login();					
					break;
				
				case("2"):
					main.registry();
					break;
					
				case("3"):
					main.search();
					opcao = "0";
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
					break;
				
				case("1"):
					this.search();
					opcao = "default";
					break;
				
				case("2"):
					this.getHistoric();
					opcao = "default";
					break;
				
				default:
					System.out.println("Selecione 0 a qualquer momento para sair.\n"
							+ "Escolha sua opção: \n"
							+ "1 - Fazer busca \n"
							+ "2 - Ver histórico de navegação \n");
				
					opcao =	scanner.nextLine();

			}
		}
		
	}
	
	public void opcoesAdmin(String username) {
		
		try {
			if(servidor.userHasNotification(username)) {
				System.out.println(servidor.getUserNotification(username));
				servidor.removeUserNotification(username);
			}
		} catch (IOException e1) {
			printErroMulticast();
		}
		
		while(true) {
					
					switch(opcao) {
					case("0"):
						return;
					
					case("1"):
						this.search();
						break;
					
					case("2"):
						this.getHistoric();
						break;
					
					case("3"):
						this.indexarURL();
						break;
					
					case("4"):
						break;
					
					case("5"):
						this.paginasMaisAcessadas();;
						break;
					
					case("6"):
						this.addAdm();
						opcao = "default";
						break;
					
					default:
						System.out.println("Selecione 0 a qualquer momento para sair.\n"
								+ "Escolha sua opção: \n"
								+ "1 - Fazer busca \n"
								+ "2 - Ver histórico de navegação \n"
								+ "3 - Adicionar URL\n"
								+ "4 - 10 páginas mais pesquisadas\n"
								+ "5 - 10 pesquisas mais comuns\n"
								+ "6 - Adicionar administrador\n");
					}
					
					opcao =	scanner.nextLine();
		
				}
		
		
	}
	
	public void login() {
		String username, password;
		System.out.println("***************************** LOGIN *****************************");
		System.out.print("Login: ");
		username = scanner.nextLine();
		System.out.print("Password: ");
		password = scanner.nextLine();
		
		if(username.equals("0") || password.contentEquals("0"))
			return;
	
		try {
			if(servidor.login(username, password)){
				System.out.println("\nBem-vindo ao UCBusca "+ username);
				
				if(servidor.userIsAdmin(username)) {
					this.opcoesAdmin(username);
					
				} else {
					this.opcoesUsuario(username);
				}
				
				
			} else {
				System.out.println("\nSenha ou usuario incorreto :(");
				scanner.hasNextLine();
			}
		} catch (RemoteException e) {
			System.out.println("Erro ao se comunicar com o servidor rmi\n");
			printErroRMI();
			scanner.hasNextLine();
			//e.printStackTrace();
			
		} catch (IOException e) {
			printErroMulticast();
			scanner.hasNextLine();
		}
		
		return;
	}
	
	public void registry() {
		
		System.out.println("***************************** REGISTRY *****************************");
		String username, password;

		while(opcao.equals("2")){
			System.out.print("Username: ");
			username = scanner.nextLine();
			System.out.print("Password: ");   
			password = scanner.nextLine();
			
			try {
				if(servidor.registerUser(username, password)) {
					System.out.println("\nObrigada por se registrar no UCBusca *-* \n");
					
					if(servidor.userIsAdmin(username)) {
						this.opcoesAdmin(username);
						return;
					} else {
						this.opcoesUsuario(username);
						return;}
					
				}else{
					System.out.println("\nNão foi possível cadastrar o username: "+ username+
							" :( \nTente novamente com um username diferente.\n");
					opcao = "2";
				}
			} catch (RemoteException e) {
				e.printStackTrace();
				printErroRMI();
				scanner.hasNextLine();
				return;
				
			} catch (IOException e) {
				e.printStackTrace();
				printErroMulticast();
				scanner.hasNextLine();
				return;
			}
		
	}
	}
	
	public void search() {
		String texto;
		System.out.println("***************************** Buscar *****************************");
		System.out.print("Digite 0 para sair a qualuer momento\n\n");
		
		System.out.print("O que procura? \n     * ");
		texto = scanner.nextLine();
		
		if(texto.equals("0")) {
			return;
		}
		
		try {
			ArrayList<String> sites = this.servidor.search(texto);
			
			if(sites.isEmpty()) {
				System.out.print("\nNão há resultados para essa pesquisa :\\ \n\n");
				return;
			} else {
			
			
			System.out.print(sites.size() + " resultados\n\n");

			
			for(int i =0; i< sites.size(); i=i+3) {
				System.out.print(sites.get(i)+ "\n"+
								sites.get(i+1)+"\n"+
								sites.get(i+2)+"\n\n");

			}
			
			}
			
		} catch (RemoteException e) {
			printErroRMI();
		} catch (IOException e) {
			printErroMulticast();
		}
		
		scanner.nextLine();
		return;
		
	}
	
	
	public void getHistoric() {
		System.out.println("***************************** Buscar *****************************");

		try {
			ArrayList<String> historico = this.servidor.getHistoric(username);
			
			if(historico == null) {
				System.out.println("Seu histórico está vazio\n");
				
			} else {
				
				for(int i=0; i< historico.size(); i=i+2) {
					System.out.print(historico.get(i)+ "\n"+
							historico.get(i+1)+"\n\n");
				}
					
			}
			
			
		} catch (RemoteException e) {
			printErroRMI();
		} catch (IOException e) {
			printErroMulticast();
		}
		
		scanner.hasNextLine();
		return;
	}
	
	
	public void addAdm() {
		System.out.println("***************************** ADICIONAR ADMINISTRADOR *****************************");
		System.out.print("Digite o username: ");
		String username = scanner.nextLine();
		
		if(username.equals("0")) {
			return;
		}
		
		try {
			if(this.servidor.changeUserPermission(username)) {
				System.out.println("O usuário "+ username + " agora é um administrador\n");
			}else {
				System.out.println("Erro o usuario "+ username+ " não existe\n");
			}
			
			
		} catch (RemoteException e) {
			printErroRMI();
		} catch (IOException e) {
			printErroMulticast();
		}
		scanner.nextLine();

	}
	
	
	private void printErroRMI() {
		System.out.println("Erro ao se comunicar com o servidor rmi\n");
	}
	
	private void printErroMulticast() {
		System.out.println("Erro ao se comunicar com o servidor multicast\n");

	}
	
	public void indexarURL() {
		System.out.println("***************************** INDEXAR URL *****************************");
		System.out.print("Digite a url: ");
		String url = scanner.nextLine();
		
		try {
			this.servidor.indexURL(url);
			System.out.print("Sua solicitação foi enviada");			
			
		} catch (RemoteException e) {
			printErroRMI();
			e.printStackTrace();
		} catch (IOException e) {
			printErroMulticast();
			e.printStackTrace();
		}
		
		scanner.nextLine();

	}
	
	
	public void paginasMaisAcessadas() {
		System.out.println("***************************** PÁGINA MAIS ACESSADAS *****************************");
		try {
			ArrayList<String> pages = this.servidor.getImportantPages();
			
			if(pages == null) {
				System.out.println("Nenhuma página foi pesquisada ainda");
				return;

			}
			
			for(int i=0; i< pages.size(); i=i+2) {
				System.out.print("Url: "+pages.get(i)+ "\n"+
						"Número de acessos: "+pages.get(i+1)+"\n\n");
			}
			
			
			
		} catch (RemoteException e) {
			printErroRMI();
			e.printStackTrace();
		} catch (IOException e) {
			printErroMulticast();
			e.printStackTrace();
		}
		
		scanner.nextLine();

		
	}

	public void textosMaisPesquisados() {
		System.out.println("***************************** Textos mais pesquisados *****************************");
		try {
			ArrayList<String> text = this.servidor.getImportantSearch();
			
			if(text == null) {
				System.out.println("Nenhum texto foi pesquisado ainda");
				return;

			}
			
			for(int i=0; i< text.size(); i=i+2) {
				System.out.print("Url: "+text.get(i)+ "\n"+
						"Número de acessos: "+text.get(i+1)+"\n\n");
			}
			
			
			
		} catch (RemoteException e) {
			printErroRMI();
			e.printStackTrace();
		} catch (IOException e) {
			printErroMulticast();
			e.printStackTrace();
		}
		
		scanner.nextLine();

		
	}

}
