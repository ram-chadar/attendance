<!DOCTYPE html>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>Forfot Password</title>
  <link rel="stylesheet" href="regstyle.css">
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
  <div class="container">
    <section class="register">
     <h3>Forgot Form</h3>
     <br>

<form action="forgot" method="post">
<%--       <h1 style="color:red">${msg}</h1>  
 --%>      
      
      <div class="reg_section password">
     	   <h3>Question And Answer</h3>
     	   <input type="text" name="user" placeholder="Username" required="required">
     	   <select name="question">
     	   <option value="" name="question" >Select Question</option>
     	   
      	   <option value="WHAT IS YOUR NICKNAME ?" name="question" >WHAT IS YOUR NICKNAME ?</option>
     	   <option value="WHAT IS YOUR BIRTH YEAR ?" name="question">WHAT IS YOUR BIRTH YEAR ?</option>
     	   </select>
     	   <input type="text" name="ans" value="" placeholder="Answer" required="required">
   	 </div>
   	 
   	 <div class="reg_section personal_info">
    	  <h3>New And Confirm Password</h3>
    	  
    	  <input type="text" name="newpass" value="" placeholder="New Password" required="required">
      	<input type="text" name="confpass" value="" placeholder="Confirm Password" required="required">
      	
      </div>
    
           <p class="submit"><input type="submit" name="action" value="Reset Password">
           </p>
      </form>
   	 </section>
  </div>
</body>
</html>