package berryberrymarket;

import java.util.Scanner;

public class BerryBerrymarketApp {

	public static void main(String[] args) {

		int pageCase = 0;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			if(pageCase==0) { //시스템 종료
				System.out.println("시스템을 종료합니다.");
			} else if (pageCase==1) { //메인페이지 출력
				pageCase = PrintPage.printMainPage(sc);
			} else if (pageCase==2) { //로그인페이지 출력
				pageCase = PrintPage.printLogInPage(sc);
			} else if (pageCase==3) { //회원가입페이지 출력
				pageCase = PrintPage.printSignUpPage(sc);
			} else if (pageCase==4) { //마이페이지 출력
				pageCase = PrintPage.printMyPage(sc);
			} else if (pageCase==5) { //게시글상세페이지 출력
				pageCase = PrintPage.printPostDetailPage(sc);
			} else if (pageCase==6) { //게시글등록페이지 출력
				pageCase = PrintPage.printAddPostPage(sc);
			} else if (pageCase==7) { //채팅목록페이지 출력
				pageCase = PrintPage.printChatListPage(sc);
			} else if (pageCase==8) { //채팅방페이지 출력
				pageCase = PrintPage.printChatRoomPage(sc);
			}
		}
	}

	

}
