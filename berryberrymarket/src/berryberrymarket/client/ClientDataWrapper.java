package berryberrymarket.client;


import java.io.Serializable;
import userPackage.model.User;


public class ClientDataWrapper implements Serializable {

	private static final long serialVersionUID = 3679196460660823712L;
	private User userObject;
    private String message;
    private boolean end;
    
    public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean endSignal) {
		this.end = endSignal;
	}

	public ClientDataWrapper(User userObject, String message) {
        this.userObject = userObject;
        this.message = message;
    }

    public User getUserObject() {
        return userObject;
    }
    
    public String getUserNick() {
    	return userObject.getNick();
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String msg) {
    	this.message = msg;
    }

}
