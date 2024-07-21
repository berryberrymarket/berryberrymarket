package userPackage.model;


// 자바 리스트 관련 import
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


// 파일 처리 관련 import
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// 파일 처리 관련 Exception
import java.io.IOException;
import java.lang.ClassNotFoundException;


public class UserListManager {
	private int idx;
	private List<File> fileList;
	private List<User> userList = new ArrayList<User>();;
	
	private static UserListManager userListManager;
	
	// 경로는 아래와 같아야 함
	// C:/Github/pjt-team-kosa/berryberrymarket/berryberrymarket/userList
//	private String userFilePath = "C:/Github/pjt-team-kosa/berryberrymarket/berryberrymarket/userList";
	public String nowPath = System.getProperty("user.dir");
	public File path = new File(nowPath, "user");
	
	public UserListManager() {
		// UserList 클래스의 객체 생성과 동시에 유저 리스트가 생성됨.
		// 또한 UserList 객체는 프로그램이 실행될 때 가장 먼저 만들어져야 함.
		initializeUserList();
		System.out.println("userPackage/model/UserListManager.java: UserList 초기화 완료");
	}
	
	public static UserListManager getUserListMagener() {
		if (userListManager == null) {
			userListManager = new UserListManager();
			return userListManager;
		}
		return userListManager;
	}
	
	public List<User> getUserList() {
		return userList;
	}
	
	private void initializeUserList() {
		// 폴더 목록에 있는 모든 user.dat를 가져와서 userList로 담는 메서드
		// 즉 프로그램 초기화 작업의 일부임.
//		File path = new File(userFilePath);
        if (!path.exists()) {
        	path.mkdirs();
        }
//		File[] fileArray = UserList.path.listFiles();
		File[] fileArray = path.listFiles();
		System.out.printf("userPackage/model/UserListManager.java: 현재 가입된 유저 수: %d\n", fileArray.length);
		idx = fileArray.length;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fileList = Arrays.asList(fileArray);
			if (!fileList.isEmpty()) {
				for (File file: fileList) {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					User user = (User) ois.readObject();
					userList.add(user);
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
	
	public void addUserToUserList(String id, String pw1, String name, String nick, String phoneNumber, String address) {
		idx++;
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
//			fos = new FileOutputStream(userFilePath+"/user_"+idx+"_"+id+".dat");
			fos = new FileOutputStream(path+"/user_"+idx+"_"+id+".dat");
			oos = new ObjectOutputStream(fos);
			User user = new User(id, pw1, name, nick, phoneNumber, address);
			userList.add(user);
			oos.writeObject(user);
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
	
}
