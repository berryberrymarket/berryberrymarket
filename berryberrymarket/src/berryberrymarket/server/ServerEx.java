package berryberrymarket.server;


import berryberrymarket.client.ClientDataWrapper;


// 입력
import java.io.InputStream;
import java.io.ObjectInputStream;


// 예외
import java.io.IOException;


// 출력
import java.io.OutputStream;
import java.io.ObjectOutputStream;


// 통신
import java.net.Socket;
import java.net.ServerSocket;
//import java.util.Scanner;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class ServerEx { //// 시현 할 때 상대방 채팅할 사람 킬 server 클래스/
	
	public static boolean start = false;
	private static final int SERVER_PORT = 8000;
	private static final Queue<ClientDataWrapper> messageQueue = new ConcurrentLinkedQueue<>();
	
	public static void main(String[] args) {
	   
		ServerSocket serverSocket = null;
		Socket clientSocket = null;

		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("연결 대기중....");
			clientSocket = serverSocket.accept();
			System.out.println("상대방과 연결되었습니다.");
			System.out.println("bye 입력시 채팅방을 나갈 수 있습니다..");
			start = true;
			while (start) {
                new ClientHandler(clientSocket, messageQueue).start();
                Thread.sleep(500); // 0.5초 간격으로 확인
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("대화 종료.");
		} finally {
			try {
		        serverSocket.close();
		        clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class ClientHandler extends Thread {
    private Socket clientSocket;
    private Queue<ClientDataWrapper> messageQueue;

    public ClientHandler(Socket socket, Queue<ClientDataWrapper> messageQueue) {
        this.clientSocket = socket;
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
    	
    	// 서버로 들어오는 스트림
    	InputStream is = null;
		ObjectInputStream ois = null;
//		InputStreamReader isr = null;
//		BufferedReader br = null;
		
		// 클라이언트 측으로 보내는 스트림
		OutputStream os = null;
		ObjectOutputStream oos = null;
//		OutputStreamWriter osw = null;
//		PrintWriter pw = null;
		
        try {
        
        	// 입력
        	is = clientSocket.getInputStream();
        	ois = new ObjectInputStream(is);
//            	br = new BufferedReader(isr);
        	// 출력
        	os = clientSocket.getOutputStream();
        	oos = new ObjectOutputStream(os);
//            	osw = new OutputStreamWriter(os);
//            	pw = new PrintWriter(osw);

//            	String inMsg = br.readLine();
        	
        	ClientDataWrapper clientDataWrapper = (ClientDataWrapper) ois.readObject();
        	messageQueue.add(clientDataWrapper);
        	if (messageQueue.size() != 0) {
        		// 가장 먼저 입력된 메세지객체
        		ClientDataWrapper msgObject = messageQueue.poll();
        		// msgObject가 존재할 때,
        		if (msgObject != null) {
        			String msg = msgObject.getMessage();
        			// msgObject를 까서 그 안의 메세지가 bye 인 경우...
        			if (msg.equalsIgnoreCase("bye")) {
        				msgObject.setMessage(msg+"\n상대방이 나갔습니다.");
        				msgObject.setEnd(true);
        				oos.writeObject(msgObject);
        				// 입력부 종료
    	            	is.close();
    	            	ois.close();
    	            	// 출력부 종료
    	            	os.flush();
    	            	oos.flush();
    	            	os.close();
    	            	oos.close();
    	            	ServerEx.start = false;
        			}
        			// 일반 대화 메세지인 경우...
        		} else {
        			oos.writeObject(msgObject);
        			os.flush();
	            	oos.flush();
        		}
        	}
			
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}