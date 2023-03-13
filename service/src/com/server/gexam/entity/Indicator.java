package com.server.gexam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.server.core.entity.CoreEntity;

@Entity
@Table(name = "MAS_INDICATOR")
public class Indicator extends CoreEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "indicatorId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "indicatorName")
	private String name;
	@Column(name = "indicatorShortName")
	private String nameShort;

	public Indicator() {
	}

	public Indicator(int id) {
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

	public String getNameShort() {
		return nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

}
