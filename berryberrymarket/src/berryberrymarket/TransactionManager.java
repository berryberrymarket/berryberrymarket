package berryberrymarket;

import java.util.List;

import userPackage.model.User;

/* Transaction객체를 관리하는 클래스
 * 트랜잭션 객체 생성, 회원의 점수와 거래횟수 관리 */
public class TransactionManager {

	Transaction transaction;
	
	public void createTransaction(int star,Post post) {
		this.transaction = new Transaction(star,post);
	}
	
	public List<User> evaluateTransaciton(List<User> userList) {
		User user = transaction.getPost().getUser();
		System.out.println("작성자"+user.getNick());
		for(User users:userList) {
			if(users.getNick().equals(user.getNick())) {
//				users.setTransactionsCnt(users.getTransactionsCnt()+1);
//				users.setUserLevel(users.getUserLevel()+transaction.getStar());
				transaction.getPost().getUser().setTransactionsCnt(users.getTransactionsCnt()+1);
				transaction.getPost().getUser().setUserLevel(users.getUserLevel()+transaction.getStar());
			}
		}
		
		return userList;
	}
}
