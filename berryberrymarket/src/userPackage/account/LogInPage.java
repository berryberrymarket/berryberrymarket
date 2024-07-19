package userPackage.account;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import userPackage.model.User;
import berryberrymarket.BerryBerrymarketApp;

public class LogInPage {
	private String id;
	private String password;
	
	private List<User> userList;
//	public static List<User> logInUserList = new ArrayList<>(); -> 메인으로 옮김.
	
	Scanner sc = new Scanner(System.in);

	public LogInPage(String id, String password) {
		this.id = id;
		this.password = password;
		this.userList = BerryBerrymarketApp.ul;
	}

	// 로그인
	public boolean LogIn() {
		for (User user : userList) {
			try {
				if (user.getId().equals(id) && user.getPw().equals(password)) {
//					logInUserList.add(user); // 로그인 유저 정보 저장.
					BerryBerrymarketApp.logInUserList.add(id); // id 도 마찬가지로 고유값이므로 굳이 user 를 저장할 필요 없어보임.
					Authentication authFile = Authentication.generateAuthFile(id, password); // 로그인 인증 관련 파일을 클라이언트측 저장소에 생성.
					if (authFile != null) {						
						user.setAuthFile(authFile);
					} else {
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
