package berryberrymarket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostManager {

	private List<Post> postList = new ArrayList<>();
	///////////////////////////////////////////
	public void printPost(int index) { // 게시글 상세페이지
		  if (index >= 0 && index < postList.size()) {
		        postList.get(index).printInfo();
		    } else {
		        System.out.println("해당 인덱스의 게시글이 존재하지 않습니다.");
		    }
	}
	public void printPostList() { // 게시글 리스트 목록 쫙~
		for (Post post : postList) {
	        post.printInfo();
	    }
	}
	public void printPostListByCateogry(String category) {
		 List<Post> filteredPosts = postList.stream()
		            .filter(post -> post.getTitle().contains(category) || post.getContent().contains(category))
		            .collect(Collectors.toList());

		    if (filteredPosts.isEmpty()) {
		        System.out.println("해당 카테고리에 해당하는 게시글이 없습니다.");
		    } else {
		        System.out.println("카테고리 '" + category + "' 검색 결과:");
		        for (Post post : filteredPosts) {
		            post.printInfo();
		        }
		    }
		}
	public void addPost() {
		
	}
	public void removePost() {
		
	}
	public void updatePost() {
		
	}
	public void nextPage() {
		
	}
	public void prevPage() {
		
	}
		

}
