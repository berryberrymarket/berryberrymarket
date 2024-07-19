package userPackage.account;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import userPackage.model.User;



public class AccountDeletePage {
	private List<User> userList;
	private User loggedInUser;
	Scanner sc = new Scanner(System.in);

	
	public AccountDeletePage() {
		this.userList = new ArrayList<>();
	}
	
	public void AccountDelete() {	    
	      System.out.print("정말로 회원 탈퇴를 하시겠습니까? (y/n): ");
	        String confirm = sc.nextLine();
	        if (confirm.equalsIgnoreCase("y")) {
	            userList.remove(loggedInUser);
	            System.out.println("회원 탈퇴가 완료되었습니다.");
	            loggedInUser = null; // 로그아웃
	            //메인화면으로 돌아가기
	        } else {
	            
	        }
	}
}
