package com.cmpe275.cusrTicketBooking.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe275.cusrTicketBooking.Utility;
import com.cmpe275.cusrTicketBooking.ActiveMQMail.EmailMsg;
import com.cmpe275.cusrTicketBooking.ActiveMQMail.MsgProducer;
import com.cmpe275.cusrTicketBooking.ActiveMQMail.Producer;
import com.cmpe275.cusrTicketBooking.Exceptions.CancellationTimeLimitException;
import com.cmpe275.cusrTicketBooking.Exceptions.SeatNotAvailableException;
import com.cmpe275.cusrTicketBooking.model.Booking;
import com.cmpe275.cusrTicketBooking.model.ItChecked;
import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.ItineraryList;
import com.cmpe275.cusrTicketBooking.model.SearchRoundForm;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.User;
import com.cmpe275.cusrTicketBooking.model.AllBooking;
import com.cmpe275.cusrTicketBooking.repositories.BookingDao;
import com.cmpe275.cusrTicketBooking.service.BookingService;
import com.cmpe275.cusrTicketBooking.service.TrainService;
import com.cmpe275.cusrTicketBooking.service.UserService;
import com.google.gson.Gson;

@Controller
public class TicketController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private Utility utility;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Producer producer;

	
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
	
	/*
	@RequestMapping(value = "/user/{userid}/onewaypurchase", method = RequestMethod.POST)
	public ResponseEntity<?> purchaseTicket(@PathVariable("userid") String userId, @RequestParam String trainNumber, @RequestParam String journeyDate, @RequestParam String source,
			@RequestParam String destination,@RequestParam int ticketCount) {
		try {
			Booking bookingRecord = bookingService.bookTicket(userId,trainNumber,journeyDate,source,destination,ticketCount,false);
			String msg = constrcutMessage(bookingRecord);
			
			try{
				utility.sendEmail(bookingRecord.getUser().getEmailid(), msg, "BookingConfiramtion from cusr");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<Booking>(bookingRecord,HttpStatus.OK);
			
		} catch (SeatNotAvailableException e) {
			return new ResponseEntity<String>("Seats are not avialable",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/user/{userid}/roundtrippurchase", method = RequestMethod.POST)
	public ResponseEntity<?> purchaseRoundTripTicket(@PathVariable("userid") String userId, @RequestParam String outBoundtrainNumber, @RequestParam String outboundDate,
			@RequestParam String returntrainNumber, @RequestParam String returnDate,
			@RequestParam String source,@RequestParam String destination,@RequestParam int ticketCount) {
		try {
			Booking bookingRecord = bookingService.bookRoundTripTicket(userId,outBoundtrainNumber,outboundDate,returntrainNumber,returnDate,source,destination,ticketCount,true);
			String msg = constrcutMessage(bookingRecord);
			
			try{
				utility.sendEmail(bookingRecord.getUser().getEmailid(), msg, "BookingConfiramtion from cusr");
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return new ResponseEntity<Booking>(bookingRecord,HttpStatus.OK);
			
		} catch (SeatNotAvailableException e) {
			return new ResponseEntity<String>("Seats are not avialable",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	*/
	
	// Booking Ticket 
	
	@RequestMapping(value = "/bookings")
	public ModelAndView reset(HttpSession session,Model model) {
		String userId = session.getAttribute("email").toString();
		 
		if(userId == null || userId.isEmpty()) {
			// TODO need to throw exception for not looged in user 
		}
		User user = userService.getUserByEmail(userId);
		Map<ItChecked,List<Booking>> bookingMap=(Map<ItChecked, List<Booking>>) bookingDao.getALLBookingsForUser(user);
		AllBooking allbookinglist = new AllBooking(bookingMap);
		
		     model.addAttribute("allbookinglist", allbookinglist);
			 ModelAndView mv = new ModelAndView("bookings");
			 return mv;
    
}
	
	
	
	@PostMapping(value = "/bookTicket" )
	public ModelAndView bookTicket(@ModelAttribute("searchList") ItineraryList itineraryList,HttpSession session,Model model) {
		String userId = session.getAttribute("email").toString();
		
		List<String> passsengers=itineraryList.getPassengerNames();
		
		List<Itinerary> itList = itineraryList.getItineraryList();
		Itinerary bookItinerary = null;
		for(Itinerary itn : itList) {
			if(itn.isChecked()) {
				bookItinerary = itn;
				break;
			}
		}
		
		List<Itinerary> toBepassed = new ArrayList<Itinerary>();
		
		toBepassed.add(bookItinerary);

		

		
		
		if(userId == null || userId.isEmpty()) {
			// TODO need to throw exception for not looged in user 
		}
		User user = userService.getUserByEmail(userId);
//		List<Itinerary> it = new ArrayList<Itinerary>();
//		it.add(itineraryList);
//		
		//TODO change the method signaturess to accept only itenraties NS SEPECTARE LOGIC FOR ROUNDTRIP
		
		
		try {
			List<Booking> bookingRecord = bookingService.bookTicketsWithAvailabilityCheck(user,toBepassed,passsengers);
			// TODO -  Add code for booking confirmation email
			EmailMsg emailMsg = new EmailMsg();
			emailMsg.setMailId(user.getEmailid());
			emailMsg.setBody(bookingRecord.toString());
			emailMsg.setSubject(Utility.bookingConfirmation);
			producer.setEmailMsg(emailMsg);
			this.producer.send(emailMsg);
			// Email code ends
			
		//	model.addAttribute("searchList", ItenaryList.get(TicketType.ONEWAY));
	       model.addAttribute("bookingList",bookingRecord);
			ModelAndView mv = new ModelAndView("booking");
			 
		      return mv;
			
			//return new ResponseEntity<List<Booking>>(bookingRecord,HttpStatus.OK);

		}catch(Exception e) {
			e.printStackTrace();
			//return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
		ModelAndView mv = new ModelAndView("unsuccess");
		 
	      return mv;
	
		}
		}
	
	@PostMapping(value = "/bookRoundTicket" )
	public ModelAndView bookRoundTicket(@ModelAttribute("SearchRoundForm") SearchRoundForm itineraryListList,HttpSession session,Model model) {
		String userId = session.getAttribute("email").toString();
		List<String> passsengers = null;
		List<Itinerary> toBepassed = new ArrayList<Itinerary>();
		passsengers=itineraryListList.getPassengerNames();
		for(ItineraryList itineraryList:itineraryListList.getSearchRoundList()) {
				
		List<Itinerary> itList = itineraryList.getItineraryList();
		Itinerary bookItinerary = null;
		for(Itinerary itn : itList) {
			if(itn.isChecked()) {
				bookItinerary = itn;
				toBepassed.add(bookItinerary);
				break;
			}
		}
		}
		
		
		

		if(userId == null || userId.isEmpty()) {
			// TODO need to throw exception for not looged in user 
		}
		User user = userService.getUserByEmail(userId);
//		List<Itinerary> it = new ArrayList<Itinerary>();
//		it.add(itineraryList);
//		
		//TODO change the method signaturess to accept only itenraties NS SEPECTARE LOGIC FOR ROUNDTRIP
		
		
		try {
			List<Booking> bookingRecord = bookingService.bookTicketsWithAvailabilityCheck(user,toBepassed,passsengers);
			// TODO -  Add code for booking confirmation email
			EmailMsg emailMsg = new EmailMsg();
			emailMsg.setMailId(user.getEmailid());
			emailMsg.setBody(bookingRecord.toString());
			emailMsg.setSubject(Utility.bookingConfirmation);
			producer.setEmailMsg(emailMsg);
			this.producer.send(emailMsg);
			// Email code ends
			
		//	model.addAttribute("searchList", ItenaryList.get(TicketType.ONEWAY));
	       model.addAttribute("bookingList",bookingRecord);
			ModelAndView mv = new ModelAndView("booking");
			 
		      return mv;
			
			//return new ResponseEntity<List<Booking>>(bookingRecord,HttpStatus.OK);

		}catch(Exception e) {
			e.printStackTrace();
			//return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		
		ModelAndView mv = new ModelAndView("unsuccess");
		 
	      return mv;
	
		}
		}

	
	@PostMapping(value = "/bookTicketRound" )
	public ModelAndView bookTicketRound(@ModelAttribute("allbookinglist") ItineraryList itineraryList,HttpSession session,Model model) {
		String userId = session.getAttribute("email").toString();
		
		
				
		if(userId == null || userId.isEmpty()) {
			// TODO need to throw exception for not looged in user 
		}
		User user = userService.getUserByEmail(userId);
//		List<Itinerary> it = new ArrayList<Itinerary>();
//		it.add(itineraryList);
//		
		//TODO change the method signaturess to accept only itenraties NS SEPECTARE LOGIC FOR ROUNDTRIP
		
		
		try {
//			List<Booking> bookingRecord = bookingService.bookTicketsWithAvailabilityCheck(userId,itineraryList.getItineraryList());
//			// TODO -  Add code for booking confirmation email
//			EmailMsg emailMsg = new EmailMsg();
//			emailMsg.setMailId(user.getEmailid());
//			emailMsg.setBody(bookingRecord.toString());
//			emailMsg.setSubject(Utility.bookingConfirmation);
//			producer.setEmailMsg(emailMsg);
//			this.producer.send(emailMsg);
//			// Email code ends
//			
		//	model.addAttribute("searchList", ItenaryList.get(TicketType.ONEWAY));
			//model.addAttribute("searchList",val);
			ModelAndView mv = new ModelAndView("unsuccess");
			 
		      return mv;
			
			//return new ResponseEntity<List<Booking>>(bookingRecord,HttpStatus.OK);

		}catch(Exception e) {
			e.printStackTrace();
			//return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return null;
		
	}
	
	
	// Cancel Tickets that belongs to an Itinerary
	
	@PostMapping(value = "/cancelTicket")
	public ModelAndView bookTicket(@RequestParam("allbookinglist") String allbooking,HttpSession session,Model model) throws CancellationTimeLimitException {
//	
		String userId = session.getAttribute("email").toString();

		User user = userService.getUserByEmail(userId);

			try{
				List<Booking> bookingRecord = bookingService.cancelTickets(userId,allbooking.trim());
				// TODO -  Add code for cancellation confirmation email
				EmailMsg emailMsg = new EmailMsg();
				emailMsg.setMailId(user.getEmailid());
				emailMsg.setBody(bookingRecord.toString());
				emailMsg.setSubject(Utility.cancelationSubject);
				producer.setEmailMsg(emailMsg);
				this.producer.send(emailMsg);
		// Email code ends
				
		return getBookings(user,model);	
	
			} catch(CancellationTimeLimitException e) {
				model.addAttribute("Message", "Can not cancel the Ticket Time limit exceeded");
				ModelAndView mv = new ModelAndView("unsuccess");
				 
			    return mv;
			}
			
				    
		}
	
	private ModelAndView getBookings(User user,Model model) {
		Map<ItChecked,List<Booking>> bookingMap=(Map<ItChecked, List<Booking>>) bookingDao.getALLBookingsForUser(user);
		AllBooking allbookinglist = new AllBooking(bookingMap);
		model.addAttribute("allbookinglist", allbookinglist);
		ModelAndView mv = new ModelAndView("bookings");
		return mv;		
	}
		
	
	private String constrcutMessage(Booking bookingRecord) {
		Gson gson = new Gson();
		StringBuilder sb = new StringBuilder();
		sb.append("Your Ticket has been booked");
		sb.append("\n");
		sb.append("Below are the booking details");
		sb.append(gson.toJson(bookingRecord));
		return sb.toString();
	}
	
	
	
}
