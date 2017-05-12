package com.rogers.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * Servlet implementation class ValidUser
 */
public class ValidUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(ValidUser.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled())
			logger.debug("Enter into ValidUser doGet");
		String username = (String)request.getSession().getAttribute("username");
		if(logger.isDebugEnabled())
			logger.debug("  ValidUser username is :"+username);
		JSONParser parser = new JSONParser();
		String webAppPath = getServletContext().getRealPath("/");
		String excelFileName=webAppPath+"//output//storePerformerDetails.xlsx";
		System.out.println(" ValidUser webAppPath :"+webAppPath);
		String fullPath = webAppPath + "//resources//admin.json";
		System.out.println("Full path : " + fullPath);
		if(logger.isDebugEnabled())
			logger.debug(" ValidUser  full json path is " + fullPath);
		boolean adminFlag=false;
		try{
			ValidUser vu=new ValidUser();
			Map<String,String> map=vu.getDataFromJson(fullPath, username, "admins");
			if(logger.isDebugEnabled())
				logger.debug("admin values  "+map);
			if(null!=map){
			for(Entry<String,String> entry:map.entrySet()){
					if (username.equalsIgnoreCase(entry.getValue())) {
						if(logger.isDebugEnabled())
							logger.debug(
								"entered into if condition ValidUser admin username:");
						adminFlag = true;
						break;
				}
			}
			}
		}
		catch(Exception e){
			if(logger.isDebugEnabled())
				logger.debug( " ValidUser Exception"+e); 
		}
		
		if(adminFlag){
			request.getSession().setAttribute("adminUserName",username);
			response.getWriter().write("ValidUser");
		}else{
	
		Map<Integer, Map<String, String>> data;
		boolean flag=false;
		try {
			ValidUser bp=new ValidUser();
			data = bp.readDataFromExcel(excelFileName);
			if (null != data) {
				for (Map<String, String> rows : data.values()) {
						String reviousUserName = rows.get("UserName");
						if(username.equals(reviousUserName)){
							flag=true;
							break;
						}
					}
				}
		} catch (InvalidFormatException e) {
			response.getWriter().write("NotValidUser");
		}
		if(flag)
			response.getWriter().write("ValidUser");
		else
			response.getWriter().write("NotValidUser");
		
		}
	}
	public static Map<String,String> getDataFromJson(String fileName,String nameOrValue,String arrayName){
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
						if(nameOrValue.equalsIgnoreCase(key)|| nameOrValue.equalsIgnoreCase(value)){
							Map<String,String> map=new HashMap<String, String>();
							map.put(key,value);
							return map;
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			if(logger.isDebugEnabled())
				logger.debug("  getRelease FileNotFoundException"+e);
		} catch (IOException e) {
			if(logger.isDebugEnabled())
				logger.debug("  getRelease IOException"+e);
		} catch (ParseException e) {
			if(logger.isDebugEnabled())
				logger.debug("  getRelease ParseException"+e);
		}
		
			return null;

	}
	public static Map<Integer, Map<String, String>> readDataFromExcel(String filaName)
			throws IOException, InvalidFormatException {
		if(logger.isDebugEnabled()){
			logger.debug(" readDataFromExcel :"+filaName);
		}
		FileInputStream input_document = new FileInputStream(new File(filaName)); // Read
		// XLSX
		// document
		// -
		// Office
		// 2007,
		// 2010
		// format
		Workbook my_xlsx_workbook = WorkbookFactory.create(input_document);

		// XSSFWorkbook my_xlsx_workbook = new XSSFWorkbook(input_document);
		// //Read the Excel Workbook in a instance object
		XSSFSheet my_worksheet = (XSSFSheet) my_xlsx_workbook.getSheetAt(0); // This
		// will
		// read
		// the
		// sheet
		// for
		// us
		// into
		// another
		// object
		Iterator<Row> rowIterator = my_worksheet.iterator(); // Create iterator
		// object

		Map<Integer, Map<String, String>> dataMap = new HashMap<Integer, Map<String, String>>();
		Map<String, Integer> releases = new HashMap<String, Integer>();
		String header[] = new String[20];

		while (rowIterator.hasNext()) {
			int colindex = 0, headerindex = 0;
			Map<String, String> colList = new HashMap<String, String>();
			Row row = rowIterator.next();

			Iterator<Cell> cellIterator = row.cellIterator();// Read every
			// column for
			// every row
			// that is READ
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next(); // Fetch CELL
				if (null != cell) {
					if (row.getRowNum() == 0) {
						header[headerindex] = cell.getStringCellValue();
					} else {
						colList.put(header[headerindex], cell.getStringCellValue());
						if (colindex == 0)
							releases.put(cell.getStringCellValue(), row.getRowNum());
					}
				}
				headerindex++;
				colindex++;
			}
			dataMap.put(row.getRowNum(), colList);
		}
		input_document.close(); // Close the XLS file opened for printing
		if(logger.isDebugEnabled()){
			logger.debug("  readData closed"+filaName);
		}
		return dataMap;

	}
}
