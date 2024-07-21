package userPackage.account;

import java.util.List;
import java.util.Scanner;

import userPackage.model.User;
import berryberrymarket.BerryBerrymarketApp;

public class UserLoginPage {
	
	/*
	 * 로그인:
	 * 1. 유저가 (유효한)정보를 입력하고,
	 * 2. 유저 리스트에서 모든 유저들을 확인하며 올바른 정보인지 읽고,
	 * 3. 유저 객체(의 특정 정보)를 바탕으로 인증 파일을 생성하여 저장하고,
	 * 4. 로그인중인 유저 리스트에 추가하고,
	 * 5. 유저 객체의 Authentication 필드값을 생성한 인증 파일로 할당.
	 */
	
	private String id;
	private String password;
	
	private List<User> userList;
//	public static List<User> logInUserList = new ArrayList<>(); -> 메인으로 옮김.
	
	Scanner sc = new Scanner(System.in);

	public UserLoginPage(String id, String password) {
		this.id = id;
		this.password = password;
		this.userList = BerryBerrymarketApp.ul;
	}

	// 로그인
	public boolean LogIn() {
		for (User user : userList) { 
			try {
				if (user.getId().equals(id) && user.getPw().equals(password)) { // 2단계.
//					logInUserList.add(user); // 로그인 유저 정보 저장.
					UserAuthentication authFile = UserAuthentication.generateAuthObject(id, password); // 3단계, 로그인 인증 관련 파일을 클라이언트측 저장소에 생성.
					BerryBerrymarketApp.loginUserList.add(user); // 4단계.
					if (authFile != null) {						
						user.setAuthFile(authFile); // 5단계.
					} else { // 없는 경우 예외를 만들어서 던짐.
						System.out.println("인증 파일이 존재하지 않습니다.");
						throw new Exception("인증 파일이 생성되지 않았습니다.\n인증 파일 생성 로직을 확인하세요.");
					} return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return false;

	}
	
}
