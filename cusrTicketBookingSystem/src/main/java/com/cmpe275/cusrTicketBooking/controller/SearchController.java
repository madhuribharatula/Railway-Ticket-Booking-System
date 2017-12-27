package com.cmpe275.cusrTicketBooking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cmpe275.cusrTicketBooking.Utility;
import com.cmpe275.cusrTicketBooking.model.ConnectionType;
import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.ItineraryItem;
import com.cmpe275.cusrTicketBooking.model.ItineraryList;
import com.cmpe275.cusrTicketBooking.model.RitineraryList;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.TrainType;
import com.cmpe275.cusrTicketBooking.model.SearchOptions;
import com.cmpe275.cusrTicketBooking.model.SearchRoundForm;
import com.cmpe275.cusrTicketBooking.service.SearchService;

@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private Utility utility;
	
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
	@RequestMapping(value = "/search")
	public ModelAndView reset() {
     
			 ModelAndView mv = new ModelAndView("hello");
			    return mv;
    
}
	
	
	
	@PostMapping(value = "/searchOneway")
	public ModelAndView searchTrains(
			@RequestParam(required=false) int passengerCount_ow, 
			@RequestParam(required=false) String outBoundJourneyDate_ow,
			@RequestParam(required=false) String outBoundDeptTime_ow,
			@RequestParam(required=false) String fromStation_ow, 
			@RequestParam(required=false) String toStation_ow,
			@RequestParam(required=false) String ticketType_ow, 
			@RequestParam(required=false) ConnectionType connectionType_ow, 
			@RequestParam(required=false) boolean isExactTime_ow,
			Model model) {
		try {
			
			SearchOptions options = new SearchOptions(
					passengerCount_ow,  
					Utility.StringtoDateConvertor2(outBoundJourneyDate_ow),  
					outBoundDeptTime_ow,  
					fromStation_ow,  
					toStation_ow,  
					TrainType.valueOf(ticketType_ow),
					connectionType_ow,  
					false, 
					isExactTime_ow);
		
			Map<TicketType, List<Itinerary>> ItenaryList = searchService.search(options);
			
			
			ItineraryList list = new ItineraryList();
			list.setItineraryList(ItenaryList.get(TicketType.ONEWAY));
			
			model.addAttribute("passengerCount", passengerCount_ow);
			model.addAttribute("searchList", list);
			//model.addAttribute("searchList",val);
			 ModelAndView mv = new ModelAndView("searchResult");
			 
		      return mv;
				
			
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("Error", e.getMessage());
		    ModelAndView mv = new ModelAndView("unsuccess");
		    return mv;
		}
	}
	
	@PostMapping(value = "/searchRoundtrip")
	public ModelAndView searchTrains(
			@RequestParam(required=true) int passengerCount_rt, 
			@RequestParam(required=true) String outBoundJourneyDate_rt,
			@RequestParam(required=true) String returnJourneyDate, 
			@RequestParam(required=true) String outBoundDeptTime_rt,
			@RequestParam(required=true) String returnDeptTime, 
			@RequestParam(required=true) String fromStation_rt, 
			@RequestParam(required=true) String toStation_rt,
			@RequestParam(required=true) String ticketType_rt, 
			@RequestParam(required=true) ConnectionType connectionType_rt, 
			@RequestParam(required=false) boolean isExactTime_rt,
			Model model) {
		try {
			
			
			SearchOptions options = new SearchOptions(
					passengerCount_rt,  
					Utility.StringtoDateConvertor2(outBoundJourneyDate_rt),  
					Utility.StringtoDateConvertor2(returnJourneyDate),  
					outBoundDeptTime_rt,
					returnDeptTime,  
					fromStation_rt,  
					toStation_rt,  
					TrainType.valueOf(ticketType_rt),
					connectionType_rt,  
					true, 
					isExactTime_rt);
		
			Map<TicketType, List<Itinerary>> ItenaryMap = searchService.search(options);

			ItineraryList list1 = new ItineraryList();
			list1.setItineraryList(ItenaryMap.get(TicketType.ONEWAY));
			
			ItineraryList list2 = new ItineraryList();
			list2.setItineraryList(ItenaryMap.get(TicketType.ROUNDTRIP));
			
			List<ItineraryList> val = new ArrayList<>();
			val.add(list1);
			val.add(list2);
			
			SearchRoundForm list = new SearchRoundForm();
			list.setSearchRoundList(val);
			model.addAttribute("passengerCount", passengerCount_rt);
			model.addAttribute("searchParentList", list);
			
			ModelAndView mv = new ModelAndView("searchResultRound");
			 
		      return mv;
				
		} catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("Error", e.getMessage());
		    ModelAndView mv = new ModelAndView("error");
		    return mv;		
		}
	}
	
}
