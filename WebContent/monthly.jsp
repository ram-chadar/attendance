<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Monthly Attendance Result</title>
<link rel="stylesheet" href="css/attendance&result.css">
</head>

<body>
	<%
		String msg = (String) request.getAttribute("msg");

		if (msg != null) {
	%>
	<script type="text/javascript">
		alert("${msg}");
	</script>
	<%
		}
	%>
	<h2 style="color: yellow;">${msg}</h2>
	<form action="monthres" name="monthres" method="post">


		<div class="container"
			style="float: left; margin-left: 130px; margin-top: 50px">
			<section class="register">
				<h3>Section 1</h3>
				<br>
				<%-- 				<h1 style="color: red">${msg}</h1>
 --%>
				<div class="reg_section password">

					<h3>Year</h3>
					<input type="text" placeholder="Year" name="year" value="${year}">

					<h3>Month</h3>
					<select name="month">
					<option value="${month}" name="month">${month}</option>
					 	
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


					<h3>Branch</h3>
					<select name="branch">
						<%
							Iterator itr;
						%>
						<%
							List data = (List) request.getAttribute("data");
							if (data != null) {
								for (itr = data.iterator(); itr.hasNext();) {
									String value = (String) itr.next();
						%>

						<option value=<%=value%>><%=value%></option>
						<%
							}
							}
						%>
					</select>


					<h3>Sem</h3>


					<select name="sem">
					<option value="${sem}" name="sem">${sem}</option>
						
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
					<!--      	 <h3 style="color:navy;"> <a href="getsub" >Get Subject</a></h3>
 -->

					<select name="subject">
						<%
							Iterator itr1;
							List subject = (List) request.getAttribute("subject");
							if (subject != null) {
								for (itr1 = subject.iterator(); itr1.hasNext();) {
									String value1 = (String) itr1.next();
						%>

						<option value=<%=value1%>><%=value1%></option>
						<%
							}
							}
						%>
					</select> <input type="text" name="rollno" value="" placeholder="Roll No">
					<script type="text/javascript">
						function getsubjects() {
							var branch = document.forms['monthres']['branch'].value;
							var month = document.forms['monthres']['month'].value;
							var sem = document.forms['monthres']['sem'].value;
							var action = document.forms['monthres']['action'].value;
							document.location.href = "getsub?branch=" + branch
									+"&month=" + month + "&sem=" + sem + "&action=" + action;

						}
					</script>

					<input type="button" id="action" name="action" value="Get Subjects"
						onclick="getsubjects()" />
				</div>

			</section>
		</div>
		<!-- -------------------------------------------------------------------------------------------------------- -->
		<div class="container"
			style="float: left; margin-left: 200px; margin-top: 50px">
			<section class="register"
				style="position: absolute; height: 500px; width: 350px">

				<h3 style="float: left; margin-top: 20px">&nbsp;&nbsp;Attend
					Lecture
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total
					Lecture</h3>
				<select name="total" id="total" multiple="multiple"
					style="height: 380px; width: 150px; margin-left: 30px;">
					<%
						Iterator totaldate;
					%>
					<%
						List dates = (List) request.getAttribute("totaldate");
						if (dates != null) {
							for (totaldate = dates.iterator(); totaldate.hasNext();) {
								String value = (String) totaldate.next();
					%>

					<option value=<%=value%>><%=value%></option>
					<%
						}
						}
					%>
				</select> <select name="attend" id="attend" multiple="multiple"
					style="float: left; width: 150px; height: 380px;">
					<%
						Iterator attenddate;
					%>
					<%
						List date = (List) request.getAttribute("attenddate");
						if (date != null) {
							for (attenddate = date.iterator(); attenddate.hasNext();) {
								String value = (String) attenddate.next();
					%>

					<option value=<%=value%>><%=value%></option>
					<%
						}
						}
					%>

				</select>

				<h3>Total Lecture=${totlec}</h3>
				<br>
				<h3>Attend Lecture=${attlec}</h3>
				<br>
				<h3>Status=${status } %</h3>
				<input type="submit" value="Show Result">
				<script type="text/javascript">
					function clearopt() {
						document.getElementById('attend').innerText = null
						document.getElementById('total').innerText = null
						alert("remove all");
					}
				</script>

				<script type="text/javascript">
					function printreport() {
						var year = document.forms['monthres']['year'].value;
						var month = document.forms['monthres']['month'].value;
						var branch = document.forms['monthres']['branch'].value;
						var sem = document.forms['monthres']['sem'].value;
						var subject = document.forms['monthres']['subject'].value;
						var rollno = document.forms['monthres']['rollno'].value;
						document.location.href = "monthreport?year=" + year
								+ "&month=" + month + "&branch=" + branch
								+ "&sem=" + sem + "&subject=" + subject
								+ "&rollno=" + rollno;
					}
				</script>
				<input type="button" value="clear List" onclick="clearopt()">
				<input type="button" value="Print Report" onclick="printreport()">
			</section>

		</div>

		<!-- -------------------------------------------------------------------------------------------------------- -->

	</form>


	<!--   --------------------------------------------------------------------------------------------- -->
</body>
</html>