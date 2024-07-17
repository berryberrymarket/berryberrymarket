package user;

public class WhoIAm {
	private String self;
	public String getSelf() {
		return self;
	}
	public WhoIAm(String self) {
		this.self = self; // 현재 로그인한 사람이 누구인지 알려줌
	}
}
