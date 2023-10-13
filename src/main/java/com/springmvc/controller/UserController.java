package com.springmvc.controller;
import com.springmvc.entity.Address;
import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    Logger logger = Logger.getLogger(UserController.class);
    @RequestMapping("/")
    public String viewPage(){
        return "index";
    }
    @RequestMapping("/loginBtn")
    public String loginPage(){return "index";}
    @RequestMapping("/signUp")
    public String signUp(){
        return "newRegister";
    }

    @RequestMapping(value = "/SaveUser",method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult br, Address address, Model model)
    {
        if (br.hasErrors()) {
            model.addAttribute("ErrorFromBackend", br.getAllErrors());
            return "newRegister";
        }else{
            boolean status = userService.saveUser(user, address);
            if(status){
            model.addAttribute("errorMessage", "email is already taken");
           return "newRegister" ;
            }
            logger.info("saved user");
           model.addAttribute("errorMessage", "Register Successfully");

        return "newRegister";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username") String username,@RequestParam("password") String password,Model model){
        List<User> users = userService.login(username, password);
        if(users!=null){
            int userId=0;
            for (User use:users){
             userId = use.getUser_id();
             }
            List<Address> allAddressByUserId = userService.getAllAddressByUserId(userId);
            List<User> allUsers = userService.getAllUsers();
            logger.info("login user with username "+username);
            model.addAttribute("profile",users);
            model.addAttribute("addresses",allAddressByUserId);
            model.addAttribute("registrations",allUsers);
            return "view";
        }
        model.addAttribute("errorMessage", "Invalid username or password");
        return "index";
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String deleteUSer(@RequestParam("user_id") int id, Model model,HttpSession session){
        if (session.getAttribute("username") != null){
            userService.deleteUser(id);
        }else {
            return "index";
        }
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        List<User> users = userService.userDetails(username, password);
        logger.info("user deleted");
        int userId = 0;
        for (User use:users){
             userId = use.getUser_id();
        }
            List<Address> allAddressByUserId = userService.getAllAddressByUserId(userId);
            List<User> allUsers = userService.getAllUsers();
            model.addAttribute("profile",users);
            model.addAttribute("addresses",allAddressByUserId);
            model.addAttribute("registrations",allUsers);
            return "view";
    }


    @RequestMapping(value = "/viewUser",method = RequestMethod.POST)
    public String viewMoreUser(@RequestParam("user_id") int id, Model model,HttpSession session) {
        if (session.getAttribute("username") != null) {
            List<User> userById = userService.getUserById(id);
            logger.info("view  user profile by admin");
            List<Address> allAddressByUserId = userService.getAllAddressByUserId(id);
            List<User> allUsers = userService.getAllUsers();
            model.addAttribute("profile", userById);
            model.addAttribute("addresses", allAddressByUserId);
            model.addAttribute("registrations", allUsers);
            return "view";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/UpdateProfile",method = RequestMethod.POST)
    public String updateUser(@RequestParam("user_id") int id, Model model,HttpSession session) {
        if(session.getAttribute("username")!=null){
        List<User> userById = userService.getUserById(id);
        List<Address> allAddressByUserId = userService.getAllAddressByUserId(id);
        model.addAttribute("profile", userById);
        model.addAttribute("addresses", allAddressByUserId);
        return "updateProfile";
        }else {
            return "index";
        }
    }

@RequestMapping(value = "/logout",method = RequestMethod.GET)
public String logout(  HttpServletResponse response, HttpSession session){
         session.removeAttribute("username");
         session.invalidate();
          logger.info("logout user");


    return "index";
}

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home( HttpSession session,Model model) {
        if (session.getAttribute("username") == null) {
                return "index";
        } else {
            String username = (String) session.getAttribute("username");
            String password = (String) session.getAttribute("password");
            logger.info("home user with username"+username);
            List<User> users = userService.userDetails(username, password);

            for (User user : users) {
                int userId = user.getUser_id();
                List<Address> allAddressByUserId = userService.getAllAddressByUserId(userId);
                List<User> allUsers = userService.getAllUsers();
                model.addAttribute("profile", users);
                model.addAttribute("addresses", allAddressByUserId);
                model.addAttribute("registrations", allUsers);
        }
            return "view";
        }

    }

    @RequestMapping(value = "/saveUpdateUser",method = RequestMethod.POST)
    public String updateProfile(@RequestParam("removedAddressIds") String AddressdeleteIds, User user,Address address, HttpSession session, Model model) {
        if (session.getAttribute("username") != null) {
            int id = user.getUser_id();
            boolean status = userService.updateUser(user, address,AddressdeleteIds);
            if (status) {
                List<User> userById = userService.getUserById(id);
                List<Address> allAddressByUserId = userService.getAllAddressByUserId(id);
                model.addAttribute("profile", userById);
                model.addAttribute("addresses", allAddressByUserId);
                model.addAttribute("errorMessage", "Email is already taken.");
                return "updateProfile";
            } else {
                  List<User> userById = userService.getUserById(id);
                  List<Address> allAddressByUserId = userService.getAllAddressByUserId(id);
                  logger.info("Update user");
                  List<User> allUsers = userService.getAllUsers();
                  model.addAttribute("profile", userById);
                  model.addAttribute("addresses", allAddressByUserId);
                  model.addAttribute("registrations", allUsers);
                  return "view";
           }
        }
        else {

        return "index";
        }

}
}
