package com.cmpe275.cusrTicketBooking.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe275.cusrTicketBooking.model.User;
import com.cmpe275.cusrTicketBooking.service.UserService;

@Controller
@SessionAttributes({"name","email"})
public class UserController {
	
	@Autowired
	private UserService userservice;
	/**
	 * 
	 * <p>
	 *  
	 *  
	 *
	 * @param  
	 * @param  
	 * @return      
	 * @see         
	 */
	
	@RequestMapping(value="/")
	   public ModelAndView index() {
		  
		  ModelAndView mv = new ModelAndView("index");
	      return mv;
	   }
	 @PostMapping(value="/signin")
	   public ModelAndView sayHello(@RequestParam("email_l") String email,@RequestParam("password_l") String password,Model model) {
	     
		 User present=userservice.getUserByEmail(email);
		 
		 if(present!=null&& present.getPassword().equals(password))
		 {
			 
	      model.addAttribute("email", email);
	      model.addAttribute("name",present.getName());
	      ModelAndView mv = new ModelAndView("hello");
	      return mv;
		 }
		 else 
		 {
			 /**
			  * login unsucessful
			  */
			 ModelAndView mv=new ModelAndView("unsuccess");
			 return mv;
		 }
		 
	   }
	 
	   @PostMapping(value="/newlogin")
	   public ModelAndView sayHome(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") String password,Model model) {
	    
	      User present=userservice.getUserByEmail(email);
	     
	     
			 
			 if(present==null)
			 {
			  User user=new User(name,email,password);
			  User added=userservice.save(user); 
		      model.addAttribute("email", email);
		      model.addAttribute("name",present.getName());
		      ModelAndView mv = new ModelAndView("hello");
		      return mv;
			 }
			 else 
			 {
				 /**
				  * login unsucessful
				  */
				 ModelAndView mv=new ModelAndView("unsuccess");
				 return mv;
			 }
			 
	   }
	
	   @RequestMapping(value="/welcome")
	   public ModelAndView sayHome(@PathParam("name") String name,@PathParam("email") String email,Model model) {
	    
	      User present=userservice.getUserByEmail(email);
	      if(present==null)
	      {   String password="google";
	    	  User user=new User(name,email,password);
		      User added=userservice.save(user);  
	      }
	      model.addAttribute("name", name);
	      model.addAttribute("email", email);
	      ModelAndView mv = new ModelAndView("hello");
	      return mv;
	     
	   }
	
	   @RequestMapping(value = "/logout")
	    public ModelAndView logout(HttpSession httpSession) {
	      
	       
	        httpSession.invalidate();
	        
		  
		  ModelAndView mv = new ModelAndView("unsuccess");
	      return mv;
	   }
	
	
	
	
}
