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
		} catch (FileNotFoundException e) {
			System.out.println("게시글 파일이 없습니다.");
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
///////////////////////////////2차 수정/////////////////////////////////////////////////////////////////////
	public void printBoard(String search) { // 게시글 리스트 목록 쫙~
		AtomicInteger index = new AtomicInteger(boardPagination.getCurPage()*10-9);
		
		filteredBoard = board.stream()
				.filter(post -> post.getTitle().contains(search) || post.getContent().contains(search))
				.collect(Collectors.toList());
		
		if (filteredBoard.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.");
        } else {
            List<Post> subBoard = boardPagination.currentPage(filteredBoard);
          // System.out.println("------------------------------게시글-----------------------------");
          // System.out.printf("%-3s %-30s %-1s %s\n", "No.", "제목", "작성자", "등록날짜"); //게시판 헤더 출력
           // System.out.println("-----------------------------------------------------------------");
            subBoard.forEach(post -> post.printSimpleInfo(index.getAndIncrement()));
            System.out.println("-----------------------------------------------------------------");
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/*
	private int calculateMaxTitleWidth(List<Post> posts) {
        return posts.stream().mapToInt(post -> post.getFormattedTitle().length()).max().orElse(30);
    }

    private int calculateMaxNicknameWidth(List<Post> posts) {
        return posts.stream().mapToInt(post -> post.getFormattedNickname().length()).max().orElse(10);
    }

    public void printBoard(String search) {
        AtomicInteger index = new AtomicInteger(boardPagination.getCurPage() * 10 - 9);

        filteredBoard = board.stream()
                .filter(post -> post.getTitle().contains(search) || post.getContent().contains(search))
                .collect(Collectors.toList());

        if (filteredBoard.isEmpty()) {
            System.out.println("등록된 게시글이 없습니다.");
        } else {
            List<Post> subBoard = boardPagination.currentPage(filteredBoard);
            int maxTitleWidth = calculateMaxTitleWidth(subBoard);
            int maxNicknameWidth = calculateMaxNicknameWidth(subBoard);

         
            subBoard.forEach(post -> post.printSimpleInfo(index.getAndIncrement(), maxTitleWidth, maxNicknameWidth));
            System.out.println("-----------------------------------------------------------------");
        }
    }
	
	*/
	
	
	
	
	
	
	
	
	

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
			System.out.println("해당 게시글이 삭제 되었습니다.");
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
