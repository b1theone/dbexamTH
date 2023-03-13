package com.web.taglib.paging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.MessageResources;

import com.web.taglib.paging.util.Pagination;

public class PageNavigation extends TagSupport {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected Pagination paging = null;
	protected String tableId = null;
	protected String function = null;
	protected Boolean selectAll = true;
	protected String position = null;

	public Pagination getPaging() {
		return paging;
	}

	public void setPaging(Pagination paging) {
		this.paging = paging;
	}

	@Override
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		JspWriter out = pageContext.getOut();
		try {
			if (paging != null) {

				// Locale..
				HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
				String local = (String) request.getSession().getAttribute("pLang");

				String nLocal = "_th";
				if (local != null && local.equalsIgnoreCase("en")) {
					nLocal = "";
				}

				// Message resource..
				String view = MessageResources.getMessageResources("com.web.taglib.paging.resource.paging" + nLocal).getMessage("msg.paging.view");
				String page = MessageResources.getMessageResources("com.web.taglib.paging.resource.paging" + nLocal).getMessage("msg.paging.page");
				String all = MessageResources.getMessageResources("com.web.taglib.paging.resource.paging" + nLocal).getMessage("msg.paging.all");
				String next = MessageResources.getMessageResources("com.web.taglib.paging.resource.paging" + nLocal).getMessage("msg.paging.next");
				String prev = MessageResources.getMessageResources("com.web.taglib.paging.resource.paging" + nLocal).getMessage("msg.paging.prev");

				String pagePosition = "bottom";
				if (position != null) {
					pagePosition = position;
				}

				String eID = "";
				if (tableId != null) {
					out.println("<div>");
					eID = tableId;
				} else {
					out.println("<div>");
				}

				out.println("<script type=\"text/javascript\">");
				out.println("	function doReloadPage_" + pagePosition + "_" + eID + "(pg)");
				out.println("	{");
				if (pagePosition.equals("top")) {
					out.println("		var pDisplay = $('#" + eID + "').find('thead').find('.ipage-display').val();");
					out.println("		$('#" + eID + "').find('thead').find('.ipage-number').val(pg)");
				} else {
					out.println("		var pDisplay = $('#" + eID + "').find('tfoot').find('.ipage-display').val();");
					out.println("		$('#" + eID + "').find('tfoot').find('.ipage-number').val(pg)");
				}
				out.println("		" + function + "(pg, pDisplay);");
				out.println("	}");
				out.println("</script>");

				// SET display per page (default: 50)
				out.println("<span class=\"tleft\">");
				out.println("	" + view + ":");

				String onChange = "";
				if (paging.getPageList() != null && paging.getPageList().size() > 0) {
					onChange = "onchange=\"doReloadPage_" + pagePosition + "_" + eID + "('1')\"";
				}

				out.println("	<select class=\"ipage-display\" " + onChange + ">");
				out.println("		<option value=\"50\" " + (paging.getPageSize() == 50 ? "selected=\"selected\"" : "") + ">50</option>");
				out.println("		<option value=\"100\" " + (paging.getPageSize() == 100 ? "selected=\"selected\"" : "") + ">100</option>");
				out.println("		<option value=\"200\" " + (paging.getPageSize() == 200 ? "selected=\"selected\"" : "") + ">200</option>");
				out.println("		<option value=\"500\" " + (paging.getPageSize() == 500 ? "selected=\"selected\"" : "") + ">500</option>");
				if (selectAll) {
					out.println("		<option value=\"9999\" " + (paging.getPageSize() == 9999 ? "selected=\"selected\"" : "") + ">" + all + "</option>");
				}
				out.println("	</select>");

				out.println("</span>");

				// SET paging
				out.println("<span class=\"tright\">");

				if (paging.getPageList() != null && paging.getPageList().size() > 0) {

					if (paging.isPrevPageAvail()) {
						out.println("	<a href=\"#\" onclick=\"doReloadPage_" + pagePosition + "_" + eID + "('" + paging.getPrevPage() + "')\">&laquo; " + prev + "</a>");
						out.println("	&nbsp;&nbsp;&nbsp;");
					}

					if (paging.isNextPageAvail()) {
						out.println("	<a href=\"#\" onclick=\"doReloadPage_" + pagePosition + "_" + eID + "('" + paging.getNextPage() + "')\">" + next + " &raquo;</a>");
						out.println("	&nbsp;&nbsp;&nbsp;");
					}

					if (paging.getPageSize() != 9999) {
						out.println("	" + page + ":");
						out.println("	<select class=\"ipage-number\" onchange=\"doReloadPage_" + pagePosition + "_" + eID + "(this.value)\">");
						int totalPage = 1;
						if (paging.getTotalPages() != null && !paging.getTotalPages().equals("")) {
							totalPage = Integer.parseInt(paging.getTotalPages());
						}

						for (int i = 1; i <= totalPage; i++) {
							out.println("<option value=\"" + i + "\" " + (paging.getCurrentPage().equals(String.valueOf(i)) ? "selected=\"selected\"" : "") + ">" + i + "</option>");
						}

						out.println("	</select>");
					} else {
						out.println("	<input type=\"hidden\" class=\"ipage-number\" value=\"1\" />");
					}

				} else {
					out.println("	" + page + ":");
					out.println("	<select class=\"ipage-number\">");
					out.println("		<option value=\"1\">1</option>");
					out.println("	</select>");
				}

				out.println("</span>");

				out.println("</div>");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	@Override
	public void release() {
		super.release();
		paging = null;
		function = null;
		tableId = null;
		selectAll = true;
		position = null;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public Boolean getSelectAll() {
		return selectAll;
	}

	public void setSelectAll(Boolean selectAll) {
		this.selectAll = selectAll;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}
