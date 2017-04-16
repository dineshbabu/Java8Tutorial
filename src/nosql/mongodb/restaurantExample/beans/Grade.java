package nosql.mongodb.restaurantExample.beans;

import java.time.LocalDateTime;

public class Grade {
	private LocalDateTime dateTime;
	private String grade;
	private int score;
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
