package berryberrymarket;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import userPackage.account.UserAuthentication;
import userPackage.model.User;
import userPackage.model.UserListManager;

public class BerryBerrymarketApp {

	// 정적 영역에 생성하고 여기저기서 호출함.
	public static UserListManager ulm = UserListManager.getUserListMagener(); // 유저 관리자 초기화.
	public static List<User> loginUserList = new ArrayList<>(); // 로그인중인 유저들 관리할 리스트 초기화.
	public static List<User> ul = ulm.getUserList(); // 전체 유저 리스트 가져오기.

	public static void main(String[] args) {

		UserAuthentication.checkAuthFile();
		
		PrintPage pp = new PrintPage();
		int pageCase = 2;

		try { 
			while (true) { //페이지 이동을 위해 while 무한 반복문을 넣어줌.
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println();
				if (pageCase == 0) { // 시스템 종료
					System.out.println("시스템을 종료합니다.");
					break;
				} else if (pageCase == 1) { // 메인페이지 출력
					pageCase = pp.printMainPage();
				} else if (pageCase == 2) { // 로그인페이지 출력
					pageCase = pp.printLogInPage();
				} else if (pageCase == 3) { // 회원가입페이지 출력
					pageCase = pp.printSignUpPage();
				} else if (pageCase == 4) { // 마이페이지 출력
					pageCase = pp.printMyPage();
				} else if (pageCase == 5) { // 게시글상세페이지 출력
					pageCase = pp.printPostDetailPage();
				} else if (pageCase == 6) { // 게시글등록페이지 출력
					pageCase = pp.printAddPostPage();
				} else if (pageCase == 7) { // 거래완료페이지 출력
					pageCase = pp.printTransactionComplete();
				} else if (pageCase == 8) { // 채팅방페이지 출력
					pageCase = pp.printChatRoomPage();
				} else if (pageCase == 9) { // 회원정보수정페이지 출력
					pageCase = pp.printUserUpdatePage();
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			UserAuthentication.deleteAuthFile(); // 인증 파일은 예외로 인한 프로그램 종료 시 반드시 지워줘야 함.
		}

	}

}