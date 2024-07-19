package berryberrymarket;

import java.util.List;

public class BoardPagination{
	
	private int curPage = 1;
	private int pageSize;
	
	public BoardPagination() {
		super();
	}

	public List<Post> currentPage(List<Post> board){
		
		int startIndex = (curPage-1)*10;
		int endIndex = curPage*10;
		pageSize = board.size()/10+1;
		if(endIndex>board.size()) {
			return board.reversed().subList(startIndex, board.size());
		}
		return board.subList(startIndex, endIndex);
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
			curPage=0;
		}
		
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}
