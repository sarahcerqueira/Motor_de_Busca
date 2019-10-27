package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

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
	public byte[] acceptPacket(byte[] buf) throws IOException{
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		clientSocket.receive(packet);
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
