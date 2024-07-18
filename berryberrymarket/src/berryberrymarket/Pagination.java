package berryberrymarket;

public abstract class Pagination {

	protected final int pageSize = 10;
	protected static int curPage = 1;
	protected int boardSize;
	
	abstract void getNextPage();
	abstract void getPrevPage();
}
