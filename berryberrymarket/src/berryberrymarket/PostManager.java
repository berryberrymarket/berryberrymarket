package berryberrymarket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostManager {

	private List<Post> postList = new ArrayList<>();
	///////////////////////////////////////////
	public void printPost(Post post) { // 게시글 상세페이지
		 post.printInfo();
	}
	
	public void printPostList() { // 게시글 리스트 목록 쫙~
	
		
		 if (postList.isEmpty()) {
	            System.out.println("등록된 게시글이 없습니다.");
	        } else {
	            System.out.println("전체 게시글 목록:");
	            for (Post post : postList) {
	                post.printInfo();
	            }
	        }
	    }
	public void printPostListByCategory(String category) {
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
	
	public void addPost(Post post) {
		 postList.add(post);
	}
	public void removePost(String title) {
		boolean removed = postList.removeIf(post -> post.getTitle().equals(title));
        if (removed) {
            System.out.println("게시글 '" + title + "'이(가) 삭제되었습니다.");
        } else {
            System.out.println("게시글 '" + title + "'이(가) 존재하지 않습니다.");
        }
    }
	public void updatePost(Post updatedPost) {
		 for (Post post : postList) {
	            if (post.getTitle().equals(updatedPost.getTitle())) {
	                // 예시로 제목을 기준으로 업데이트 처리
	                post.setContent(updatedPost.getContent());
	                post.setPrice(updatedPost.getPrice());
	                post.setPlace(updatedPost.getPlace());
	                post.setDate(updatedPost.getDate());
	                System.out.println("게시글 '" + updatedPost.getTitle() + "'이(가) 업데이트 되었습니다.");
	                return;
	            }
	        }
	        System.out.println("게시글 '" + updatedPost.getTitle() + "'이(가) 존재하지 않습니다.");
	    }
	
	public void nextPage() {
		// 다음 페이지로 이동하는 기능 구현
	}
	public void prevPage() {
		  // 이전 페이지로 이동하는 기능 구현
	}
	
		

}
