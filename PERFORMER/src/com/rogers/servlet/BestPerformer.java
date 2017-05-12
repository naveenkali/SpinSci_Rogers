package com.rogers.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.rogers.util.Util;

/**
 * Servlet implementation class BestPerformer
 */

public class BestPerformer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static final Logger logger = Logger.getLogger(BestPerformer.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestPerformer() {
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
		String webAppPath = getServletContext().getRealPath("/");
		String log4jConfPath =webAppPath+ "/WEB-INF/log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		if(logger.isDebugEnabled())
			logger.debug("entered into best performer class");
		String performerName=request.getParameter("bestPerformer");
		String nextReleaseDate=request.getParameter("nextRelease");
		String excelFileName=webAppPath+"output//storePerformerDetails.xlsx";
		String jsonFileName=webAppPath+"resources//ReleaseHistory.json";
		String nameOrValue="",arrayName="releases";
		arrayName="names";
		String releaseUsersJson=webAppPath+"resources//users.json";
		BestPerformer bp=new BestPerformer();
		String perfomerUserName=bp.getJosnData(releaseUsersJson, performerName, arrayName,webAppPath);
		if(logger.isDebugEnabled()){
			logger.debug("ReleaseHistory.json :"+jsonFileName);
			logger.debug("perfomerUserName :"+perfomerUserName);
		}
		request.getSession().setAttribute("perfomerUserName", perfomerUserName);
		boolean flag=false;
		try {
		//	Date date = new Date();
			//LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			//int releaseyear  = localDate.getYear();
			Calendar now = Calendar.getInstance();   // Gets the current date and time
			int releaseyear = now.get(Calendar.YEAR);      // The current year as an int
			request.getSession().setAttribute("releaseyear", releaseyear);
			String releaseDate=request.getSession().getAttribute("releaseDate").toString();
			flag=bp.insertBestPerformer(excelFileName, jsonFileName, perfomerUserName, releaseDate, performerName);
			
		} catch (InvalidFormatException e) {
			response.getWriter().write("fail");
		}
		if(flag){
			response.getWriter().write("Success");
			String releasesJson=webAppPath+"resources//Releases.json";
			bp.insertNextReleaseDate(releasesJson,nextReleaseDate);
		}else{
			response.getWriter().write("fail");
		}
		if(flag){
			
		}
	}
	
	public static boolean insertBestPerformer(String excelFileName,String jsonFileName,String userName,String releaseName,String performer) throws InvalidFormatException, IOException{
		Date date = new Date();
		boolean flag=true;
	//		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	//	int year  = localDate.getYear();
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR);      // The current year as an int
		List<String> justification=null;
		
		//System.out.println("Year is:"+year);
		//BestPerformer bp=new BestPerformer();
		 Map<String,Map<List<Integer>,List<String>>> performerOfTheRelease=Util.performerOfTheRelease(excelFileName);
		 Map<List<Integer>,List<String>> performerDetails= performerOfTheRelease.get(performer);
		 for(Entry<List<Integer>,List<String>> entry:performerDetails.entrySet()){
			 justification=entry.getValue();
		 }
		 JSONArray justificationArray=new JSONArray();
		
		 if(null!=justification){
		 for(String jj:justification)
			 for(String j:jj.substring(1, jj.length()-1).split(","))
				 justificationArray.add(""+j);
		 }
		 JSONObject obj = new JSONObject(); 
		 obj.put("username",userName);
		 obj.put("releaseName",releaseName);
	 obj.put("releaseValue",year);
		 obj.put("justification", justificationArray);
		 JSONArray releaseDetails=null;
		 
		 JSONParser parser = new JSONParser();
			Object obj1;
			try {
				obj1 = parser.parse(new FileReader(jsonFileName));
				JSONObject releases=null;
				if(null != obj1)
					releases= (JSONObject) obj1;
				if(null != releases){
					 releaseDetails=(JSONArray)releases.get("names");
					releaseDetails.add(obj);
				}
			}catch(Exception e){
				flag=false;
			}
		 
		 JSONObject obj2 = new JSONObject();
		 obj2.put("names", releaseDetails);
		FileWriter file = new FileWriter(jsonFileName,false) {
		};
		logger.debug(" insertBestPerformer  excelFileName :"+excelFileName);
		logger.debug(" insertBestPerformer  jsonFileName :"+jsonFileName);
		logger.debug(" insertBestPerformer  userName :"+userName);
		logger.debug(" insertBestPerformer  releaseName :"+releaseName);
		logger.debug(" insertBestPerformer  performer :"+performer);
		logger.debug(" insertBestPerformer  obj :"+obj);
		logger.debug(" insertBestPerformer  obj2 :"+obj2);
		file.write(obj2.toJSONString());
		file.flush();
		file.close();
		logger.debug("Successfully Copied JSON Object to File..."+obj2);
		if(flag){
			flag=deleteData(excelFileName);
		}
		return flag;
		
	}
	public static boolean deleteData(String fileName) throws InvalidFormatException, IOException{
		boolean flag=false;
		if(logger.isDebugEnabled())
			logger.debug( " Entered into deleteData :");
		 XSSFWorkbook workbook=null;
		    XSSFSheet sheet =null;
		    FileInputStream file = new FileInputStream(new File(fileName));
		    try{
		        file = new FileInputStream(new File(fileName));
		        workbook= new XSSFWorkbook(file);
		        sheet = workbook.getSheet("Sheet1");
		        if(sheet==null){
		            return false;
		        }
		        int lastRowNum=sheet.getLastRowNum();
		        int rowNo=1;
		        while(rowNo<=lastRowNum){
		            XSSFRow removingRow=sheet.getRow(rowNo);
		            if(removingRow!=null){
		                sheet.removeRow(removingRow);
		                flag=true;
		            }
		        rowNo++;
		        }
		        file.close();
		        FileOutputStream outFile =new FileOutputStream(new File(fileName));
		        workbook.write(outFile);
		        outFile.close();

		        if(logger.isDebugEnabled())
					logger.debug( " deleteData close :");
		    }catch(Exception e){
		    	if(logger.isDebugEnabled())
					logger.debug( "Exception Delete  :"+e);
		    }finally{
		        if(workbook!=null)
		            workbook.close();
		        if(logger.isDebugEnabled())
					logger.debug("finally Delete  :");
		    }
		return flag;
	}
	public static Map<String,Map<List<Integer>,List<String>>> performerOfTheRelease(String excelFileName) throws InvalidFormatException, IOException {
		Map<Integer, Map<String, String>> data;
		Map<String, Integer> performer1 = new HashMap<String, Integer>();
		Map<String, Integer> performer2 = new HashMap<String, Integer>();
		Map<String, Integer> performer3 = new HashMap<String, Integer>();

		Map<String, List<String>> justifications = new HashMap<String, List<String>>();
		//BestPerformer bp=new BestPerformer();
		data = Util.readDataFromExcel(excelFileName);
		if (null != data) {
			for (Map<String, String> rows : data.values()) {
				if (rows.size() != 0) {
					String perf1 = rows.get("Performer1");
					String just1 = rows.get("Justification1");
					addScore(1, perf1, just1, performer1, justifications);
					String perf2 = rows.get("Performer2");
					String just2 = rows.get("Justification2");
					if (perf2 != null && just2 != null)
						addScore(1, perf2, just2, performer2, justifications);
					String perf3 = rows.get("Performer3");
					String just3 = rows.get("Justification3");
					if (perf3 != null && just3 != null)
						addScore(1, perf3, just3, performer3, justifications);
				}

			}
			if(logger.isDebugEnabled())
				logger.debug("justifications all :"+justifications);
			if(logger.isDebugEnabled())
				logger.debug(performer1);
			if(logger.isDebugEnabled())
				logger.debug(performer2);
			if(logger.isDebugEnabled())
				logger.debug(performer3);
			//Map<String, Integer> performer = findPerformer(performer1, performer2, performer3);
			//String result=generateTableHTML(performer1, performer2, performer3);
			//System.out.println(result);
			Map<String,Map<List<Integer>,List<String>>> map=Util.getPerformers(performer1, performer2, performer3,justifications);
			return map;

		}
		return null;
	}
	public static boolean insertNextReleaseDate(String jsonFileName,String nextReleaseDate){
		boolean flag=true;
		try{
		 JSONArray justificationArray=new JSONArray();
		 JSONObject obj = new JSONObject(); 
		 obj.put("name", nextReleaseDate);
		 obj.put("value", nextReleaseDate);
		 JSONArray releaseDetails=new JSONArray();
		 releaseDetails.add(obj);
		 JSONParser parser = new JSONParser();
		 JSONObject obj2 = new JSONObject();
		 obj2.put("releases", releaseDetails);
		FileWriter file = new FileWriter(jsonFileName) {
		};
		if(logger.isDebugEnabled()){
			logger.debug( " nextReleaseDate   :"+nextReleaseDate);
			logger.debug(" insertBestPerformer  jsonFileName :"+jsonFileName);
			logger.debug(" insertBestPerformer  obj :"+obj);
			logger.debug(" insertBestPerformer  obj2 :"+obj2);
		}
		file.write(obj2.toJSONString());
		file.flush();
		file.close();
		if(logger.isDebugEnabled())
			logger.debug( "Successfully Copied JSON Object to File..."+obj2);
		}catch(Exception e){
			flag=false;
		}
		return flag;
		
	
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
	public static Map<String,Map<List<Integer>,List<String>>> getPerformers(Map<String, Integer> performer1, Map<String, Integer> performer2,
			Map<String, Integer> performer3,Map<String, List<String>> justifications){
		Set<String> performers = new HashSet<String>();
		performers.addAll(performer1.keySet());
		performers.addAll(performer2.keySet());
		performers.addAll(performer3.keySet());
		Map<String,Map<List<Integer>,List<String>>> map=new HashMap<String,Map<List<Integer>,List<String>>>();
		for(String performer : performers){
			List<Integer> points=new ArrayList<Integer>();
			List<String> just=new ArrayList<String>();
			Map<List<Integer>,List<String>> performerList=new HashMap<List<Integer>,List<String>>();
			points.add((performer1.get(performer) != null ? performer1.get(performer) :0 ));
			points.add((performer2.get(performer) != null ? performer2.get(performer) :0 ));
			points.add((performer3.get(performer) != null ? performer3.get(performer) :0 ));
			just.add(""+(justifications.get(performer)!=null?justifications.get(performer):""));
			performerList.put(points, just);
			if(null!=performer && (!"".equals(performer))){
			map.put(performer, performerList);
			}
		}
		return map;
	}
	private static void addScore(int score, String perf, String just, Map<String, Integer> performer,
			Map<String, List<String>> justifications) {
		if (performer.containsKey(perf))
			performer.put(perf, performer.get(perf) + score);
		else
			performer.put(perf, score);

		if (justifications.containsKey(perf)) {
			List<String> jst = justifications.get(perf);
			jst.add(just);
			justifications.put(perf, jst);
		} else if(null!=perf){
			List<String> jst = new ArrayList<String>();
			jst.add(just);
			justifications.put(perf, jst);
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
