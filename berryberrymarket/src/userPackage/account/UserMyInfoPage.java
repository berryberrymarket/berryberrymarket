package userPackage.account;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.*;

public class UserMyInfoPage {
	
	private User user;
	
	public UserMyInfoPage() {
		this.user = GetUser.findUserFromLoginUserList();
	}
	
	// 자기 정보 출력
	public void printInfo() {
		System.out.printf("현재 로그인중인 유저 수: %d\n", BerryBerrymarketApp.loginUserList.size());
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.printf(""
				+ "|아이디\t|%35s\t\t\t\t\t|\n"
				+ "|이름\t|%35s\t\t\t\t\t|\n"
				+ "|닉네임\t|%35s\t\t\t\t\t|\n"
				+ "|주소\t|%35s\t\t\t\t\t|\n"
				+ "|전화번호\t|%35s\t\t\t\t\t|\n"
				+ "|레벨\t|%35d\t\t\t\t\t|\n"
				+ "|거래횟수\t|%35d\t\t\t\t\t|\n", user.getId(), user.getName(), user.getNick(), user.getAddress(), user.getPhoneNumber(), user.getUserLevel(), user.getTransactionsCnt());
		System.out.println("---------------------------------------------------------------------------------------------------");
		System.out.print("(b)돌아가기\t(a)회원정보 수정\t(d)회원 탈퇴\n");
	}
	
}
