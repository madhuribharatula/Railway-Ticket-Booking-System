package com.cmpe275.cusrTicketBooking.repositories;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.cusrTicketBooking.model.BookingStatus;
import com.cmpe275.cusrTicketBooking.model.TicketType;
import com.cmpe275.cusrTicketBooking.model.Train;
import com.cmpe275.cusrTicketBooking.model.TrainStatus;
import com.cmpe275.cusrTicketBooking.model.TrainTrip;
import com.cmpe275.cusrTicketBooking.model.TrainType;

@Transactional
@Repository
public class TrainDaoJpaImpl  implements TrainDao{

	@PersistenceContext	
	private EntityManager entityManager;
	
	@Transactional
	public Train readByNumber(String trainNumber) {
		String sql = "SELECT t FROM Train t WHERE t.trainNumber = :trainNumber";
		Query query = entityManager.createQuery(sql);
		query.setParameter("trainNumber", trainNumber);
		return (Train) query.getResultList().get(0);
	}

	@Transactional
	public List<Train> getRunningTraindByType(TrainType type,Date journeyDate) {
		Query query = entityManager.createQuery("SELECT t FROM train t, Train_trip tp WHERE c.Trainid = tp.Train_Id AND c.Traintype LIKE :trainType AND tp.STATUS = :status AND tp.Trip_date = :journeyDate");
		query.setParameter("trainType", type);
		query.setParameter("journeyDate", journeyDate);
		//query.setParameter("status", TrainStatus.RUNNING);
		return query.getResultList();
	}
	
	@Transactional
	public List<Train> getTrainsAtGivenSource(Date journeyDate,String departTime,String source,boolean isExactTime,TrainType trainType,String destination,Time beforeTime){
		String beforeTimeQuery="";
//		if(beforeTime != null) {
//			 beforeTime = Time.valueOf(beforeTime.toLocalTime().plusHours(2));
//			 beforeTimeQuery = "AND ts.departureTime  <= Time(:beforeTime)";
//		}
        LocalDate localDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now(ZoneId.of("America/Los_Angeles"));
		LocalTime timeCheck = timeNow.plusMinutes(5);
		int charval = source.charAt(0) - destination.charAt(0);
		String trainNumber = (charval >0)? "NB%" : "SB%";
		
		String timeCheckQ = "";
		if(localDate.equals(journeyDate.toLocalDate())) {
			timeCheckQ = "AND ts.departureTime  > Time(:timeCheck)"; 
		}
		
		String notIsExactTimeQuery = "SELECT DISTINCT(t) FROM Train t   \r\n" + 
				"	JOIN t.schedules ts  \r\n" + 
				"	JOIN t.trainTrips tp  \r\n" + 
				"	WHERE   \r\n" + 
				"	ts.departureTime >= TIME(:deptTime)							        \r\n" + 
				"	AND t.trainType LIKE :trainType    \r\n" + 
				"	AND tp.tripDate = :journeyDate   \r\n" + 
				"	AND tp.status = :status \r\n" + 
				"   AND ts.station.stationName =:startStation\r\n" + 
				"   AND t.trainNumber LIKE :trainNumber\r\n" + 
				timeCheckQ +" "+
				"    GROUP BY t.trainId\r\n" + 
				"    ORDER BY ts.arrivalTime \r\n" ;
		String isExactTimeQuery = "SELECT DISTINCT(t) FROM Train t   \r\n" + 
				"	JOIN t.schedules ts  \r\n" + 
				"	JOIN t.trainTrips tp  \r\n" + 
				"	WHERE   \r\n" + 
				"	ts.departureTime = TIME(:deptTime)							        \r\n" + 
				"	AND t.trainType LIKE :trainType    \r\n" + 
				"	AND tp.tripDate = :journeyDate   \r\n" + 
				"	AND tp.status = :status \r\n" + 
				"   AND ts.station.stationName =:startStation\r\n" +
				"   AND t.trainNumber LIKE :trainNumber\r\n" + 
				timeCheckQ +" "+
				"    GROUP BY t.trainId\r\n" + 
				"    ORDER BY ts.arrivalTime \r\n" ;
				
		String queryToExecute = (isExactTime)? isExactTimeQuery : notIsExactTimeQuery;
		Query query = entityManager.createQuery(queryToExecute);
		query.setParameter("deptTime", departTime);
		query.setParameter("journeyDate", journeyDate);
		query.setParameter("startStation", source);
		query.setParameter("trainNumber", trainNumber);
		query.setParameter("trainType", TrainType.REGULAR);
		query.setParameter("status", TrainStatus.RUNNING);
		if(!beforeTimeQuery.equals("")) {
			query.setParameter("beforeTime", beforeTime);
		}
//		if (!timeCheckQ.equals("")) {
//			query.setParameter("timeCheck", timeCheck);
//		}
		System.out.println("*****************************" + query);
		List<Train> trainListRegular = query.setMaxResults(5).getResultList();
		List<Train> trainListExpress = null;
		if(!trainType.equals(TrainType.REGULAR)) {
			
			String queryToExecute_express = (isExactTime)? isExactTimeQuery : notIsExactTimeQuery;
			Query queryNew = entityManager.createQuery(queryToExecute_express);
			queryNew.setParameter("deptTime", departTime);
			queryNew.setParameter("journeyDate", journeyDate);
			queryNew.setParameter("startStation", source);
			queryNew.setParameter("trainNumber", trainNumber);
			queryNew.setParameter("trainType", TrainType.EXPRESS);
			queryNew.setParameter("status", TrainStatus.RUNNING);
//			if(!beforeTimeQuery.equals("")) {
//				queryNew.setParameter("beforeTime", beforeTime);
//			}
			if (!timeCheckQ.equals("")) {
				queryNew.setParameter("timeCheck", timeCheck);
			}
			
			trainListExpress = queryNew.setMaxResults(5).getResultList();
			}
		if(trainListExpress !=null)
			trainListRegular.addAll(trainListExpress);
		
		return trainListRegular;

		


	}

