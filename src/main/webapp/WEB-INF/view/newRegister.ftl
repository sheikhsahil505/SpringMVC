<#include "header.ftl">
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"/>


<style type="text/css">

    .card {
        margin-bottom: 30px;
    }

    .btn-primary {
        background-color: #FD79A8;
        border-color: #FD79A8;
    }

    .btn-primary:hover {
        background-color: #FFA07A;
        border-color: #FFA07A;
    }

    h1 {
        text-align: center;
        margin-top: 40px;
        margin-bottom: 20px;
    }

    label {
        display: block;
        margin-bottom: 5px;
        font-weight: bold;
    }

    input[type="text"], input[type="email"] {

        padding: 8px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;892
        box-sizing: border-box;
    }

    button[type="button"] {


        border: none;
        padding: 8px 16px;
        margin-bottom: 10px;
        border-radius: 4px;
        cursor: pointer;
    }

    .address {
        padding: 10px;
        margin-bottom: 10px;
        border-radius: 4px;
    }

    .address label {
        margin-bottom: 0;
    }

    .address input[type="text"] {
        margin-bottom: 5px;
    }

    .address:last-child {
        margin-bottom: 0;
    }
    .error-list {
        color: red;
        list-style-type: disc;
    }

</style>
</head>
<body>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>



  <form action="SaveUser" method="post"  id="myForm"   >

          <div class="p-4">
        <div class="row">
            <div class="">
                <div class="card">
                    <div class="card-header text-center">
                        <h3>Register Page</h3>
                    </div>
                    <div class="card-body">

                   <div class="row col-lg-12">
                       <div class="col-lg-4 mb-3">
                           <label>First Name</label><br>
                        <input type="text" id="firstName" placeholder="First Name" name="first_name" required>
                           <div class="text-danger" id="first-name-err"></div>
                       </div>
                       <div class="col-lg-4 mb-3">
                           <label>Last Name</label><br>
                           <input type="text" id="lastName" placeholder="Last Name" name="last_name" required>
                           <div class="text-danger" id="last-name-err"></div>
                       </div>
                       <div class="col-lg-4 mb-3">
                           <label>Email</label> <br>
                           <input placeholder="Enter Your Email" type="text" id="txtEmail" name="email" onkeyup="ValidateEmail();" required>
                          <br> <span id="emailError" style="color: red"></span>
                       </div>
                   </div>
            <#if errorMessage??>
               <h5 class="text-danger text-center" role="alert">
                  ${errorMessage}
                    </h5>
                   </#if>


    <#if ErrorFromBackend??>
        <ul class="error-list">
            <#list ErrorFromBackend as error>
                <li>${error.defaultMessage}</li>
            </#list>
        </ul>
    </#if>


                      <div  class="row col-lg-12">
                          <div class="col-lg-4 mb-3">
                              <label for="exampleInputEmail1">Contact No.</label>
                              <input type="text" placeholder="Enter Your Contact" class="form-control" name="contact_number" id="contactNumber" id="exampleInputEmail1" onkeyup="ValidateContact();" required>
                              <span id="contactNoError" style="color: red"></span>
                          </div>
                          <div class="col-lg-4 mb-3">
                              <label for="password">Password:</label>
                              <input type="password" name="password" id="password" class="form-control" required>
                              <h5 id="passcheck" style="color: red;">Please Fill the password</h5>
                          </div>
                          <div class="col-lg-4 mb-3">
                              <label for="conpassword">Confirm Password:</label>
                              <input type="password"  id="conpassword" class="form-control" required>
                              <h5 id="conpasscheck" style="color: red;">Password didn't match</h5>
                          </div>
                      </div>

                           <div class="row col-lg-12 pl-5">
                               <div class="col-lg-6 mb-3">
                                   <label>Role</label>
                                   <div class="form-check">
                                       <input type="radio" id="role-admin" name="role" value="admin" class="form-check-input" required>
                                       <label for="role-admin" class="form-check-label">Admin</label>
                                   </div>
                                   <div class="form-check">
                                       <input type="radio" id="role-user" name="role" value="user" class="form-check-input" required>
                                       <label for="role-user" class="form-check-label">User</label>
                                   </div>
                               </div>
                               <div class="col-lg-6 mb-3">
                                   <label for="dob">Date of Birth:</label><br>
                                   <input type="date" id="dob" name="dob" required onkeyup="DOB();">
                                   <h5 id="dob1" style="color: red; display: none;">Birthday didn't match</h5>
                               </div>
                           </div>

                            <div id="address">
                                <h3>Address </h3>
                                <div class="address">
                          <div class="row">
            <div class="col-lg-1 mb-3">
                <label for="street">Street:</label>
                <input type="text"    name="street" required>
            </div>
            <div class="col-lg-1 mb-3">
                <label for="apartment">Apartment:</label>
                <input type="text"   name="apartment" required>
            </div>
            <div class="col-lg-1 mb-3">
                <label for="city">City:</label>
                <input type="text" name="city" required>
            </div>
           <div class="col-lg-1 mb-3">
               <label for="pincode">PinCode:</label>
               <input type="text" id="pincode0" name="pincode"  required onblur="validateFirstAddressPincode();">
               <span id="pincodeError0" style="color: red"></span>
           </div>

            <div class="col-lg-1 mb-3">
                <label for="state">State:</label>
                <input type="text"   name="state"  required>
            </div>
            <div class="col-lg-1 mb-3">
                <label for="country">Country:</label>
                <input type="text"   name="country"  required>
            </div>

        </div>
        </div>
           </div>
                            <button type="button" class="btn btn-success" onclick="addAddress()">Add Address</button>
                            <br> <br>

                            <button class="btn btn-primary pb-1" type="submit" href="SaveUser" id="submitBtn">Register</button>
                            <div class="card-footer text-center">

                                <p>
                                    Already have an account? <a href="loginBtn">Login</a>
                                </p>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </form>


  <script>






  var addressIndex = 0;

  function addAddress() {
      var addressDiv = document.createElement('div');
      addressDiv.className = 'address';
      addressDiv.innerHTML =
          '<div class="row">' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="street">Street:</label><br>' +
          '<input type="text" id="street' + addressIndex + '"  name="street"  required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="apartment">Apartment:</label><br>' +
          '<input type="text" id="apartment' + addressIndex + '" name="apartment"  required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="city">City:</label><br>' +
          '<input type="text" id="city' + addressIndex + '" name="city"  required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="pincode' + addressIndex + '">PinCode:</label><br>' +
          '<input type="text" id="pincode' + addressIndex + '" name="pincode"  required onblur="ValidatePincode(' + addressIndex + ');"><br>' +
          '<span id="pincodeError' + addressIndex + '" style="color: red"></span>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="state">State:</label><br>' +
          '<input type="text" id="state' + addressIndex + '" name="state" required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3">' +
          '<label for="country">Country:</label><br>' +
          '<input type="text" id="country' + addressIndex + '" name="country"  required>' +
          '</div>' +
          '<div class="col-lg-1 mb-3 " style="padding-left: 195px; padding-top: 50px;">' +
          '<button type="button" class="btn btn-danger" onclick="removeAddress()">Remove</button>' +
          '</div>' +
          '</div>';

      var addressesDiv = document.getElementById('address');
      addressesDiv.appendChild(addressDiv);

      addressIndex++;
  }


 function removeAddress() {
                var addressDiv = document.getElementById('address');
                var addresses = addressDiv.getElementsByClassName('address');

                // Check if there is more than one address
                if (addresses.length > 1) {
                    // Remove the last address
                    addressDiv.removeChild(addresses[addresses.length - 1]);
                }
            }





             function ValidateEmail() {
            var email = document.getElementById("txtEmail").value;
            var emailError = document.getElementById("emailError");
            emailError.innerHTML = "";

            var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
            var maxLength = 40; // Maximum length of email

            if (!expr.test(email)) {
                emailError.innerHTML = "Invalid email address.";
                 return false;
            } else if (email.length > maxLength) {
                emailError.innerHTML = "Email address cannot exceed the maximum length of " + maxLength + " characters.";
                // Truncate the email to the maximum length
                email = email.slice(0, maxLength);
                // Update the input field with the truncated email
                document.getElementById("txtEmail").value = email;
                 return false;
            }else{
                    emailError.innerHTML = "";
                      return true;
        }
}







    var firstName= document.getElementById("firstName");
    var firstNameValidation=function(){
       firstNameValue=firstName.value.trim();
       validFirstName=/^[A-Za-z]+$/;
       firstNameErr=document.getElementById('first-name-err');
       if(firstNameValue=="")
       {
        firstNameErr.innerHTML="First Name is required";
       }else if(!validFirstName.test(firstNameValue)){
         firstNameErr.innerHTML="First Name must be only string without white spaces";
       }else{
         firstNameErr.innerHTML="";
         return true;

       }
    }
    firstName.oninput=function(){

       firstNameValidation();
    }





     var lastName= document.getElementById("lastName");
        var lastNameValidation=function(){
           lastNameValue=lastName.value.trim();
           validlastName=/^[A-Za-z]+$/;
           lastNameErr=document.getElementById('last-name-err');
           if(lastNameValue=="")
           {

           lastNameErr.innerHTML="Last Name is required";
           }else if(!validlastName.test(lastNameValue)){
             lastNameErr.innerHTML="Last Name must be only string without white spaces";
           }else{
             lastNameErr.innerHTML="";
             return true;

           }
        }
        lastName.oninput=function(){

           lastNameValidation();
        }








            function ValidateContact() {

                var contactNumber = document.getElementById("contactNumber").value;
                var contactNoError = document.getElementById("contactNoError");
                contactNoError.innerHTML = "";

                var maxLength = 10; // Maximum length of contact number

                if (contactNumber.length > maxLength) {
                    contactNoError.innerHTML = "Contact number cannot exceed the maximum length of " + maxLength + " digits.";
                    // Truncate the contact number to the maximum length
                    contactNumber = contactNumber.slice(0, maxLength);
                    // Update the input field with the truncated contact number
                    document.getElementById("contactNumber").value = contactNumber;
                }

                var expr =  /^(0|91)?[6-9][0-9]{9}$/;
                if (!expr.test(contactNumber)) {
                    contactNoError.innerHTML = "Invalid Contact Number!";
                } else {
                return true;
                    // Clear the error message if the contact number is valid
                    contactNoError.innerHTML = "";
                }
            }




           function validateDOB() {
               var dobInput = document.getElementById('dob');
               var inputDate = new Date(dobInput.value);
               var currentDate = new Date();
               currentDate.setHours(0, 0, 0, 0); // Set current date to midnight for accurate comparison

               var dobError = document.getElementById('dob1');

               if (inputDate >= currentDate) {
                   dobError.style.display = 'block';
                   dobError.textContent = 'Birthday date should be before today';
                   dobError.style.color = 'red';
                   return false;
               } else {
                   dobError.style.display = 'none';
                   return true;
               }
           }
           document.getElementById('dob').addEventListener('change', validateDOB);




            // Password validation
            document.getElementById('passcheck').style.display = 'none';
            let passwordError = true;

            document.getElementById('password').addEventListener('keyup', function () {
                validatePassword();
            });

            function validatePassword() {
                let passwordValue = document.getElementById('password').value;
                let passcheck = document.getElementById('passcheck');
                if (passwordValue.length === 0) {
                    passcheck.style.display = 'block';
                    passcheck.innerHTML = 'Please enter a password';
                    passcheck.style.color = 'red';
                    passwordError = false;
                    return false;
                }
                if (passwordValue.length < 3 || passwordValue.length > 20) {
                    passcheck.style.display = 'block';
                    passcheck.innerHTML = 'The password length must be between 3 and 20';
                    passcheck.style.color = 'red';
                    passwordError = false;
                    return false;
                }

                // Regular expressions
                let specialCharRegex = /[!@#$%^&*(),.?":{}|<>]/;
                let digitRegex = /\d/;
                let capitalLetterRegex = /[A-Z]/;

                if (
                    !specialCharRegex.test(passwordValue) ||
                    !digitRegex.test(passwordValue) ||
                    !capitalLetterRegex.test(passwordValue)
                ) {
                    passcheck.style.display = 'block';
                    passcheck.innerHTML = 'The password must include a special character, a digit, and a capital letter';
                    passcheck.style.color = 'red';
                    passwordError = false;
                    return false;
                } else {
                    passcheck.style.display = 'none';
                    passwordError = true;
                    return true;
                }
            }

            document.getElementById('conpasscheck').style.display = 'none';
            let confirmPasswordError = true;

            document.getElementById('conpassword').addEventListener('keyup', function () {
                validateConfirmPassword();
            });

            function validateConfirmPassword() {
                let confirmPasswordValue = document.getElementById('conpassword').value;
                let passwordValue = document.getElementById('password').value;
                let conpasscheck = document.getElementById('conpasscheck');
                if (passwordValue !== confirmPasswordValue) {
                    conpasscheck.style.display = 'block';
                    conpasscheck.innerHTML = 'Passwords do not match';
                    conpasscheck.style.color = 'red';
                    confirmPasswordError = false;
                    return false;
                } else {
                    conpasscheck.style.display = 'none';
                    confirmPasswordError = true;
                    return true;
                }
            }



function validateFirstAddressPincode() {
    var pincodeInput = document.getElementById("pincode0"); // Use the correct ID
    var pincode = pincodeInput.value.trim();
    var pincodeError = document.getElementById("pincodeError0"); // Use the correct ID

    // Regular expression to match exactly 6 digits
    var pincodePattern = /^\d{6}$/;

    if (!pincodePattern.test(pincode)) {
        pincodeError.innerHTML = "Invalid PIN code. Please enter a 6-digit number.";
        pincodeError.style.color = "red";
        pincodeInput.focus();
        return false;
    } else {
        pincodeError.innerHTML = ""; // Clear the error message
        return true;
    }
}




            function ValidatePincode(addressIndex) {
                var pincodeInput = document.getElementById("pincode" + addressIndex);
                var pincode = pincodeInput.value.trim();
                var pincodeError = document.getElementById("pincodeError" + addressIndex);

                // Regular expression to match exactly 6 digits
                var pincodePattern = /^\d{6}$/;

                if (!pincodePattern.test(pincode)) {

                    pincodeError.innerHTML = "Invalid PIN code. Please enter a 6-digit number.";
                    pincodeError.style.color = "red";
                    pincodeInput.focus();
                    return false ;
                } else {
                   return true ;
                    pincodeError.innerHTML = ""; // Clear the error message
                }
            }



      function validateForm(event) {
          // First Name Validation
          if (!firstNameValidation()) {
              alert("Please enter a valid first name.");
              event.preventDefault();
              return false;
          }

          // Last Name Validation
          if (!lastNameValidation()) {
              alert("Please enter a valid last name.");
              event.preventDefault();
              return false;
          }



          // Contact Number Validation
          if (!ValidateEmail()) {
              alert("Please enter a valid email.");
              event.preventDefault();
              return false;
          }
             if (!ValidateContact()) {
              alert("Please enter a valid Contact number.");
              event.preventDefault();
              return false;
          }


          // Password Validation
          if (!validatePassword()) {
              alert("Please enter a valid password.");
              event.preventDefault();
              return false;
          }

          // Confirm Password Validation
          if (!validateConfirmPassword()) {
              alert("Passwords do not match.");
              event.preventDefault();
              return false;
          }

            if (!validateDOB()) {
              alert("Birthday date should be less then today.");
              event.preventDefault();
              return false;
              }



               if (!validateFirstAddressPincode()) {
              alert("Pin code is not correct.");
              event.preventDefault();
              return false;
              }




          return true;
      }

      document.querySelector("form").addEventListener("submit", validateForm);

  </script>


<#include "footer.ftl">

</body>
</html>