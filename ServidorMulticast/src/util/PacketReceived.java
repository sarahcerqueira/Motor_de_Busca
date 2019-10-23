package util;
import java.net.InetAddress;

/** Guarda informações dos pacotes recebidos para que seja possível responder aos clientes depois.
 */
public class PacketReceived {
	private InetAddress  ip;
	private byte[] data;
	private int port;
	
	/**Recebe os dados do pacote recebido.
	 * 
	 *@param ip 	Endereço do remetente do pacote.
	 *@param port	Porta pela qual o pacote foi enviado.
	 *@param data	Vetor de bytes com a informação enviada no pacote.
	 */
	public PacketReceived(InetAddress ip, byte[] data, int port) {
		this.ip = ip;
		this.data = data;
		this.port = port;
	}

	/** Pega o endereço do remetente do pacote.
	 * 
	 * @return 		InetAddress com o endereço do remetente.
	 */
	public InetAddress  getIp() {
		return ip;
	}

	/** Pega a informação do pacote.
	 * 
	 * @return 		Vetor de Bytes com a informação do pacote.
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