	@Transactional
	public List<Train> getTrainsAtGivenSourceDestination(Date journeyDate, String departTime, String source,
			String destination, boolean isExactTime,TrainType trainType,Time beforeTime) {
		
		LocalDate localDate = LocalDate.now();
        LocalTime timeNow = LocalTime.now(ZoneId.of("America/Los_Angeles"));
		LocalTime timeCheck = timeNow.plusMinutes(5);
		int charval = source.charAt(0) - destination.charAt(0);
		String trainNumber = (charval >0)? "NB%" : "SB%";
		String timeCheckQ = "";
		if(localDate.equals(journeyDate.toLocalDate())) {
			timeCheckQ = "AND ts.departureTime  > Time(:timeCheck)"; 
		}
		
//		String beforeTimeQuery="";
//		if(beforeTime != null) {
//			 beforeTime = Time.valueOf(beforeTime.toLocalTime().plusHours(2));
//			 beforeTimeQuery = "AND ts.departureTime  <= Time(:beforeTime)";
//		}
		
		String notIsExactTimeQuery = "SELECT  DISTINCT(t) FROM Train t   \r\n" + 
				"	JOIN t.schedules ts  \r\n" + 
				"	JOIN t.trainTrips tp  \r\n" + 
				"	WHERE   \r\n" + 
				"	ts.departureTime >= TIME(:deptTime)							        \r\n" + 
				"	AND t.trainType LIKE :trainType    \r\n" + 
				"	AND tp.tripDate = :journeyDate   \r\n" + 
				"	AND tp.status = :status \r\n" + 
				"    AND ts.station.stationName =:startStation\r\n" + 
				"    AND EXISTS (SELECT 1 FROM t.schedules tb WHERE tb.station.stationName = :destination AND tb.train.trainId = t.trainId)\r\n" + 
				"    AND t.trainNumber LIKE :trainNumber\r\n" + 
				timeCheckQ +" "+
				"    GROUP BY t.trainId\r\n" + 
				"    ORDER BY ts.arrivalTime \r\n" ;
		
		String isExactTimeQuery = "SELECT DISTINCT(t) FROM Train t   \r\n" + 
				"	JOIN t.schedules ts  \r\n" + 
				"	JOIN t.trainTrips tp  \r\n" + 
				"	WHERE   \r\n" + 
				"	ts.departureTime = TIME(:deptTime)							        \r\n" + 
				"	AND t.trainType LIKE :trainType    \r\n" + 
				"	AND tp.tripDate = :journeyDate   \r\n" + 
				"	AND tp.status = :status \r\n" + 
				"    AND ts.station.stationName =:startStation\r\n" + 
				"    AND EXISTS (SELECT 1 FROM t.schedules tb WHERE tb.station.stationName = :destination AND tb.train.trainId = t.trainId)\r\n" + 
				"    AND t.trainNumber LIKE :trainNumber\r\n" + 
				timeCheckQ +" "+
				"    GROUP BY t.trainId\r\n" + 
				"    ORDER BY ts.arrivalTime \r\n";
		
		String queryToExecute = (isExactTime)? isExactTimeQuery : notIsExactTimeQuery;
		Query query = entityManager.createQuery(queryToExecute);
		query.setParameter("deptTime", departTime);
		query.setParameter("journeyDate", journeyDate);
		query.setParameter("startStation", source);
		query.setParameter("trainType", TrainType.REGULAR);
		query.setParameter("status", TrainStatus.RUNNING);
		query.setParameter("destination", destination);
		query.setParameter("trainNumber", trainNumber);
//		if(!beforeTimeQuery.equals("")) {
//			query.setParameter("beforeTime", beforeTime);
//		}
		if (!timeCheckQ.equals("")) {
			query.setParameter("timeCheck", timeCheck);
		}
		
		List<Train> trainListRegular = query.setMaxResults(5).getResultList();
		List<Train> trainListExpress = null;
		if(!trainType.equals(TrainType.REGULAR)) {
			String queryToExecute_express = (isExactTime)? isExactTimeQuery : notIsExactTimeQuery;
			Query query_new = entityManager.createQuery(queryToExecute_express);
			query_new.setParameter("deptTime", departTime);
			query_new.setParameter("journeyDate", journeyDate);
			query_new.setParameter("startStation", source);
			query_new.setParameter("trainType", TrainType.EXPRESS);
			query_new.setParameter("status", TrainStatus.RUNNING);
			query_new.setParameter("destination", destination);
			query_new.setParameter("trainNumber", trainNumber);
//			if(!beforeTimeQuery.equals("")) {
//				query_new.setParameter("beforeTime", beforeTime);
//			}
			if (!timeCheckQ.equals("")) {
				query_new.setParameter("timeCheck", timeCheck);
			}
			trainListExpress = query_new.setMaxResults(5).getResultList();
		}
				
		if(trainListExpress !=null)
			trainListRegular.addAll(trainListExpress);
		
		return trainListRegular;

	}


	@Transactional
	public Train read(int trainId) {
		return entityManager.find(Train.class, trainId);
	}

	@Transactional
	public void cancelTrainTrip(TrainTrip trip) {
		trip.setStatus(TrainStatus.CANCELED);		
	}
	@Transactional
	public void resetCapacity(int trainCapacity) {
		Query query = entityManager.createQuery(
			      "UPDATE Train t SET t.capacity = :trainCapacity");
		query.setParameter("trainCapacity", trainCapacity);
		query.executeUpdate();
	}

	@Transactional
	public TrainTrip readByNumberAndTripDate(String trainNumber, Date tripDate) {
		
		Train t = readByNumber(trainNumber);
		
		String sql = "SELECT tp FROM TrainTrip tp  WHERE tp.train = :train AND tp.tripDate = :tripDate";
		Query query = entityManager.createQuery(sql);
		query.setParameter("train", t);
		query.setParameter("tripDate", tripDate);
		return (TrainTrip) query.getResultList().get(0);
		
	}

	
	

	

}
