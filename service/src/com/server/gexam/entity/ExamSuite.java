package com.server.gexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.server.core.entity.CoreEntity;

@Entity
@Table(name = "EXAM_SUITE")
public class ExamSuite extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "suiteId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "suiteName")
	private String name;
	@Column(name = "randomType")
	private String randomType;
	@Column(name = "scorePass")
	private int scorePass;
	@Column(name = "scoreMax")
	private int scoreMax;
	@Column(name = "scorePoint")
	private int scorePoint;
	@Column(name = "examTime")
	private int examTime;
	@Column(name = "totalQuestion")
	private int totalQuestion;
	@Column(name = "active")
	private String active;

	public ExamSuite() {
	}

	public ExamSuite(int id) {
		this.setId(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScorePass() {
		return scorePass;
	}

	public void setScorePass(int scorePass) {
		this.scorePass = scorePass;
	}

	public int getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(int scoreMax) {
		this.scoreMax = scoreMax;
	}

	public int getExamTime() {
		return examTime;
	}

	public void setExamTime(int examTime) {
		this.examTime = examTime;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getRandomType() {
		return randomType;
	}

	public void setRandomType(String randomType) {
		this.randomType = randomType;
	}

	public int getScorePoint() {
		return scorePoint;
	}

	public void setScorePoint(int scorePoint) {
		this.scorePoint = scorePoint;
	}

}
