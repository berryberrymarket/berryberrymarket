package userPackage.account;

//import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationUtils {
	
	 	private static final String SPECIAL_CHARACTERS = "!\"#$%&'()*+,-./:;?@[\\]^_`{|}~";
	    private static final String ID_REGEX = "[a-z0-9_-]{5,20}";
	    private static final String PASSWORD_REGEX = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[" + Pattern.quote(SPECIAL_CHARACTERS) + "])[a-zA-Z\\d" + Pattern.quote(SPECIAL_CHARACTERS) + "]{8,16}$";
	    private static final String PHONE_REGEX = "^\\d{3}-\\d{4}-\\d{4}$";

	    public static boolean isValidId(String id) {
	        return id.matches(ID_REGEX);
	    }

	    public static boolean isValidPassword(String password) {
	        return password.matches(PASSWORD_REGEX);
	    }

	    public static boolean isValidPhoneNumber(String phoneNumber) {
	        return phoneNumber.matches(PHONE_REGEX);
	    }

	    public static boolean isValidAddress(String address) {
	        return address.contains("시") || address.contains("군") || address.contains("구");
	    }
	    
	
	    
}
