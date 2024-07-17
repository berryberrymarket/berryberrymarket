package berryberrymarket;

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
		case" s":
//			String category = sc.nextLine();
//			pm.printPostListByCateogry(category);
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
		return 3;
	}
	
	public int printSignUpPage(Scanner sc) {
		printHead("회원가입페이지");
		return 4;
	}
	
	public int printMyPage(Scanner sc) {
		printHead("마이페이지");
		return 5;
	}
	
	public int printPostDetailPage(Scanner sc) {
		printHead("게시글상세페이지");
		return 6;
	}
	
	public int printAddPostPage(Scanner sc) {
		printHead("게시글등록페이지");
		return 7;
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
