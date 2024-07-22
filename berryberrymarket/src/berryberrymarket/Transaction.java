package berryberrymarket;

/* 거래 객체 클래스
 * 필드: 거래 게시글 객체, 평점 */
public class Transaction {

	private int star;
	private Post post;

	public Transaction(int star,Post post) {
		this.star = star;
		this.post = post;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}
