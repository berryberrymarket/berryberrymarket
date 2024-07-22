package userPackage.account;

import java.util.List;
import java.util.Scanner;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.User;
import userPackage.model.UserListManager;

public class UserUpdatePage {
	private List<User> userList = BerryBerrymarketApp.ul;
	private UserListManager ulm = UserListManager.getUserListMagener();
	
	private User user;
	
	Scanner sc = new Scanner(System.in);

	public UserUpdatePage() {
		this.user = GetUser.findUserFromLoginUserList();
	}
	
//	public void checkUser() {
//		System.out.println("비밀번호를 입력하세요: ");
//		String pw = sc.nextLine();
//		if(equals(pw)) {
//			//updateUserInfo 실행
//		}
//		else
//			System.out.println("비밀번호가 일치하지 않습니다. ");
//		
//	}
	
	public void updateUserInfo(String in) {
//		System.out.println("수정하실 회원 정보 항목을 선택하세요: ");
//		System.out.println("(1)비밀번호");
//		System.out.println("(2)이름");
//		System.out.println("(3)닉네임");
//		System.out.println("(4)전화번호");
//		System.out.println("(5)주소");
//		System.out.println("(6)마이페이지로 돌아가기");
//		String in = sc.nextLine();

		if (this.user != null) {
			switch(in) {
			case"1":
				String newPw1;
				while (true) {
					System.out.print("수정하실 비밀번호를 입력하세요: ");
					newPw1 = sc.nextLine();
					
					if (!ValidationUtils.isValidPassword(newPw1)) {
						System.out.println("비밀번호는 8~16자의 영문 대(소)문자+숫자+특수문자 조합으로 입력해주세요.");				
					}
					else
						break;
				}

				while (true) {
					System.out.print("수정하실 비밀번호를 한번 더 입력하세요: ");
					String newPw2 = sc.nextLine();
					if (!newPw1.equals(newPw2)) {
						System.out.println("비밀번호가 일치하지 않습니다.");
					} else {
						user.setPw(newPw1);
						System.out.println("비밀번호가 수정 되었습니다.");				
						break;					
					}
				}
				break;
			case"2":
				System.out.print("수정하실 이름을 입력하세요: ");
				String newName = sc.nextLine();
				user.setName(newName);
				System.out.println("이름이 수정 되었습니다.");	
				break;				
			case"3":
				String newNick;
				while (true) {
					System.out.print("수정하실 닉네임을 입력하세요: ");
					newNick = sc.nextLine();
					if (!ValidationUtils.isValidNick(newNick)) {
						System.out.println("닉네임은 6글자 이내로 입력해주세요.");
						continue;
					}
					boolean nickExists = false;

					if (!userList.isEmpty()) {
						for (User user : userList) {
							if (user.getNick().equals(newNick)) {
								System.out.println("이미 사용중인 닉네임 입니다. ");
								nickExists = true;
								break;
							}
						}
					}

					if (!nickExists) {
						user.setNick(newNick);
						System.out.println("닉네임이 수정되었습니다.");
						break; // 루프 종료
					}
				}				
				break;
			case"4":	
				String newPhoneNumber;
				while (true) {
					System.out.print("수정하실 전화번호를 입력하세요: ");
					newPhoneNumber = sc.nextLine();
					if (!ValidationUtils.isValidPhoneNumber(newPhoneNumber)) {
						System.out.println("형식이 올바르지 않습니다.");
					} else {
						user.setPhoneNumber(newPhoneNumber);
						System.out.println("전화번호가 수정되었습니다.");
						break;
					}
				}
				break;
			case"5":	
				 String newAddress;
			     //while (true) {
			          System.out.print("주소를 입력하세요: ");
			          newAddress = sc.nextLine();
//			          if (!ValidationUtils.isValidAddress(newAddress)) {
//			              System.out.println("형식이 올바르지 않습니다.");
//			          } else {
//			        	  System.out.println("주소가 수정되었습니다.");
//			              break;
//			          }
							user.setAddress(newAddress);
			          System.out.println("주소가 수정되었습니다.");  
			         // break;
			     //}
			     break;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			ulm.updateUserList();
		} else {
			System.out.println("userPackage.account.UserUpdatePage.java: 유저 정보를 찾을 수 없습니다.");
		}
		
	}	
		
}
