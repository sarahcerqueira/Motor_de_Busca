package view;

import java.io.IOException;
import java.util.Scanner;

import control.Controler;
import model.Multicast;
import model.PacketReceived;

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
				System.out.print(request[0] + ":"+ request[1]+":"+ request[2]);
				answer = null;
				
				//Verifica o tipo de solicitação e manda para o controler
				switch(request[0]) {
				case("login"):
					answer = system.login(request);
					break;
				
				case("useRegistry"):
					answer = system.useRegistry(request);
				break;
				
				}
				
				//Se for necessário enviar alguma resposta
				if(answer != null) {
					multicast.sendPacketUser(answer.getBytes(), packet.getIp(), packet.getPort());
				}
				
				//Só para pa teste apagar depois
				//Scanner s = new Scanner(System.in);
				//s.nextLine();
			}

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/* Teste de ordenaçãao
		HashMap<String, Integer> qtdAcess = new HashMap<String, Integer>();
		
	    qtdAcess.put("c", 50);
		qtdAcess.put("a", 10);
	    qtdAcess.put("b", 30);
	    qtdAcess.put("d", 40);
	    qtdAcess.put("e", 100);
	    qtdAcess.put("f", 60);
	    qtdAcess.put("g", 110);
	    qtdAcess.put("h", 50);
	    qtdAcess.put("i", 90);
	    qtdAcess.put("k", 70);
	    qtdAcess.put("L", 80);
		
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(qtdAcess.entrySet());
		Collections.sort(list, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				Entry<String, Integer> mapa1 =  (Entry<String, Integer>) o1;
				Entry<String, Integer> mapa2 =  (Entry<String, Integer>) o2;
				
				return mapa1.getValue().compareTo(mapa2.getValue());
			}});
		
		for(Entry<String, Integer> s: list) {
			System.out.println(s.getKey() +":"+ s.getValue()+"\n");

		}
		
		*/
		
		/* Teste Multicast
		try {
			Multicast serv = new Multicast();
			
			String mensagem = "Sou servidor 3 e entrei no grupo";
			byte buf[] = new byte[1024];
			
			serv.sendPacket(mensagem.getBytes());
			
			while(true) {
				mensagem = new String(serv.acceptPacket(buf));
				System.out.print(mensagem+"\n");
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		

	}

}
