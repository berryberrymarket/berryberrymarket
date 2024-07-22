package userPackage.account;

import java.util.List;
import userPackage.model.User;
import berryberrymarket.BerryBerrymarketApp;

public class UserLogoutPage {

	//로그아웃
	public static String logOut() {
		/*
		 * 로그아웃:
		 * 1. 현재 로그인 리스트에서 빼고,
		 * 2. 유저 객체의 Authentication = null 로 바꾸고,
		 * 3. 인증 파일 지우기.
		 */
		String uid = null;
		List<User> logInUserList = BerryBerrymarketApp.loginUserList;
		UserAuthentication authObject = UserAuthentication.getAuthObject();
		if (!BerryBerrymarketApp.loginUserList.isEmpty()) { // 로그인 유저리스트에 유저가 한 명이라도 있으면 검사.
			for (int i = 0; i < logInUserList.size(); i ++) {
				uid = authObject.getId();
				if (logInUserList.get(i).getId().equals(uid)) { // 로그인 유저 리스트에 인증 파일과 일치하는 유저가 있으면,
					logInUserList.remove(i); // 로그인 리스트에서 빼고,
					UserAuthentication.deleteAuthFile(); // 인증 파일 지우기.
				}
			}
			System.out.println("로그아웃 되었습니다.");
		} else {
            System.out.println("로그인 상태가 아닙니다.");
        } return uid;
	}
	
}
