package com.server.gexam.entity;

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
@Table(name = "MAS_TEMPLATE_IMAGE")
public class TemplateImage extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "templateImageId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "templateQuestionId")
	private TemplateQuestion templateQuestion;
	@Column(name = "imageId")
	private String imageId;
	@Column(name = "imageName")
	private String imageName;
	@Column(name = "unitName")
	private String unitName;
	@Column(name = "unit")
	private String unit;
	@Column(name = "unit2")
	private String unit2;
	@Column(name = "optionType")
	private String optionType;
	@Column(name = "parameter1")
	private int parameter1;
	@Column(name = "parameter2")
	private int parameter2;

	@Transient
	private int amount;
	@Transient
	private int item;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TemplateQuestion getTemplateQuestion() {
		return templateQuestion;
	}

	public void setTemplateQuestion(TemplateQuestion templateQuestion) {
		this.templateQuestion = templateQuestion;
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

	@Transient
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Transient
	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public String getUnit2() {
		return unit2;
	}

	public void setUnit2(String unit2) {
		this.unit2 = unit2;
	}

	public String getOptionType() {
		return optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	public int getParameter1() {
		return parameter1;
	}

	public void setParameter1(int parameter1) {
		this.parameter1 = parameter1;
	}

	public int getParameter2() {
		return parameter2;
	}

	public void setParameter2(int parameter2) {
		this.parameter2 = parameter2;
	}

}
