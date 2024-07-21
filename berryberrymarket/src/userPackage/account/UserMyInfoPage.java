package userPackage.account;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.*;

public class UserMyInfoPage {
	
	private User user;
	
	public UserMyInfoPage() {
		this.user = GetUser.findUserFromLoginUserList();
	}
	
	public void printInfo() {
		System.out.printf("현재 로그인중인 유저 수: %d\n", BerryBerrymarketApp.loginUserList.size());
		System.out.printf("\t아이디\t%-20s\n\t비밀번호%-20s\n\t이름%-20s\n\t닉네임%-20s\n\t주소%-20s\n\t전화번호%-20s\n\t레벨%-20d\n\t거래횟수%-20d\n", user.getId(), user.getPw(), user.getName(), user.getNick(), user.getAddress(), user.getPhoneNumber(), user.getUserLevel(), user.getTransactionsCnt());
		System.out.print("(b)돌아가기\t\t\t(a)회원정보 수정\t\t\t(d)회원 탈퇴\n");
	}
	
}
