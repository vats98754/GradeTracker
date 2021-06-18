package gradetracker.data;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Test implements Serializable {

	private String topicName;
	private String type;
	private String date;
	private int level;
	private String score;
	private String outOf;
	private int weight;
	private String reflection;

	public Test(String topicName, String type, String date, int level, String score, String outOf, int weight,
			String reflection) {
		super();
		this.topicName = topicName;
		this.type = type;
		this.date = date;
		this.level = level;
		this.score = score;
		this.outOf = outOf;
		this.weight = weight;
		this.reflection = reflection;
	}

	public Double testPercentage() {
		String testPercentage = "";
		DecimalFormat df = new DecimalFormat("###.#");
		double percentage = (Double.parseDouble(getScore()) / Double.parseDouble(getOutOf())) * 100;
		testPercentage = df.format(percentage);
		return Double.parseDouble(testPercentage);
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getOutOf() {
		return outOf;
	}

	public void setOutOf(String outOf) {
		this.outOf = outOf;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getReflection() {
		return reflection;
	}

	public void setReflection(String reflection) {
		this.reflection = reflection;
	}

}