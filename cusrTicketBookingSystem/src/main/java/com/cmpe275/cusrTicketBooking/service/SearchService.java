package com.cmpe275.cusrTicketBooking.service;

import java.util.List;
import java.util.Map;

import com.cmpe275.cusrTicketBooking.model.Itinerary;
import com.cmpe275.cusrTicketBooking.model.Schedule;
import com.cmpe275.cusrTicketBooking.model.SearchOptions;
import com.cmpe275.cusrTicketBooking.model.TicketType;

public interface SearchService {

	List<List<Schedule>> searchForTrains(SearchOptions options) throws Exception;

	List<Itinerary> search(SearchOptions options, TicketType oneway);

	Map<TicketType, List<Itinerary>> search(SearchOptions options);

}
