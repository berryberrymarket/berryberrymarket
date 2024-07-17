package berryberrymarket;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class BerryBerrymarketApp {

	public static void main(String[] args) throws FileNotFoundException {

		PrintPage pm = new PrintPage();
		int pageCase = 1;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			if(pageCase==0) { //시스템 종료
				System.out.println("시스템을 종료합니다.");
				break;
			} else if (pageCase==1) { //메인페이지 출력
				pageCase = pm.printMainPage(sc);
			} else if (pageCase==2) { //로그인페이지 출력
				pageCase = pm.printLogInPage(sc);
			} else if (pageCase==3) { //회원가입페이지 출력
				pageCase = pm.printSignUpPage(sc);
			} else if (pageCase==4) { //마이페이지 출력
				pageCase = pm.printMyPage(sc);
			} else if (pageCase==5) { //게시글상세페이지 출력
				pageCase = pm.printPostDetailPage(sc);
			} else if (pageCase==6) { //게시글등록페이지 출력
				pageCase = pm.printAddPostPage(sc);
			} else if (pageCase==7) { //채팅목록페이지 출력
				pageCase = pm.printChatListPage(sc);
			} else if (pageCase==8) { //채팅방페이지 출력
				pageCase = pm.printChatRoomPage(sc);
			}
		}
		
	
		
		
	}

	

}