package model;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**Representa um servidor UDP, que pode enviar e receber pacotes de clientes.
 * A porta est� configurada para 4446.
 * 
 */
public class ServerUDP {
	private static int port = 4446;
	private DatagramSocket severSocket;
	
	/**Abre a porta para permitir a troca e pacotes com clientes. 
	 * 
	 * @throws SocketException Lan�a exce��o caso n�o seja poss�vel criar o socket server na porta determinada.
	 */
	public ServerUDP() throws SocketException {
		severSocket = new DatagramSocket(port);
	}
	
	/**Aceita pacotes UDP. 
	 * 
	 * @param buf 			Vetor de byte que indica o tamanho do a vetor a ser recebido.
	 * @throws IOException	Lan�a exce��o caso haja problemas ao receber o pacote.
	 * @return				Retorna um objeto do tipo PacketReceived, que tem toda as informa��es do pacote recebido.
	 */
	public PacketReceived acceptPacket(byte[] buf) throws IOException{
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		severSocket.receive(packet);
		return new PacketReceived(packet.getAddress(), packet.getData(), packet.getPort());
	}
	
	/**Envia pacotes UDP. 
	 * 
	 * @param ip 			Ip do cliente que receber� o pacote.
	 * @param port 			Porta onde o cliente receber� o pacote.
	 * @param buf			Vetor de bytes com os dados que ser�o enviados ao cliente.
	 * @throws IOException	Lan�a exce��o caso n�o seja poss�vel enviar o pacote.
	 */
	public void sendPacket(InetAddress ip, int port, byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf,buf.length, ip, port);
		severSocket.send(packet);
	}
	
	

}

