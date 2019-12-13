package model;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import view.MainServerMulticast;

public class UDPConection {
	private DatagramSocket socket;
	private int port;
	private MainServerMulticast masterServe;
	
	public UDPConection(int port) throws SocketException {
		this.port = port;
		socket = new DatagramSocket(port);
		
	}
	
	/** Envia pacote para um determinado usu�rio.
	 * 
	 * @param buf		Mensagem a qual ser� enviada como vetor de bytes.
	 * @param ip		Endere�o de ip da resposta.
	 * @param port		Porta pela qual a mensagem deve ser enviada.
	 * @throws IOException Exce��o lan�ada caso haja problemas ao enviar o pacote.
	 */
	public void sendPacketUser(DatagramSocket socket, byte[] buf, InetAddress ip, int port) {
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
				acceptPacket(socket, bufAux);

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
	}
	
	
	/**Aceita pacotes UDP do grupo multicast. 
	 * 
	 * @param buf 			Vetor de bytes que indica o tamanho do array que vai ser recebido.
	 * @throws IOException	Lan�a exce��o se n�o for poss�vel aceitar o pacote recebido.
	 * @return 				Retorna um vetor de bytes com os dados do pacote recebido.
	 * */
	public static PacketReceived acceptPacket(DatagramSocket socket, byte[] buf) throws IOException {
		DatagramPacket packet = new DatagramPacket(buf, buf.length);
		socket.receive(packet);
		return new PacketReceived(packet.getAddress(), packet.getData(), packet.getPort());
	}
	
	

}
