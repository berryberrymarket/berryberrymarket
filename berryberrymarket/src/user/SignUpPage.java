package user;

import java.util.List;
import java.util.Scanner;


public class SignUpPage {
	private List<User> userList;
	Scanner sc = new Scanner(System.in);

	public SignUpPage() {
		this.userList = userList;
	}

	
	public void SignUp() {
//		//화면
//		System.out.println("----------회원가입----------");
//		System.out.print("1.아이디를 입력하세요: ");
//		String id = sc.nextLine();
//		System.out.print("2.비밀번호를 입력하세요: ");
//		String pw1 = sc.nextLine();
//		System.out.print("3.비밀번호를 한번 더 입력하세요: ");
//		String pw2 = sc.nextLine();
//		System.out.print("4.이름을 입력하세요: ");
//		String name = sc.nextLine();
//		System.out.print("5.닉네임을 입력하세요: ");
//		String nick = sc.nextLine();
//		System.out.print("6.전화번호를 입력하세요: ");
//		String phoneNumber = sc.nextLine();
//		System.out.print("7.주소를 입력하세요: ");
//		String address = sc.nextLine();
//		System.out.println("--------------------------");
//		System.out.println("        (r)가입하기         ");		

		
		
		//아이디 유효성 검사	
		String id;
		while (true) {
			System.out.print("1.가입하실 아이디를 입력하세요: ");
			id = sc.nextLine();
			boolean idExists = false;

			for (User user : userList) {
				if (user.getId().equals(id)) {
					System.out.println("이미 사용중인 아이디 입니다. ");
					idExists = true;
					break;
				}
			}

			if (!idExists) {
				System.out.println("사용 가능한 아이디 입니다.");
				break; // 루프 종료
			}

		}
		//sc.close();

		//비번 유효성 검사
		System.out.print("2.가입하실 비밀번호를 입력하세요: ");
		String pw1 = sc.nextLine();
		while (true) {
			System.out.print("비밀번호를 한번 더 입력하세요: ");
			String pw2 = sc.nextLine();
			if (!pw1.equals(pw2)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			} 
			else {
				System.out.println("비밀번호가 일치합니다.");
				break;
			}
		}

		//sc.close();
		
		//이름 입력
		System.out.print("3.이름을 입력하세요: ");
		String name = sc.nextLine();

		//닉네임 유효성 검사
		String nick;
		while (true) {
			System.out.print("4.닉네임을 입력하세요: ");
			nick = sc.nextLine();
			boolean nickExists = false;

			for (User user : userList) {
				if (user.getNick().equals(nick)) {
					System.out.println("이미 사용중인 닉네임 입니다. ");
					nickExists = true;
					break;
				}
			}

			if (!nickExists) {
				System.out.println("사용 가능한 닉네임 입니다.");
				break; // 루프 종료
			}

		}
		//sc.close();
		
		//전화번호 입력
		System.out.print("5.전화번호를 입력하세요: ");
		String phoneNumber = sc.nextLine();
		
		//주소 입력
		System.out.print("6.주소를 입력하세요: ");
		String address = sc.nextLine();
		
		
		
		User u = new User(id, pw1, name, nick, phoneNumber, address);
		userList.add(u);

		//return 1;
	}

}
