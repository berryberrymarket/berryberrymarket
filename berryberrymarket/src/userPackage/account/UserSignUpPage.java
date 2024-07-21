package userPackage.account;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.User;
import userPackage.model.UserListManager;

public class UserSignUpPage {
	
	/*
	 * 가입:
	 * 1. 유저가 (유효한)정보를 입력하고,
	 * 2. 유저 파일을 만들어서 저장.
	 */
	
//	화면
//	System.out.println("----------회원가입----------");
//	System.out.print("1.아이디를 입력하세요: ");
//	String id = sc.nextLine();
//	System.out.print("2.비밀번호를 입력하세요: ");
//	String pw1 = sc.nextLine();
//	System.out.print("3.비밀번호를 한번 더 입력하세요: ");
//	String pw2 = sc.nextLine();
//	System.out.print("4.이름을 입력하세요: ");
//	String name = sc.nextLine();
//	System.out.print("5.닉네임을 입력하세요: ");
//	String nick = sc.nextLine();
//	System.out.print("6.전화번호를 입력하세요: ");
//	String phoneNumber = sc.nextLine();
//	System.out.print("7.주소를 입력하세요: ");
//	String address = sc.nextLine();
//	System.out.println("--------------------------");
//	System.out.println("        (r)가입하기         ");	
	private List<User> userList;
	Scanner sc = new Scanner(System.in);

	public UserSignUpPage() {
		this.userList = BerryBerrymarketApp.ul;
	}

	public void SignUp() {
		// 아이디 유효성 검사
		String id;
		while (true) {
			System.out.print("가입하실 아이디를 입력하세요: ");
			id = sc.nextLine();
			if (!isValidId(id)) {
				System.out.println("아이디는 5~20자의 영문 소문자, 숫자, '-', '_' 만 사용 가능합니다.");
				continue;
			}
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

		// 비번 유효성 검사
		String pw1;
		while (true) {
			System.out.print("가입하실 비밀번호를 입력하세요: ");
			pw1 = sc.nextLine();
			if (!isValidPassword(pw1)) {
				System.out.println("비밀번호는 8~16자의 영문 대/소문자, 숫자, 특수문자만 사용 가능합니다.");
			} else
				break;
		}

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

		// 이름 입력
		System.out.print("이름을 입력하세요: ");
		String name = sc.nextLine();

		// 닉네임 유효성 검사
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

		// 전화번호 입력
		String phoneNumber;
		while (true) {
			System.out.print("전화번호를 입력하세요: ");
			phoneNumber = sc.nextLine();
			if (!isValidPhoneNumber(phoneNumber)) {
				System.out.println("형식이 올바르지 않습니다.");
			} else {
				break;
			}
		}

		// 주소 입력	
		 String address;
	     while (true) {
	          System.out.print("주소를 입력하세요: ");
	          address = sc.nextLine();
	          if (!isValidAddress(address)) {
	              System.out.println("형식이 올바르지 않습니다.");
	          } else {
	              break;
	          }
	     }

	     UserListManager ulm = UserListManager.getUserListMagener();
		ulm.addUserToUserList(id, pw1, name, nick, phoneNumber, address);

		// 코드 전체에서 콘솔을 통해 명령을 전달하고 있기 때문에 스캐너를 닫으면 에러납니다.
//		sc.close();

//		return 1;
	}

	public boolean isValidId(String id) {
		if (id.equals("test")) {
			return true;
		}
		return id.matches("[a-z0-9_-]{5,20}");
	}

	public boolean isValidPassword(String password) {
		if (password.equals("test")) {
			return true;
		}
	    String specialCharacters = "!\"#$%&'()*+,-./:;?@[\\]^_`{|}~";
	    String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[" + Pattern.quote(specialCharacters) + "])[a-zA-Z\\d" + Pattern.quote(specialCharacters) + "]{8,16}$";
	    return password.matches(regex);
	}

	public boolean isValidPhoneNumber(String phoneNumber) {
		if (phoneNumber.equals("test")) {
			return true;
		}
		return phoneNumber.matches("^\\d{3}-\\d{4}-\\d{4}$");
	}
	
	public boolean isValidAddress(String address) {
		if (address.equals("test")) {
			return true;
		}
	    return address.contains("시") && address.contains("구") && address.contains("동");
	}
	
}
