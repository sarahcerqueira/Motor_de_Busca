package ws;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.Session;

@ServerEndpoint(value = "/ws")
public class WebSocketAnnotation {
    private static final AtomicInteger sequence = new AtomicInteger(1);
    private final String username;
    private Session session;

    public WebSocketAnnotation() {
    	System.out.println("Hola");
        username = "User" + sequence.getAndIncrement();
    }

    @OnOpen
    public void start(Session session) {
    	System.out.println("Hola2");
        this.session = session;
        String message = "*" + username + "* connected.";
        sendMessage(message);
    }

    @OnClose
    public void end() {

    	System.out.println("Hola3");
    	// clean up once the WebSocket connection is closed
    }

    @OnMessage
    public void receiveMessage(String message) {
    	System.out.println("Hola4");
		// one should never trust the client, and sensitive HTML
        // characters should be replaced with &lt; &gt; &quot; &amp;
    	String upperCaseMessage = message.toUpperCase();
    	sendMessage("[" + username + "] " + upperCaseMessage);
    }
    
    @OnError
    public void handleError(Throwable t) {
    	System.out.println("Hola5");
    	t.printStackTrace();
    }

    private void sendMessage(String text) {
    	System.out.println("Hola6");
    	// uses *this* object's session to call sendText()
    	try {
			this.session.getBasicRemote().sendText(text);
		} catch (IOException e) {
			// clean up once the WebSocket connection is closed
			try {
				this.session.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }
}
