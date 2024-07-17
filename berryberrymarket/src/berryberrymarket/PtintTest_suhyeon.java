package berryberrymarket;

public class PtintTest_suhyeon {

	public static void main(String[] args) {
//PostManager 테스트 클래스
		 PostManager postManager = new PostManager();
		 Post post1 = new Post("제목1", "사용자1", "내용1", 10000, "서울", "2024-07-17");
	     Post post2 = new Post("제목2", "사용자2", "내용2", 15000, "부산", "2024-07-18");
	  
	     	postManager.addPost(post1);
	        postManager.addPost(post2);
	     
	     	// 모든 게시글 출력
	        System.out.println("전체 게시글 목록:");
	        postManager.printPostList();

	        // 카테고리별 게시글 출력 (예: "서울" 카테고리 게시글 출력)
	        System.out.println("\n서울 카테고리 게시글 목록:");
	        postManager.printPostListByCategory("서울");

	        // 특정 게시글 상세 정보 출력 (예: 제목이 "제목1"인 게시글)
	        System.out.println("\n제목이 '제목1'인 게시글 상세 정보:");
	        postManager.printPost(post1);

	        // 게시글 삭제 (예: 제목이 "제목2"인 게시글 삭제)
	        postManager.removePost("제목2");

	        // 삭제 후 모든 게시글 출력
	        System.out.println("\n게시글 삭제 후 목록:");
	        postManager.printPostList();
	    }
}
