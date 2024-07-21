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
		System.out.println("제목          : " + title);
		System.out.println("조회수        : "+ hit);
		System.out.println("작성자        : " + nickname);
		System.out.println("내용          : " + content);
		System.out.println("가격          : " + price);
		System.out.println("거래 희망 장소: " + place);
		System.out.println("등록 날짜     : " + date.format(DateTimeFormatter.ofPattern("yy.MM.dd E HH:mm")));
	}

	//public void printSimpleInfo() {
	//	System.out.println("  "+title+"  "+nickname+"  "+date.format(DateTimeFormatter.ofPattern("yy.MM.dd E HH:mm"))+"  "+hit);
	//}
	////////////////////////////////////////////////수현 수정 ////////////////////수현 수정//////////////수현 수정///////////////수현 수정///////
	public String getFormattedTitle() {
        final int MAX_LENGTH = 10; // 제목의 최대 길이 설정
        if (title == null) {
            title = "";
        }
        return String.format("%-10s", title.length() > MAX_LENGTH ? title.substring(0, MAX_LENGTH) : title);
    }

    public String getFormattedNickname() {
        final int MAX_LENGTH = 10; // 닉네임의 최대 길이 설정
        if (nickname == null) {
            nickname = "";
        }
        return String.format("%-10s", nickname.length() > MAX_LENGTH ? nickname.substring(0, MAX_LENGTH) : nickname);
    }
ㅇ0oOli1Li
    public void printSimpleInfo() {
        System.out.printf("%-5s%-20s%-20s%s\n", "", getFormattedTitle(), getFormattedNickname(), date.format(DateTimeFormatter.ofPattern("yy.MM.dd E HH:mm")));
    }
}
