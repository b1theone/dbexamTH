package com.web.taglib.option;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.beanutils.PropertyUtils;

public class OptionsJSP extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected List<?> property = null;
	protected String value = null;
	protected String label = null;
	protected String selected = null;
	protected String local = null;

	public List<?> getProperty() {
		return property;
	}

	public void setProperty(List<?> property) {
		this.property = property;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			if (property != null) {
				Iterator<?> collIterator = property.iterator();
				while (collIterator.hasNext()) {
					Object bean = collIterator.next();
					
					String lab = label;
					
					if (local != null && local.equalsIgnoreCase("Y")) {
						HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
						String local = (String) request.getSession().getAttribute("pLang");
						
						lab = "nameTh";
						
						if (local != null && local.equalsIgnoreCase("en")) {
							lab = "nameEn";
						}
					}

					Object objValue = PropertyUtils.getProperty(bean, value);
					Object objLabel = PropertyUtils.getProperty(bean, lab);

					if (selected != null && !selected.trim().equals("")) {
						if (selected.equals(objValue.toString())) {
							out.println("<option value='" + objValue.toString() + "' selected='selected'>" + objLabel.toString() + "</option>");
						} else {
							out.println("<option value='" + objValue.toString() + "'>" + objLabel.toString() + "</option>");
						}
					} else {
						out.println("<option value='" + objValue.toString() + "'>" + objLabel.toString() + "</option>");
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		property = null;
		value = null;
		label = null;
		selected = null;
		local = null;
	}
}
