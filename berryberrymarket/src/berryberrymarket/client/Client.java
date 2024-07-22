package berryberrymarket.client;


import java.net.Socket;


import java.io.ObjectInputStream;
import java.io.InputStream;


import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import userPackage.account.GetUser;
import userPackage.model.User;


import java.io.IOException;


public class Client {

	
	private static final String SERVER_IP = "192.168.240.57";
	private static final int SERVER_PORT = 8000;
	private static User user = GetUser.findUserFromLoginUserList();
	
	public static boolean end = false;
	public static void startChat() {
		
		try {
			Socket socket = null;
			
			// 입력
			InputStream is = null;
			
		    Scanner sc = new Scanner(System.in);
	    	socket = new Socket(SERVER_IP, SERVER_PORT);
			
	        while (true) {
	        	
	        	System.out.println("(O)나가기");
	        	System.out.print("보내기 >> ");
	        	String clientMessage = sc.nextLine();
	        	ClientDataWrapper data = new ClientDataWrapper(user, clientMessage);
	        	is = socket.getInputStream();
	        	
	        	if (is.read() != -1) {
	        		new ServerHandler(is, socket, data).start();
	        	}
	        	
	        	Thread.sleep(500); // 0.5초 간격으로 확인
	        	// 내가 대화 종료.
	            if (clientMessage.equalsIgnoreCase("O")) {
	            	System.out.println(clientMessage);
	                break;
	            }
	            // 상대가 대화 종료.
	            if (end) {
	            	System.out.println("상대가 떠났습니다.");
	                break;
	            } // 아니면 대화를 지속합니다.
	        }
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    }
}

class ServerHandler extends Thread {
	
	private Socket serverSocket;
	private ClientDataWrapper data;
	private InputStream is;

    public ServerHandler(InputStream is, Socket socket, ClientDataWrapper data) {
        this.serverSocket = socket;
        this.data = data;
        this.is = is;
    }
    
	@Override
	public void run() {
		
		// 입력
		ObjectInputStream ois = null;
		// 출력
		OutputStream os = null;
		ObjectOutputStream oos = null;
	    
	    try {
	    	// 서버에서 오는 데이터
	    	ois = new ObjectInputStream(is);
	    	ClientDataWrapper responseObject = (ClientDataWrapper) ois.readObject();
	    	String responseNick = responseObject.getUserNick();
	    	String responseMsg = responseObject.getMessage();
	    	boolean responseEnd = responseObject.isEnd();
	    	System.out.printf("[%s] %s\n", responseNick, responseMsg);
	    	System.out.println();
	    	os = serverSocket.getOutputStream();
			oos = new ObjectOutputStream(os);
			oos.writeObject(data);
	    	// 상대방이 대화를 종료했다는 메세지가 온 경우...
	    	if (responseMsg.contains("상대방이 나갔습니다.")) {
	    		// 그게 진짜라면 나도 종료.
	    		if (responseEnd) {
	            	// 입력부 종료
	            	is.close();
	            	ois.close();
	            	// 출력부 종료
	            	os.flush();
	            	oos.flush();
	            	os.close();
	            	oos.close();
	            	Client.end = true;
	    		}
	    	}
	    } catch (IOException | ClassNotFoundException e) {
	    	e.printStackTrace();
	    } finally {
    		try {
    			if (is != null) {
    				is.close();
    			}
    			if (ois != null) {
    				ois.close();
    			}
    			if (os != null) {
    	            os.flush();
    	            os.close();
    			}
    			if (oos != null) {
    				oos.flush();
    	            oos.close();
    			}
    			serverSocket.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
    	}
		
	}
}
