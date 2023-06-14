 <%@page language="java" import="java.util.*" %>
<html>
<head>
 <link rel="stylesheet" href="viewAllStudent.css">
<title>List Of All Student</title>
</head>
<body > 

<form action="printstudent" method="post">
<input type="hidden" name="branch" value="<%=request.getAttribute("branch")%>">
<input type="hidden" name="sem" value="<%=request.getAttribute("sem")%>">

<table border="1" >
<tr>
<th width="100"><b>Roll NO</b></th>
<th width="500"><b>Student Name</b></th>
<th width="500"><b>Enrollment No.</b></th>
<th width="500"><b>Image</b></th>

</tr>
<%Iterator itr;
 List data= (List)request.getAttribute("data");
for (itr=data.iterator(); itr.hasNext(); )
{
%>
<tr>
<td ><div><%=itr.next()%></div></td>
<td ><div><%=itr.next()%></div></td>
<td ><div><%=itr.next()%></div></td>
<td ><div><img src="images/<%=itr.next()%>"></div></td> 
</tr>
<%
}
%>
</table>
<center><input type="submit" value="print"></center>
</form>
</body>
</html>