package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/** Comunica��o do servidor RMI com os servidores Multicast, atrav�s do protocolo UDP.
 */
public class ClienteUDP {

	private static String ip_server = "230.0.0.0";
	private static int port = 5448;
	private DatagramSocket  clientSocket;
	private InetAddress servidor;
	
	/**Abre a porta para permitir a troca e pacotes com clientes. 
	 * 
	 * @throws SocketException Lan�a exce��o caso n�o seja poss�vel criar o socket server na porta determinada.
	 * @throws UnknownHostException 
	 */
	public ClienteUDP() throws SocketException, UnknownHostException {
		clientSocket = new DatagramSocket();
		servidor = InetAddress.getByName(ip_server);
	}
	
	/**Aceita pacotes UDP. 
	 * 
	 * @param buf 			Vetor de byte que indica o tamanho do a vetor a ser recebido.
	 * @throws IOException	Lan�a exce��o caso haja problemas ao receber o pacote.
	 * @return				Retorna um objeto do tipo PacketReceived, que tem toda as informa��es do pacote recebido.
	 */
	public byte[] acceptPacket() throws IOException{
		int lengthPac=1024;
		byte[] buf = new byte[lengthPac];
		DatagramPacket packet = new DatagramPacket(buf, lengthPac);
		clientSocket.receive(packet);
		
		String s = new String(packet.getData());
		
		s = s.replaceAll("[^0-9]", "");
		int qtdPacket = Integer.parseInt(s);
		//System.out.println("N�meros de pacotes "+ qtdPacket);

		//int qtdPacket = Integer.parseInt(packet.getData().toString());
		
		if(qtdPacket > 1) {
			buf = new byte[lengthPac * qtdPacket];
			byte[] auxBuf;
			
			for(int i =0; i<qtdPacket; i++ ) {
				auxBuf = new byte[lengthPac];
				packet = new DatagramPacket(auxBuf, lengthPac);
				clientSocket.receive(packet);
				this.sendPacket("ok".getBytes());
				auxBuf = packet.getData();
				System.arraycopy(auxBuf, 0, buf, i * lengthPac, lengthPac);
				
			}

			return buf;
			
		} else {
			buf = new byte[65507];
			packet = new DatagramPacket(buf, 65507);
			clientSocket.receive(packet);
		}
		
		return packet.getData();
	}
	
	/**Envia pacotes UDP. 
	 * 
	 * @param ip 			Ip do cliente que receber� o pacote.
	 * @param port 			Porta onde o cliente receber� o pacote.
	 * @param buf			Vetor de bytes com os dados que ser�o enviados ao cliente.
	 * @throws IOException	Lan�a exce��o caso n�o seja poss�vel enviar o pacote.
	 */
	public void sendPacket(byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf,buf.length, servidor, port);
		clientSocket.send(packet);
	}
	
	

}
