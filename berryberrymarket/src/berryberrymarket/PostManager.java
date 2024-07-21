package berryberrymarket;

import java.io.EOFException;
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
	private List<Post> filteredBoard = new ArrayList<>();
	private BoardPagination boardPagination = new BoardPagination();
	String nowPath = System.getProperty("user.dir");
	File path = new File(nowPath, "post");
	
	
	public void initGetBoard() {
		try {
			if (!path.exists()) {
				path.mkdirs();
				return;
			} else {
				FileInputStream fis = new FileInputStream(path+"/Post.dat");
		        ObjectInputStream ois = new ObjectInputStream(fis);

		        while (true) {
	                try {
	                    // 객체를 읽어와 리스트에 추가합니다
	                    Post post = (Post) ois.readObject();
	                    board.add(post);
	                } catch (EOFException e) {
	                    break;
	                }
	            }

		        ois.close(); fis.close();
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public String[] printPost(int index) { // 게시글 상세페이지
		Post post = filteredBoard.get(filteredBoard.size()-index);
		post.printInfo();
		String[] titleAndNick = new String[2];
		titleAndNick[0] = post.getTitle();
		titleAndNick[1] = post.getNickname();
		return titleAndNick;
	}

	public void printBoard(String search) { // 게시글 리스트 목록 쫙~
		AtomicInteger index = new AtomicInteger(boardPagination.getCurPage()*10-9);
		
		filteredBoard = board.stream()
				.filter(post -> post.getTitle().contains(search) || post.getContent().contains(search))
				.collect(Collectors.toList());
		
		if (board.isEmpty()) {
			System.out.println("등록된 게시글이 없습니다.");
		} else {
			List<Post> subBoard = boardPagination.currentPage(filteredBoard);
			subBoard.stream().forEach(n-> 
			{int curIndex = index.getAndIncrement();
			System.out.print(curIndex+".");
			n.printSimpleInfo();
			});
		}
	}

	public void addPost(Post post) throws FileNotFoundException {

		try {
			OutputStream os = new FileOutputStream(path+"/Post.dat");
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
		boolean removed = false;
		for(Post post:board) {
			if(post.getTitle().equals(title)) {
				board.remove(post);
				removed=true;
				break;
			}
		}
		if (removed) {
			
		} else {
			System.out.println("게시글 '" + title + "'이(가) 존재하지 않습니다.");
		}
		
		try {
			OutputStream os = new FileOutputStream(path+"/Post.dat");
			ObjectOutputStream oos = new ObjectOutputStream(os);
			
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


	public void nextPage() {
		boardPagination.nextPage();
	}

	public void prevPage() {
		boardPagination.prevPage();
	}

	public int getCurPage() {
		return boardPagination.getCurPage();
	}
	
	public int getPageSize() {
		return boardPagination.getPageSize();
	}

	public boolean compareIndex(int index) {
		if(index>board.size() || index<1) {
			System.out.println("인덱스가 없습니다. 다시 입력하세요:");
			return false;
		}else {
			return true;
		}
	}

	public void incHit(int index) {
		Post post = filteredBoard.get(filteredBoard.size()-index);
		post.setHit(post.getHit()+1);
		
	}
}
