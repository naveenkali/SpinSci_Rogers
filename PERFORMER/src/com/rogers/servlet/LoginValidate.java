package com.rogers.servlet;

import java.io.FileNotFoundException;
import java.io.FileReader;
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
 * Servlet implementation class LoginValidate
 */
public class LoginValidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(LoginValidate.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginValidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String webAppPath = getServletContext().getRealPath("/");
		String log4jConfPath = webAppPath+"/WEB-INF/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		if(logger.isDebugEnabled())
			logger.debug("Entered into doPost LoginValidate ");
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(null!=username)
			username=username.trim();
		if(null!=password)
			password=password.trim();
		if(logger.isDebugEnabled()){
			logger.debug("username : " + username);
			logger.debug("LoginValidate userName is :" + username);
			//logger.debug( "LoginValidate password is :" + password);
		}
		boolean flag = false;
		JSONParser parser = new JSONParser();
		try {
			if(logger.isDebugEnabled())
				logger.debug(" webAppPath :" + webAppPath);
			String fullPath = webAppPath + "//resources//Login.json";
			if(logger.isDebugEnabled())
				logger.debug("Full path : " + fullPath);
			JSONObject UserNames = null;
			Object obj = parser.parse(new FileReader(fullPath));
			String releaseDateJson = webAppPath + "//resources//Releases.json";
			if(logger.isDebugEnabled())
				logger.debug( "releaseDateJson is :"+releaseDateJson );
			String nameOrValue="",arrayName="releases";
			if(logger.isDebugEnabled())
				logger.debug( "before getJsonData :" );
			LoginValidate lv=new LoginValidate();
			String releaseDate=	lv.getJosnData(releaseDateJson, nameOrValue, arrayName,webAppPath);
			if(logger.isDebugEnabled())
				logger.debug( "release date is :"+releaseDate );
			request.getSession().setAttribute("releaseDate", releaseDate);
			if(logger.isDebugEnabled())
				logger.debug( "after getJsonData :" );
			/*if(logger.isDebugEnabled()){
				logger.debug(" parse object is " + obj);
			}*/
			if (null != obj)
				UserNames = (JSONObject) obj;
			if (null != UserNames) {
				JSONArray userDetails = (JSONArray) UserNames.get("usernames");
				if (null != userDetails) {
					for (Object o : userDetails) {
						JSONObject UserDetail = (JSONObject) o;
						String jsonUserName = (String) UserDetail
								.get("username");
						String jsonPassword = (String) UserDetail
								.get("password");
						if(logger.isDebugEnabled())
							logger.debug("username:" + jsonUserName);
								//+ "  password:" + jsonPassword);
						if (username.equalsIgnoreCase(jsonUserName)
								&& password.equals(jsonPassword)) {
							session.setAttribute("username", username);
							if(logger.isDebugEnabled())
								logger.debug("entered into if condition LoginValidate");
							flag = true;
							break;
							
						}
					}
				}
			}
			if (flag) {
				response.getWriter().write("Success");
			} else {
				if(logger.isDebugEnabled())
					logger.debug("entered into else condition LoginValidate ");
				response.getWriter().write("Fail");
			}
		} catch (FileNotFoundException e) {
			if(logger.isDebugEnabled())
				logger.debug(" FileNotFoundException :" + e);
			response.getWriter().write("Fail");
		} catch (IOException e) {
			if(logger.isDebugEnabled())
				logger.debug( " IOException :" + e);
			response.getWriter().write("Fail");
		} catch (ParseException e) {
			if(logger.isDebugEnabled())
				logger.debug( " ParseException :" + e);
			response.getWriter().write("Fail");
		}catch (Exception e) {
			if(logger.isDebugEnabled())
				logger.debug( " Exception :" + e);
			response.getWriter().write("Fail");
		}
		

	}
	public static String getJosnData(String fileName,String nameOrValue,String arrayName,String webAppPath){
		if(logger.isDebugEnabled()){
			logger.debug("getJsonData -- fileName:"+fileName);
			logger.debug("getJsonData -- nameOrValue:"+nameOrValue);
			logger.debug("getJsonData -- arrayName:"+arrayName);
		}
		String key="";
		String value="";
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(fileName));
			JSONObject releases=null;
			if(null != obj)
				releases= (JSONObject) obj;
			if(null != releases){
				JSONArray releaseDetails=(JSONArray)releases.get(arrayName);
				if(null != releaseDetails){
					for (Object o : releaseDetails) {
						JSONObject release= (JSONObject) o;
						key=(String)release.get("name");
						value=(String)release.get("value");
						if(nameOrValue.equalsIgnoreCase(key)){
							if(logger.isDebugEnabled()){
								logger.debug("getJsonData -- Key found :"+value);
							}
							return value;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			if(logger.isDebugEnabled())
				logger.debug(" getRelease FileNotFoundException"+e);
		} catch (IOException e) {
			if(logger.isDebugEnabled())
				logger.debug("  getRelease IOException"+e);
		} catch (ParseException e) {
			if(logger.isDebugEnabled())
				logger.debug("  getRelease ParseException"+e);
		}
		
			return key;

	}

}
