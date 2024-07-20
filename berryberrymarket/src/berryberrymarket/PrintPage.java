package berryberrymarket;

import java.io.FileNotFoundException;
import java.util.Scanner;

import userPackage.account.LogInPage;
import userPackage.account.SignUpPage;

public class PrintPage {

	PostManager pm = new PostManager();
	int index = 0;
	String search = "";

	public int printMainPage(Scanner sc) {
		printHead("메인페이지");
		
		System.out.println("(m)마이페이지                  (o)로그아웃"); //수정 //수현
		System.out.println("      (S)검색    (c)채팅목록   (p)등록");//수정 //수현
		
		printSmallHead("게시글");
		
		pm.printBoard(search);
		System.out.println("(<)이전페이지                다음페이지(>)");//수정 //수현
		printTail();
		String in = sc.nextLine();
		switch (in) {
		case "m":
			return 4;
		case "o":
			return 2;
		case "<":
			pm.prevPage();
			return 1;
		case ">":
			pm.nextPage();
			return 1;
		case "S": // 앞에 " s" 공백 없앰//수현
			System.out.println("검색할 내용을 입력하세요: ");
			search = sc.nextLine();
			return 1;
		case "c":
			return 7;
		case "p":
			return 6;
		default:
			try {
				index = Integer.parseInt(in);
				return 5;
			} catch(NumberFormatException e) {
				System.out.println("다시 입력하세요");
				return 1;
			}

		}
	}
	

	public int printLogInPage(Scanner sc) {
		System.out.println("        |\\/|/|/|/|/|");
		System.out.println("       !,*.-*-*-*,*!");
		System.out.println("      ,' , ' , `,'.'\\");
		System.out.println("     / ,  '  , ' , ',\\");
		System.out.println("    |  ,0 ,' 0 ','.',|");
		System.out.println("    | , '\\___/ .',',',/");
		System.out.println("   ㅡ\\', ' , ,'.',','/ㅡ");
		System.out.println("      \\',', ',',',',/");
		System.out.println("       \\','.',',', /");
		System.out.println("        \\ ,',',', /");
		System.out.println("          \\'.', /");
		System.out.println("            \"-\"");
		System.out.println("           / (");
		printHead("로그인페이지");
		System.out.println("(1)로그인");
		System.out.println("(2)회원가입");
		printTail();
		String in = sc.nextLine();
		switch (in) {
		case "1":
			while (true) {
				System.out.print("아이디를 입력하세요: ");
				String id = sc.nextLine();
				System.out.print("비밀번호를 입력하세요: ");
				String password = sc.nextLine();
//				아이디패스워드 확인 메소드
				LogInPage loginPage = new LogInPage(id, password);
				
				boolean loginEx = loginPage.LogIn();
				if (loginEx) {
					pm.initGetBoard();
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
		SignUpPage sp = new SignUpPage();

		try {
			sp.SignUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 2;
	}

	public int printMyPage(Scanner sc) {
		printHead("마이페이지");
		return 5;
	}

	public int printPostDetailPage(Scanner sc) {
		printHead("게시글상세페이지");
		pm.printPost(index);
		System.out.println("(c)채팅하기");///////////수현 추가//////////////////////수현 추가/////////수현 추가//////////////////////수현 추가
		System.out.println("(H)홈으로        (U)수정 (D)삭제");
		printTail();
		while(true) {
			String in = sc.nextLine();
			switch (in) {
			case "H":
				return 1;
			case "U":
				return 1;
			case "c"://///////수현 추가//////////////////////수현 추가/////////수현 추가//////////////////////수현 추가
				return 8;// 
			default:		
				System.out.println("다시 입력하세요");
			}
		}
	}

	public int printAddPostPage(Scanner sc) throws FileNotFoundException {
		printHead("게시글등록페이지");
		System.out.print("제목을 입력하세요: ");
		String title = sc.nextLine();
		System.out.print("내용을 입력하세요: ");
		String content = sc.nextLine();
		boolean validInput = false;
		int price = 0;
	    while (!validInput) {
	        try {
	            System.out.print("가격을 입력하세요: ");
	            price = Integer.parseInt(sc.nextLine());
	            if (price <= 0) {
	                throw new IllegalArgumentException("가격은 양수여야 합니다.");
	            }
	            validInput = true; // 입력이 유효하면 반복문 종료
	        } catch (NumberFormatException e) {
	            System.out.println("유효하지 않은 입력입니다. 다시 시도하세요.");
	        } catch (IllegalArgumentException e) {
	            System.out.println(e.getMessage());
	        }
	    }
		System.out.print("거래 희망 장소를 입력하세요: ");
		String place = sc.nextLine();

		pm.addPost(new Post(title, "user1", content, price, place));

		return 1;
	}

	public int printChatListPage(Scanner sc) { ////////// 수현
		printHead("채팅목록페이지");
		// 여기에 채팅 목록을 출력하는 코드를 작성합니다.
		// 예를 들어, 채팅 목록을 가져오고 출력하는 코드를 작성할 수 있습니다.
		// 사용자가 선택할 채팅 방 번호나 기타 작업을 처리하는 로직을 추가합니다.

		System.out.println("1. 채팅방 입장");
		System.out.println("0. 메인 메뉴로 돌아가기");
		System.out.print("원하는 작업을 선택하세요: ");
		int choice = sc.nextInt();
		sc.nextLine(); // 버퍼 비우기

		return choice == 1 ? 8 : 1; // 채팅방 입장 선택 시 8 반환, 그 외에는 메인 페이지로 돌아가기

	}

	public int printChatRoomPage(Scanner sc) {
	    printHead("채팅방페이지");
	    System.out.println("채팅을 시작합니다...printChatRoomPage()");

	    Client.startChat();
	   
	    System.out.println("0. 채팅 나가기printChatRoomPage()");
	    System.out.print("원하는 작업을 선택하세요: printChatRoomPage()");
	    int choice = sc.nextInt();
	    sc.nextLine(); // 버퍼 비우기

	    return choice == 0 ? 7 : 8; // 채팅 나가기 선택 시 7 반환, 그 외에는 채팅 방 페이지로 남기
	}

	private void printHead(String str) {
		System.out.println("===========" + str + "===========");
	}

	private void printSmallHead(String str) {
		System.out.println("-----------" + str + "-----------");
	}

	private void printTail() {
		System.out.println("=============================");
		System.out.print("입력하세요: ");
	}

}
