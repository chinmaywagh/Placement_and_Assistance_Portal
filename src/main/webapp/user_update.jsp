<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
      <%@ taglib prefix="c" uri="jakarta.tags.core" %>
 <%
 if(session.getAttribute("u_name")==null){
	 response.sendRedirect("user_signin.jsp");
 }%>

<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Placement and Assistance System</title>
    <link rel = "icon" href = "images/logo1.png" type = "image/x-icon">
    <link rel="stylesheet" href="CSS/style.css">
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
	<script type='text/javascript'src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>
    <input type="hidden" id="status"value="<%= request.getAttribute("status") %>">

  <nav class="navbar">
    <div class="content">
    <a href="user_section.jsp"style="text-decoration:none;">  <div class="logo">Placement and Assistance System</div></a>
      <ul class="menu-list">
        <div class="icon cancel-btn">
          <i class="fas fa-times"></i>
        </div>
    
     
                                        <div class="logo" style="font-size:20px;margin-right:30px"><%=session.getAttribute("u_name") %></div>
          <a href="UserLogoutServlet" class="button-two"style="background-color:rgb(164, 10, 10);text-decoration:none;padding:5px 15px 5px 15px;width:100px"type="submit"  >LOG OUT</a>
      
   
    
      </ul>
      <div class="icon menu-btn">
        <i class="fas fa-bars"></i>
      </div>
    </div>
  </nav>
  
  <div class="banner"></div>


<section class="signup"style="width:100%">
			<div class="container"style="width:100%;padding:0 0 0 0;">
				<div class="signup-content"style="width:100%;padding:20px 0 0 0;">
					<div class="signup-form"style="width:50%;padding:0 0 0 0">
					
<aside>
 <div class="rcorners2"> 
  <nav>
    <ul>
     <form action="UserListServlet" method="post"><input type="hidden" id="uid" name="uid" value="<%=session.getAttribute("uid") %>"><button type="submit"class="profilebtn"><li>User Profile</li></button></form>
    <a href="user_edu_register.jsp"style="text-decoration:none;"><li>Add Educational Details</li></a>
    <a href="UserCompListServlet"style="text-decoration:none;"><li>Available Internships</li></a>
    <a href="#"style="text-decoration:none;"><li>Available Jobs</li></a>
    <a href="#"style="text-decoration:none;"><li>Applies</li></a>
    <a href="user_del_profile.jsp"style="text-decoration:none;"><li>Delete Profile</li></a>
    </ul>
  </nav>
 </div> 
</aside>

</div>
<div class="signup-image">
												           <div class="rcorners3"> 
						
						
						 <form action="UserUpdateServlet" method="post">
                       			<div class="user__details">         
                				 <h2 class="form-title">Update Profile</h2>
									<input type="hidden" name="uid" value="${uid}"/>
							
									<div class="form-group">
									<input type="text" name="u_name"style="width:450px" id="u_name" value="${u_name}" required placeholder="Enter Full Name"/></div>
							<div class="form-group">
							<input type="text" name="u_uname"style="width:450px" id="u_uname"value="${u_uname}"required placeholder="Enter Username"/></div>
             
                			<div class="select">
							   <select name="u_gender" id="u_gender">
							      <option selected disabled>Select Gender</option>
							      <option value="Male">Male</option>
							      <option value="Female">Female</option>
							      <option value="None">Prefer not to say</option>
							   </select>
							</div>
							
							<div class="form-group">
							<input style="cursor:pointer;" type="date" required name="u_dob"value="${u_dob}" id="u_dob" placeholder="Date of Birth">
							    </div>
							
							<div class="form-group">
							<input type="password" id="password" required name="u_pass" style="width:450px"class="form-control" placeholder="Password" value="${u_pass}"></div> 
							
							
							<div class="form-group">
							<input type="password" id="confirm_password" required name="u_pass2" style="width:450px"class="form-control" placeholder="Confirm password" />
							 <span style="font-size:18px;color:red;" id="confirm_password_msg"></span>
							</div>
							<div class="form-group">
							<input type="email" required name="u_email"style="width:450px;display:inline-block; text-decoration:none;" value="${u_email}" id="u_email" placeholder="Your Email" /></div>
							               
							<div class="form-group">
							<input type="text" required name="u_mobno"style="width:450px" id="u_mobno" value="${u_mobno}" placeholder="Contact no" /></div>
							
							
						<div class="form-group">
						<input type="text" required name="u_address"style="width:450px" id="u_address"value="${u_address}" placeholder="Enter Address" /></div>
							
				
				<div class="form-group">
							<div class="select">
							   <select name="u_status" id="u_status">
							      <option selected disabled  value="${u_status}">Work Status</option>
							      <option value="Fresher">Fresher</option>
							      <option value="Experienced">Experienced</option>
							   </select>
							</div>
								</div>			
								
										
							<div class="form-group sub-main"style="width:100vh">
      						<button class="button-two"type="submit" name="update2" style="margin:30px 0 30px 30px"class="form-submit" ><span>Submit</span></button>
   							 </div>
							</div>
							
						</form>
						</div>
				</div>
			</div>
			</div>
		</section>


