package create;

public class Question {
	
	private String answer;
	private String userAnswer;
	private String title;
	private String description;	
	public int qNumber;
	
	
	public Question(String title , String description, String answer){
			
			this.answer = answer;
			this.title = title;
			this.description = description;
			this.qNumber = 0;
	}
	
		
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}



	public String getUserAnswer() {
		return userAnswer;
	}



	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

}
