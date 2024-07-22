package berryberrymarket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4520338998642408106L;
	private String title;
	private String nickname;
	private String content;
	private int price;
	private String place;
	private int hit;
	private LocalDateTime date;

	public Post() {

	}

	public Post(String title, String nickname, String content, int price, String place) {
		super();
		this.title = title;
		this.nickname = nickname;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	      System.out.printf("%-11s: ","작성자");
	      System.out.println(nickname);	////////////////////////수현 수정/////수현 수정////////////
	      System.out.printf("%-11s: ","조회수");
	      System.out.println(hit);
	      System.out.printf("%-12s: ","내용");//////////////////////수현 수정/////수현 수정////////////
	      System.out.println(content);//////////////////////수현 수정/////수현 수정////////////
	      System.out.printf("%-12s: ","가격");
	      System.out.println(price);	
	      System.out.printf("%-8s: ","거래 희망 장소");
	      System.out.println(place);	
	      System.out.printf("%-10s: ","작성 날짜");
	      System.out.println(date.format(DateTimeFormatter.ofPattern("yy.MM.dd E HH:mm")));	
//	      System.out.println("조회수: "+ hit);
//	      System.out.println("가격           : " + price);
//	      System.out.println("거래 희망 장소    : " + place);
//	      System.out.println("작성 날짜        : " + date.format(DateTimeFormatter.ofPattern("yy.MM.dd E HH:mm")));
	   }

	//public void printSimpleInfo() {
	//	System.out.println("  "+title+"  "+nickname+"  "+date.format(DateTimeFormatter.ofPattern("yy.MM.dd E HH:mm"))+"  "+hit);
	//}
	////////////////////////////////////////////////수현 수정 ////////////////////수현 수정//////////////수현 수정///////////////수현 수정///////
	public String getFormattedTitle() {
        final int MAX_LENGTH = 15; // 제목의 최대 길이 설정
        if (title == null) {
            title = "";
        }
        return String.format("%-10s", title.length() > MAX_LENGTH ? title.substring(0, MAX_LENGTH)+ "..." : title);
    }

    public String getFormattedNickname() {
        final int MAX_LENGTH = 6; // 닉네임의 최대 길이 설정
        if (nickname == null) {
            nickname = "";
        }
        return String.format("%-10s", nickname.length() > MAX_LENGTH ? nickname.substring(0, MAX_LENGTH) : nickname);
    }
    public void printSimpleInfo() {
        System.out.printf("%-1s%-20s%-10s%s\n", "", getFormattedTitle(), getFormattedNickname(), date.format(DateTimeFormatter.ofPattern("yy.MM.dd E HH:mm")));
    }
    
    ////////////수정전/////
}
