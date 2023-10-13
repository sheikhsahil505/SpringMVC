<#include "header.ftl">

<!DOCTYPE html>
<html>
<head>
 <#if cacheControl??>
        <#assign cacheControlHeaders = cacheControl?split(",")>
        <#list cacheControlHeaders as header>
            <#assign headerParts = header?trim?split("=")>
            <#if headerParts?size == 1>
                <meta http-equiv="${headerParts[0]}" content="no-store">
            <#else>
                <meta name="${headerParts[0]}" content="${headerParts[1]}">
            </#if>
        </#list>
    </#if>
    <#if expiresHeader??>
        <meta http-equiv="Expires" content="${expiresHeader}">
    </#if>

    <style>
        /* Custom CSS for additional styling */
        .profile-picture-container {
            display: flex;
            justify-content: center;
            align-items: center;
 }

        .profile-picture {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
            border: 2px solid #007bff;
        }

        .profile-card {
            background-color: #f8f9fa;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            text-align: center;
            margin-top: 20px;
        }

        .profile-name {
            font-size: 1.25rem;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .profile-info {
            font-size: 0.9rem;
            color: #777;
        }

        .table-container {
            max-height: 190px;
            overflow-y: auto;
        }

        /* Modal Styles */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 50px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.9);
        }

        .modal-content {
            margin: auto;
            display: block;
            max-width: 50%;
            max-height: auto;
        }

        .close {
            position: absolute;
            top: 20px;
            right: 30px;
            font-size: 30px;
            font-weight: bold;
            color: white;
            cursor: pointer;
        }
    </style>
    <title>Registration List</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" />
</head>

<body>


        <a class="btn btn-danger btn-md" type="button" href="logout" style="position: absolute; top: 0; right: 0; margin: 1rem;" >Logout</a>


        <a class="btn btn-success btn-md" type="button" href="home" style="position: absolute; top: 0; right: 90px; margin: 1rem;">Home</a>


    <div id="profilePictureModal" class="modal">
        <span class="close" id="closeModal">&times;</span>
        <img id="profilePictureModalImage" class="modal-content">
    </div>

    <div class="container">
        <#if profile??>
            <#list profile as profiles>
                <h1 class="text-center mt-5">${profiles.first_name} ${profiles.last_name}</h1>
                <div class="text-center">
                     <form action="UpdateProfile" method="post">
                       <input type="hidden" name="user_id" value="${profiles.user_id}"/>
                        <button type="submit" class="btn btn-primary btn-sm">Edit Profile</button>
                      </form>
                </div>
                <div class="card profile-card mb-5">
                    <h5 class="card-title profile-name"> Details</h5>
                    <p class="card-text profile-info">

                        <strong>Email:</strong> ${profiles.email}<br>
                        <strong>Contact:</strong> ${profiles.contact_number}<br>
                        <strong> Role:</strong> ${profiles.role}<br>
                        <strong>Date of Birth:</strong> ${profiles.dob}<br>
                    </p>
                    <div class="container">
                        <h5>Address List</h5>
                        <div class="table-responsive table-container">
                            <table class="table table-striped table-bordered">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>S.No.</th>
                                        <th>Street</th>
                                        <th>Apartment</th>
                                        <th>City</th>
                                        <th>Pincode</th>
                                        <th>State</th>
                                        <th>Country</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <#list addresses as address>
                                        <tr>
                                            <td>${address_index + 1}</td>
                                            <td>${address.street}</td>
                                            <td>${address.apartment}</td>
                                            <td>${address.city}</td>
                                            <td>${address.pincode}</td>
                                            <td>${address.state}</td>
                                            <td>${address.country}</td>
                                        </tr>
                                    </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

    </div>

    <div class="container mt-5">
        <div class="container text-center"></div>
        <div class="table-responsive" id="adminTable">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>S.No.</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Contact</th>
                        <th>User Role</th>
                        <th>Date of Birth</th>
                        <th>View More</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                    <#list registrations as registration>
                        <tr>
                            <td>${registration_index + 1}</td>
                            <td>${registration.first_name}</td>
                            <td>${registration.last_name}</td>
                            <td>${registration.email}</td>
                            <td>${registration.contact_number}</td>
                            <td>${registration.role}</td>
                            <td>${registration.dob}</td>
                        <td>
                         <form action="viewUser" method="post">
                        <input type="hidden" name="user_id" value="${registration.user_id}"/>
                        <button type="submit" class="btn btn-primary">View More</button>
                        </form></td>
                         <td> <form action="deleteUser" method="post">
                        <input type="hidden" name="user_id" value="${registration.user_id}"/>
                           <button type="submit" class="btn btn-danger">Delete User</button>
                           </form></td>

                       </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>

<#if Session??>
    <#assign username = Session.username>
<#else>
    <#assign username = "null">
    </#if>





    <script>
    var username="${username}";
    console.log(username)

        if (username===null) {

            window.location.href = "index.ftl";
        }


     // Get the value of ${profiles.role}
       var userProfileRole = "${profiles.role}";
         console.log(userProfileRole);
       // Get the table element by its ID
       var adminTable = document.getElementById("adminTable");

       // Check if the user's role is 'admin'
       if (userProfileRole === "admin") {
           // If the user is an admin, display the table
           adminTable.style.display = "block";
       } else {
           // If the user is not an admin, hide the table
           adminTable.style.display = "none";
       }

        </script>
     </#list>
   </#if>


</body>
</html>
<#include "footer.ftl">
