package berryberrymarket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

// 서버의 주요 로직을 처리하는 클래스, 클라이언트가 SocketManager를 사용하여 서버와 연결하고, 채팅 메시지를 주고받습니다.
public class ChatServer {
    private static final int PORT = 59781;
    private static Set<SocketManager> clients = new HashSet<>(); // 연결된 클라이언트를 관리하는 Set

    public static void main(String[] args) {
        System.out.println("채팅 서버 시작");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 클라이언트의 접속을 기다림
                Socket clientSocket = serverSocket.accept();
                System.out.println("새로운 클라이언트가 접속: " + clientSocket);

                // 새로운 클라이언트를 관리할 SocketManager 생성
                SocketManager clientManager = new SocketManager(clientSocket);
                clients.add(clientManager); // 클라이언트 관리 Set에 추가

                // 각 클라이언트를 위한 핸들러 스레드 시작
                Thread handlerThread = new Thread(new ClientHandler(clientManager));
                handlerThread.start();
            }
        } catch (IOException e) {
            System.err.println("서버 오류: " + e.getMessage());
        }
    }

    // 각 클라이언트를 처리하는 핸들러 스레드
    private static class ClientHandler implements Runnable {
        private SocketManager clientManager;

        public ClientHandler(SocketManager clientManager) {
            this.clientManager = clientManager;
        }

        @Override
        public void run() {
            try {
                String message;
                // 클라이언트로부터 메시지를 받아서 처리
                while ((message = clientManager.receiveMessage()) != null) {
                    System.out.println("받은 메시지: " + message);
                    broadcast(message); // 모든 클라이언트에게 메시지 브로드캐스트
                }
            } catch (IOException e) {
                System.err.println("클라이언트 오류: " + e.getMessage());
            } finally {
                try {
                    clientManager.close(); // 클라이언트 연결 닫기
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clients.remove(clientManager); // 클라이언트 관리 Set에서 제거
            }
        }

        // 모든 클라이언트에게 메시지를 전송하는 메서드
        private void broadcast(String message) {
            for (SocketManager client : clients) {
                client.sendMessage(message);
            }
        }
    }
}
