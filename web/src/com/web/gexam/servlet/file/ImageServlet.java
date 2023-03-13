package com.web.gexam.servlet.file;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ImageServlet() {
		super();
	}

	@Override
	public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse) throws ServletException, IOException {
		doPost(httpservletrequest, httpservletresponse);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		OutputStream out = null;
		try {

			String path = (String) request.getSession().getAttribute("SSPathUpload");

			// HttpSession session = request.getSession(false);
			// String path = (String) session.getAttribute("SSPathUpload");

			File file = null;
			String ext = null;

			String data = request.getParameter("id");

			if (data != null && !data.equals("")) {
				file = new File(path + data);

				ext = data.substring(data.lastIndexOf(".") + 1, data.length());

				if (ext.equalsIgnoreCase("png"))
					response.setContentType("image/png");
				else
					response.setContentType("image/jpeg");
			} else {
				file = new File(path + "image/none.jpg");

				response.setContentType("image/jpeg");
			}

			BufferedImage bi = ImageIO.read(file);
			out = response.getOutputStream();

			if (ext != null && ext.equalsIgnoreCase("png"))
				ImageIO.write(bi, "png", out);
			else
				ImageIO.write(bi, "jpg", out);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}