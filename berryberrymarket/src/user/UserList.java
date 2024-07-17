package user;

// 자바 리스트 관련 import
import java.util.List;
import java.util.ArrayList;

// 파일 처리 관련 import
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

// 엑셀 처리 관련 추가 import
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class UserList {
	private List<User> userList;
	String nowPath = System.getProperty("user.dir");
	
//	public List<User> getUserListFromFile() {
	public void getUserListFromFile() {
		try  {
			FileInputStream fis = new FileInputStream(nowPath+"/userList.xlsx");
			ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xml")) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        baos.write(buffer, 0, len);
                    }
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(bais);
                    doc.getDocumentElement().normalize();

                    // 예를 들어 sheet1.xml에서 값을 읽기
                    if (entry.getName().contains("sheet1.xml")) {
                        NodeList nodeList = doc.getElementsByTagName("c"); // c는 셀(cell)을 의미합니다.
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            System.out.printf(nodeList.item(i).getTextContent());
                        }
                    }
                }
            }
            zis.closeEntry();
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addUser() {
		
	}
	
}
