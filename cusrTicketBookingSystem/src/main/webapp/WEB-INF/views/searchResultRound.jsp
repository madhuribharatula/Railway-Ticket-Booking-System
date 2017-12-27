<!--
	Author: W3layouts
	Author URL: http://w3layouts.com
	License: Creative Commons Attribution 3.0 Unported
	License URL: http://creativecommons.org/licenses/by/3.0/
-->

<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>

<%@ page isELIgnored="false"%>

<title>CUSR Booking</title>
<link rel="stylesheet" href="/resources/css/style2.css">
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300italic,300,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700'
	rel='stylesheet' type='text/css'>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Flight Ticket Booking  Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

</head>
<body>


	<ul class="tabs-list" style="text-align: right;">
		<li class="tab-item">welcome ${name}</li>
		<li class="tab-item"><a href="/search">Search</a></li>
		<li class="tab-item"><a href="/bookings">Bookings</a></li>
		<li class="tab-item"><a href="/cancel"> Train Cancellation</a></li>
		<li class="tab-item"><a href="/reset">Reset</a></li>
		<li class="tab-item"><a href="/logout">logout</a></li>

	</ul>
	<h1>California Ultra-Speed Rail Ticket Booking</h1>

	<div class="main-agileinfo">
		<div class="sap_tabs">
			<div id="horizontalTab">
				<ul class="resp-tabs-list" style="text-align: center;">

					<h2>Search Result</h2>
				</ul>
				<div class="clearfix"></div>
				<div class="resp-tabs-container"></div>
				<form:form modelAttribute="searchParentList" action="/bookRoundTicket"
					method="POST" id="bookTicket">
					
					<c:forEach var='i' begin='0' end='${passengerCount-1}'>
							<h3>
								Passenger Name:
								<form:input path="PassengerNames[${i}]"
									placeholder="passenger ${i+1} name"
									style="width:50%;float:right;s" />
								<br></br>
							</h3>
						</c:forEach>
						
					<c:forEach items="${searchParentList.searchRoundList}"
						var="searchList" varStatus="parentIndex">
						
						<c:choose>
    <c:when test="${parentIndex.index==0}">
<h3>Out Bound Search Results  </h3>      <br />
    </c:when>    
    <c:otherwise>
     <br /> <h3> Return Search Results</h3>
        <br />
    </c:otherwise>
</c:choose>

						<c:forEach items="${searchList.itineraryList}" var="item"
							varStatus="ctr">
							<li>

								<h3>
									Result:
									<c:out value="${ctr.index+1}" />
								</h3> <form:input path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].totalPrice"
									value="${item.totalPrice}" style="display:none;" /> <input
								type="text" style="display: none;"
								value="<c:out value="${ctr.index}" />" />







								<table>

									<tr>
										<td></td>


										<td><form:checkbox
												path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].checked" /></td>


										<td width="100">Train Number</td>
										<td width="100">Train type</td>
										<td width="150">From Station</td>
										<td width="150">To Station</td>
										<td width="150">Journey date</td>
										<td width="150">Departure time</td>
										<td width="150">Arrival time</td>
										<td width="150">Price</td>
									</tr>
									<c:forEach items="${item.itineraryItemList}" var="itemList"
										varStatus="itr">


										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index}].train.trainId"
											value="${itinerary.train.trainNumber}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].train.trainType"
											value="${itinerary.train.trainType}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].source"
											value="${itinerary.train.source}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].destination"
											value="${itinerary.destination}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].journeyDate"
											value="${itinerary.journeyDate}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].departureTime"
											value="${itinerary.departureTime}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].arrivalTime"
											value="${itinerary.arrivalTime}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].price"
											value="${itinerary.price}" style="display:none;" />
										<form:input
											path="searchRoundList[${parentIndex.index}].itineraryList[${ctr.index}].itineraryItemList[${itr.index }].ticketCount"
											value="${itinerary.ticketCount}" style="display:none;" />


										<tr>
											<td></td>
											<td></td>
											<td><c:out value="${itemList.train.trainNumber}" /></td>
											<td><c:out value="${itemList.train.trainType}" /></td>
											<td><c:out value="${itemList.source}" /></td>
											<td><c:out value="${itemList.destination}" /></td>
											<td><c:out value="${itemList.journeyDate}" /></td>
											<td><c:out value="${itemList.departureTime}" /></td>
											<td><c:out value="${itemList.arrivalTime}" /></td>
											<td><c:out value="${itemList.price}" /></td>
										</tr>
										</c:forEach>
								</table>


							</li>
						</c:forEach>

					</c:forEach>

					<input type="submit" id="book<c:out value="${ctr.index}" />"
						value="book">
				</form:form>



			</div>
		</div>

	</div>


	<div class="footer-w3l">
		<p class="agileinfo">
			&copy; 2016 Flight Ticket Booking . All Rights Reserved | Design by <a
				href="http://w3layouts.com">W3layouts</a>
		</p>
	</div>
	<!--script for portfolio-->
	<script src="/resources/js/jquery.min.js"> </script>
	<script src="/resources/js/easyResponsiveTabs.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		function myFunc(id) {
 var btn=document.getElementById(id);
 var x = document.getElementById(id).nextElementSibling;
  if (btn.value === 'more') {
    btn.value = 'less';
     x.style.display = "block";
   
  } else {
    btn.value = 'more';
     x.style.display = "none";
  }
}
		
		function checkFunc(id) {
			var element=document.getElementById(id).value;
			if(element.checked)
				{ console.log(id);
			document.getElementById("to").value=id; 
				}
		}
</script>


	<!-- //load-more -->



</body>
</html>