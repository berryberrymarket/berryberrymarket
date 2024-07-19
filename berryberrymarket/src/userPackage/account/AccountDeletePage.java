package userPackage.account;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import berryberrymarket.BerryBerrymarketApp;
import userPackage.model.User;


public class AccountDeletePage {
	private List<User> userList;
	private List<File> fileList;

	public String nowPath = System.getProperty("user.dir");
	public File path = new File(nowPath, "user");
	
	Scanner sc = new Scanner(System.in);
	
	public AccountDeletePage() {
		this.userList = BerryBerrymarketApp.ul;
	}
	
	public void accountFileDelete(String uid) {
		File[] fileArray = path.listFiles();
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			for (int i = 0; i < fileArray.length; i ++) {
				fis = new FileInputStream(fileArray[i]);
				ois = new ObjectInputStream(fis);
				
				User user = (User) ois.readObject();
				String targetId = user.getId();
				if (targetId.equals(uid)) {
					fileArray[i].delete();
				} else {
					System.out.println("일치하는 유저 파일이 없습니다.");
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();	
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int accountDelete() {
		/*
		 * 로그아웃 시키기:
		 * 1. 현재 로그인 리스트에서 빼고,
		 * 2. 유저 객체의 Authentication = null 로 바꾸고,
		 * 3. 인증 파일 지우기.
		 * 
		 * 회원 탈퇴 시키기:
		 * 1. 로그아웃 시키고,
		 * 2. 전체 유저 리스트에서 지우고,
		 * 3. 유저 파일 삭제하기.
		 */
	    System.out.print("정말로 회원 탈퇴를 하시겠습니까? (y/n): ");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) { // 대소문자 구분하지 않습니다. Y == y ...
        	String logOutUID = LogOutPage.logOut(); // 로그아웃 시키고,
        	if (logOutUID != null) {
        		for (int i = 0; i < userList.size(); i ++) {
        			String uid = userList.get(i).getId();
        			if (uid.equals(logOutUID)) {
        				userList.remove(i); // 전체 유저 리스트에서 지우고,
        				accountFileDelete(uid); // 유저 파일 삭제하기.    
        			}
        		}
                System.out.println("회원 탈퇴가 완료되었습니다.");
        	} else {
        		System.out.println("userPackage.account.AccountDeletePage.java: 로그아웃 도중 에러가 발생했습니다.");
        	}
            return 2; // 성공시 로그인 페이지로 이동
        }
        return 4; // 취소시 마이페이지로 이동
	}
}
