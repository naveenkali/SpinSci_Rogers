package com.rogers.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

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
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.rogers.util.Util;


/**
 * Servlet implementation class HistoryOfPerformers
 */
public class HistoryOfPerformers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(HistoryOfPerformers.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryOfPerformers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String webAppPath = getServletContext().getRealPath("/");
		String log4jConfPath =webAppPath+ "//WEB-INF//log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		
		String fullPath=webAppPath+"output//storePerformerDetails.xlsx";
		if(logger.isDebugEnabled())
			logger.debug("webAppPath :"+webAppPath);
		Map<Integer, Map<String, String>> data;
		try {
			HistoryOfPerformers hp=new HistoryOfPerformers();
			data = hp.readDataFromExcel(fullPath);
			if(null!=data){
			for ( Entry<Integer, Map<String, String>> entry : data.entrySet()) {
			    Integer key = entry.getKey();
			    if(logger.isDebugEnabled())
					logger.debug(" RowNum:"+key+"  ");
			    Map<String,String> tab = entry.getValue();
			    String release=tab.get("Release");
			    String userName=tab.get("UserName");
			    String performer1=tab.get("Performer1");
			    String justification1=tab.get("Justification1");
			    String performer2=tab.get("performer2");
			    String justification2=tab.get("justification2");
			    String performer3=tab.get("performer3");
			    String justification3=tab.get("justification3");
			    if(logger.isDebugEnabled()){
					logger.debug("Release :"+release);
					logger.debug("UserName :"+userName);
					logger.debug("Performer1 :"+performer1);
					logger.debug("Justification1 :"+justification1);
					logger.debug("Performer2 :"+performer2);
					logger.debug("Justification2 :"+justification2);
					logger.debug("Performer3 :"+performer3);
					logger.debug("Justification3 :"+justification3);
			    }
				
			}
			}else{
				
			}
		} catch (InvalidFormatException e) {
			logger.debug("HistoryOfPerformers InvalidFormatException : "+e);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
