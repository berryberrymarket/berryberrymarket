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


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class ServerEx { //// 시현 할 때 상대방 채팅할 사람 킬 server 클래스/
	
	private static final int SERVER_PORT = 8000;
	private static final Queue<ClientDataWrapper> messageQueue = new ConcurrentLinkedQueue<>();
	public static boolean start = false;
	
	public static ServerSocket serverSocket = null;
	public static Socket socket = null;
	public static InputStream is;
	public static ObjectInputStream ois;
	public static OutputStream os;
	public static ObjectOutputStream oos;
	
	public static void main(String[] args) {
	   
		try {
			
			serverSocket = new ServerSocket(SERVER_PORT);
			System.out.println("연결 대기중....");
			socket = serverSocket.accept();
			System.out.println("상대방과 연결되었습니다.");
			// 입력 스트림
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			// 출력 스트림
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			
			Thread.inputThread().start();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ServerEx.serverSocket != null) {					
					ServerEx.serverSocket.close();
				}
				if (ServerEx.socket != null) {					
					ServerEx.socket.close();
				}
				if (ServerEx.is != null) {						
					ServerEx.is.close();
				}
				if (ServerEx.ois != null) {
					ServerEx.ois.close();
				}
				if (ServerEx.os != null) {
					ServerEx.os.flush();
					ServerEx.os.close();
				}
				if (ServerEx.oos != null) {
					ServerEx.oos.flush();
					ServerEx.oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

class inputThread extends Thread {
	private Queue<ClientDataWrapper> sharedMessageQueue;
	
	// inputStream을 읽어서 메세지큐에 저장하는 쓰레드.
	public inputThread(Queue<ClientDataWrapper> sharedMessageQueue) {
		this.sharedMessageQueue = sharedMessageQueue;
	}
	
	@Override
	public void run() {
		while (ServerEx.start) {
			try {
				ClientDataWrapper clientDataWrapper = (ClientDataWrapper) ServerEx.ois.readObject();
				sharedMessageQueue.add(clientDataWrapper);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ServerEx.serverSocket != null) {					
						ServerEx.serverSocket.close();
					}
					if (ServerEx.socket != null) {					
						ServerEx.socket.close();
					}
					if (ServerEx.is != null) {						
						ServerEx.is.close();
					}
					if (ServerEx.ois != null) {
						ServerEx.ois.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}

class outputThread extends Thread {
	private Queue<ClientDataWrapper> sharedMessageQueue;
	
	// 메세지큐에 있던 데이터들을 outputStream으로 내보내는 쓰레드.
	public outputThread(Queue<ClientDataWrapper> sharedMessageQueue) {
		this.sharedMessageQueue = sharedMessageQueue;
	}
	
	@Override
	public void run() {
		while (ServerEx.start) {
			try {
				if (sharedMessageQueue.size() != 0) {
	        		// 가장 먼저 입력된 메세지객체
	        		ClientDataWrapper msgObject = sharedMessageQueue.poll();
	        		// msgObject가 존재할 때,
	        		if (msgObject != null) {
	        			String msg = msgObject.getMessage();
	        			// msgObject를 까서 그 안의 메세지가 bye 인 경우...
	        			if (msg.equalsIgnoreCase("bye")) {
	        				msgObject.setMessage(msg+"\n상대방이 나갔습니다.");
	        				msgObject.setEnd(true);
	        				ServerEx.oos.writeObject(msgObject);
	    	            	// 출력부 종료
	        				ServerEx.oos.flush();
	        				ServerEx.oos.close();
	        			}
	        			// 일반 대화 메세지인 경우...
	        		} else {
	        			ServerEx.oos.writeObject(msgObject);
	        			ServerEx.oos.flush();
	        		}
	        	}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (ServerEx.serverSocket != null) {					
						ServerEx.serverSocket.close();
					}
					if (ServerEx.socket != null) {					
						ServerEx.socket.close();
					}
					if (ServerEx.os != null) {
						ServerEx.os.flush();
						ServerEx.os.close();
					}
					if (ServerEx.oos != null) {
						ServerEx.oos.flush();
						ServerEx.oos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
