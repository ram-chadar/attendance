<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Semister Wise Attendance Result</title>
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
	<form action="semresult" name="semresult" method="post">


		<div class="container"
			style="float: left; margin-left: 130px; margin-top: 50px">
			<section class="register">
				<h3>Section 1</h3>
				<br>
				<%-- 	<h1 style="color:red">${msg}</h1>  
 --%>
				<div class="reg_section password">

					<h3>Year</h3>
					<input type="text" placeholder="Year" name="year" value="${year}">

					<h3>From</h3>
					<select name="smonth">
						<option value="${smonth}" name="smonth">${smonth}</option>

						<option value="1" name="smonth">1</option>
						<option value="2" name="smonth">2</option>
						<option value="3" name="smonth">3</option>
						<option value="4" name="smonth">4</option>
						<option value="5" name="smonth">5</option>
						<option value="6" name="smonth">6</option>
						<option value="7" name="smonth">7</option>
						<option value="8" name="smonth">8</option>
						<option value="9" name="smonth">9</option>
						<option value="10" name="smonth">10</option>
						<option value="11" name="smonth">11</option>
						<option value="12" name="smonth">12</option>
					</select>


					<h3>TO</h3>
					<select name="endmonth">
						<option value="${endmonth }" name="endmonth">${endmonth}</option>

						<option value="1" name="endmonth">1</option>
						<option value="2" name="endmonth">2</option>
						<option value="3" name="endmonth">3</option>
						<option value="4" name="endmonth">4</option>
						<option value="5" name="endmonth">5</option>
						<option value="6" name="endmonth">6</option>
						<option value="7" name="endmonth">7</option>
						<option value="8" name="endmonth">8</option>
						<option value="9" name="endmonth">9</option>
						<option value="10" name="endmonth">10</option>
						<option value="11" name="endmonth">11</option>
						<option value="12" name="endmonth">12</option>
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


					<h3>Subject</h3>


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
					</select>

					<script type="text/javascript">
						function getsubjects() {
							var branch = document.forms['semresult']['branch'].value;
							var year = document.forms['semresult']['year'].value;
							var smonth = document.forms['semresult']['smonth'].value;
							var endmonth = document.forms['semresult']['endmonth'].value;
							var sem = document.forms['semresult']['sem'].value;
							var action = document.forms['semresult']['action'].value;
							document.location.href = "getsub?branch="+branch+"&smonth=" + smonth+"&endmonth=" + endmonth+ "&sem=" + sem + "&action=" + action+ "&year=" + year;

						}
					</script>

					<input type="button" id="action" name="action" value="Get_Subjects"
						onclick="getsubjects()" /> <input type="text" name="rollno"
						value="" placeholder="Roll No">

				</div>

			</section>
		</div>
		<!-- -------------------------------------------------------------------------------------------------------- -->
		<div class="container"
			style="float: left; margin-left: 200px; margin-top: 50px">
			<section class="register"
				style="position: absolute; height: 550px; width: 350px">

				<h3 style="float: left; margin-top: 20px">&nbsp;&nbsp;Attend
					Lecture
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Total
					Lecture</h3>



				<select name="total" multiple="multiple"
					style="height: 400px; width: 150px; margin-left: 30px;">
					<%
						Iterator itr2;
						List rollno = (List) request.getAttribute("totaldatesem");
						if (rollno != null) {
							for (itr2 = rollno.iterator(); itr2.hasNext();) {
								String roll = (String) itr2.next();
					%>

					<option value=<%=roll%>><%=roll%></option>
					<%
						}
						}
					%>
				</select> <select name="attend" multiple="multiple"
					style="float: left; width: 150px; height: 400px;">
					<%
						Iterator itr3;
						List attenddate = (List) request.getAttribute("attenddatesem");
						if (attenddate != null) {
							for (itr3 = attenddate.iterator(); itr3.hasNext();) {
								String attlec = (String) itr3.next();
					%>

					<option value=<%=attlec%>><%=attlec%></option>
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
					function printreport() {
						var year = document.forms['semresult']['year'].value;
						var smonth = document.forms['semresult']['startmonth'].value;
						var emonth = document.forms['semresult']['endmonth'].value;
						var branch = document.forms['semresult']['branch'].value;
						var sem = document.forms['semresult']['sem'].value;
						var subject = document.forms['semresult']['subject'].value;
						var rollno = document.forms['semresult']['rollno'].value;
						document.location.href = "semreport?year=" + year
								+ "&startmonth=" + smonth + "&endmonth="
								+ emonth + "&branch=" + branch + "&sem=" + sem
								+ "&subject=" + subject + "&rollno=" + rollno;
					}
				</script>
				<input type="button" value="Print Report" onclick="printreport()">
			</section>

		</div>

		<!-- -------------------------------------------------------------------------------------------------------- -->

	</form>


	<!--   --------------------------------------------------------------------------------------------- -->
</body>
</html>