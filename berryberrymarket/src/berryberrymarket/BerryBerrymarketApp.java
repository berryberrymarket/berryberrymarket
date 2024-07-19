package berryberrymarket;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import userPackage.model.User;
import userPackage.model.UserListManager;

public class BerryBerrymarketApp {

	// 유저 리스트 초기화.
	// 정적 영역에 생성하고 여기저기서 호출함.
	public static UserListManager ulm = UserListManager.getUserListMagener();
	public static List<User> ul = ulm.getUserList();
	
	public static void main(String[] args) {

		PrintPage pp = new PrintPage();
		int pageCase = 2;
		Scanner sc = new Scanner(System.in);

		try {
			while(true) {
				if(pageCase==0) { //시스템 종료
					System.out.println("시스템을 종료합니다.");
					break;
				} else if (pageCase==1) { //메인페이지 출력
					pageCase = pp.printMainPage(sc);
				} else if (pageCase==2) { //로그인페이지 출력
					pageCase = pp.printLogInPage(sc);
				} else if (pageCase==3) { //회원가입페이지 출력
					pageCase = pp.printSignUpPage(sc);
				} else if (pageCase==4) { //마이페이지 출력
					pageCase = pp.printMyPage(sc);
				} else if (pageCase==5) { //게시글상세페이지 출력
					pageCase = pp.printPostDetailPage(sc);
				} else if (pageCase==6) { //게시글등록페이지 출력
					pageCase = pp.printAddPostPage(sc);
				} else if (pageCase==7) { //채팅목록페이지 출력
					pageCase = pp.printChatListPage(sc);
				} else if (pageCase==8) { //채팅방페이지 출력
					pageCase = pp.printChatRoomPage(sc);
				}
//				} else if (pageCase==9) {
//					pageCase = pp.printFilteredPage(sc);
//				}
			}
	    
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		}

	}
	
}