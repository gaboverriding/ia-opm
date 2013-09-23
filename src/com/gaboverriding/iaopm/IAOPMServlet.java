package com.gaboverriding.iaopm;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class IAOPMServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Servlet de prueba ...");
	}
}
