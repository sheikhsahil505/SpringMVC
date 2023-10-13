<#include "header.ftl">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar with Logo and Login/Signup</title>

<style>
.card{
 background-color: #343a40;
}
</style
</head>
<body>
<form action="sendOtp" method="post">
   <section class=" gradient-custom">
     <div class="container py-2">
       <div class="row d-flex justify-content-center align-items-center h-100">
         <div class="col-12 col-md-8 col-lg-6 col-xl-5">
           <div class="card      text-white" style="border-radius: 1rem;">
             <div class="card-body p-3 text-center">

               <div class=" ">

                 <h2 class="fw-bold mb-2 text-uppercase">Forget Password</h2>
                 <p class="text-white-50 mb-5">Please enter your email and Date Of Birth!</p>

                 <div class="form-outline form-white mb-4">
                   <input type="email" id="email" name="email" class="form-control form-control-lg" required/>
                   <label class="form-label" >Email</label>
                 </div>

                 <div class="form-outline form-white mb-4">
                   <input type="date" id="typePasswordX" name="dob" class="form-control form-control-lg" required/>
                   <label class="form-label" >Password</label>
                 </div>

           <#if errorMessage??>
               <h5 class="text-danger" role="alert">
                  ${errorMessage}
                    </h5>
                   </#if>

                 <button class="btn btn-outline-light btn-lg px-5" type="submit" id="submit">Sent OTP</button>


               </div>
               <div>
                 <p class="mb-0"> Don't want to  forget password <a href="loginBtn" class=" fw-bold">Login</a>
                 </p>
               </div>

             </div>
           </div>
         </div>
       </div>
     </div>
   </section>
</form>

</body>
</html>

<#include "footer.ftl">
