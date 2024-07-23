package berryberrymarket;

import java.io.FileNotFoundException;
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
	

	/* pageCase=1, 메인페이지 
	 * 중고거래 게시판 메인페이지를 출력합니다.*/
	public int printMainPage() {
		System.out.println("============================메인페이지============================");//네비게이션 출력 부분
		System.out.println("(M)마이페이지                                         (O)로그아웃");
		System.out.println("                     "+loginChecker.getUser().getNick()+"님. 반갑습니다!");
		System.out.println("      (S)검색                                         (P)등록");
		
		//printSmallHead("게시글");
		System.out.println("------------------------------게시글-----------------------------");
		System.out.println("| No |            제목              |    작성자   |    등록날짜  |");
		System.out.println("-----------------------------------------------------------------");
		
		pm.printBoard(search); //게시판 출력 부분
		int curPage = pm.getCurPage(); //게시판 페이지 계산 및 출력 부분
		int pageSize = pm.getPageSize();
		if(curPage==1 && pageSize==curPage) {
			System.out.println("                             "+pm.getCurPage()+"                  ");
		}else if(curPage==1) {
			System.out.println("                             "+pm.getCurPage()+"                     다음페이지(>)");
		}else if(curPage==pageSize) {
			System.out.println("(<)이전페이지                "+pm.getCurPage()+"                  ");
		}else {
			System.out.println("(<)이전페이지                "+pm.getCurPage()+"                     다음페이지(>)");
		} 
		System.out.println("(H)홈으로");
		printTail();
		String in = sc.nextLine().trim().toLowerCase();
		switch (in) {
		case "m": //마이페이지로 이동
			return 4;
		case "o": //로그아웃
			loginChecker.setUser(null);
			UserLogoutPage.logOut();
			return 2;
		case "<": //이전페이지
			pm.prevPage();
			return 1;
		case ">": //다음페이지
			pm.nextPage();
			return 1;
		case "s": //검색하기
			System.out.println("검색할 내용을 입력하세요: ");
			search = sc.nextLine();
			return 1; 
		case "p": //게시글 등록 페이지로 이동
			return 6;
		case "h": //홈(메인페이지)로 이동
			search="";
			return 1;
		default:
			try { //유효한 게시글 인덱스를 입력받아 게시글 상세 페이지로 이동
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
				
			} catch(NumberFormatException e) { //숫자형 예외처리
				System.out.println("다시 입력하세요: ");
				return 1;
			}

		}
	}
	
	/* pageCase=2, 로그인 페이지
	 * 로그인 or 회원가입 가능*/
	public int printLogInPage() throws InterruptedException {
		PrintAscii.printStrawberry();
		System.out.println("===========================로그인페이지==========================");
		System.out.println("(1)로그인");
		System.out.println("(2)회원가입");
		printTail();
		String in = sc.nextLine();
		switch (in) {
		case "1": //로그인
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
		case "2": //회원가입 페이지로 이동
			return 3;
		default: //뭘눌러도 다시 로그인 페이지로 이동
			return 2;
		}
	}

	/* pageCase=3, 회원가입 페이지
	 * 회원가입 메소드를 호출하여 회원가입*/
	public int printSignUpPage() {
		//printHead("회원가입페이지");
		System.out.println("==========================회원가입페이지=========================");
		UserSignUpPage sp = new UserSignUpPage();

		try {
			sp.signUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 2;
	}

	
	/* pageCase=4, 마이페이지
	 * 내 정보 수정, 회원 탈퇴 가능*/
	public int printMyPage() {
		//printHead("마이페이지");
		System.out.println("=============================마이페이지============================");
		UserMyInfoPage myPage = new UserMyInfoPage();
		while (true) {
			myPage.printInfo();
			System.out.printf("원하는 메뉴키를 입력하세요: ");
			String keyPress = sc.nextLine();
			switch (keyPress) {
				case "a","A" -> { //내정보 수정
					return 9;
					}
				case "d", "D" -> { //회원 탈퇴
					UserDeletePage udp = new UserDeletePage();
					int deleteResult = udp.userDelete(sc);
					myPage = null;
					return deleteResult;
				}
				case "b","B" -> { //뒤로가기(메인 페이지)
					return 1;
				}
				default -> {
					System.out.println("올바른 키를 입력하세요.");
				}
			}
		}
	}

	
	/* pageCase=5, 게시글 상세 페이지 
	 * 내가 작성한 글이면, 게시글 수정 삭제 가능
	 * 내가 작성한 글이 아니면, 채팅하기 가능*/
	public int printPostDetailPage() throws FileNotFoundException {
		pm.incHit(index);
		//printHead("게시글상세페이지");
		System.out.println("=========================게시글상세페이지========================");
		Post post = pm.printPost(index); //인덱스에 맞는 게시글 출력
		if(loginChecker.getUser().getNick().equals(post.getUser().getNick())) {
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
			case "b": //뒤로가기(메인페이지)
				return 1;
			case "d": //삭제
				pm.removePost(post);
				return 1;
			case "u": //수정(기존 post 객체 삭제 후, 수정된 post 객체 추가
				pm.removePost(post);
				System.out.println("----수정되었습니다----");
				setPostInfo(sc);
				return 1;
			case "c": //채팅페이지로 이동
				return 8;
			case "t": //거래완료 페이지로 이동
				return 7;
			default:
				System.out.println("다시 메뉴키를 입력하세요");
			}
		}
	}

	
	/* pageCase=6, 게시글 등록 페이지 */
	public int printAddPostPage() throws FileNotFoundException {
		//printHead("게시글등록페이지");
		System.out.println("=========================게시글등록페이지========================");
		setPostInfo(sc);

		return 1;
	}

	
	/* pageCase=7, 거래완료 페이지
	 * transaction 객체 생성함. 상대방 평가 가능 */
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
		            if (star < -5 || star > 5) { //별점 유효성 검사
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
			tm.createTransaction(star, post); //Transaction 인스턴스에는 별점과 게시글 인스턴스가 담김
			List<User> userList = ulm.getUserList(); 
			tm.evaluateTransaciton(userList); //상대방 평가
			ulm.updateUserList(); //별점과 거래횟수 수정 -> 유저리스트 업데이트
			
		}
		return 1;
	}

	
	/* pageCase=8, 채팅방 페이지
	 * 소켓을 이용하여 서버와 TCP 통신함
	 * 채팅 완료되면 게시글 상세 페이지로 이동*/
	public int printChatRoomPage() {
	    //printHead("채팅방페이지");
		System.out.println("==========================채팅방페이지==========================");
	    System.out.println("채팅을 시작합니다...");

	    Client.startChat();
	    
	    return 5;
	}
	
	/* pageCase=9, 회원정보 수정 페이지
	 * 회원 정보들 수정 가능*/
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
		case "1","2","3","4","5": //회원 정보를 수정
			UserUpdatePage uu = new UserUpdatePage(); 
			uu.updateUserInfo(in); //해당 정보 수정
			return 9; //이 페이지로 다시 옴
		case "0":
			return 4;

		default: 
			System.out.println("잘못 입력하셨습니다.");
			return 9;
		
		}
		 
	}


	
	private void printTail() { //페이지 꼬리 부분 출력 메소드
		System.out.println("=================================================================");
		System.out.print("원하는 메뉴키를 입력하세요: ");
	}

	private void setPostInfo(Scanner sc) throws FileNotFoundException { //post객체 등록 메소드
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

