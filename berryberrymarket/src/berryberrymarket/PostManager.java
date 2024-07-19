package berryberrymarket;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PostManager {
	private List<Post> board = new ArrayList<>();
	private BoardPagination boardPagination = new BoardPagination();
	
	public void initGetBoard() {
		try {
			// FileInputStream fis = new FileInputStream("C:/Edu/Temp/Post.dat"); -> 경로 관련 논의 필요
			String nowPath = System.getProperty("user.dir");
			File postListFile = new File(nowPath, "/Post.dat");
			if (!postListFile.exists()) {
				System.out.println("등록된 게시글이 없습니다.");
				return;
			} else {
				FileInputStream fis = new FileInputStream(postListFile);
		        ObjectInputStream ois = new ObjectInputStream(fis);

		        Post post = (Post) ois.readObject();

		        board.add(post);

		        ois.close(); fis.close();

		        System.out.println("전체 게시글 목록:");
		        board.stream().forEach(n->n.printSimpleInfo());
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public void printPost(int index) { // 게시글 상세페이지
		Post post = board.get(index-1);
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
	}

	public void printBoardByCategory(String category) {

		List<Post> filteredPosts = board.stream()
				.filter(post -> post.getTitle().contains(category) || post.getContent().contains(category))
				.collect(Collectors.toList());

		if (filteredPosts.isEmpty()) {
			System.out.println("필터링된 결과가 없습니다.");
		} else {
			filteredPosts.forEach(post -> {
				post.printSimpleInfo();
			});
		}
	}

	public void addPost(Post post) throws FileNotFoundException {

		try {
			OutputStream os = new FileOutputStream("C:/Edu/Temp/Post.dat");
			ObjectOutputStream oos = new ObjectOutputStream(os);

			board.add(post);
			board.stream().forEach(n->{
				try {
					oos.writeObject(n);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			oos.flush();
			oos.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removePost(String title) {
		boolean removed = board.removeIf(post -> post.getTitle().equals(title));
		if (removed) {
			System.out.println("게시글 '" + title + "'이(가) 삭제되었습니다.");
		} else {
			System.out.println("게시글 '" + title + "'이(가) 존재하지 않습니다.");
		}
	}

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

	public void nextPage() {
		boardPagination.nextPage();
	}

	public void prevPage() {
		boardPagination.prevPage();
	}

}
