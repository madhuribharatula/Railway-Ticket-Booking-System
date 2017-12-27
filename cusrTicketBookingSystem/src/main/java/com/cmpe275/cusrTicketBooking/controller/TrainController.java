package com.cmpe275.cusrTicketBooking.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe275.cusrTicketBooking.Utility;
import com.cmpe275.cusrTicketBooking.Exceptions.CancellationTimeLimitException;
import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.service.BookingService;
import com.cmpe275.cusrTicketBooking.service.TrainService;

@Controller
public class TrainController {

	
	@Autowired
	private TrainService trainService;
	
	@RequestMapping(value = "/cancel")
	public ModelAndView cancel() {
     
			 ModelAndView mv = new ModelAndView("cancel");
			    return mv;
    
}
		@RequestMapping(value = "/cancelTrain")
	public ModelAndView cancelTrain(@RequestParam String trainId,@RequestParam String tripDate) throws ParseException, CancellationTimeLimitException {
		
		trainService.cancelTrain(trainId,Utility.StringtoDateConvertor2(tripDate));
		
		//todo list
		ModelAndView mv = new ModelAndView("cancel");
		    return mv;
	}
}
