package util;
import java.net.InetAddress;

/** Guarda informa��es dos pacotes recebidos para que seja poss�vel responder aos clientes depois.
 */
public class PacketReceived {
	private InetAddress  ip;
	private byte[] data;
	private int port;
	
	/**Recebe os dados do pacote recebido.
	 * 
	 *@param ip 	Endere�o do remetente do pacote.
	 *@param port	Porta pela qual o pacote foi enviado.
	 *@param data	Vetor de bytes com a informa��o enviada no pacote.
	 */
	public PacketReceived(InetAddress ip, byte[] data, int port) {
		this.ip = ip;
		this.data = data;
		this.port = port;
	}

	/** Pega o endere�o do remetente do pacote.
	 * 
	 * @return 		InetAddress com o endere�o do remetente.
	 */
	public InetAddress  getIp() {
		return ip;
	}

	/** Pega a informa��o do pacote.
	 * 
	 * @return 		Vetor de Bytes com a informa��o do pacote.
	 */
	public byte[] getData() {
		return data;
	}

	/**Pega a porta pela qual foi enviado o pacote.
	 * 
	 * @return Porta do remetente.
	 */
	public int getPort() {
		return port;
	}
	
	

}