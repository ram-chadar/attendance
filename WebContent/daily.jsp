<!DOCTYPE html>
<%@ page language="java" import="java.util.*"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Student Registration</title>
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
	<form name="dayresult" action="dayres" method="post".>
		<script>
			function myFunction() {
				var x = document.getElementById("attdate").value;
				document.getElementById("demo").innerHTML = x;
			}
		</script>
		<div class="container"
			style="float: left; margin-left: 30px; margin-top: 50px;">
			<section class="register">
				<h3>Section 1</h3>
				<br>
				<%-- 				<h1 style="color: red">${msg}</h1>
 --%>
				<div class="reg_section password">

					<h3>Date</h3>
					<input type="date" id="attdate" name="attdate">

					<h3>Branch</h3>
					<select name="branch" id="branch">
						<%
							Iterator itr;

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


					<select name="sem" id="sem">
						
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
					<select name="subject" id="subject">
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
							var branch = document.forms['dayresult']['branch'].value;
							var sem = document.forms['dayresult']['sem'].value;
							var action = document.forms['dayresult']['action'].value;
							document.location.href = "getsub?branch=" + branch
									+ "&sem=" + sem + "&action=" + action;

						}
					</script>

					<input type="button" id="action" name="action" value="Get Subject"
						onclick="getsubjects()" /> <input type="text"
						placeholder="Roll NO." name="rollno" id="rollno">



				</div>

			</section>
		</div>

		<!-- ------------------------------ -->

		<div class="container"
			style="float: left; margin-left: 50px; margin-top: 50px">
			<section class="register"
				style="position: absolute; height: 425px; width: 280px">
				<h3>Status</h3>

				<input type="text" value="${name}" style="width: 230px"> <input
					type="text" value="${status}" style="width: 230px">

				<script type="text/javascript">
					function byrollno() {
						var branch = document.forms['dayresult']['branch'].value;
						var sem = document.forms['dayresult']['sem'].value;
						var subject = document.forms['dayresult']['subject'].value;
						/*  var rollno=document.forms['dayresult']['rollno'].value;
						alert(rollno); */
						var rollno = document.getElementById("rollno").value;
						var date = document.forms['dayresult']['attdate'].value;
						document.location.href = "byrollno?branch=" + branch
								+ "&sem=" + sem + "&subject=" + subject
								+ "&rollno=" + rollno + "&date=" + date;

					}
				</script>

				<input type="button" id="action1" name="action1"
					value="Show By Roll NO." onclick="byrollno()" />
			</section>

		</div>



		<!-- -------------------------------------------------------------------------------------------------------- -->
		<div class="container" style="float: left; margin-left: 400px;">
			<section class="register"
				style="position: absolute; height: 425px; width: 200px;">
				<h3>Roll NO</h3>
				<select name="rollno" id="rollno" multiple="multiple"
					style="height: 330px; width: 150px; margin-left: 5px;">
					<%
						Iterator itr2;
						List rollno = (List) request.getAttribute("rollbydate");
						if (rollno != null) {
							for (itr2 = rollno.iterator(); itr2.hasNext();) {
								String value1 = (String) itr2.next();
					%>

					<option value=<%=value1%>><%=value1%></option>
					<%
						}
						}
					%>
				</select>
				<script type="text/javascript">
					function report() {
						var branch = document.forms['dayresult']['branch'].value;
						var sem = document.forms['dayresult']['sem'].value;
						var subject = document.forms['dayresult']['subject'].value;
						/*  var rollno=document.forms['dayresult']['rollno'].value;
						alert(rollno); */
						var rollno = document.getElementById("rollno").value;
						var date = document.forms['dayresult']['attdate'].value;
						document.location.href = "dayreport?branch=" + branch
								+ "&sem=" + sem + "&subject=" + subject
								+ "&rollno=" + rollno + "&date=" + date;

					}
				</script>
				<input type="submit" value="Show By Date"> <input
					type="button" value=" Print Report" onclick="report()">


			</section>

		</div>
	</form>


	<!--   --------------------------------------------------------------------------------------------- -->
</body>
</html>