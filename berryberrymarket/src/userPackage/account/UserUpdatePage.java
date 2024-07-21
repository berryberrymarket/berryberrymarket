package userPackage.account;


import userPackage.model.User;


public class UserUpdatePage {
	
	private User user;
	
	public UserUpdatePage() {
		this.user = GetUser.findUserFromLoginUserList();
	}
	
	public int userUpdate() {
		if (this.user != null) {
			// 여기에 수정 로직...
			System.out.println("userPackage.account.UserUpdatePage.java: 아직 구현이 안됐습니다.");
		}
		return 4;
	}
}
