package view;

import java.io.IOException;
import control.Controler;
import model.Multicast;
import model.PacketReceived;

/** A classe MainServerMulticast faz a interface entre os pacotes UDP's recebidos e o sistema
 * que armazena as informações.
 */
public class MainServerMulticast {

	public static void main(String[] args) {
		
		
		try {
			//Declações de variáveis que serão utilizadas
			Multicast multicast = new Multicast();
			Controler system = new Controler();
			String answer= null;
			
			System.out.println("Servidor Multicast Ativo");

			while(true){
				//Aguarda a chegada de pacotes
				PacketReceived packet = multicast.acceptPacket(new byte[1024]);
				String [] request = new String(packet.getData()).split("\\|");
				
				//System.out.printf(request[0] + ":"+ request[1]+":"+ request[2]);
				answer = null;
				
				//Verifica o tipo de solicitação e manda para o controler
				switch(request[0]) {
				case("login"):
					answer = system.login(request);
					break;
				
				case("useRegistry"):
					answer = system.useRegistry(request);
				break;
				
				case("changeUserPermission"):
					answer = system.changeUserPermission(request);
					break;
					
				case("userIsAdmin"):
					answer = system.userIsAdmin(request);
					break;
				
				case("getHistoric"):
					answer = system.getHistoric(request);
					break;
				
				case("addHistoric"):
					system.addHistoric(request);
					break;
				
				case("userHasNotification"):
					answer = system.userHasNotification(request);
					break;
					
				case("getUserNotification"):
					answer = system.getUserNotification(request);
					break;
				
				case("removeUserNotification"):
					system.removeUserNotification(request);
					answer = null;
					break;
				
				case("indexURL"):
					system.indexURL(request);
					answer= null;
					break;
				
				case("getImportantPages"):
					answer = system.getImportantPages();
					break;
				
				case("getImportantSearch"):
					answer = system.getImportantSearch();
					break;
					
				case("search"):
					answer = system.search(request);
					break;
				
				}
				
				//Se for necessário enviar alguma resposta
				if(answer != null) {
					multicast.sendPacketUser(answer.getBytes(), packet.getIp(), packet.getPort());
				}
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
