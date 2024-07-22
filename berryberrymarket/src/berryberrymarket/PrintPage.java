package berryberrymarket;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import userPackage.account.UserDeletePage;
import userPackage.account.UserLoginPage;
import userPackage.account.UserLogoutPage;
import userPackage.account.UserMyInfoPage;
import userPackage.account.UserSignUpPage;
import userPackage.account.UserUpdatePage;
import userPackage.model.User;
import userPackage.model.UserListManager;

public class PrintPage {

	PostManager pm = new PostManager();
	LoginChecker loginChecker = new LoginChecker();
	int index = 0;
	String search = "";
	Scanner sc = new Scanner(System.in);
	UserListManager ulm = UserListManager.getUserListMagener();

	public PrintPage() {
		pm.initGetBoard();
	}


	public int printMainPage() {
		//printHead("메인페이지");
		System.out.println("============================메인페이지===========================");
		System.out.println("(M)마이페이지                                         (O)로그아웃"); //수정 //수현
		System.out.println("      (S)검색               (C)채팅목록               (P)등록");//수정 //수현
		
		//printSmallHead("게시글");
		System.out.println("------------------------------게시글-----------------------------");
		System.out.println("| No |            제목              |    작성자   |    등록날짜  |");
		System.out.println("-----------------------------------------------------------------");
		
		pm.printBoard(search);
		int curPage = pm.getCurPage();
		int pageSize = pm.getPageSize();
		if(curPage==1 && pageSize==curPage) {
			System.out.println("                             "+pm.getCurPage()+"                  ");
		}else if(curPage==1) {
			System.out.println("                             "+pm.getCurPage()+"                     다음페이지(>)");
		}else if(curPage==pageSize) {
			System.out.println("(<)이전페이지                "+pm.getCurPage()+"                  ");
		}else {
			System.out.println("(<)이전페이지                "+pm.getCurPage()+"                     다음페이지(>)");//수정 //수현
		} 
		System.out.println("(H)홈으로");
		printTail();
		String in = sc.nextLine().trim().toLowerCase(); // 입력을 소문자로 변환
		switch (in) {
		case "m":
			return 4;
		case "o":
			loginChecker.setUser(null);
			UserLogoutPage.logOut(); // 유저를 로그아웃 시킴
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
						System.out.print("검색한 인덱스의 게시글이 없습니다. 다시 입력하세요:");
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
		System.out.println("                           [ 딸기장터 ]");
		System.out.println("                          |\\/|/|/|/|/|/|");
		System.out.println("                          !,*.-*-*-*-*-!");
		System.out.println("                         / ,' , ' ,`,'.'\\");
		System.out.println("                        / ,  '  , ' , ','\\");
		System.out.println("                       |  ,0 ,' 0 ','.',',|");
		System.out.println("                       | , '\\___/ .',',',/");
		System.out.println("                     oㅡ\\', ' , ,'.',','/ㅡo");
		System.out.println("                         \\',', ',',',',/");
		System.out.println("                          \\','.',',', /");
		System.out.println("                           \\ ,',',', /");
		System.out.println("                             \\'.', /");
		System.out.println("                               \"-\"");
		System.out.println("                               / (");
		System.out.println("                              o   o");
		//printHead("로그인페이지");
		System.out.println("===========================로그인페이지==========================");
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
				UserLoginPage loginPage = new UserLoginPage(id, password);
				
				boolean loginEx = loginPage.LogIn();
				if (loginEx) {
					List<User> userList = ulm.getUserList();
					userList.stream().filter(n->n.getId().equals(id)).forEach(n->loginChecker.setUser(n));
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
		//printHead("회원가입페이지");
		System.out.println("==========================회원가입페이지=========================");
		UserSignUpPage sp = new UserSignUpPage();

		try {
			sp.SignUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 2;
	}

	public int printMyPage() {
		//printHead("마이페이지");
		System.out.println("=============================마이페이지============================");
		UserMyInfoPage myPage = new UserMyInfoPage();
		while (true) {
			myPage.printInfo();
			System.out.printf("원하는 메뉴키를 입력하세요: ");
			String keyPress = sc.nextLine();
			switch (keyPress) {
				case "a","A" -> {
					return 9;
					}
				case "d", "D" -> {
					UserDeletePage udp = new UserDeletePage();
					int deleteResult = udp.userDelete(sc);
					myPage = null;
					return deleteResult;
				}
				case "b","B" -> {				
					return 1;
				}
				default -> {
					System.out.println("올바른 키를 입력하세요.");
				}
			}
		}
	}

	public int printPostDetailPage() throws FileNotFoundException {
		pm.incHit(index);
		//printHead("게시글상세페이지");
		System.out.println("=========================게시글상세페이지========================");
		Post post = pm.printPost(index);
		if(loginChecker.getUser().equals(post.getUser())) {
			System.out.println("(B)뒤로가기                    (U)수정 (D)삭제");
		}
		else {
			System.out.println("(C)채팅하기");
			System.out.println("(B)뒤로가기                    (T)거래완료");
		}
		printTail();
		while(true) {
			String in = sc.nextLine().trim().toLowerCase(); // 입력을 소문자로 변환
			switch (in) {
			case "b":
				return 1;
			case "d":
				pm.removePost(post);
				return 1;
			case "u":
				pm.removePost(post);
				System.out.println("----수정----");
				setPostInfo(sc);
				return 1;
			case "c":
				return 8;// 
			case "t":
				return 7;
			default:		
				System.out.println("다시 메뉴키를 입력하세요");
			}
		}
	}

	public int printAddPostPage() throws FileNotFoundException {
		//printHead("게시글등록페이지");
		System.out.println("=========================게시글등록페이지========================");
		setPostInfo(sc);

		return 1;
	}

	public int printTransactionComplete() { ////////// 수현
		boolean validInput = false;
		int star = 0;
		System.out.println("거래 완료 하시겠습니까? [y/n]: ");
		String in = sc.nextLine();
		if(in.equals("y")) {
			System.out.print("만족스러운 거래가 되셨나요? -5 ~ 5점으로 적어주세요.");
			while (!validInput) {
		        try {
		            System.out.print("별점을 입력하세요: ");
		            star = Integer.parseInt(sc.nextLine());
		            if (star < -5 || star > 5) {
		                throw new IllegalArgumentException("별점은 -5 ~ 5 점이여야 합니다.");
		            }
		            validInput = true; // 입력이 유효하면 반복문 종료
		        } catch (NumberFormatException e) {
		            System.out.println("유효하지 않은 입력입니다. 다시 시도하세요.");
		        } catch (IllegalArgumentException e) {
		            System.out.println(e.getMessage());
		        }
		    }
			Post post = pm.getPost(index);
			TransactionManager tm = new TransactionManager();
			tm.createTransaction(star, post);
			List<User> userList = ulm.getUserList();
			tm.evaluateTransaciton(userList);
			
		}
		return 1;
	}

	public int printChatRoomPage() {
	    //printHead("채팅방페이지");
		System.out.println("==========================채팅방페이지==========================");
	    System.out.println("채팅을 시작합니다...");

	    Client.startChat();
	    
	    return 5;
	}
	
	public int printUserUpdatePage() {
		
		//printHead("회원정보수정페이지");
		System.out.println("========================회원정보수정페이지=======================");
		System.out.println("수정하실 회원 정보 항목을 선택하세요: ");
		System.out.println("(1)비밀번호");
		System.out.println("(2)이름");
		System.out.println("(3)닉네임");
		System.out.println("(4)전화번호");
		System.out.println("(5)주소");
		System.out.println("(0)마이페이지로 돌아가기");
		printTail();
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

	//private void printHead(String str) {
	//	System.out.println("============================" + str + "============================");
	//}

	//private void printSmallHead(String str) {
	//	System.out.println("------------------------------" + str + "-----------------------------");
	//}

	private void printTail() {
		System.out.println("=================================================================");
		System.out.print("원하는 메뉴키를 입력하세요: ");
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

		pm.addPost(new Post(title, loginChecker.getUser(), content, price, place));
	}
	
}
