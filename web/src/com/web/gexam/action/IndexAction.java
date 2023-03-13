package com.web.gexam.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.server.gexam.entity.User;
import com.server.gexam.service.UserService;

public class IndexAction extends CoreAction {

	private UserService userService;

	public ActionForward index(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			resetForm(dynaForm, mapping, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward("Index");
	}

	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			DynaActionForm dynaForm = (DynaActionForm) form;
			List<User> users = userService.getLogin(dynaForm.getString("un"), dynaForm.getString("pw"));
			if (users != null && users.size() > 0) {
				User user = users.iterator().next();
				setObjectSession(request, SESSION_USER, user);
			} else {
				return mapping.findForward("Index");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mappingForward(mapping, request, "mode", "init", "master.htm", "masterForm", null);
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
