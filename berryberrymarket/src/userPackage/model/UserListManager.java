package userPackage.model;


// 자바 리스트 관련 import
import java.util.List;
import java.util.ArrayList;


import java.io.FileNotFoundException;

// 파일 처리 관련 import
import java.io.File;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// 파일 처리 관련 Exception
import java.io.IOException;
import java.lang.ClassNotFoundException;


public class UserListManager {
	private List<User> userList = new ArrayList<>();
	private static UserListManager userListManager;
	
	public static String nowPath = System.getProperty("user.dir");
	public static File path = new File(nowPath, "user");
	
	private FileInputStream fis = null;
	private ObjectInputStream ois = null;
	private FileOutputStream fos = null;
	private ObjectOutputStream oos = null;
	
	public UserListManager() {
		// UserList 클래스의 객체 생성과 동시에 유저 리스트가 생성됨.
		// 또한 UserList 객체는 프로그램이 실행될 때 가장 먼저 만들어져야 함.
		initializeUserList();
//		System.out.println("userPackage/model/UserListManager.java: UserList 초기화 완료");
	}
	
	public static UserListManager getUserListMagener() {
		if (userListManager == null) {
			userListManager = new UserListManager();
			return userListManager;
		}
		return userListManager;
	}
	// 여기 위까지는 싱글톤 객체 관련 로직임
	// 여기 아래부터 유저 리스트 데이터 관련 로직들임
	public List<User> getUserList() {
		return userList;
	}
	
	public void updateUserList() { // 유저 리스트가 변동될 때마다 호출되는 유틸 메서드.
		try {
			fos = new FileOutputStream(path+"/userList.dat");
			oos = new ObjectOutputStream(fos);
			
			userList.stream().forEach(userObject -> {
				try {
					oos.writeObject(userObject);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {				
					oos.flush();
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addUserToUserList(String id, String pw1, String name, String nick, String phoneNumber, String address) {
		User user = new User(id, pw1, name, nick, phoneNumber, address);
		userList.add(user); // 전체 유저 리스트에 추가하고,
		updateUserList(); // 파일화 작업.
	}
	
	// 회원 삭제 메서드.
	public int deleteUserFromUserList(String logOutUID) {
		/*
		 * 회원 탈퇴 시키기:
		 * 1. 로그아웃 시키고,
		 *		1-1. 현재 로그인 리스트에서 빼고,
		 * 		1-2. 유저 객체의 Authentication = null 로 바꾸고,
		 * 		1-3. 인증 파일 지우기.
		 * 2. 전체 유저 리스트에서 지우고,
		 * 3. 유저가 지워진 유저리스트를 파일로 만들기.
		 */
    	if (logOutUID != null) {
    		// 전체 유저리스트를 반복해서 돌면서,
    		for (int i = 0; i < userList.size(); i ++) {
    			// 아이디를 얻고,
    			String uid = userList.get(i).getId();
    			// 로그아웃한 유저와 일치하는 순간,
    			if (uid.equals(logOutUID)) {
    				userList.remove(i); // 전체 유저 리스트에서 지우고,
    				updateUserList(); // 파일화 작업.
    				System.out.println("회원 탈퇴가 완료되었습니다.");
    				return 2; // 성공시 메인 페이지로 이동.
    			}
    		}
    	} else {
//    		System.out.println("userPackage/model/UserListManager.java: 로그아웃 도중 에러가 발생했습니다.");
        }
    	return 4; // 실패시 마이페이지로 이동.
	}
	
	public List<User> getUserListFromUserListFile() {
		
		try {
			fis = new FileInputStream(path+"/userList.dat");
			ois = new ObjectInputStream(fis);
			
			while (true) {
				try {
					User user = (User) ois.readObject();
					userList.add(user);
				} catch (EOFException e) {
                    break;
                }
			} 

		} catch (FileNotFoundException e) {
//			System.out.println("userPackage/model/UserListManager.java: 파일이 존재하지 않습니다.");
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
		return userList;
	}
	
	public void generateUserListFile() {

		File userListFile = new File(path, "userList.dat");
		if (!userListFile.exists()) { // 유저 리스트 파일이 경로에 없을 때,
			/*
			 * 초기화 시 경로만 만드는 이유 :
			 * 유저는 List<User> 에 저장되는데,
			 * List<User>에 가입된 유저가 없는 경우, 즉 프로그램이 완전히 초기 상태인 경우는,
			 * List<User>가 존재할 필요가 없기 때문입니다.
			 */
			path.mkdirs(); // 경로만 만듦.
		} else {
//			System.out.println("userPackage/model/UserListManager.java: 파일 발견.");
		}
	}
	
	private void initializeUserList() {
		// 프로그램 초기화 시 수행할 단계들을 나열해놓았습니다.
        if (!path.exists()) {
//        	System.out.println("userPackage/model/UserListManager.java: 경로가 존재하지 않습니다.");
        	path.mkdirs();
        }
        
        generateUserListFile(); // 유저 리스트 파일이 경로에 이미 있으면 아무 작업도 하지 않음.
        getUserListFromUserListFile(); // 유저리스트 파일을 	불러와서 userList 초기화.
        
//		System.out.printf("userPackage/model/UserListManager.java: 현재 가입된 유저 수: %d\n", userList.size());
	}
	
}
