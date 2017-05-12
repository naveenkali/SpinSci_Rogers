<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.rogers.util.*,java.util.*"%>
<script type="text/javascript" src="js/pages/indexJS.js"></script>
<div style="color:red">${errorMessage}</div>
<title>Performers</title>
<style type="text/css">
  table {
      border-color:#00F;
  }
  table td {
      border-color:#ccc;
  }
</style>
</head>
<body>
<h3 class="page-header" id="performerTitle" align="center">Performer Of The Release <%=session.getAttribute("releaseDate")%></h3>
<table id="example" class="table table-striped table-bordered"
cellspacing="0" width="10%" border="6"  align="center">
<thead class="thbackground">
<tr>
<th>Names</th>
<th>Performer1</th>
<th>Performer2</th>
<th>Performer3</th>
</tr>
</thead>
<%String webAppPath = getServletContext().getRealPath("/");%>
<%String excelFileName=webAppPath+"/output/storePerformerDetails.xlsx";
Map<String,Map<List<Integer>,List<String>>> map=Util.performerOfTheRelease(excelFileName);
out.print(""+Util.generateTableHTML(map));%>
</table>
<table align="center">
<% String adminUser=(String) request.getSession().getAttribute("adminUserName");
System.out.println(" resultjsp adminUser:"+adminUser);
if(null!=adminUser){%>
<tr><td>&nbsp;</td></tr><tr><td>&nbsp;</td>
<td><input type="text" name="bestPerformer" id="bestPerformer" placeholder="Best Performer" /></td>
<td>&nbsp;</td><td><input type="text" name="nextRelease" id="nextRelease" placeholder="Next Release Date" /></td></tr>
<tr><td colspan="3" align="center"> <input type="Submit" class="btn btn-success"  value="Submit" onclick="submitBestPerformer();"/></td></tr>
<% }%>
</table>