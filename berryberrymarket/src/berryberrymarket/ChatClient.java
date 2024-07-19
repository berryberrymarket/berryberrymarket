package berryberrymarket;

import java.io.IOException;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("서버 IP 주소를 입력하세요: ");
        String serverAddress = scanner.nextLine();

        System.out.print("서버 포트를 입력하세요: ");
        int serverPort = scanner.nextInt();
        scanner.nextLine(); // 포트 입력 후 개행 문자 제거

        try {
            SocketManager socketManager = new SocketManager(serverAddress, serverPort);
            System.out.println("서버에 연결되었습니다.");

            Thread readerThread = new Thread(() -> {
                try {
                    String message;
                    while ((message = socketManager.receiveMessage()) != null) {
                        System.out.println("상대방: " + message);
                    }
                } catch (IOException e) {
                    System.err.println("서버와의 연결이 끊겼습니다.");
                }
            });
            readerThread.start();

            while (true) {
                System.out.print("나: ");
                String myMessage = scanner.nextLine();
                socketManager.sendMessage(myMessage);

                if (myMessage.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socketManager.close();

        } catch (IOException e) {
            System.err.println("서버 접속 오류: " + e.getMessage());
        } finally {
            scanner.close(); // 스캐너 닫기
        }
    }
}
