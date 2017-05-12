package com.rogers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.rogers.util.Util;


/**
 * Servlet implementation class SendMail
 */
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(SendMail.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendMail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String webAppPath = getServletContext().getRealPath("/");
		String log4jConfPath = webAppPath+"\\WEB-INF\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		if(logger.isDebugEnabled())
			logger.debug("Entered into doPost SendMail ");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		if(logger.isDebugEnabled())
			logger.debug("SendMail  username : "+username);
		/*String passcode = Util.sendMail(username);
		if(logger.isDebugEnabled())
			logger.debug("SendMail : "+passcode);*/
		if("".equalsIgnoreCase(username)){
			if(logger.isDebugEnabled())
				logger.debug("entered into else condition SendMail ");
			response.getWriter().write("Fail");
		}else{
			//session.setAttribute("passcode", passcode);
			session.setAttribute("username", username);
			if(logger.isDebugEnabled())
				logger.debug("SendMail success : ");
			response.getWriter().write("Success");
		}




	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

/*	public static String sendMail1(String to){
		if(logger.isDebugEnabled())
			logger.debug( "entered into send mail smtp :");
		final String username = "rogersperfomer@gmail.com";
		final String password = "rogersperformer";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Performer tool Password Reset Code");
			Random rd=new Random();
			String randNo=""+rd.nextInt(200000);
			message.setText("Hi. \n \n Your password reset code is : "+randNo);
			Transport.send(message);
			return randNo;
		} catch (MessagingException e) {
			if(logger.isDebugEnabled())
				logger.debug( " By sending passcode got Exception :"+e);
			return "";
		}
	}*/

}