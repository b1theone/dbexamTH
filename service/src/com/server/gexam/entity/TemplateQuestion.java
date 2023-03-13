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
@Table(name = "MAS_TEMPLATE_QUESTION")
public class TemplateQuestion extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "templateQuestionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "indicatorId")
	private Indicator indicator;
	@Column(name = "templateName")
	private String name;
	@Column(name = "templateTitle")
	private String title;
	@Column(name = "question")
	private String question;
	@Column(name = "conditionType")
	private String conditionType;
	@Column(name = "parameter1")
	private int parameter1;
	@Column(name = "parameter2")
	private int parameter2;
	@Column(name = "defaultAmount")
	private int defaultAmount;
	@Column(name = "templateType")
	private String templateType;
	@Column(name = "randomType")
	private String randomType;
	@Column(name = "name1")
	private String name1;
	@Column(name = "name2")
	private String name2;
	@Column(name = "name3")
	private String name3;
	@Column(name = "name4")
	private String name4;
	@Column(name = "column1")
	private String column1;
	@Column(name = "column2")
	private String column2;
	@Column(name = "rateType")
	private String rateType;
	@Column(name = "calculateType")
	private String calculateType;

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

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
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

	public int getDefaultAmount() {
		return defaultAmount;
	}

	public void setDefaultAmount(int defaultAmount) {
		this.defaultAmount = defaultAmount;
	}

	public String getRandomType() {
		return randomType;
	}

	public void setRandomType(String randomType) {
		this.randomType = randomType;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getName3() {
		return name3;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

	public String getName4() {
		return name4;
	}

	public void setName4(String name4) {
		this.name4 = name4;
	}

	public String getColumn1() {
		return column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCalculateType() {
		return calculateType;
	}

	public void setCalculateType(String calculateType) {
		this.calculateType = calculateType;
	}

}
