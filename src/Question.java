import java.io.Serializable;

public class Question implements Serializable {//so that it can be written in .dat file
	
	//declare the question text and the correct answer and other 3 answers
	private String questionText;
	private String correctAns;
	private String ansOne;
	private String ansTwo;
	private String ansThree;
	
	public Question(String questionText, String correctAns,
					String ansOne, String ansTwo, String ansThree) {
		this.questionText = questionText;
		this.correctAns   = correctAns;
		this.ansOne 	  = ansOne;
		this.ansTwo 	  = ansTwo;
		this.ansThree 	  = ansThree;

	}
	/* getters and setters for the fields */
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getCorrectAns() {
		return correctAns;
	}

	public void setCorrectAns(String correctAns) {
		this.correctAns = correctAns;
	}

	public String getAnsOne() {
		return ansOne;
	}

	public void setAnsOne(String ansOne) {
		this.ansOne = ansOne;
	}

	public String getAnsTwo() {
		return ansTwo;
	}

	public void setAnsTwo(String ansTwo) {
		this.ansTwo = ansTwo;
	}

	public String getAnsThree() {
		return ansThree;
	}

	public void setAnsThree(String ansThree) {
		this.ansThree = ansThree;
	}
	//toString to represent the question object as String
	@Override
	public String toString() {
		return "Question: " + this.questionText + ", Its correct answer is: "+ this.correctAns 
				+", other answer are: [" + this.ansOne +", "+this.ansTwo+", "+this.ansThree+"]";
	}

}
