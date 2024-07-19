package userPackage.account;

import java.util.List;

import userPackage.model.User;

public class WhoAmI {
	private User self;
	 private List<User> userList;

//	public void setSelf(User self) {
//		this.self = self;
//	}
	
	public User getSelf() {
		return self;
	}
	
	public WhoAmI(User self) {
		this.self = self; // 현재 로그인한 유저가 누구인지 알려줌
	}
	
	//로그아웃
	public void logOut() {
		self = null;
    }
	
	//회원탈퇴
	public void deleteUser(User user) {
	     userList.remove(user);
	}
	
}
