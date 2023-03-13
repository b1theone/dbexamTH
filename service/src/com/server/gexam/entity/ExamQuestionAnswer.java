package com.server.gexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.server.core.entity.CoreEntity;

@Entity
@Table(name = "EXAM_QUESTION_ANSWER")
public class ExamQuestionAnswer extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "questionAnswerId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "questionId")
	private ExamQuestion examQuestion;
	@Column(name = "answerSeq")
	private int seq;
	@Column(name = "answer")
	private String answer;
	@Column(name = "answerYN")
	private String answerYn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExamQuestion getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswerYn() {
		return answerYn;
	}

	public void setAnswerYn(String answerYn) {
		this.answerYn = answerYn;
	}

}
