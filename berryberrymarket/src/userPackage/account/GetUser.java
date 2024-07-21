package userPackage.account;

import java.util.List;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.User;

public class GetUser {
	
	public static User findUserFromLoginUserList() {
		User findUser = null;
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
