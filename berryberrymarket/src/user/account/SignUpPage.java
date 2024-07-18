package user.account;

import java.util.List;
import java.util.Scanner;

import berryberrymarket.BerryBerrymarketApp;
import user.model.User;
import user.model.UserListManager;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class SignUpPage {
	private List<User> userList;
	Scanner sc = new Scanner(System.in);

	public SignUpPage() {
		this.userList = BerryBerrymarketApp.ul;
	}
	
	public void SignUp() {
		//아이디 유효성 검사
		String id;
		while (true) {
			System.out.print("가입하실 아이디를 입력하세요: ");
			id = sc.nextLine();
			boolean idExists = false;

			if (!userList.isEmpty()) {				
				for (User user : userList) {
					if (user.getId().equals(id)) {
						System.out.println("이미 사용중인 아이디 입니다 ");
						idExists = true;
						break;
					}
				}
			}

			if (!idExists) {
				// System.out.println("사용 가능한 아이디 입니다.");
				break; // 루프 종료
			}
			
		}

		//비번 유효성 검사
		System.out.print("가입하실 비밀번호를 입력하세요: ");
		String pw1 = sc.nextLine();
		while (true) {
			System.out.print("비밀번호를 한번 더 입력하세요: ");
			String pw2 = sc.nextLine();
			if (!pw1.equals(pw2)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			} else {
//				System.out.println("비밀번호가 일치합니다.");
				break;
			}
		}
		
		//이름 입력
		System.out.print("이름을 입력하세요: ");
		String name = sc.nextLine();

		//닉네임 유효성 검사
		String nick;
		while (true) {
			System.out.print("닉네임을 입력하세요: ");
			nick = sc.nextLine();
			boolean nickExists = false;

			if (!userList.isEmpty()) {
				for (User user : userList) {
					if (user.getNick().equals(nick)) {
						System.out.println("이미 사용중인 닉네임 입니다 ");
						nickExists = true;
						break;
					}
				}
			}

			if (!nickExists) {
				// System.out.println("사용 가능한 아이디 입니다.");
				break; // 루프 종료
			}
		}
		
		//전화번호 입력
		System.out.print("전화번호를 입력하세요: ");
		String phoneNumber = sc.nextLine();
		
		//주소 입력
		System.out.print("주소를 입력하세요: ");
		String address = sc.nextLine();
		
		UserListManager ulm = UserListManager.getUserListMagener();
		ulm.addUserToUserList(id, pw1, name, nick, phoneNumber, address);
		
		// 코드 전체에서 콘솔을 통해 명령을 전달하고 있기 때문에 스캐너를 닫으면 에러납니다.
//		sc.close();

//		return 1;
	}

}
