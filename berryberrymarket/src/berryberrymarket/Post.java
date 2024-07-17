package berryberrymarket;

import java.io.Serializable;
import java.time.LocalDateTime;

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

	public void printInfo() {
		System.out.println("Title: " + title);
		System.out.println("Nickname: " + nickname);
		System.out.println("Content: " + content);
		System.out.println("Price: " + price);
		System.out.println("Place: " + place);
		System.out.println("Date: " + date);
	}

	public void printSimpleInfo() {
		System.out.println("\t"+title+"\t"+nickname+"\t"+date);
	}
}
