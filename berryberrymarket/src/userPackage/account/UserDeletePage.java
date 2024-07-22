package userPackage.account;


import java.io.File;
import java.util.Scanner;
import userPackage.model.UserListManager;


public class UserDeletePage {
	UserListManager ulm = UserListManager.getUserListMagener();
	public String nowPath = System.getProperty("user.dir");
	public File path = new File(nowPath, "user");
	
	public int userDelete(Scanner sc) {
		int deleteResult = 4; // 기본적으로 마이페이지로 이동;
		System.out.print("정말로 회원 탈퇴를 하시겠습니까? (y/n): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) { // 대소문자 구분하지 않습니다. Y == y ...
        	String logOutUID = UserLogoutPage.logOut();
        	deleteResult = ulm.deleteUserFromUserList(logOutUID);
        }
        return deleteResult;
	}
}
