package userPackage.account;

import userPackage.model.User;

public class UserMyInfoPage {
	
	private User user;
	
	public UserMyInfoPage() {
		this.user = GetUser.findUserFromLoginUserList();
	}
	
	// 자기 정보 출력
	public void printInfo() {
		//System.out.printf("현재 로그인중인 유저 수: %d\n", BerryBerrymarketApp.loginUserList.size()); // 마이페이지 상단에 나오는데 의미없어서 주석처리
		System.out.println("-----------------------------------------------------------------");
		System.out.printf(""
				+ "|아이디    |%1s\t\t\t\t\t\n"
				+ "|이름      |%1s\t\t\t\t\t\n"
				+ "|닉네임    |%1s\t\t\t\t\t\n"
				+ "|주소      |%1s\t\t\t\t\t\n"
				+ "|전화번호  |%1s\t\t\t\t\t\n"
				+ "|레벨      |%1d\t\t\t\t\t\n"
				+ "|거래횟수  |%1d\t\t\t\t\t\n", user.getId(), user.getName(), user.getNick(), user.getAddress(), user.getPhoneNumber(), user.getUserLevel(), user.getTransactionsCnt());

		System.out.println("-----------------------------------------------------------------");
		System.out.print("(b)돌아가기              (a)회원정보 수정            (d)회원 탈퇴\n");
		System.out.println("=================================================================");
	}
	
}
