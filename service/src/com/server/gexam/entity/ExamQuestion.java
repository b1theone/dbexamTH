package com.server.gexam.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.server.core.entity.CoreEntity;

@Entity
@Table(name = "EXAM_QUESTION")
public class ExamQuestion extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "questionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "indicatorId")
	private Indicator indicator;
	@Column(name = "questionTitle")
	private String title;
	@Column(name = "question")
	private String question;
	@Column(name = "questionScore")
	private int score;
	@Column(name = "questionType")
	private String questionType;
	@Column(name = "rateType")
	private String rateType;
	@Column(name = "createType")
	private String createType;

	@Transient
	private List<ExamQuestionAnswer> answerList;
	@Transient
	private List<ExamQuestionImage> imageList;

	public ExamQuestion() {
	}

	public ExamQuestion(int id) {
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Indicator getIndicator() {
		return indicator;
	}

	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	@Transient
	public List<ExamQuestionAnswer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<ExamQuestionAnswer> answerList) {
		this.answerList = answerList;
	}

	@Transient
	public List<ExamQuestionImage> getImageList() {
		return imageList;
	}

	public void setImageList(List<ExamQuestionImage> imageList) {
		this.imageList = imageList;
	}

	public String getCreateType() {
		return createType;
	}

	public void setCreateType(String createType) {
		this.createType = createType;
	}

}
