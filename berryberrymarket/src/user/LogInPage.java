package user;

import java.util.List;
import java.util.Scanner;

public class LogInPage {
	private List<User> userList;
	Scanner sc = new Scanner(System.in);

	public LogInPage() {
		this.userList = userList;
	}

	public void LogIn() {
		while (true) {
			System.out.print("아이디를 입력하세요: ");
			String id = sc.nextLine();
			System.out.print("비밀번호를 입력하세요: ");
			String pw = sc.nextLine();
			boolean idPwExists = false;

			for (User user : userList) {
				if (user.getId().equals(id) && user.getPw().equals(pw)) {
					idPwExists = true;
					break;
				}
			}
			

	        if (idPwExists) {
	            //System.out.println("로그인 성공!");
	            break; // 로그인 성공 시 무한 루프 종료
	        } else {
	            System.out.println("아이디 혹은 비밀번호가 틀렸습니다."); // 로그인 실패 시 메시지 출력
	        }
		}
		// 다음페이지 넘어가는 로직
	}
}
