package berryberrymarket;

import java.util.List;

public class BoardPagination extends Pagination{
	
	public BoardPagination() {
		super();
	}

	public static List<Post> currentPage(List<Post> board){
		int startIndex = (curPage-1)*10;
		int endIndex = curPage*10;
		if(endIndex>board.size()) {
			return board.subList(startIndex, board.size());
		}
		return board.subList(startIndex, endIndex);
	}

	@Override
	void getNextPage() {
		curPage++;
	}

	@Override
	void getPrevPage() {
		curPage--;
		
	}
}
