package user.account;

import user.model.User;

public class WhoIAm {
	private User self;
	public User getSelf() {
		return self;
	}
	public WhoIAm(User self) {
		this.self = self; // 현재 로그인한 유저가 누구인지 알려줌
	}
}
