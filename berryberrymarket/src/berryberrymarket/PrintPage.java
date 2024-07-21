package berryberrymarket;

import java.io.FileNotFoundException;
import java.util.Scanner;

import userPackage.account.LogInPage;
import userPackage.account.LogOutPage;
import userPackage.account.MyPage;
import userPackage.account.SignUpPage;
import userPackage.account.UserUpdatePage;

public class PrintPage {

	PostManager pm = new PostManager();
	int index = 0;
	String search = "";
	Scanner sc = new Scanner(System.in);

	public int printMainPage() {
		printHead("메인페이지");
		
		System.out.println("(M)마이페이지                   (O)로그아웃"); //수정 //수현
		System.out.println("      (S)검색    (C)채팅목록    (P)등록");//수정 //수현
		
		printSmallHead("게시글");
		
		pm.printBoard(search);
		int curPage = pm.getCurPage();
		int pageSize = pm.getPageSize();
		if(curPage==1 && pageSize==curPage) {
			System.out.println("                  "+pm.getCurPage()+"                  ");
		}else if(curPage==1) {
			System.out.println("                   "+pm.getCurPage()+"           다음페이지(>)");
		}else if(curPage==pageSize) {
			System.out.println("(<)이전페이지          "+pm.getCurPage()+"                  ");
		}else {
			System.out.println("(<)이전페이지          "+pm.getCurPage()+"          다음페이지(>)");//수정 //수현
		} 
		System.out.println("(H)홈으로");
		printTail();
		String in = setLowerCase(sc); // 입력을 소문자로 변환
		switch (in) {
		case "m":
			return 4;
		case "o":
			LogOutPage.logOut(); // 유저를 로그아웃 시킴
			return 2;
		case "<":
			pm.prevPage();
			return 1;
		case ">":
			pm.nextPage();
			return 1;
		case "s":
			System.out.println("검색할 내용을 입력하세요: ");
			search = sc.nextLine();
			return 1;
		case "c":
			return 7;
		case "p":
			return 6;
		case "h":
			search="";
			return 1;
		default:
			try {
				boolean compareIndex=false;
				while(!compareIndex) {
					index = Integer.parseInt(in);
					compareIndex=pm.compareIndex(index);
					if(compareIndex==false) {
						in = sc.nextLine();
					}
				}
				return 5;
				
			} catch(NumberFormatException e) {
				System.out.println("다시 입력하세요: ");
				return 1;
			}

		}
	}
	

	public int printLogInPage() {
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
			System.out.println("(B)뒤로가기");
			while (true) {
				System.out.print("아이디를 입력하세요: ");
				String id = sc.nextLine();
				if(id.equals("B")||id.equals("b"))
					return 2;
				System.out.print("비밀번호를 입력하세요: ");
				String password = sc.nextLine();
//				아이디패스워드 확인 메소드
				if(password.equals("B")||id.equals("b"))
					return 2;
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

	public int printSignUpPage() {
		printHead("회원가입페이지");
		SignUpPage sp = new SignUpPage();
		int isSignUp = 2;

		try {
			isSignUp = sp.SignUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return isSignUp;
	}

	public int printMyPage() {
		printHead("마이페이지");
		MyPage mp = new MyPage();
		mp.printInfo();
		System.out.print("\n(A)회원 정보 수정     (B)뒤로가기");		
		String update = sc.nextLine();

		switch (update) {
		case "a","A":
			return 9;
		
		case "b","B":
			return 1;
		default:
			return 4;
		}
	
	}

	public int printPostDetailPage() throws FileNotFoundException {
		printHead("게시글상세페이지");
		String title = pm.printPost(index);
		System.out.println("(C)채팅하기");///////////수현 추가//////////////////////수현 추가/////////수현 추가//////////////////////수현 추가
		System.out.println("(B)뒤로가기                (U)수정 (D)삭제");
		printTail();
		while(true) {
			String in = setLowerCase(sc); // 입력을 소문자로 변환
			switch (in) {
			case "b":
				return 1;
			case "d":
				pm.removePost(title);
				return 1;
			case "u":
				pm.removePost(title);
				System.out.println("----수정----");
				setPostInfo(sc);
				return 1;
			case "c"://///////수현 추가//////////////////////수현 추가/////////수현 추가//////////////////////수현 추가
				return 8;// 
			default:		
				System.out.println("다시 입력하세요");
			}
		}
	}

	public int printAddPostPage() throws FileNotFoundException {
		printHead("게시글등록페이지");
		setPostInfo(sc);

		return 1;
	}

	public int printChatListPage() { ////////// 수현
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

	public int printChatRoomPage() {
	    printHead("채팅방페이지");
	    System.out.println("채팅을 시작합니다...printChatRoomPage()");

	    Client.startChat();
	   
	    System.out.println("0. 채팅 나가기printChatRoomPage()");
	    System.out.print("원하는 작업을 선택하세요: printChatRoomPage()");
	    int choice = sc.nextInt();
	    sc.nextLine(); // 버퍼 비우기

	    return choice == 0 ? 7 : 8; // 채팅 나가기 선택 시 7 반환, 그 외에는 채팅 방 페이지로 남기
	}
	
	public int printUserUpdatePage() {
		System.out.println("수정하실 회원 정보 항목을 선택하세요: ");
		System.out.println("(1)비밀번호");
		System.out.println("(2)이름");
		System.out.println("(3)닉네임");
		System.out.println("(4)전화번호");
		System.out.println("(5)주소");
		System.out.println("(6)마이페이지로 돌아가기");
		String in = sc.nextLine();
		switch(in) {
		case "1","2","3","4","5":
			UserUpdatePage uu = new UserUpdatePage();
			uu.updateUserInfo(in);
			return 9;	
		
		default:
			return 4;
		
		}
	
		
		 
	}

	private void printHead(String str) {
		System.out.println("=================" + str + "================");
	}

	private void printSmallHead(String str) {
		System.out.println("------------------" + str + "-----------------");
	}

	private void printTail() {
		System.out.println("============================================");
		System.out.print("입력하세요: ");
	}

	private void setPostInfo(Scanner sc) throws FileNotFoundException {
		
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
	}
	
	private String setLowerCase(Scanner sc) {
		String in = sc.nextLine().trim().toLowerCase();
		return in;
	}
}
