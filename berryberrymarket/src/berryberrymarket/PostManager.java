package berryberrymarket;


import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class PostManager {
	private List<Post> board = new ArrayList<>();
	private BoardPagination boardPagination = new BoardPagination();
  
	 public PostManager() {
	        initGetBoard(); // 생성자에서 게시글 데이터 로드
	    }
	 @SuppressWarnings("unchecked")
	 public void initGetBoard() {////////////////수정 //수현// 게시글 목록 초기화 메소드
        try {
            File file = new File("C:\\Edu\\Temp\\Post.dat");
            if (!file.exists()) {
                // 파일이 없을 경우 파일을 생성하고 기본 게시글을 추가합니다.
                file.getParentFile().mkdirs();  // 디렉토리 생성
                file.createNewFile();  // 파일 생성
                addInitialPosts();
                saveBoard();
            } else {
                // 파일이 이미 존재할 경우 저장된 게시글을 읽어옵니다.
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                board = (List<Post>) ois.readObject();  // 파일에서 게시글 목록을 읽어옴
                ois.close();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	 
	// 초기 게시글 추가 메소드
	    private void addInitialPosts() {
	        board.add(new Post("Welcome Post", "admin", "This is a welcome post!", 0, "Welcome Board"));
	    }
    
    // 게시글 목록 저장 메소드
    public void saveBoard() {
        try {
            FileOutputStream fos = new FileOutputStream("C:/Edu/Temp/Post.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(board);// 게시글 목록을 파일에 저장
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printPostDetailPage(Scanner sc) {
        System.out.print("확인할 게시글의 번호를 입력하세요: ");
        int postIndex = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기

        // 인덱스에 해당하는 게시글 가져오기
        Post post = getPostByIndex(postIndex);
        if (post != null) {
            post.printInfo(); // 게시글 상세 정보 출력
        } else {
            System.out.println("해당 번호의 게시글을 찾을 수 없습니다.");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////
	public void printPost(Post post) { // 게시글 상세페이지
		post.printInfo();
	}


	public void printBoard() { // 게시글 리스트 목록 쫙~
		AtomicInteger index = new AtomicInteger(boardPagination.getCurPage()*10-9);
		
		if (board.isEmpty()) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			List<Post> subBoard = boardPagination.currentPage(board);
			subBoard.stream().forEach(n-> 
			{int curIndex = index.getAndIncrement();
			System.out.print(curIndex+".");
			n.printSimpleInfo();
			});
		}

	public void printBoardByCategory(String category) {

		List<Post> filteredPosts = board.stream()
				.filter(post -> post.getTitle().contains(category) || post.getContent().contains(category))
				.collect(Collectors.toList());

		if (filteredPosts.isEmpty()) {
			System.out.println("필터링된 결과가 없습니다.");
		} else {
			
		    System.out.println("검색된 게시글 목록:");
            filteredPosts.forEach(post -> {
                post.printSimpleInfo(); // Post 클래스의 printSimpleInfo() 메서드 호출
            });
        }
    }
	// 게시글을 추가하는 메서드
	public void addPost(Post post) throws FileNotFoundException {

	    try {
            OutputStream os = new FileOutputStream("C:/Edu/Temp/Post.dat");
            ObjectOutputStream oos = new ObjectOutputStream(os);

            board.add(post); // 게시글 목록에 게시글 추가
            oos.writeObject(board); // 게시글 목록을 파일에 저장
            oos.flush();
            oos.close();
            os.close();
            opCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	// 제목을 기준으로 게시글을 삭제하는 메서드

	public void removePost(String title) {
		boolean removed = board.removeIf(post -> post.getTitle().equals(title));
		if (removed) {
			System.out.println("게시글 '" + title + "'이(가) 삭제되었습니다.");
		} else {
			System.out.println("게시글 '" + title + "'이(가) 존재하지 않습니다.");
		}
	}
	// 게시글을 업데이트하는 메서드
	public void updatePost(Post updatedPost) {
		for (Post post : board) {
			if (post.getTitle().equals(updatedPost.getTitle())) {
				// 예시로 제목을 기준으로 업데이트 처리
				post.setContent(updatedPost.getContent());
				post.setPrice(updatedPost.getPrice());
				post.setPlace(updatedPost.getPlace());
				post.setDate(updatedPost.getDate());
				System.out.println("게시글 '" + updatedPost.getTitle() + "'이(가) 업데이트 되었습니다.");
				return;
			}
		}
		System.out.println("게시글 '" + updatedPost.getTitle() + "'이(가) 존재하지 않습니다.");
	}
	   // 인덱스 번호로 게시글을 가져오는 메서드
	public Post getPostByIndex(int index) {
	    if (index >= 1 && index <= board.size()) {
	        return board.get(index - 1); // 인덱스는 1부터 시작하지만, 리스트는 0부터 시작하므로 1을 뺍니다.
	    } else {
	        System.out.println("유효하지 않은 인덱스입니다.");
	        return null;
	    }
	}
    /////////////////////////////////////////////////////////////////////////////
	public void nextPage() {
		boardPagination.nextPage();
	}

	public void prevPage() {
		boardPagination.prevPage();
	}

}
