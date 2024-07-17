package user;

import java.util.List;
import java.util.Scanner;
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
		this.userList = userList;
	}

	
	
	
	public void SignUp() throws Exception {
		UserList.getUser();
		//아이디 유효성 검사
		String id;
		
		while (true) {
			System.out.print("가입하실 아이디를 입력하세요: ");
			id = sc.nextLine();
			boolean idExists = false;

//			for (User user : userList) {
//				if (user.getId().equals(id)) {
//					System.out.println("이미 사용중인 아이디 입니다 ");
//					idExists = true;
//					break;
//				}
//			}
			
			// 여기 아래부터 test 조각
			File[] fileArray = UserList.path.listFiles();
			List<File> fileList = Arrays.asList(fileArray);
			for (File f: fileList) {
				FileInputStream fis = new FileInputStream(UserList.path);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				User user = (User) ois.readObject();
				
				ois.close();
				fis.close();
				
				if (user.getId().equals(id)) {
//					System.out.println("이미 사용중인 아이디 입니다 ");
					idExists = true;
					break;
				}
				
			}
			// 여기까지 테스트 조각

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
			} else
				System.out.println("비밀번호가 일치합니다.");
			break;
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

//			for (User user : userList) {
//				if (user.getNick().equals(nick)) {
//					System.out.println("이미 사용중인 닉네임 입니다 ");
//					nickExists = true;
//					break;
//				}
//			}

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
		
		System.out.println(123);
//		sc.close();
		System.out.println(456);
		
		


		
		
		
		FileOutputStream fos = new FileOutputStream(UserList.path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		User u = new User(id, pw1, name, nick, phoneNumber, address);
		System.out.println(11111111);
		oos.writeObject(u);
		
		System.out.println(222222);
		oos.flush();
		System.out.println(333333);
		oos.close();
		System.out.println(444444);
		fos.close();
		System.out.println(555555);
		
		
		
		
		
		
//		userList.add(u);

		//return 1;
	}

}
