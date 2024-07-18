package user;

import java.util.List;
import java.util.Scanner;

public class MyPage {
	private List<User> userList;
	Scanner sc = new Scanner(System.in);

	public MyPage() {
		this.userList = userList;

	}
	
	public void updateInfo() {
		
		boolean found = false;
		
		for (User user : userList) {
	        if (user.getId().equals(id)) {
	            found = true;
	                 
	    		System.out.print("수정 이름을 입력하세요=>");
	    		String name = sc.nextLine();
	            if (!name.isEmpty()) {
	                person.setName(name);
	            }
	            
	            
	            System.out.print("수정 나이를 입력하세요=>");
	    		String age = sc.nextLine();
	            if (!age.isEmpty()) {
	                
	                person.setAge(Integer.parseInt(age));
	            }
	            
	            System.out.print("수정 성별을 입력하세요.값:F(여자)M(남자)=>");
	    		String sex = sc.nextLine();
	            if (!sex.isEmpty()) {
	                person.setSex(sex);
	            }
	            
	           
	            break; // 수정이 완료되었으므로 반복문 종료
	        }
	    }
		
	}
}
