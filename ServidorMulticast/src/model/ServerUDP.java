package model;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**Representa um servidor UDP, que pode enviar e receber pacotes de clientes.
 * A porta está configurada para 4446.
 * 
 */
public class ServerUDP {
	private static int port = 4446;
	private DatagramSocket severSocket;
	
	/**Abre a porta para permitir a troca e pacotes com clientes. 
	 * 
	 * @throws SocketException Lança exceção caso não seja possível criar o socket server na porta determinada.
	 */
	public ServerUDP() throws SocketException {
		severSocket = new DatagramSocket(port);
	}
	
	/**Aceita pacotes UDP. 
	 * 
	 * @param buf 			Vetor de byte que indica o tamanho do a vetor a ser recebido.
	 * @throws IOException	Lança exceção caso haja problemas ao receber o pacote.
	 * @return				Retorna um objeto do tipo PacketReceived, que tem toda as informações do pacote recebido.
	 */
	public PacketReceived acceptPacket(byte[] buf) throws IOException{
		DatagramPacket packet = new DatagramPacket(buf,buf.length);
		severSocket.receive(packet);
		return new PacketReceived(packet.getAddress(), packet.getData(), packet.getPort());
	}
	
	/**Envia pacotes UDP. 
	 * 
	 * @param ip 			Ip do cliente que receberá o pacote.
	 * @param port 			Porta onde o cliente receberá o pacote.
	 * @param buf			Vetor de bytes com os dados que serão enviados ao cliente.
	 * @throws IOException	Lança exceção caso não seja possível enviar o pacote.
	 */
	public void sendPacket(InetAddress ip, int port, byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf,buf.length, ip, port);
		severSocket.send(packet);
	}
	
	

}

