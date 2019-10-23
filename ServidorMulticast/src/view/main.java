package view;

import java.io.IOException;

import model.Multicast;

public class main {

	public static void main(String[] args) {
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
		
		

	}

}
