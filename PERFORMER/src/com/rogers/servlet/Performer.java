package com.rogers.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.rogers.util.Util;


/**
 * Servlet implementation class Performer
 */
public class Performer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(Performer.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Performer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(logger.isDebugEnabled())
			logger.debug("Enter into Performer doGet");
		String webAppPath = getServletContext().getRealPath("/");
		String excelFileName=webAppPath+"output//storePerformerDetails.xlsx";
		if(logger.isDebugEnabled())
			logger.debug(" ValidUser webAppPath :"+webAppPath);
		String username = (String)request.getSession().getAttribute("username");
		if(logger.isDebugEnabled())
			logger.debug("  Performer username is :"+username);
		Map<Integer, Map<String, String>> data;
		boolean flag=false;
		try {
			Performer bp=new Performer();
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
			if(!flag){
			String[] str=username.split("@");
			if(logger.isDebugEnabled())
				logger.debug(" Performer.java username after spliting :"+str[0]);
			String perfomer1  =  request.getParameter("perfomer1");
			if(logger.isDebugEnabled())
				logger.debug(" Performer1 is :"+perfomer1);
			String perfomer2  =  request.getParameter("perfomer2");
			if(logger.isDebugEnabled())
				logger.debug(" Performer2 is :"+perfomer2);
			String perfomer3  =  request.getParameter("perfomer3");
			if(logger.isDebugEnabled())
				logger.debug(" Performer3 is :"+perfomer3);
			String justification1  =  request.getParameter("justification1");
			if(logger.isDebugEnabled())
				logger.debug(" justification1 is :"+justification1);
			String justification2  =  request.getParameter("justification2");
			if(logger.isDebugEnabled())
				logger.debug(" justification2 is :"+justification2);
			String justification3  =  request.getParameter("justification3");
			if(logger.isDebugEnabled())
				logger.debug(" justification3 is :"+justification3); 
			if(null!=str[0]){
				if((null!=perfomer1 && perfomer1.toLowerCase().contains(str[0]))||(null!=perfomer2 && perfomer2.toLowerCase().contains(str[0]))||(null!=perfomer3 && perfomer3.toLowerCase().contains(str[0])))
				{
					response.getWriter().write("Duplicate");
				}
				else{
					bp.insertData(excelFileName,username,perfomer1,justification1,perfomer2,justification2,perfomer3,justification3);
					response.getWriter().write("Success");
					System.out.println(" Data inserted successfully");
				}
			}
			//request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else{
				response.getWriter().write("Success");
			}
		}
		catch(Exception e){
			response.getWriter().write("Failure");
		}
	
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
