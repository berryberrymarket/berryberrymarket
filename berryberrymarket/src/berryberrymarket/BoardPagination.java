package berryberrymarket;

import java.util.List;

public class BoardPagination{
	
	private int curPage = 1;
	
	public BoardPagination() {
		super();
	}

	public List<Post> currentPage(List<Post> board){
		int startIndex = (curPage-1)*10;
		int endIndex = curPage*10;
		if(endIndex>board.size()) {
			return board.subList(startIndex, board.size());
		}
		return board.subList(startIndex, endIndex);
	}

	void nextPage() {
		curPage++;
	}

	void prevPage() {
		curPage--;
		
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}
