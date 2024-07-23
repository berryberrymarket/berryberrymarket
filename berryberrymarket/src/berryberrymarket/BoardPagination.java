package berryberrymarket;

import java.util.List;

/* 페이징 클래스
 * 해당 프로젝트에서는 게시글 출력에서만 쓰임.
 * 필드: 현재 페이지, 전체페이지 수
 * 메소드: 현재 페이지의 게시글리스트 반환, 이전페이지, 다음페이지 */
public class BoardPagination{
	
	private int curPage = 1;
	private int pageSize = 1;
	
	public BoardPagination() {
		super();
	}

	public List<Post> currentPage(List<Post> board){ //현재페이지 게시글리스트 반환 
		
		int startIndex = (curPage-1)*10;
		int endIndex = curPage*10;
		pageSize = (board.size()-1)/10+1;
		if(endIndex>board.size()) {
			return board.reversed().subList(startIndex, board.size());
		}
		return board.reversed().subList(startIndex, endIndex);
	}

	void nextPage() {
		curPage++;
		if(curPage>pageSize) {
			curPage=pageSize;
		}
	}

	void prevPage() {
		curPage--;
		if(curPage<=0) {
			curPage=1;
		}
		
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
