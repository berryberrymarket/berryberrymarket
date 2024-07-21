package userPackage.account;

import java.util.List;
import java.util.Scanner;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.*;

public class MyPage {
	private User user;
	private List<User> logInUserList;
	Scanner sc = new Scanner(System.in);

	private User findUserFromLogInUserList() {
		User findUser = null;
		this.logInUserList = BerryBerrymarketApp.logInUserList;
		for (User logInUser : logInUserList) { 
			try {
				Authentication authObject = Authentication.getAuthObject();
				if (authObject != null) {						
					if (logInUser.getId().equals(authObject.getId())) {
						findUser = logInUser;
					} else {
						System.out.println("인증 파일을 확인할 수 없습니다.");
						throw new Exception("인증 파일이 생성되지 않았습니다.\n인증 파일 생성 로직을 확인하세요.");
					}
				} return findUser;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return findUser;
	}
	
	public MyPage() {
		this.user = this.findUserFromLogInUserList();
	}
	
	public void printInfo() {
		System.out.printf("ID=%s | 비밀번호=%s | 이름=%s | 닉네임=%s | 주소=%s | 전화번호=%s | 레벨=%d | 거래횟수=%d\n", user.getId(), user.getPw(), user.getName(), user.getNick(), user.getAddress(), user.getPhoneNumber(), user.getUserLevel(), user.getTransactionsCnt());
	
	}
	
	public void updateInfo() {
		
		if (this.user != null) {
			// 여기에 수정 로직...
		}
		
	}
}
