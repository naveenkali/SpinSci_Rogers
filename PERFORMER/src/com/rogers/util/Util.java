package com.rogers.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

public class Util {

	static final Logger logger = Logger.getLogger(Util.class);

	public static Map<Integer, Map<String, String>> readDataFromExcel(String filaName)
			throws IOException, InvalidFormatException {
		if(logger.isDebugEnabled()){
			logger.debug("Util readDataFromExcel :"+filaName);
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
			logger.debug(" Util readData closed"+filaName);
		}
		return dataMap;

	}

	public static void main(String[] args) throws IOException, InvalidFormatException {
		String excelFileName = "D:\\latestEclipseWork\\PERFORMER\\WebContent\\resources\\Releases.json";
		String nextReleaseDate="June 28th";
		Util.insertNextReleaseDate(excelFileName,nextReleaseDate);
		//String performer="Ravi";
		/*List<String> str=null;
		 Map<String,Map<List<Integer>,List<String>>> performerOfTheRelease=Util.performerOfTheRelease(excelFileName);
		 Map<List<Integer>,List<String>> performerDetails= performerOfTheRelease.get(performer);
		 for(Entry<List<Integer>,List<String>> entry:performerDetails.entrySet()){
			 str=entry.getValue();
		 }
		 for(String ss:str)
			 for(String s:ss.substring(1, ss.length()-1).split(","))
		 System.out.println(s);*/
		// String jsonFileName="D:\\performers.json";
		// Util.insertBestPerformer(excelFileName, jsonFileName, "ravi@spinsci.com", "june19th", "Ravi");
		
		// Util.performerOfTheRelease(jsonFileName, excelFileName);
		//Map<String,Map<List<Integer>,List<String>>> map=Util.performerOfTheRelease(excelFileName);
		//String result=Util.generateTableHTML(map);
		//System.out.println(result);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	private static Map<String, Integer> findPerformer(Map<String, Integer> performer1, Map<String, Integer> performer2,
			Map<String, Integer> performer3) {
		Map<String, Integer> performer = new HashMap<String, Integer>();
		for (Entry<String, Integer> entry : performer1.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (performer.containsKey(key)) {
				performer.put(key, performer.get(key) + value);
			} else
				performer.put(key, value);

		}
		for (Entry<String, Integer> entry : performer2.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (performer.containsKey(key)) {
				performer.put(key, performer.get(key) + value);
			} else
				performer.put(key, value);

		}
		for (Entry<String, Integer> entry : performer3.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if (performer.containsKey(key)) {
				performer.put(key, performer.get(key) + value);
			} else
				performer.put(key, value);

		}
		Map<String, Integer> finalPerformer =  sortByValue(performer);
		List<String> performers = new LinkedList<String>();
		int tempValue = 0;
		int index = 1;
		for(Entry<String, Integer> entry : finalPerformer.entrySet()){
			performers.add(entry.getKey());
			tempValue = entry.getValue();

		}
		return finalPerformer;

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

	public static Map<String,Map<List<Integer>,List<String>>> performerOfTheRelease(String excelFileName) throws InvalidFormatException, IOException {
		Map<Integer, Map<String, String>> data;
		Map<String, Integer> performer1 = new HashMap<String, Integer>();
		Map<String, Integer> performer2 = new HashMap<String, Integer>();
		Map<String, Integer> performer3 = new HashMap<String, Integer>();

		Map<String, List<String>> justifications = new HashMap<String, List<String>>();
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
	
	public static String generateTableHTML(Map<String,Map<List<Integer>,List<String>>> performers){
		StringBuilder html = new StringBuilder("");
		if(logger.isDebugEnabled())
			logger.debug("table format :");
		for(Entry<String,Map<List<Integer>,List<String>>> map:performers.entrySet() ){
			html.append("<tr>");
			html.append("<td>"+map.getKey()+"</td>");
			if(logger.isDebugEnabled())
				logger.debug(map.getKey());
			for(Entry<List<Integer>,List<String>> points:map.getValue().entrySet()){
			List<Integer> point=points.getKey();
			for(Integer pointValue:point){
				if(logger.isDebugEnabled())
					logger.debug(pointValue);
				html.append("<td>"+pointValue+"</td>");
			}
			}
			System.out.println();
			html.append("</tr>");
		}
		return html.toString();
	}
	public static String getJosnData(String fileName,String nameOrValue,String arrayName,String webAppPath){
		String log4jConfPath = webAppPath+"WEB-INF\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
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
				logger.debug(" Util getRelease FileNotFoundException"+e);
		} catch (IOException e) {
			if(logger.isDebugEnabled())
				logger.debug(" Util getRelease IOException"+e);
		} catch (ParseException e) {
			if(logger.isDebugEnabled())
				logger.debug(" Util getRelease ParseException"+e);
		}
		
			return key;

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
				logger.debug(" Util getRelease FileNotFoundException"+e);
		} catch (IOException e) {
			if(logger.isDebugEnabled())
				logger.debug(" Util getRelease IOException"+e);
		} catch (ParseException e) {
			if(logger.isDebugEnabled())
				logger.debug(" Util getRelease ParseException"+e);
		}
		
			return null;

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
	public static void insertData(String filename ,String username, String perfomer1, String justification1, String perfomer2,
			String justification2, String perfomer3, String justification3) {
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		//String filename = "/WEB-INF/output/storePerformerDetails.xlsx";
		if(filename == null || filename.length() == 0){
			if(logger.isDebugEnabled())
			logger.debug("file is null");
		}else{
			try {
				if("ChoosePerformer-2".endsWith(perfomer2)){
					perfomer2="";
				}
				if("ChoosePerformer-3".endsWith(perfomer3)){
					perfomer3="";
				}
				String temp="...";
				if(logger.isDebugEnabled())
					logger.debug(" before inserting username is :"+username);
				data.put(""+username, new Object[]{temp,perfomer1,justification1,perfomer2,justification2,perfomer3,justification3});
				FileInputStream fis = new FileInputStream(filename);
				// Finds the workbook instance for XLSX file 
				XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
				// Return first sheet from the XLSX workbook
				XSSFSheet mySheet = myWorkBook.getSheetAt(0); 
				Set<String> newRows = data.keySet(); 
				// get the last row number to append new data 
				int rownum = mySheet.getLastRowNum()+1;
				for (String key : newRows)
				{ // Creating a new Row in existing XLSX sheet 
					Row row = mySheet.createRow(rownum++); 
					Object [] objArr = data.get(key); 
					//             System.out.println(" object is :"+key);
					int cellnum = 0; 
					for (Object obj : objArr) 
					{ 
						Cell cell = row.createCell(cellnum);
						if(cellnum==0)
							cell.setCellValue( ""+key);
						else
							cell.setCellValue( ""+obj);
						cellnum++;

					} 
				}
				FileOutputStream os = new FileOutputStream(filename);
				myWorkBook.write(os); 
				if(logger.isDebugEnabled())
					logger.debug("perfomer1 :"+perfomer1+" justification1 :"+justification1+" perfomer2 :"+perfomer2+"  justification2 :"+justification2+" perfomer3 :"+perfomer3+"  justification3 :"+justification3);
			}
			catch (Exception e) {
				if(logger.isDebugEnabled())
					logger.debug( "Exception :"+e);
			}
		}
		if(logger.isDebugEnabled())
			logger.debug( " This is Inserted method :");
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
					logger.debug( "Exception Delete Util :"+e);
		    }finally{
		        if(workbook!=null)
		            workbook.close();
		        if(logger.isDebugEnabled())
					logger.debug("finally Delete Util :");
		    }
		return flag;
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
	public static String sendMail(String to){
		if(logger.isDebugEnabled())
			logger.debug( "entered into send mail smtp :");
		System.out.println( "entered into send mail smtp :");
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
			message.setSubject("Performer tool login Passcode");
			Random rd=new Random();
			String randNo=""+rd.nextInt(200000);
			message.setText("Hi. Your password reset code is :"+randNo);
			Transport.send(message);
			return randNo;
		} catch (MessagingException e) {
			System.out.println("error :"+e);
			if(logger.isDebugEnabled())
				logger.debug( " By sending passcode got Exception :"+e);
			return "Error :"+e;
		}
		
	}
	
}