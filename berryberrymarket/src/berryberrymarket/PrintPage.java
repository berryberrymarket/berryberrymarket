package berryberrymarket;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintPage {

	PostManager pm = new PostManager();
	
	public int printMainPage(Scanner sc) {
		printHead("메인페이지");
		System.out.println("(m)마이페이지         (o)로그아웃");
		printSmallHead("게시글");
		pm.printPostList();
		System.out.println("(<)이전페이지         (>)다음페이지");
		System.out.println("(s)검색   (c)채팅목록  (p)등록");		
		printTail();
		String in = sc.nextLine();
		switch(in){
		case "m":
			return 4;
		case "o":
			return 2;
		case "<":
			return 1;
		case ">":
			return 1;
		case "s":
			String category = sc.nextLine();
			pm.printPostListByCategory(category);
			return 1;
		case "c":
			return 7;
		case "p":
			return 6;
		default:
			System.out.println("다시 입력하세요");
			return 1;
		}
	}

	public int printLogInPage(Scanner sc) {
		printHead("로그인페이지");
		System.out.println("(1)로그인");
		System.out.println("(2)회원가입");
		printTail();
		String in = sc.nextLine();
		switch(in) {
		case "1":
			while(true) {
				System.out.print("아이디를 입력하세요: ");
				String id = sc.nextLine();
				System.out.print("비밀번호를 입력하세요: ");
				String password = sc.nextLine();
//				아이디패스워드 확인 메소드
				boolean loginEx = true;
				if(loginEx) {
					return 1;
				} else {
					System.out.println("아이디 혹은 비밀번호가 틀렸습니다.");
				}
			}
		case "2":
			return 3;
		default:
			return 2;
		} 
	}
	
	public int printSignUpPage(Scanner sc) {
		printHead("회원가입페이지");
		return 1;
	}
	
	public int printMyPage(Scanner sc) {
		printHead("마이페이지");
		return 5;
	}
	
	public int printPostDetailPage(Scanner sc) {
		printHead("게시글상세페이지");
		return 6;
	}
	
	public int printAddPostPage(Scanner sc) throws FileNotFoundException {
		printHead("게시글등록페이지");
		System.out.print("제목을 입력하세요: ");
		String title = sc.nextLine();
		System.out.print("내용을 입력하세요: ");
		String content = sc.nextLine();
		System.out.print("가격을 입력하세요: ");
		int price = sc.nextInt();
		sc.nextLine();
		System.out.print("거래 희망 장소를 입력하세요: ");
		String place = sc.nextLine();
		
		pm.addPost(new Post(title,"user1",content,price,place));
		
		return 1;
	}
	
	public int printChatListPage(Scanner sc) {
		printHead("채팅목록페이지");
		return 8;
	}
	
	public int printChatRoomPage(Scanner sc) {
		printHead("채팅방페이지");
		return 0;
	}

	private void printHead(String str) {
		System.out.println("==========="+str+"===========");
	}
	
	private void printSmallHead(String str) {
		System.out.println("-----------"+str+"-----------");
	}
	
	private void printTail() {
		System.out.println("=============================");
		System.out.print("입력하세요: ");
	}
	
}
