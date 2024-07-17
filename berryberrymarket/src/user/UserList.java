package user;

// 자바 리스트 관련 import
import java.util.List;
import java.util.ArrayList;

// 파일 처리 관련 import
import java.io.Writer;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

// 엑셀 처리 관련 추가 import
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class UserList {
	private List<User> userList;
	String nowPath = System.getProperty("user.dir");
	
	/*
	 * 구현중
	 */
	
	public void addUser() {
		
	}
	
}
