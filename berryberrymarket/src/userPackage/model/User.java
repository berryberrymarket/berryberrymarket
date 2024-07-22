package userPackage.model;


import java.io.Serializable;
import userPackage.account.UserAuthentication;


public class User implements UserInterface, Serializable {
	/*
	 * 유저 거래 횟수에 비례해서 친절 레벨을 올릴지 어떻게 할지 논의 필요
	 */
	private static final long serialVersionUID = 742406210225915812L;

	private UserAuthentication authentication;
	
	private String id;
	private String pw1;
	private String name;
	private String nick;
	private String address;
	private String phoneNumber;
	private int userLevel = 0; // 유저 친절점수
	private int transactionsCnt = 0; // 유저 거래 횟수
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
 	
	public UserAuthentication getAuthFile() {
		return authentication;
	}
	
	public void setAuthFile(UserAuthentication authentication) {
		this.authentication = authentication;
	}
	
	public String getId() {
		return id;
	}
	
//	아이디는 바꿀 수 없음
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getPw() {
		return pw1;
	}

	public void setPw(String pw1) {
		this.pw1 = pw1;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public int getTransactionsCnt() {
		return transactionsCnt;
	}

	public void setTransactionsCnt(int transactionsCnt) {
		this.transactionsCnt = transactionsCnt;
	}

	public User(String id, String pw1, String name, String nick, String phoneNumber, String address) {
		this.id = id;
		this.pw1 = pw1;
		this.name = name;
		this.nick = nick;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	
	// printManager에서 사용
	@Override
	public void printInfo() {
		System.out.printf("ID=%s | 비밀번호=%s | 이름=%s | 닉네임=%s | 주소=%s | 전화번호=%s | 레벨=%d | 거래횟수=%d\n", this.id, this.pw1, this.name, this.nick, this.address, this.phoneNumber, this.userLevel, this.transactionsCnt);
	};
	
}
