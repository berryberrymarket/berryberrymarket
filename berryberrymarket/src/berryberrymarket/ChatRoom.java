package berryberrymarket;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom { // 여러 Chat 객체를 포함
   
	private String roomName;
    private List<Chat> chatList = new ArrayList<>();

    public ChatRoom(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void addChat(Chat chat) {
        chatList.add(chat);
    }

    public void printChatHistory() {
        if (chatList.isEmpty()) {
            System.out.println("채팅 내역이 없습니다.");
        } else {
            System.out.println("채팅방 '" + roomName + "'의 채팅 내역:");
            for (Chat chat : chatList) {
                chat.printInfo();
            }
        }
    }
}