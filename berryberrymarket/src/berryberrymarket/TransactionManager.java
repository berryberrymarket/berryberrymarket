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
	
	public void evaluateTransaciton(List<User> userList) {
		User user = transaction.getPost().getUser();
		userList.stream().filter(n->n.equals(user)).forEach(u->{
			
			u.setTransactionsCnt(u.getTransactionsCnt()+1);
			u.setUserLevel(u.getUserLevel()+transaction.getStar());
			});
		
	}
}
