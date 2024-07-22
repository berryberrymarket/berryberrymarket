package berryberrymarket;

import java.util.List;

import userPackage.model.User;

public class TransactionManager {

	Transaction transaction;
	
	public void createTransaction(int star,Post post) {
		this.transaction = new Transaction(star,post);
	}
	
	public void evaluateTransaciton(List<User> userList) {
		User user = transaction.getPost().getUser();
		System.out.println("ㅎㅇ");
		userList.stream().filter(n->n.equals(user)).forEach(u->{
			u.printInfo();
			u.setTransactionsCnt(u.getTransactionsCnt()+1);
			u.setUserLevel(u.getUserLevel()+transaction.getStar());
			});
		
	}
}
