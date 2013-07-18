package com.imageshare;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Share extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Share() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String imageUrl = request.getParameter("img");
		PrintWriter out = response.getWriter();
		String host = "www.xmark.info";

		if (imageUrl != null && !imageUrl.equals("")) {
			try {
				String name = String.valueOf(Calendar.getInstance().getTimeInMillis()) + ".jpg";
				String path = getServletContext().getRealPath("/");
				String fname = path + name;

				URLConnectionDownloader.download(imageUrl, fname);
				System.out.println("fname: " + fname);
				File file = new File(fname);
				System.out.println("file path:" + file.getPath());
				out.print(host + "/imageShare/" + name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
