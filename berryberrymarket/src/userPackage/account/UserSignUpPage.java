package userPackage.account;

import java.util.List;
import java.util.Scanner;

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

	public void signUp() {
		// 아이디 유효성 검사
		String id;
		// 조건 만족 할때까지 무한반복
		while (true) {
			System.out.println("(B)뒤로가기");
			System.out.print("1.아이디를 입력하세요: ");
			id = sc.nextLine();
			//이전페이지로 이동
			if(id.equals("B")||id.equals("b"))
				return;
			//입력받은 아이디가 제한 조건에 부합하는지 검사
			if (!ValidationUtils.isValidId(id)) {
				System.out.println("아이디는 5~20자의 영문 소문자, 숫자, '-', '_' 만 사용 가능합니다.");
				//부합하면 출력하지 않고 다음으로 넘어감
				continue;
			}
			//false로 초기화
			boolean idExists = false;

			// userList가 비어있지 않으면 코드 실행
			if (!userList.isEmpty()) {
				// userList 돌면서 기존의 id와 일치하는지 확인
				for (User user : userList) {
					if (user.getId().equals(id)) {
						System.out.println("이미 사용중인 아이디 입니다 ");
						//아이디 일치하면 true 반환
						idExists = true;
						break; // for문 탈출
					}
				}
			}
			
			// 일치하는 아이디가 없으면 
			if (!idExists) {
				System.out.println("사용 가능한 아이디 입니다.");
				break; // while문 탈출
			}

		}

		// 비번 유효성 검사
		String pw1;
		while (true) {
			System.out.print("2.비밀번호를 입력하세요: ");
			pw1 = sc.nextLine();
			if(pw1.equals("B")||pw1.equals("b"))
				return;
			if (!ValidationUtils.isValidPassword(pw1)) {
				System.out.println("비밀번호는 8~16자의 영문 대(소)문자+숫자+특수문자 조합으로 입력해주세요.");
			} else
				break;
		}

		while (true) {
			System.out.print("비밀번호를 한번 더 입력하세요: ");
			String pw2 = sc.nextLine();
			if(pw2.equals("B")||pw2.equals("b"))
				return;
			if (!pw1.equals(pw2)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
			} 
			// 일치하면 출력 후 while문 탈출
			else {
				System.out.println("비밀번호가 일치합니다.");
				break;
			}
		}

		// 이름 입력
		System.out.print("3.이름을 입력하세요: ");
		String name = sc.nextLine();
		if(name.equals("B")||name.equals("b"))
			return;

		// 닉네임 유효성 검사
		String nick;
		while (true) {
			System.out.print("4.닉네임을 입력하세요: ");
			nick = sc.nextLine();
			if(nick.equals("B")||nick.equals("b"))
				return;
			if (!ValidationUtils.isValidNick(nick)) {
				System.out.println("닉네임은 6글자 이내로 입력해주세요.");
				continue;
			}
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
			System.out.print("5.전화번호를 입력하세요: ");
			phoneNumber = sc.nextLine();
			if(phoneNumber.equals("B")||phoneNumber.equals("b"))
				return;
			if (!ValidationUtils.isValidPhoneNumber(phoneNumber)) {
				System.out.println("형식이 올바르지 않습니다.");
			} else {
				break;
			}
		}

		// 주소 입력	
		 String address;
	   //  while (true) {
	          System.out.print("6.주소를 입력하세요: ");
	          address = sc.nextLine();
	          if(address.equals("B")||address.equals("b"))
	        	  return;
        	  // 주소는 유효성 검사 없애기로 하여 주석처리
//	          if (!isValidAddress(address)) {
//	              System.out.println("형식이 올바르지 않습니다.");
//	          } else {
//	              break;
//	          }
	    // }

	    UserListManager ulm = UserListManager.getUserListMagener();
		// addUserToUserList에 유저 정보 추가
		ulm.addUserToUserList(id, pw1, name, nick, phoneNumber, address);

		// 코드 전체에서 콘솔을 통해 명령을 전달하고 있기 때문에 스캐너를 닫으면 에러납니다.
//		sc.close();

//		return 1;
	}

//	public boolean isValidId(String id) {
//		if (id.equals("test")) {
//			return true;
//		}
//		return id.matches("[a-z0-9_-]{5,20}");
//	}
//
//	public boolean isValidPassword(String password) {
//		if (password.equals("test")) {
//			return true;
//		}
//	    String specialCharacters = "!\"#$%&'()*+,-./:;?@[\\]^_`{|}~";
//	    String regex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[" + Pattern.quote(specialCharacters) + "])[a-zA-Z\\d" + Pattern.quote(specialCharacters) + "]{8,16}$";
//	    return password.matches(regex);
//	}
//
//	public boolean isValidPhoneNumber(String phoneNumber) {
//		if (phoneNumber.equals("test")) {
//			return true;
//		}
//		return phoneNumber.matches("^\\d{3}-\\d{4}-\\d{4}$");
//	}
//	
//	public boolean isValidAddress(String address) {
//		if (address.equals("test")) {
//			return true;
//		}
//	    return address.contains("시") && address.contains("구") && address.contains("동");
//	}
	
}
