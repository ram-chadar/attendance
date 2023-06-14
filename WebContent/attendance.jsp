<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Attendance</title>
  <link rel="stylesheet" href="css/attendance&result.css">
</head>

<body>

<%String msg=(String)request.getAttribute("msg");

if(msg!=null)
{%>
<script type="text/javascript">
    
	alert("${msg}");
  </script>
<% 
}
%>
<form action="attendance" name="attendance" method="post">
<script>
function myFunction() {
    var x = document.getElementById("myDate").value;
    document.getElementById("demo").innerHTML = x;
}


</script>
  <div class="container" style="float: left;margin-left: 130px;margin-top: 50px">
    <section class="register">
    
     <h3>Section 1</h3>
     <br>
<%-- 	<h1 style="color:red">${msg}</h1>  
 --%>      <div class="reg_section password">
     	
	
	<input type="text"  placeholder="Year" name="year" ">
	
	<h3>Month</h3>
	<select name="month">
	      	   	<option value="" name="month">Choose Month</option>
	
      	   		<option value="1" name="month">1</option>
     	   		<option value="2" name="month">2</option>
     	   		<option value="3" name="month">3</option>
     	   		<option value="4" name="month">4</option>
     	   		<option value="5" name="month">5</option>
     	   		<option value="6" name="month">6</option>
     	   		<option value="7" name="month">7</option>
     	   		<option value="8" name="month">8</option>
     	   		<option value="9" name="month">9</option>
     	   		<option value="10" name="month">10</option>
     	   		<option value="11" name="month">11</option>
     	   		<option value="12" name="month">12</option>
     	   </select>
     	   
     	   <h3>Date</h3>
     	   <input type="date" id="myDate"  name="date" >
	
     	   
     	   <h3>Branch</h3>
     	   <select name="branch" >
<%Iterator itr;%>
	<% List data= (List)request.getAttribute("data");
	if(data!=null){
		for (itr=data.iterator(); itr.hasNext(); )
		{
			String value=(String)itr.next();
	%>

 <option value=<%=value%>><%=value%></option>
 	<%
 	}
	}
 	%>	
</select>
     	   
     	   
     	   <h3>Sem</h3>
     	   
     	   
     	   <select name="sem" >
     	        <option value="" name="sem">Choose Sem</option> 
     	   
      	   		<option value="1" name="sem">1</option> 
     	   		<option value="2" name="sem">2</option>
     	   		<option value="3" name="sem">3</option>
     	   		<option value="4" name="sem">4</option>
     	   		<option value="5" name="sem">5</option>
     	   		<option value="6" name="sem">6</option>
     	   		<option value="7" name="sem">7</option>
     	   		<option value="8" name="sem">8</option>
     	   </select>
     	   
     	   
     	   <!--  <script type="text/javascript">
     	   function getsub() {
     		  document.location.href="getsub";
		}
     	   
     	   </script>  -->
     	   <h3>Subject</h3>

  <select name="subject">
<%Iterator itr1;
	 List subject= (List)request.getAttribute("subject");
	if(subject!=null){
		for (itr1=subject.iterator(); itr1.hasNext(); )
		{
			String value1=(String)itr1.next();
	%>

 <option value=<%=value1%>><%=value1%></option>
 	<%
 	}
	}
 	%>	
</select>
<script type="text/javascript">
						function getsubrollno() {
							var branch = document.forms['attendance']['branch'].value;
							var sem = document.forms['attendance']['sem'].value;
							var action = document.forms['attendance']['action'].value;
							document.location.href = "getsub?branch=" + branch
									+ "&sem=" + sem + "&action=" + action;

						}
					</script>

					<input type="button" id="action" name="action" value="Get" onclick="getsubrollno()" />
   	</div>
   	
   	  </section>
   	</div>
<!-- -------------------------------------------------------------------------------------------------------- --> 
<div class="container" style="float:left;margin-left:200px;margin-top: 50px;">
    <section class="register" style="position: absolute; height: 500px; width: 350px; max-height: 500px;overflow-y: scroll;" >
<%--     <h3 style="color: red">${msg}</h3>
 --%>   <!--  <select name="rollno" multiple="multiple" style="height: 460px;"> -->
 <%Iterator itr2;
	 List rollno= (List)request.getAttribute("attrollno");
	if(rollno!=null){
		for (itr2=rollno.iterator(); itr2.hasNext(); )
		{
			String roll=(String)itr2.next();
	%>

<input type="checkbox" name="rollno" value=<%=roll%>><%=roll%><br> 	
	<%
 	}
	}
 	%>	 <!-- 
</select> -->
         	    <input type="submit" value="Submit Attendance" name="submitattendance">

     </section>
      
   	</div>
   	
   	</form>
   	 
  
<!--   --------------------------------------------------------------------------------------------- -->
 </body>
</html>