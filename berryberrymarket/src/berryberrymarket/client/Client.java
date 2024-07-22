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


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Client {

	
	private static final String SERVER_IP = "192.168.240.57";
	private static final int SERVER_PORT = 8000;
	private static User user = GetUser.findUserFromLoginUserList();
	
	public static boolean end = false;
	public static void startChat() {
		
		try {
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);
			
			// 입력
			InputStream is = socket.getInputStream();
			
			// 출력
			OutputStream os = socket.getOutputStream();
	    	
	    	// 스케줄러 생성 (0.5초 간격으로 계속 확인할 것임).
	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
	        // 데이터 받는 것은 계속 실행.
	        OutputHandler oh = new OutputHandler(os, socket, user);
	        InputHandler ih = new InputHandler(is, socket);

	        
	        // 0.5초 간격으로 run 메서드를 실행.
	        scheduler.scheduleAtFixedRate(ih, 0, 500, TimeUnit.MILLISECONDS);
	        scheduler.scheduleAtFixedRate(oh, 0, 500, TimeUnit.MILLISECONDS);
	    	
	    	
	        if (is.read() == -1 | end) {
        		scheduler.shutdown();
        	}
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}

// 서버로부터 데이터 받는애.
class InputHandler extends Thread {
	
	private Socket serverSocket;
	private InputStream is;

    public InputHandler(InputStream is, Socket socket) {
        this.serverSocket = socket;
        this.is = is;
    }
    
	@Override
	public void run() {
		
		// 입력
		ObjectInputStream ois = null;
	    
	    try {
	    	// 서버에서 오는 데이터
	    	ois = new ObjectInputStream(is);
	    	ClientDataWrapper responseObject = (ClientDataWrapper) ois.readObject();
	    	String responseNick = responseObject.getUserNick();
	    	String responseMsg = responseObject.getMessage();
	    	boolean responseEnd = responseObject.isEnd();
	    	System.out.printf("[%s] %s\n", responseNick, responseMsg);
	    	System.out.println();
	    	// 상대방이 대화를 종료했다는 메세지가 온 경우...
	    	if (responseMsg.contains("상대방이 나갔습니다.")) {
	    		// 그게 진짜라면 나도 종료.
	    		if (responseEnd) {
	    			System.out.println("상대가 떠났습니다.");
	            	// 입력부 종료
	            	is.close();
	            	ois.close();
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
    			serverSocket.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
    	}
		
	}
}

//서버로 데이터 주는애.
class OutputHandler extends Thread {
	
	private User user;
	private OutputStream os;
	private Socket serverSocket;

    public OutputHandler(OutputStream os, Socket socket, User user) {
        this.serverSocket = socket;
        this.user = user;
        this.os = os;
    }
    
	@Override
	public void run() {
		
		Scanner sc = null;
		
		// 출력
		ObjectOutputStream oos = null;
	    
	    try {
	    	sc = new Scanner(System.in);
	    	System.out.println("(O)나가기");
        	System.out.print("보내기 >> ");
        	String clientMessage = sc.nextLine();
        	ClientDataWrapper data = new ClientDataWrapper(user, clientMessage);
	    	System.out.printf("[%s] %s\n", user.getNick(), clientMessage);
	    	System.out.println();
			oos = new ObjectOutputStream(os);
			oos.writeObject(data);
			// 내가 대화 종료.
            if (clientMessage.equalsIgnoreCase("O")) {
            	System.out.println(clientMessage);
            	Client.end = true;
            }
	    	// 상대방이 대화를 종료했다는 메세지가 온 경우...
	    } catch (IOException e) {
	    	e.printStackTrace();
	    } finally {
    		try {
    			if (os != null) {
    	            os.flush();
    	            os.close();
    			}
    			if (oos != null) {
    				oos.flush();
    	            oos.close();
    			}
    			sc.close();
    			serverSocket.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
    	}
		
	}
}
