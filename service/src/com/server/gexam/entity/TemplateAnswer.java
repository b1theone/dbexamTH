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
@Table(name = "MAS_TEMPLATE_ANSWER")
public class TemplateAnswer extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "templateAnswerId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "templateQuestionId")
	private TemplateQuestion templateQuestion;
	@Column(name = "answer")
	private String answer;
	@Column(name = "conditionType")
	private String conditionType;

	@Transient
	private String name;

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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
