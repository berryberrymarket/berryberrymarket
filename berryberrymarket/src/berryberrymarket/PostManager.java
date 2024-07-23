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

/* post객체 관리 클래스
 * post 추가, 삭제, 콘솔 출력, Post.dat 파일 입출력 기능 */
public class PostManager {
	private List<Post> board = new ArrayList<>();
	private List<Post> filteredBoard = new ArrayList<>();
	private BoardPagination boardPagination = new BoardPagination();
	String nowPath = System.getProperty("user.dir");
	File path = new File(nowPath, "post");
	
	
	public void initGetBoard() { //프로그램 실행시 Post.dat 파일로부터 객체들을 읽어와 리스트 처리
		try {
			if (!path.exists()) { //파일 경로에 폴더 없으면 폴더 생성
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
		} catch (FileNotFoundException e) {
			System.out.println("게시글 파일이 없습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public Post printPost(int index) { //입력 index값의 post객체의 정보 콘솔 출력
		Post post = filteredBoard.get(filteredBoard.size()-index);
		post.printInfo();
		return post;
	}
	
	public void printBoard(String search) { // 입력 search 값과 동일한 내용을 가진 post객체만 필터링하여 페이징 출력
		AtomicInteger index = new AtomicInteger(boardPagination.getCurPage()*10-9);
		
		filteredBoard = board.stream() //search와 동일한 키워드를 가진 board만 필터링
				.filter(post -> post.getTitle().contains(search) || post.getContent().contains(search))
				.collect(Collectors.toList());
		
		if (filteredBoard.isEmpty()) { //필터링된 값이 없으면
            System.out.println("등록된 게시글이 없습니다.");
        } else {
            List<Post> subBoard = boardPagination.currentPage(filteredBoard); //필터링 board의 페이지를 나눔. 
            subBoard.forEach(post -> post.printSimpleInfo(index.getAndIncrement())); //해당 post객체의 간단한 정보와 index 출력
            System.out.println("-----------------------------------------------------------------");
        }
    }
	
	public void addPost(Post post) throws FileNotFoundException { //board에 객체 추가 후 파일에 출력

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

	public void removePost(Post post) { //board에서 해당 객체 삭제
		boolean removed = false;
		for(Post post2:board) {
			if(post2.equals(post)) {
				board.remove(post);
				removed=true;
				break;
			}
		}
		if (removed) {
//			System.out.println("해당 게시글이 삭제 되었습니다.");
		} else {
			System.out.println("게시글 '" + post.getTitle() + "'이(가) 존재하지 않습니다.");
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
	
	public Post getPost(int index) {
		Post post = filteredBoard.get(filteredBoard.size()-index);
		return post;
	}
}
