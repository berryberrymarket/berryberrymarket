package berryberrymarket;

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
