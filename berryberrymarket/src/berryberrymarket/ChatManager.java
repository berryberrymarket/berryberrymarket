package berryberrymarket;

import java.util.HashMap;
import java.util.Map;

public class ChatManager { //여러 ChatRoom을 관리하는 역할
	
	private Map<String, ChatRoom> chatRoomMap = new HashMap<>();

	    public void createChatRoom(String roomName) {
	        if (chatRoomMap.containsKey(roomName)) {
	            System.out.println("이미 존재하는 채팅방입니다.");
	        } else {
	            chatRoomMap.put(roomName, new ChatRoom(roomName));
	            System.out.println("채팅방 '" + roomName + "'이 생성되었습니다.");
	        }
	    }

	    public void sendMessage(String roomName, Chat chat) {
	        ChatRoom chatRoom = chatRoomMap.get(roomName);
	        if (chatRoom != null) {
	            chatRoom.addChat(chat);
	            System.out.println("메시지가 채팅방 '" + roomName + "'에 전송되었습니다.");
	        } else {
	        	
	            System.out.println("채팅방 '" + roomName + "'이 존재하지 않습니다.");
	        }
	    }

	    public void printChatHistory(String roomName) {
	        ChatRoom chatRoom = chatRoomMap.get(roomName);
	        if (chatRoom != null) {
	            chatRoom.printChatHistory();
	        } else {
	            System.out.println("채팅방 '" + roomName + "'이 존재하지 않습니다.");
	        }
	    }
	}