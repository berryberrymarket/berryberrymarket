package berryberrymarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


// 소켓 통신을 관리하는 클래스입니다. 클라이언트와 서버 간의 연결을 설정하고, 메시지를 주고받는 기능을 담당합니다.
public class SocketManager { 
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SocketManager(String serverAddress, int port) throws IOException {
        socket = new Socket(serverAddress, port);
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public SocketManager(Socket clientSocket) throws IOException {
        socket = clientSocket;
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

	public void sendMessage(String message) {
        writer.println(message);
    }

    public String receiveMessage() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
