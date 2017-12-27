package com.cmpe275.cusrTicketBooking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe275.cusrTicketBooking.Utility;
import com.cmpe275.cusrTicketBooking.model.ConnectionType;
import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.SearchOptions;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.TrainType;
import com.cmpe275.cusrTicketBooking.service.ResetService;
import com.cmpe275.cusrTicketBooking.service.SearchService;

@Controller
public class ResetController {

	@Autowired
	private ResetService resetService;
	
	
	@RequestMapping(value = "/reset")
	public ModelAndView reset() {
     
			 ModelAndView mv = new ModelAndView("reset");
			    return mv;
    
}
	
	@RequestMapping(value = "/resetSystem")
	public ModelAndView reset(
			@RequestParam(required=true) int trainCapacity,
			Model model) {
		try {
			
			resetService.resetSystem(trainCapacity);
				
		    ModelAndView mv = new ModelAndView("successful");
		    return mv;
				
			
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
		    ModelAndView mv = new ModelAndView("unsuccess");
		    return mv;
		}
	}

}
