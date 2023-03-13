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
@Table(name = "EXAM_QUESTION_IMAGE")
public class ExamQuestionImage extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "questionImageId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "questionId")
	private ExamQuestion examQuestion;
	@Column(name = "imageId")
	private String imageId;
	@Column(name = "imageName")
	private String imageName;
	@Column(name = "unitName")
	private String unitName;
	@Column(name = "unit")
	private String unit;
	@Column(name = "amount")
	private int amount;
	@Column(name = "imageOnly")
	private String imageOnly;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public ExamQuestion getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(ExamQuestion examQuestion) {
		this.examQuestion = examQuestion;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getImageOnly() {
		return imageOnly;
	}

	public void setImageOnly(String imageOnly) {
		this.imageOnly = imageOnly;
	}

}
