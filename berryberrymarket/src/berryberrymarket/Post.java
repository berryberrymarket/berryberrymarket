package berryberrymarket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import userPackage.model.User;


/* 게시글 객체 클래스
 * 필드: 제목, 작성 유저, 내용, 가격, 거래 장소, 조회수, 작성 날짜
 * 메소드: printInfo() - 객체 정보출력
 *         printSimpleInfo() - 게시판페이지에 쓰이는 객체 간단 출력 */
public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4520338998642408106L;
	private String title;
	private User user;
	private String content;
	private int price;
	private String place;
	private int hit;
	private LocalDateTime date;

	public Post() {

	}

	public Post(String title, User user, String content, int price, String place) {
		super();
		this.title = title;
		this.user = user;
		this.content = content;
		this.price = price;
		this.place = place;
		this.hit = 0;
		this.date = LocalDateTime.now();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void printInfo() {

	      System.out.printf("%-12s: ","제목");
	      System.out.println(title);	      
	      System.out.printf("%-7s: ","작성자(점수)");
	      System.out.println(user.getNick()+"("+user.getUserLevel()+")");
	      System.out.printf("%-11s: ","조회수");
	      System.out.println(hit);
	      System.out.printf("%-12s: ","가격");
	      System.out.println(price);	
	      System.out.printf("%-8s: ","거래 희망 장소");
	      System.out.println(place);	
	      System.out.printf("%-10s: ","작성 날짜");
	      System.out.println(date.format(DateTimeFormatter.ofPattern("MM.dd E HH:mm")));
	      System.out.println("-----------------------------------------------------------------");
	      System.out.printf("%-12s: ","내용");
	      System.out.println(content);
	      System.out.println();
	      System.out.println();
	      
	   }

	
	private int calculateDisplayWidth(String text) { //실제 폭을 계산합니다
        int width = 0;
        for (char c : text.toCharArray()) {
            if (isKorean(c)) {
                width += 2; // 한글은 2자리 폭을 차지
            } else {
                width += 1; // 영어, 숫자, 특수문자는 1자리 폭을 차지
            }
        }
        return width;
    }

    private boolean isKorean(char c) {
        return (c >= '가' && c <= '힣');
    }

    private String padRight(String text, int totalWidth) {
        int currentWidth = calculateDisplayWidth(text);
        StringBuilder paddedText = new StringBuilder(text);
        while (currentWidth < totalWidth) {
            paddedText.append(" ");
            currentWidth++;
        }
        return paddedText.toString();
    }
    
    
    public String getFormattedTitle() {
        final int MAX_WIDTH = 30; // 제목의 최대 폭 설정
        if (title == null) {
            title = "";
        }
        int width = calculateDisplayWidth(title);
        if (width > MAX_WIDTH) {
            // 폭이 넘으면 줄임표 추가
            StringBuilder truncatedTitle = new StringBuilder();
            int currentWidth = 0;
            for (char c : title.toCharArray()) {
                int charWidth = isKorean(c) ? 2 : 1;
                if (currentWidth + charWidth + 3 > MAX_WIDTH) { // 줄임표(...) 고려
                    truncatedTitle.append("...");
                    break;
                }
                truncatedTitle.append(c);
                currentWidth += charWidth;
            }
            return padRight(truncatedTitle.toString(), MAX_WIDTH);
        }
        return padRight(title, MAX_WIDTH);
    }

    public String getFormattedNickname() {
        final int MAX_WIDTH = 15; // 닉네임의 최대 폭 설정
        if (user.getNick() == null) {
            user.setNick(" ");
        }
    	int width = calculateDisplayWidth(user.getNick());
    	if (width > MAX_WIDTH) {
    		// 폭이 넘으면 줄임표 추가
    		StringBuilder truncatedNickname = new StringBuilder();
    		int currentWidth = 0;
    		for (char c : user.getNick().toCharArray()) {
    			int charWidth = isKorean(c) ? 2 : 1;
    			if (currentWidth + charWidth + 3 > MAX_WIDTH) { // 줄임표(...) 고려
    			truncatedNickname.append(" ");
    				break;
    			}
    			truncatedNickname.append(c);
    			currentWidth += charWidth;
    		}
    		return padRight(truncatedNickname.toString(), MAX_WIDTH);
    	}
    	return padRight(user.getNick(), MAX_WIDTH);
    }

    
    public void printSimpleInfo(int index) {
        String titleFormatted = getFormattedTitle();
        String nicknameFormatted = getFormattedNickname();
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("MM.dd E HH:mm"));
        
        System.out.printf("| %-3d|%-15s| %-7s %s\n", index, titleFormatted, nicknameFormatted.trim(), dateFormatted);
    }


	
	
	
	
	
	
	
}
