package com.web.taglib.date;

import java.util.Date;
import java.util.Locale;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.util.DateTimeUtil;

public class DateFormat extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Date date = null;
	protected String format = null;
	protected String local = null;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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
			if (date != null) {
				Locale locale = null;
				if (local != null) {
					if (local.equalsIgnoreCase("th")) {
						locale = new Locale("th", "TH");
					} else if (local.equalsIgnoreCase("en")) {
						locale = new Locale("en", "US");
					}
				} else {
					locale = new Locale("th", "TH");
				}

				String fmt = "dd/MM/yyyy";
				if (format != null) {
					fmt = format;
				}

				out.println(DateTimeUtil.parseOutputCalendar(date, fmt, locale));
			} else {
				out.println("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		date = null;
		format = null;
		local = null;
	}

}
