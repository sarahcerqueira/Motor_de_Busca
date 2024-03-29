package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**A classe Multicast � respons�vel pela comunica��o multicast entre os servidores.
 * Nela est� configurado de forma est�tica o ip do grupo multicast como "230.0.0.0" e a porta para 4448.
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
	 * @throws IOException Lan�a exce��o caso o programa n�o consiga abrir um socket multicast na porta estabelecida.
	 **/
	public Multicast() throws IOException {
		socket = new MulticastSocket(port);
		group = InetAddress.getByName(ip_group);
		socket.joinGroup(group);

	}
	
	/**Aceita pacotes UDP do grupo multicast. 
	 * 
	 * @param buf 			Vetor de bytes que indica o tamanho do array que vai ser recebido.
	 * @throws IOException	Lan�a exce��o se n�o for poss�vel aceitar o pacote recebido.
	 * @return 				Retorna um vetor de bytes com os dados do pacote recebido.
	 * */
	public PacketReceived acceptPacket(byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		return new PacketReceived(packet.getAddress(), packet.getData(), packet.getPort());
	}
	
	/**Envia pacotes UDP para o grupo multicast. 
	 * 
	 * @param buf			Vetor de bytes com os dados que ser�o enviados.
	 * @throws IOException	Lan�a exce��o caso n�o seja poss�vel enviar o pacote.
	 **/
	public void sendPacket(byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
		socket.send(packet);
	}
	
	
	/** Envia pacote para um determinado usu�rio.
	 * 
	 * @param buf		Mensagem a qual ser� enviada como vetor de bytes.
	 * @param ip		Endere�o de ip da resposta.
	 * @param port		Porta pela qual a mensagem deve ser enviada.
	 * @throws SocketException 
	 * @throws IOException Exce��o lan�ada caso haja problemas ao enviar o pacote.
	 */
	public void sendPacketUser(byte[] buf, InetAddress ip, int port) throws SocketException {
		socket.setSoTimeout(1000);
		//System.out.print("Buf: "+ buf + "Tamanho: "+buf.length+"\n");
		int lengthPac=1024;
		DatagramPacket packet ;
		int numPac;
		byte[] bufAux;
		
		try {
		if(buf.length > 65507){
			numPac = buf.length/lengthPac;
			
			//N�mero de pacotes
			//System.out.println("N�meros de pacotes "+ numPac);		
			//Envia o n�mero de pacotes
			bufAux = Integer.toString(numPac).getBytes();
			packet = new DatagramPacket(bufAux, bufAux.length, ip, port);
			socket.send(packet);

			for(int i =0; i< numPac; i++) {
				bufAux = new byte[1024]; //Resert bufAux
				System.arraycopy(buf, i*lengthPac, bufAux,0 , 1024);
				packet = new DatagramPacket(bufAux, bufAux.length, ip, port);
				socket.send(packet);
				this.acceptPacket(bufAux);

			}

			 
			
			
		} else {
			numPac = 1;
			bufAux = Integer.toString(numPac).getBytes();
			packet = new DatagramPacket(bufAux, bufAux.length, ip, port);
			socket.send(packet);

			packet = new DatagramPacket(buf, buf.length, ip, port);
			socket.send(packet);

			
		}
		
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		socket.setSoTimeout(0);
	}
	
	/**Deixa o grupo e fecha o socket. 
	 * 
	 * @throws IOException Lan�a exce��o quando h� algum problema ao deixar o grupo, ou fechar a conex�o.
	 * 
	 */
	public void closeConection() throws IOException {
		socket.leaveGroup(group);
		socket.close();
	}
	
	
	

}
