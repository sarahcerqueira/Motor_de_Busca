package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**A classe Multicast é responsável pela comunicação multicast entre os servidores.
 * Nela está configurado de forma estática o ip do grupo multicast como "230.0.0.0" e a porta para 4448.
 * 
 *  
 * */
public class Multicast {
	
	private static String ip_group = "230.0.0.0";
	private static int port = 5448;
	private MulticastSocket socket;
	private InetAddress group;
	
	/**Cria o socket multicast e se junta ao grupo. 
	 * 
	 * @throws IOException Lança exceção caso o programa não consiga abrir um socket multicast na porta estabelecida.
	 **/
	public Multicast() throws IOException {
		socket = new MulticastSocket(port);
		group = InetAddress.getByName(ip_group);
		socket.joinGroup(group);
	}
	
	/**Aceita pacotes UDP do grupo multicast. 
	 * 
	 * @param buf 			Vetor de bytes que indica o tamanho do array que vai ser recebido.
	 * @throws IOException	Lança exceção se não for possível aceitar o pacote recebido.
	 * @return 				Retorna um vetor de bytes com os dados do pacote recebido.
	 * */
	public PacketReceived acceptPacket(byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		return new PacketReceived(packet.getAddress(), packet.getData(), packet.getPort());
	}
	
	/**Envia pacotes UDP para o grupo multicast. 
	 * 
	 * @param buf			Vetor de bytes com os dados que serão enviados.
	 * @throws IOException	Lança exceção caso não seja possível enviar o pacote.
	 **/
	public void sendPacket(byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
		socket.send(packet);
	}
	
	
	/** Envia pacote para um determinado usuário.
	 * 
	 * @param buf		Mensagem a qual será enviada como vetor de bytes.
	 * @param ip		Endereço de ip da resposta.
	 * @param port		Porta pela qual a mensagem deve ser enviada.
	 * @throws IOException Exceção lançada caso haja problemas ao enviar o pacote.
	 */
	public void sendPacketUser(byte[] buf, InetAddress ip, int port) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, port);
		socket.send(packet);
	}
	
	/**Deixa o grupo e fecha o socket. 
	 * 
	 * @throws IOException Lança exceção quando há algum problema ao deixar o grupo, ou fechar a conexão.
	 * 
	 */
	public void closeConection() throws IOException {
		socket.leaveGroup(group);
		socket.close();
	}
	
	
	

}
