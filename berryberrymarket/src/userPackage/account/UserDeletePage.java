package userPackage.account;


import java.io.File;
import java.util.Scanner;
import userPackage.model.UserListManager;


public class UserDeletePage {
	// 전체 유저리스트를 가져오고,
	UserListManager ulm = UserListManager.getUserListMagener();
	// 유저리스트가 저장된 경로를 불러옴.
	public String nowPath = System.getProperty("user.dir");
	public File path = new File(nowPath, "user");
	
	// 유저 삭제 메서드.
	public int userDelete(Scanner sc) {
		int deleteResult = 4; // 기본적으로 마이페이지로 이동;
		System.out.print("정말로 회원 탈퇴를 하시겠습니까? (y/n): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) { // 대소문자 구분하지 않습니다. Y == y ...
        	String logOutUID = UserLogoutPage.logOut(); // 로그아웃 시키고,
        	deleteResult = ulm.deleteUserFromUserList(logOutUID); // 유저삭제 결과값 할당.
        }
        return deleteResult;
	}
}
