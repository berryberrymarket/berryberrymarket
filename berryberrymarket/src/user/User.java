package user;

public class User implements UserInterface {
	/*
	 * 유저 거래 횟수에 비례해서 친절 레벨을 올릴지 어떻게 할지 논의 필요
	 */
	private String id;
	private String pw1;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String nick;
	private String address;
	private String phoneNumber;
	private int userLevel = 0; // 유저 친절레벨
	private int transactionsCnt = 0; // 유저 거래 횟수
	private boolean isAdmin = false; // 관리자 여부
	
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

	// 관리자 여부 확인
	public boolean isAdmin() {
		return isAdmin;
	}

//	관리자 모드는 set을 수동으로 (파일 직접 조작) 만들지 논의 필요
//	public void setAdmin(boolean isAdmin) {
//		this.isAdmin = isAdmin;
//	}

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
		System.out.printf("ID=%s | 비밀번호=%s | 닉네임=%s | 주소=%s | 전화번호=%s | 레벨=%d | 거래횟수=%d\n", this.id, this.pw1, this.nick, this.address, this.phoneNumber, this.userLevel, this.transactionsCnt);
	};
	
	@Override
	public void updateUser(String pw, String nick) {
		// 아이디는 바꿀 수 없음
		this.setPw(pw);
		this.setNick(nick);
	};
	
	@Override
	public void deleteUser(String id) {
		/*
		 * 유저 전체 리스트를 가져와주는 메서드로 가져온 다음, -> 얘는 static 같은 걸로 만들어야 될 것 같은데
		 * 전체 리스트를 stream 으로 만들어서,
		 * stream filter 메서드로 여기 함수에서 받아온 String id 와 일치하는 유저 가져온다음 삭제
		 * 그렇게 하고 삭제된 전체 유저 리스트를 유저 엑셀 파일에 반영하기
		 */
	};
	
}
