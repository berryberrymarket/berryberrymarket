package berryberrymarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ChatServer {  //여러 클라이언트의 메시지를 브로드캐스트하는 기능을 구현한 코드
    private static final int PORT = 5000;
    private static HashSet<PrintWriter> writers = new HashSet<>();

    public static void start() throws IOException {
        System.out.println("채팅 서버 실행 중...");
        ServerSocket listener = new ServerSocket(PORT);
        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (writers) {
                        if (!writers.contains(out)) {
                            out.println("NAMEACCEPTED");
                            writers.add(out);
                            break;
                        }
                    }
                }

                while (true) {// 브로드캐스트 메시지 루프
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
//                    for (PrintWriter writer : writers) {
//                        writer.println("MESSAGE " + name + ": " + input);
//                        
//                    }
//                }
//            } catch (IOException e) {
//                System.out.println(e);
//            } finally {
//                if (out != null) {
//                    writers.remove(out);
//                }
//                try {
//                    socket.close();
//                } catch (IOException e) {
//                }
//            }
//        }
//    }
//}
                    
                    broadcastMessage("MESSAGE " + name + ": " + input);
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        private void broadcastMessage(String message) {
            synchronized (writers) {
                for (PrintWriter writer : writers) {
                    if (writer != out) {  // 메시지를 보낸 클라이언트 자신을 제외
                        writer.println(message);
                    }
                }
            }
        }
    }
}