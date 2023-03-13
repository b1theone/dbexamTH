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
@Table(name = "EXAM_SUITE_QUESTION")
public class ExamSuiteQuestion extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "suiteQuestionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "suiteId")
	private ExamSuite examSuite;
	@ManyToOne
	@JoinColumn(name = "questionId")
	private ExamQuestion examQuestion;

	public ExamSuiteQuestion() {
	}

	public ExamSuiteQuestion(int id) {
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExamSuite getExamSuite() {
		return examSuite;
	}

	public void setExamSuite(ExamSuite examSuite) {
		this.examSuite = examSuite;
	}

	public ExamQuestion getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

}
