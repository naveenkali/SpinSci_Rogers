package com.rogers.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Servlet implementation class ForgetPassword
 */
@SuppressWarnings("unchecked")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(ForgetPassword.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgetPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String webAppPath = getServletContext().getRealPath("/");
		String log4jConfPath = webAppPath+"//WEB-INF//log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		if(logger.isDebugEnabled())
			logger.debug("Entered into doPost ForgetPassword ");
		HttpSession session = request.getSession();
		//String passcode = request.getParameter("passcode");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
	//	String sessionPasscode=(String)request.getSession().getAttribute("passcode");
		String username = (String)request.getSession().getAttribute("username");
		if(logger.isDebugEnabled()){
			//logger.debug("ForgetPassword sessionPasscode is :" + sessionPasscode);
		//	logger.debug("ForgetPassword passcode is :" + passcode);
			logger.debug("ForgetPassword New Password is :" + password);
			logger.debug("ForgetPassword Confirm New Password is :" + cpassword);
			logger.debug("ForgetPassword username is :" + username);
		}
		//if(null!=passcode && passcode.equalsIgnoreCase(sessionPasscode)){
			boolean flag = false;
			JSONParser parser = new JSONParser();
			try {
				if(logger.isDebugEnabled())
					logger.debug(" webAppPath :" + webAppPath);
				String fullPath = webAppPath + "//resources//Login.json";
				if(logger.isDebugEnabled())
					logger.debug( " full json path is " + fullPath);
				JSONObject UserNames = null;

				Object obj = parser.parse(new FileReader(fullPath));
				//	JSONObject obj1 = new JSONObject();
				//	String releaseDateJson=webAppPath+"/resources/Releases.json";
				//	String nameOrValue="",arrayName="releases";
				//	String releaseDate=	Util.getJosnData(releaseDateJson, nameOrValue, arrayName);
				//	request.getSession().setAttribute("releaseDate", releaseDate);
				if (null != obj)
					if(logger.isDebugEnabled())
						logger.debug("obj is not null");
				UserNames = (JSONObject) obj;
				if (null != UserNames) {
					if(logger.isDebugEnabled())
						logger.debug("usernames is not null");
					JSONArray userDetails = (JSONArray) UserNames.get("usernames");
					if (null != userDetails) {
						if(logger.isDebugEnabled())
							logger.debug("userdetails is not null");
						for (Object o : userDetails) {
							JSONObject UserDetail = (JSONObject) o;
							String jsonUserName = (String) UserDetail
									.get("username");
							if(logger.isDebugEnabled())
								logger.debug("jsonusername is : " + jsonUserName);
							if (username.equalsIgnoreCase(jsonUserName) ){
								UserDetail.put("password", password);
								System.out.println("entered into if condition : " );
								System.out.println("username is : " + username);
								System.out.println("jsonusername is : " + jsonUserName);
								JSONObject obj1=new JSONObject();
								obj1.put("usernames", userDetails);
								try {
									System.out.println("entered into write json ForgetPassword ");
									FileWriter file = new FileWriter(fullPath);
									file.write(obj1.toJSONString());
									file.flush();
									file.close();
								}catch (IOException e) {
									e.printStackTrace();
								}

								flag = true;
								break;

							}
						}
					}			
				}
			}catch(Exception e){
				if(logger.isDebugEnabled())
					logger.debug("ForgetPassword exception :"+e);
			}
			if(logger.isDebugEnabled())
				logger.debug("ForgetPassword flag : "+flag);
			
				if (flag) {
					response.getWriter().write("Success");
					if(logger.isDebugEnabled())
						logger.debug("ForgetPassword entered into if : ");
				} else {
					if(logger.isDebugEnabled())
						logger.debug("ForgetPassword entered into else condition ForgetPassword ");
					response.getWriter().write("Fail");
				}
		
		
	}
}



