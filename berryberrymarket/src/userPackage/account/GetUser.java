package userPackage.account;

import java.util.List;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.User;

public class GetUser {
	
	public static User findUserFromLoginUserList() {
		/*
		 * 유저 인증 파일을 활용하여 유저 객체를 가져오는 메서드입니다.
		 * 자세한 작동 방식은 딸기장터 개발 문서의 '회원 인증 파일의 인증 원리' 항목에 기술되어 있습니다.
		 */
		User findUser = null;
		// 로그인 유저 리스트는 어느 위치에서나 접근할 수 있어야 함.
		List<User> loginUserList = BerryBerrymarketApp.loginUserList;
		for (User logInUser : loginUserList) { 
			try {
				UserAuthentication authObject = UserAuthentication.getAuthObject();
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
}
