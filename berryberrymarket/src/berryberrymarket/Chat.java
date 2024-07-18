package berryberrymarket;

public class Chat { // 개별 메시지, 채팅 메시지의 구조를 정의하고 관리
	private String sender;
	private String message;
	private String timestamp;

	public Chat(String sender, String message, String timestamp) {
		this.sender = sender;
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String Aessage) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void printInfo() {
		System.out.println("[" + timestamp + "] " + sender + ": " + message);
	}
}