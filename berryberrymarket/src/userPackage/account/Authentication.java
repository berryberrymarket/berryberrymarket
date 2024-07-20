package userPackage.account;


import java.util.List;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

public class Authentication implements Serializable {

	/*
	 * 인증 파일이 일종의 `key`이자 프로그램 내에서 자기 자신이 누구인지를 식별하는 식별자로서 작동함.
	 */
	
	private static final long serialVersionUID = 7098334274704476322L;
	private String id;
	private String password;
	private static List<File> fileList;
	private static Authentication authObject;
	
	// 아래 두 줄은 static 으로 바꿀 필요가 있어보임.
	public static String nowPath = System.getProperty("user.dir");
	public static File path = new File(nowPath, "authentication");

	public String getId() {
		return id;
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public String getPassword() {
		return password;
	}

//	public void setPassword(String password) {
//		this.password = password;
//	}

	private Authentication(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public static Authentication generateAuthObject(String id, String password) {

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			
			fos = new FileOutputStream(path+"/authentication.dat");
			oos = new ObjectOutputStream(fos);
			
			authObject = new Authentication(id, password);
			oos.writeObject(authObject);
			
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
		return authObject;
	}
	
	public static Authentication getAuthObject() {
		
		File[] fileArray = path.listFiles();
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fileList = Arrays.asList(fileArray);
			if (!fileList.isEmpty()) {
				for (File file: fileList) {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					authObject = (Authentication) ois.readObject();
//					return authFile;
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
		return authObject;
	}
	
	@SuppressWarnings("serial")
	public static void deleteAuthFile() {
		File authFile = new File(nowPath, "authentication/authentication.dat");
		try {
			if (authFile.exists()) {
				authFile.delete();
			} else {
				throw new Exception("인증 파일이 존재하지 않습니다.\n"
						+ "자세한 사항은 문현동에게 물어보세요.\n") {};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	public static void checkAuthFile() {
		// 폴더 목록에 있는 모든 user.dat를 가져와서 userList로 담는 메서드
		// 즉 프로그램 초기화 작업의 일부임.
//		File path = new File(userFilePath);
        if (!path.exists()) {
        	System.out.println("userPackage/account/Authentication.java: 인증 폴더를 생성합니다.");
        	path.mkdirs();
        }
//		File[] fileArray = UserList.path.listFiles();
		File[] fileArray = path.listFiles();
		try {
			if (fileArray.length >= 1) {
				System.out.printf("userPackage/account/Authentication.java: 인증 파일이 존재합니다 (%d 개 있음.)\n", fileArray.length);
				throw new Exception("이 예외는 커스텀 예외입니다.\n"
						+ "이것은 서버 시작시에 인증 파일이 존재하는 경우 발생하는 에러입니다.\n"
						+ "따라서 인증 파일이 저장되는 경로에 가서 기존 인증 파일을 지워야 합니다.\n"
						+ "자세한 사항은 문현동에게 물어보세요.\n") {};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