</body>

 <script src="vendor/jquery/jquery.min.js"></script>
	<script src="js/main.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="alert/dist/sweetalert.css">
	<script type="text/javascript">
	var status = document.getElementById("status").value;
	if(status == "success"){
		swal("congrats","User updated Successfully","success");
	}
	if(status == "incomplete"){
		swal("Sorry","Check inserted data","incomplete");
	}
	</script>
<script
  src="https://code.jquery.com/jquery-3.3.1.js"
  integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
  crossorigin="anonymous"></script> 
  <script>
    const body = document.querySelector("body");
    const navbar = document.querySelector(".navbar");
    const menuBtn = document.querySelector(".menu-btn");
    const cancelBtn = document.querySelector(".cancel-btn");
    menuBtn.onclick = ()=>{
      navbar.classList.add("show");
      menuBtn.classList.add("hide");
      body.classList.add("disabled");
    }
    cancelBtn.onclick = ()=>{
      body.classList.remove("disabled");
      navbar.classList.remove("show");
      menuBtn.classList.remove("hide");
    }
    window.onscroll = ()=>{
      this.scrollY > 20 ? navbar.classList.add("sticky") : navbar.classList.remove("sticky");
    }
    
  </script>
<script>document.querySelector('body').addEventListener('click', event => {
                	    if (event.target.matches('#submit1')) {
                	    	event.preventDefault();
                	    	       
                	    	    }
                	    	});
  </script>
  <script>
  
  document.querySelector('body').addEventListener('click', event => {
	    if (event.target.matches('#submit2')) {
	    	event.preventDefault();
	    	       
	    	    }
	    	});
  
  
  </script>
  <script>
  document.querySelector('body').addEventListener('click', event => {
                	    if (event.target.matches('#submit3')) {
                	    	event.preventDefault();
                	    	       
                	    	    }
                	    	});
  </script>
  <script>
  
  document.querySelector('body').addEventListener('click', event => {
	    if (event.target.matches('#submit4')) {
	    	event.preventDefault();
	    	       
	    	    }
	    	});
  
  </script>
 <script>      
 $(document).ready(function(){
     
     $("#confirm_password").bind('keyup change', function(){

       check_Password( $("#password").val(), $("#confirm_password").val() )
       
       
     })

     $("#sign_in_btn").click(function(){

       check_Password( $("#password").val(), $("#confirm_password").val() )

     })
   })

   function check_Password( Pass, Con_Pass){

     if(Pass === ""){

       

     }else if( Pass === Con_Pass){

       $("#sign_in_btn").removeAttr("onclick")
       $('#confirm_password_msg').show()
       $("#confirm_password_msg").html('<div class="alert alert-success">Password matched</div>')

       setTimeout(function() {
           $('#confirm_password_msg').fadeOut('slow');
       }, 3000);

     }else{
       $("#confirm_password").focus()
       $('#confirm_password_msg').show()
       $("#confirm_password_msg").html('<div class="alert alert-danger">Password did not matched</div>')

       setTimeout(function() {
           $('#confirm_password_msg').fadeOut('slow');
       }, 3000);

     }

   }</script>
  
<style>



:root {
  --main-blue: #71b7e6;
  --main-purple: #9b59b6;
  --main-grey: #ccc;
  --sub-grey: #d9d9d9;
}




.container form .user__details {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 20px 0 12px 0;
}

form .user__details .form-group {
  width: calc(100% / 2 - 20px);
}


.btn{
  margin:5px;
  -webkit-transition: background-color .5s ease-out;
  -moz-transition: background-color .5s ease-out;
  -o-transition: background-color .5s ease-out;
  transition: background-color .5s ease-out;
  border-width:1px;
  border-color:black;
  font-size:calc(0.65em + .7vmin);
}

.btn:hover{
  color:white;
}


/* ----------- BUTTON STYLES - OUTLINE OR FULL ----------- */
.btn-o{
  background-color:transparent;
  border-width:1px;
}
.btn-f{
  border-width:1px;
}

/* ----------- CORNER STYLES - ROUNDED OR SQUARE ----------- */
.btn-sq{
  border-radius:0px;
}
.btn-rnd{
  border-radius:4px;
}

/* ----------- BUTTON SIZES ----------- */
.btn-sm{
  padding:10px 18px;
}
.btn-md{
  padding:15px 35px;
}
.btn-lrg{
  padding:15px 65px;
}





.btn-o.btn-teal{
   background-color:transparent;
   color:#008489;
}
.btn-o.btn-teal:hover{
   background-color:#008489;
   color:white;
}

</style>
</html>
</html>