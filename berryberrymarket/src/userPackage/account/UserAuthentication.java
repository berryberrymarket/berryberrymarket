package userPackage.account;


import java.io.File;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;


public class UserAuthentication implements Serializable {

	/*
	 * 인증 파일이 일종의 `key`이자 프로그램 내에서 자기 자신이 누구인지를 식별하는 식별자로서 작동함.
	 */
	
	private static final long serialVersionUID = 7098334274704476322L;
	private String id;
	private String password;
	private static List<File> fileList;
	private static UserAuthentication authObject;
	
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

	private UserAuthentication(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	public static UserAuthentication generateAuthObject(String id, String password) {

		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			
			fos = new FileOutputStream(path+"/authentication.dat");
			oos = new ObjectOutputStream(fos);
			
			authObject = new UserAuthentication(id, password);
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
	
	public static UserAuthentication getAuthObject() {
		
		File[] fileArray = path.listFiles();
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fileList = Arrays.asList(fileArray);
			if (!fileList.isEmpty()) {
				for (File file: fileList) {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					authObject = (UserAuthentication) ois.readObject();
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
				System.out.println("userPackage/account/UserAuthentication.java: 인증 파일이 잘 삭제되었습니다.");
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
        	System.out.println("userPackage/account/UserAuthentication.java: 인증 폴더를 생성합니다.");
        	path.mkdirs();
        }
//		File[] fileArray = UserList.path.listFiles();
		File[] fileArray = path.listFiles();
		try {
			if (fileArray.length >= 1) {
				System.out.printf("userPackage/account/UserAuthentication.java: 인증 파일이 존재합니다 (%d개 있음.)\n", fileArray.length);
//				throw new Exception("\n이것은 서버 시작시에 인증 파일이 존재하는 경우 발생하는 에러입니다.\n"
//						+ "보통 이 에러가 발생하는 경우는 다음과 같습니다.\n"
//						+ "\t1. 로그아웃을 정상적으로 하지 않고 (다른 예외 등의 이유로)프로그램이 종료되거나,\n"
//						+ "\t2. 프로그램이 실행중일 때 콘솔의 빨간색 버튼을 눌러서 강제로 종료하는 경우입니다. ***\n"
//						+ "따라서 인증 파일이 저장되는 경로에 가서 기존 인증 파일을 수동으로 지워야 합니다.\n"
//						+ "자세한 사항은 문현동에게 물어보세요.\n") {};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
