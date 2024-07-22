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
	 * 컨셉은 개발 문서(회원 인증 파일의 인증 원리)를 참고 바랍니다.
	 */
	
	private static final long serialVersionUID = 7098334274704476322L;
	private String id;
	private String password;
	private static List<File> fileList;
	private static UserAuthentication authObject;
	
	// 프로젝트 루트 경로 및 인증파일 저장경로 정의.
	public static String nowPath = System.getProperty("user.dir");
	public static File path = new File(nowPath, "authentication");

	public String getId() {
		return id;
	}

	// id, pw 는 생성자에 의해 정의됩니다.
	// 인증 파일의 정보는 임의로 정의할 수 없습니다.
//	public void setId(String id) {
//		this.id = id;
//	}

	public String getPassword() {
		return password;
	}

	// id, pw 는 생성자에 의해 정의됩니다.
	// 인증 파일의 정보는 임의로 정의할 수 없습니다.
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
			
			// 인증파일 객체 생성 후,
			authObject = new UserAuthentication(id, password);
			// 경로상에 저장.
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
	
	// 유저 인증파일을 가져오는 메서드.
	public static UserAuthentication getAuthObject() {
		
		File[] fileArray = path.listFiles();
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			// 파일 리스트를 모두 가져온 다음,
			fileList = Arrays.asList(fileArray);
			// 만약 비어있지 않으면(인증 파일이 존재하면), 
			if (!fileList.isEmpty()) {
				for (File file: fileList) {
					fis = new FileInputStream(file);
					ois = new ObjectInputStream(fis);
					
					// 인증 파일을 읽어옴.
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
		// 가져온 인증 파일을 반환.
		return authObject;
	}
	
	// 인증 파일을 삭제하는 메서드.
	@SuppressWarnings("serial")
	public static void deleteAuthFile() {
		File authFile = new File(nowPath, "authentication/authentication.dat");
		try {
			// 지정된 경로에 인증 파일이 존재한다면,
			if (authFile.exists()) {
				authFile.delete(); // 삭제.
				System.out.println("userPackage/account/UserAuthentication.java: 인증 파일이 잘 삭제되었습니다.");
			} else {
				// 삭제하지 않았음에도 존재하지 않는다면 예외 발생.
				throw new Exception("인증 파일이 존재하지 않습니다.\n"
						+ "자세한 사항은 문현동에게 물어보세요.\n") {};
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("serial")
	public static void checkAuthFile() {
		/*
		 * 폴더 목록에 있는 모든 user.dat를 가져와서 userList로 담는 메서드.
		 * 즉 프로그램 초기화 작업의 일부임.
		 */
		
		// 경로 자체가 존재하지 않는다면,
        if (!path.exists()) {
        	System.out.println("userPackage/account/UserAuthentication.java: 인증 폴더를 생성합니다.");
        	path.mkdirs(); // 경로만 생성함.
        }
        // 경로는 존재하는 경우,
		File[] fileArray = path.listFiles();
		try {
			if (fileArray.length >= 1) { // 파일이 존재한다면,
				System.out.printf("userPackage/account/UserAuthentication.java: 인증 파일이 존재합니다 (%d개 있음.)\n", fileArray.length);
//				throw new Exception("\n이것은 서버 시작시에 인증 파일이 존재하는 경우 발생하는 에러입니다.\n"
//						+ "보통 이 에러가 발생하는 경우는 다음과 같습니다.\n"
//						+ "\t1. 로그아웃을 정상적으로 하지 않고 (다른 예외 등의 이유로)프로그램이 종료되거나,\n"
//						+ "\t2. 프로그램이 실행중일 때 콘솔의 빨간색 버튼을 눌러서 강제로 종료하는 경우입니다. ***\n"
//						+ "따라서 인증 파일이 저장되는 경로에 가서 기존 인증 파일을 수동으로 지워야 합니다.\n"
//						+ "자세한 사항은 문현동에게 물어보세요.\n") {};
				// 굳이 예외처리 할 필요 없어서(덮어씌워지기 때문에) 주석처리함.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
