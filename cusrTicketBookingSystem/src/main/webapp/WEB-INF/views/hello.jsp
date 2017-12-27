<!--
	Author: W3layouts
	Author URL: http://w3layouts.com
	License: Creative Commons Attribution 3.0 Unported
	License URL: http://creativecommons.org/licenses/by/3.0/
-->


<!DOCTYPE html>
<html>
<head>
	<title>CUSR Booking</title>
	<link rel="stylesheet" href="/resources/css/style2.css">
	<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300italic,300,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
	<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="Flight Ticket Booking  Widget Responsive, Login Form Web Template, Flat Pricing Tables, Flat Drop-Downs, Sign-Up Web Templates, Flat Web Templates, Login Sign-up Responsive Web Template, Smartphone Compatible Web Template, Free Web Designs for Nokia, Samsung, LG, Sony Ericsson, Motorola Web Design" />
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	
</head>
<body>
	

<ul class="tabs-list" style=" text-align: right;">	
<li class="tab-item">welcome ${name}</li>
<li class="tab-item"><a href="/search">Search</a></li>
<li class="tab-item" ><a href="/bookings">Bookings</a></li>	
<li class="tab-item" ><a href="/cancel"> Train Cancellation</a></li>	
<li class="tab-item"><a href="/reset">Reset</a></li>
<li class="tab-item"><a href="/logout">logout</a></li>
</ul>
		
		
		
<h1> California Ultra-Speed Rail Ticket Booking</h1>
	
<div class="main-agileinfo">
	<div class="sap_tabs">			
		<div id="horizontalTab">
			<ul class="resp-tabs-list" style=" text-align: center;">
				<li class="resp-tab-item"><span>One Way</span></li>
				<li class="resp-tab-item"><span>Roundtrip</span></li>			
			</ul>	
			<div class="clearfix"> </div>	
			<div class="resp-tabs-container">
				<div class="tab-1 resp-tab-content oneway">
					<form action="/searchOneway" method="post" id="oneway">
						
						<div class="from">
							<h3>From Station</h3>
							<select name ="fromStation_ow" id="fromStation_ow" required style=" float:left;width: 45%;"><option value="">Enter Departure Station</option><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option><option value="E">E</option><option value="F">F</option><option value="G">G</option><option value="H">H</option><option value="I">I</option><option value="J">J</option><option value="K">K</option><option value="L">L</option><option value="M">M</option><option value="N">N</option><option value="O">O</option><option value="P">P</option><option value="Q">Q</option><option value="R">R</option><option value="S">S</option><option value="T">T</option><option value="U">U</option><option value="V">V</option><option value="W">W</option><option value="X">X</option><option value="Y">Y</option><option value="Z">Z</option></select>

						</div>
						
						<div class="to">
							<h3>To Station</h3>
							<select name="toStation_ow" id="toStation_ow" required style=" float:left;width: 45%;"><option value="">Enter Destination Station</option><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option><option value="E">E</option><option value="F">F</option><option value="G">G</option><option value="H">H</option><option value="I">I</option><option value="J">J</option><option value="K">K</option><option value="L">L</option><option value="M">M</option><option value="N">N</option><option value="O">O</option><option value="P">P</option><option value="Q">Q</option><option value="R">R</option><option value="S">S</option><option value="T">T</option><option value="U">U</option><option value="V">V</option><option value="W">W</option><option value="X">X</option><option value="Y">Y</option><option value="Z">Z</option></select>
						</div>
						<div class="clear"></div>
						
						<div class="date">
							<div class="depart">
								<h3>Departure date</h3>
								<input id="outBoundJourneyDate_ow" name="outBoundJourneyDate_ow" required type="text" value="yyyy-MM-dd" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'yyyy-MM-dd';}" required >
								
								<h3>Departure time</h3>
				
								<span class="departtime" style="display: inline;">
									<select required class="hour1" id="hours1" style=" float:left;width: 45%;"><option value="">hour</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option></select>
									&nbsp;<h3 style="float:left">:</h3>&nbsp;
									<select required class="minute1" id="minutes1" style="float:left; width: 45%;"><option value="">minute</option><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option></select>
								</span>
 
                                <input type="text" id="outBoundDeptTime_ow"  name="outBoundDeptTime_ow" style="display: None;" value=""></input>
								<span class="checkbox1">
									<input type="checkbox"  value="true" name="isExactTime_ow" ><label>Exact time</label>
								</span>
							</div>
						</div>
							
						<div>	
						<h3>Ticket Type</h3>			
						<select id="ticketType_ow" name="ticketType_ow" required class="type" style=" float:left;width:40%">
							<option value="REGULAR">Regular</option>
							<option value="EXPRESS">Express</option>
							<option value="Any" selected>Any</option>
						</select>
							</div>
									
						<div>
						<h3 style="padding: 0.6em;"> Number of Connections</h3>
     					<select id="connectionType_ow" name="connectionType_ow" required style="float:left;width: 40%;">
	     					<option value="Any" selected>Any</option>
	     					<option value="None">None</option>
	     					<option value="One">One</option>
	     				</select>
							</div>
						<div class="clear"></div>
						
						
							
								<h3>Number of Passengers</h3>
								
								<div class="quantity" > 
									<div class="quantity-select">                           
										<div class="entry value-minus" id="value-minus-ow">&nbsp;</div>
										<div class="entry value"><span>1</span></div>
										<div class="entry value-plus active" id="value-plus-ow">&nbsp;</div>
									</div>
							 <input type="text"  id="passengerCount_ow"   Name="passengerCount_ow" style="display: block;" value=1 ></input>
									
								</div>
							
							<div class="clear"></div>
						
						<div class="clear"></div>
							
						<input type="submit" value="Search Trains">
					</form>						
				</div>		
				
				<div class="tab-1 resp-tab-content roundtrip">
					<form action="/searchRoundtrip" method="post" id="roundtrip">
						<div class="from">
							<h3>From Station</h3>
							<select id="fromStation_rt" name="fromStation_rt" required style=" float:left;width: 45%;"><option value="">Enter Departure Station</option><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option><option value="E">E</option><option value="F">F</option><option value="G">G</option><option value="H">H</option><option value="I">I</option><option value="J">J</option><option value="K">K</option><option value="L">L</option><option value="M">M</option><option value="N">N</option><option value="O">O</option><option value="P">P</option><option value="Q">Q</option><option value="R">R</option><option value="S">S</option><option value="T">T</option><option value="U">U</option><option value="V">V</option><option value="W">W</option><option value="X">X</option><option value="Y">Y</option><option value="Z">Z</option></select>
						</div>

						<div class="to">
							<h3>To Station</h3>
							<select id="toStation_rt" name="toStation_rt" required style=" float:left;width: 45%;"><option value="">Enter Destination Station</option><option value="A">A</option><option value="B">B</option><option value="C">C</option><option value="D">D</option><option value="E">E</option><option value="F">F</option><option value="G">G</option><option value="H">H</option><option value="I">I</option><option value="J">J</option><option value="K">K</option><option value="L">L</option><option value="M">M</option><option value="N">N</option><option value="O">O</option><option value="P">P</option><option value="Q">Q</option><option value="R">R</option><option value="S">S</option><option value="T">T</option><option value="U">U</option><option value="V">V</option><option value="W">W</option><option value="X">X</option><option value="Y">Y</option><option value="Z">Z</option></select>
						</div>
						<div class="clear"></div>
							
						<div class="date">
							<div class="depart">
								<h3>Departure date</h3>
								<input  id="outBoundJourneyDate_rt" name="outBoundJourneyDate_rt" required type="text" value="mm/dd/yyyy" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'mm/dd/yyyy';}" required="">
									
								<h3>Departure time</h3>
								<span class="departtime"  style="display: inline;">
									<select required class="hour2" id="hours2" style=" float:left;width: 45%;"><option value="">hour</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option></select>
									&nbsp;<h3 style="float:left">:</h3>&nbsp;
									<select required class="minute2" id="minutes2" style="float:left; width: 45%;"><option value="">minute</option><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option></select>
								</span>
							  <input type="text" id="outBoundDeptTime_rt"  name="outBoundDeptTime_rt" style="display: None;" value=""></input>
	
								<span class="checkbox1">
									<label class="checkbox"><input type="checkbox" name="isExactTime_rt" ><i> </i>Exact time</label>
								</span>
							</div>
								
							<div class="return">
								<h3>Return date</h3>
								<input  id="returnJourneyDate" name="returnJourneyDate" required type="text" value="mm/dd/yyyy" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'mm/dd/yyyy';}" required="">
									
								<h3>Return time</h3>
								<span class="returntime" >
									<select required class="hour3" id="hours3" style=" float:left;width: 45%;"><option value="">hour</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option></select>
									&nbsp;<h3 style="float:left">:</h3>&nbsp;
									<select required class="minute3" id="minutes3"  style="float:left;width: 45%;"><option value="">minute</option><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option><option value="32">32</option><option value="33">33</option><option value="34">34</option><option value="35">35</option><option value="36">36</option><option value="37">37</option><option value="38">38</option><option value="39">39</option><option value="40">40</option><option value="41">41</option><option value="42">42</option><option value="43">43</option><option value="44">44</option><option value="45">45</option><option value="46">46</option><option value="47">47</option><option value="48">48</option><option value="49">49</option><option value="50">50</option><option value="51">51</option><option value="52">52</option><option value="53">53</option><option value="54">54</option><option value="55">55</option><option value="56">56</option><option value="57">57</option><option value="58">58</option><option value="59">59</option></select>
								</span>
							</div>
							<input type="text" id="returnDeptTime"  name="returnDeptTime" style="display: None;" value=""></input>
	
						</div>
						
						<div>	
							<h3>Ticket Type</h3>			
							<select id="ticketType_rt" name="ticketType_rt" required class="type1" style=" float:left;width:40%">
							<option value="Any" selected>Any</option>
							<option value="REGULAR">Regular</option><option value="EXPRESS">Express</option></select>
						</div>
						
						<div>
							<h3 style="padding: 0.6em;">Number of Connections</h3>
     						<select id="connectionType_rt" name="connectionType_rt" required style="float:left;width: 40%;">
     						<option value="Any" selected>Any</option><option value="None">None</option><option value="One">One</option></select>
						</div>
							
						<div class="clear"></div>
						<div class="numofppl">
						<div class="passengers1">
							<h3>Number of Passengers</h3>
								<div class="quantity"> 
									<div class="quantity-select">                           
										<div class="entry value-minus" id="value-minus-rt">&nbsp;</div>
										<div class="entry value"><span>1</span></div>
										<div class="entry value-plus active" id="value-plus-rt">&nbsp;</div>
									</div>
									<input type="text"  id="passengerCount_rt"   name="passengerCount_rt" style="display: block;" value=1 ></input>
									
								</div>
								<div class="clear"></div>
						</div>
						<div class="clear"></div>
						<input type="submit" value="Search Trains">
					</form>	
				</div>
			</div>						
		</div>
	</div>
</div>
	
<div class="footer-w3l">
<p class="agileinfo"> &copy; 2016 Flight Ticket Booking . All Rights Reserved | Design by <a href="http://w3layouts.com">W3layouts</a></p>
</div>
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
<!--script for portfolio-->
		<script src="/resources/js/jquery.min.js"> </script>
		<script src="/resources/js/easyResponsiveTabs.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				$('#horizontalTab').easyResponsiveTabs({
					type: 'default', //Types: default, vertical, accordion           
					width: 'auto', //auto or any width like 600px
					fit: true   // 100% fit in a container
				});
			});		
		</script>
		<!--//script for portfolio-->
				<!-- Calendar -->
				<link rel="stylesheet" href="/resources/css/jquery-ui.css" />
				<script src="/resources/js/jquery-ui.js"></script>
				  <script>
				  $(function() {
							$( "#outBoundJourneyDate_ow,#returnJourneyDate" ).datepicker({minDate:0,maxDate: '21'});

							$( "#outBoundJourneyDate_rt" ).datepicker({minDate:0,maxDate: '21',
								onSelect: function (selectedDate) {
								endDate = $(this).datepicker('getDate');
								var final=new Date(endDate);
								final.setDate(endDate.getDate() + 6);
								$('#returnJourneyDate').datepicker('option', 'maxDate',final);
								$('#returnJourneyDate').datepicker('option', 'minDate',endDate);						
								}
							
							});
							
						
							
						  });

				  </script>
				  
				  
				  	<script >
function function1(selectObject)
{
	
	var value = selectObject.value; 
	var count=0;
	if(value==21)
		{
		
		 document.getElementById('minutes1').options.length = 0;
		var select = document.getElementById("minutes1");
		select.options[select.options.length] = new Option('00', '0'); 
		
		
		}
	else
		{
		if(count!=1){
		var select = document.getElementById("minutes1");
		select.options[select.options.length] = new Option('01', '1');select.options[select.options.length] = new Option('02', '2');select.options[select.options.length] = new Option('03', '3');select.options[select.options.length] = new Option('04', '4');select.options[select.options.length] = new Option('05', '05');select.options[select.options.length] = new Option('06', '6');select.options[select.options.length] = new Option('07', '7');select.options[select.options.length] = new Option('08', '8');select.options[select.options.length] = new Option('09', '9');select.options[select.options.length] = new Option('10', '10');select.options[select.options.length] = new Option('11', '11');select.options[select.options.length] = new Option('12', '12');select.options[select.options.length] = new Option('13', '13');select.options[select.options.length] = new Option('14', '14');select.options[select.options.length] = new Option('15', '15');select.options[select.options.length] = new Option('16', '16');select.options[select.options.length] = new Option('17', '17');select.options[select.options.length] = new Option('18', '18');select.options[select.options.length] = new Option('19', '19');select.options[select.options.length] = new Option('20', '20');select.options[select.options.length] = new Option('21', '21');select.options[select.options.length] = new Option('22', '22');select.options[select.options.length] = new Option('23', '23');select.options[select.options.length] = new Option('24', '24');select.options[select.options.length] = new Option('25', '25');select.options[select.options.length] = new Option('26', '26');select.options[select.options.length] = new Option('27', '27');select.options[select.options.length] = new Option('28', '28');select.options[select.options.length] = new Option('29', '29');select.options[select.options.length] = new Option('30', '30');select.options[select.options.length] = new Option('31', '31');select.options[select.options.length] = new Option('32', '32');select.options[select.options.length] = new Option('33', '33');select.options[select.options.length] = new Option('34', '34');select.options[select.options.length] = new Option('35', '35');select.options[select.options.length] = new Option('36', '36');select.options[select.options.length] = new Option('37', '37');select.options[select.options.length] = new Option('38', '38');select.options[select.options.length] = new Option('39', '39');select.options[select.options.length] = new Option('40', '40');select.options[select.options.length] = new Option('41', '41');select.options[select.options.length] = new Option('42', '42');select.options[select.options.length] = new Option('43', '43');select.options[select.options.length] = new Option('44', '44');select.options[select.options.length] = new Option('45', '45');select.options[select.options.length] = new Option('46', '46');select.options[select.options.length] = new Option('47', '47');select.options[select.options.length] = new Option('48', '48');select.options[select.options.length] = new Option('49', '49');select.options[select.options.length] = new Option('50', '50');select.options[select.options.length] = new Option('51', '51');select.options[select.options.length] = new Option('52', '52');select.options[select.options.length] = new Option('53', '53');select.options[select.options.length] = new Option('54', '54');select.options[select.options.length] = new Option('55', '55');select.options[select.options.length] = new Option('56', '56');select.options[select.options.length] = new Option('57', '57');select.options[select.options.length] = new Option('58', '58');select.options[select.options.length] = new Option('59', '59');
		count++;
		}
		}
	
	}
function function2(selectObject)
{
	
	var value = selectObject.value; 
	var count=0;
	if(value==21)
		{
		
		
		 document.getElementById('minutes2').options.length = 0;
		var select = document.getElementById("minutes2");
		select.options[select.options.length] = new Option('00', '0'); 
		
		
		}
	else
		{
		if(count!=1){
		var select = document.getElementById("minutes2");
		select.options[select.options.length] = new Option('01', '1');select.options[select.options.length] = new Option('02', '2');select.options[select.options.length] = new Option('03', '3');select.options[select.options.length] = new Option('04', '4');select.options[select.options.length] = new Option('05', '05');select.options[select.options.length] = new Option('06', '6');select.options[select.options.length] = new Option('07', '7');select.options[select.options.length] = new Option('08', '8');select.options[select.options.length] = new Option('09', '9');select.options[select.options.length] = new Option('10', '10');select.options[select.options.length] = new Option('11', '11');select.options[select.options.length] = new Option('12', '12');select.options[select.options.length] = new Option('13', '13');select.options[select.options.length] = new Option('14', '14');select.options[select.options.length] = new Option('15', '15');select.options[select.options.length] = new Option('16', '16');select.options[select.options.length] = new Option('17', '17');select.options[select.options.length] = new Option('18', '18');select.options[select.options.length] = new Option('19', '19');select.options[select.options.length] = new Option('20', '20');select.options[select.options.length] = new Option('21', '21');select.options[select.options.length] = new Option('22', '22');select.options[select.options.length] = new Option('23', '23');select.options[select.options.length] = new Option('24', '24');select.options[select.options.length] = new Option('25', '25');select.options[select.options.length] = new Option('26', '26');select.options[select.options.length] = new Option('27', '27');select.options[select.options.length] = new Option('28', '28');select.options[select.options.length] = new Option('29', '29');select.options[select.options.length] = new Option('30', '30');select.options[select.options.length] = new Option('31', '31');select.options[select.options.length] = new Option('32', '32');select.options[select.options.length] = new Option('33', '33');select.options[select.options.length] = new Option('34', '34');select.options[select.options.length] = new Option('35', '35');select.options[select.options.length] = new Option('36', '36');select.options[select.options.length] = new Option('37', '37');select.options[select.options.length] = new Option('38', '38');select.options[select.options.length] = new Option('39', '39');select.options[select.options.length] = new Option('40', '40');select.options[select.options.length] = new Option('41', '41');select.options[select.options.length] = new Option('42', '42');select.options[select.options.length] = new Option('43', '43');select.options[select.options.length] = new Option('44', '44');select.options[select.options.length] = new Option('45', '45');select.options[select.options.length] = new Option('46', '46');select.options[select.options.length] = new Option('47', '47');select.options[select.options.length] = new Option('48', '48');select.options[select.options.length] = new Option('49', '49');select.options[select.options.length] = new Option('50', '50');select.options[select.options.length] = new Option('51', '51');select.options[select.options.length] = new Option('52', '52');select.options[select.options.length] = new Option('53', '53');select.options[select.options.length] = new Option('54', '54');select.options[select.options.length] = new Option('55', '55');select.options[select.options.length] = new Option('56', '56');select.options[select.options.length] = new Option('57', '57');select.options[select.options.length] = new Option('58', '58');select.options[select.options.length] = new Option('59', '59');
		count++;
		}
		}
	
	}
function function3(selectObject)
{
	
	var value = selectObject.value; 
	var count=0;
	if(value==21)
		{
		//alert('YOYOY');
		
		 document.getElementById('minutes3').options.length = 0;
		var select = document.getElementById("minutes3");
		select.options[select.options.length] = new Option('00', '0'); 
		
		
		}
	else
		{
		if(count!=1){
		var select = document.getElementById("minutes3");
		select.options[select.options.length] = new Option('01', '1');select.options[select.options.length] = new Option('02', '2');select.options[select.options.length] = new Option('03', '3');select.options[select.options.length] = new Option('04', '4');select.options[select.options.length] = new Option('05', '05');select.options[select.options.length] = new Option('06', '6');select.options[select.options.length] = new Option('07', '7');select.options[select.options.length] = new Option('08', '8');select.options[select.options.length] = new Option('09', '9');select.options[select.options.length] = new Option('10', '10');select.options[select.options.length] = new Option('11', '11');select.options[select.options.length] = new Option('12', '12');select.options[select.options.length] = new Option('13', '13');select.options[select.options.length] = new Option('14', '14');select.options[select.options.length] = new Option('15', '15');select.options[select.options.length] = new Option('16', '16');select.options[select.options.length] = new Option('17', '17');select.options[select.options.length] = new Option('18', '18');select.options[select.options.length] = new Option('19', '19');select.options[select.options.length] = new Option('20', '20');select.options[select.options.length] = new Option('21', '21');select.options[select.options.length] = new Option('22', '22');select.options[select.options.length] = new Option('23', '23');select.options[select.options.length] = new Option('24', '24');select.options[select.options.length] = new Option('25', '25');select.options[select.options.length] = new Option('26', '26');select.options[select.options.length] = new Option('27', '27');select.options[select.options.length] = new Option('28', '28');select.options[select.options.length] = new Option('29', '29');select.options[select.options.length] = new Option('30', '30');select.options[select.options.length] = new Option('31', '31');select.options[select.options.length] = new Option('32', '32');select.options[select.options.length] = new Option('33', '33');select.options[select.options.length] = new Option('34', '34');select.options[select.options.length] = new Option('35', '35');select.options[select.options.length] = new Option('36', '36');select.options[select.options.length] = new Option('37', '37');select.options[select.options.length] = new Option('38', '38');select.options[select.options.length] = new Option('39', '39');select.options[select.options.length] = new Option('40', '40');select.options[select.options.length] = new Option('41', '41');select.options[select.options.length] = new Option('42', '42');select.options[select.options.length] = new Option('43', '43');select.options[select.options.length] = new Option('44', '44');select.options[select.options.length] = new Option('45', '45');select.options[select.options.length] = new Option('46', '46');select.options[select.options.length] = new Option('47', '47');select.options[select.options.length] = new Option('48', '48');select.options[select.options.length] = new Option('49', '49');select.options[select.options.length] = new Option('50', '50');select.options[select.options.length] = new Option('51', '51');select.options[select.options.length] = new Option('52', '52');select.options[select.options.length] = new Option('53', '53');select.options[select.options.length] = new Option('54', '54');select.options[select.options.length] = new Option('55', '55');select.options[select.options.length] = new Option('56', '56');select.options[select.options.length] = new Option('57', '57');select.options[select.options.length] = new Option('58', '58');select.options[select.options.length] = new Option('59', '59');
		count++;
		}
		}
	
	}
</script>
				  <!--time-->
					<script>

					var selectedField1 = "";
						jQuery('#hours1').on('change', function () {
							jQuery("#outBoundDeptTime_ow").val(jQuery("#hours1").val());
							selectedField1 = jQuery("#hours1").val();
						});

						jQuery('#minutes1').on('change', function () {
							var val = selectedField1
							jQuery("#outBoundDeptTime_ow").val(val + ':' + jQuery("#minutes1").val()+":00");
						});

					var selectedField2 = "";
						jQuery('#hours2').on('change', function () {
							jQuery("#outBoundDeptTime_rt").val(jQuery("#hours2").val());
							selectedField2= jQuery("#hours2").val();
						});

						jQuery('#minutes2').on('change', function () {
							var val = selectedField2
							jQuery("#outBoundDeptTime_rt").val(val + ':' + jQuery("#minutes2").val()+":00");
						});
					var selectedField3= "";
						jQuery('#hours3').on('change', function () {
							jQuery("#returnDeptTime").val(jQuery("#hours3").val());
							selectedField3 = jQuery("#hours3").val();
						});

						jQuery('#minutes3').on('change', function () {
							var val = selectedField3
							jQuery("#returnDeptTime").val(val + ':' + jQuery("#minutes3").val()+":00");
						});
					</script>
			<!--quantity-->
									<script>
									$('#value-plus-ow').on('click', function(){
										
										var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)+1;
										if(newVal<=5){ divUpd.text(newVal); jQuery("#passengerCount_ow").val(newVal);}
									});

									$('#value-minus-ow').on('click', function(){
										var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)-1;
										if(newVal>=1){ divUpd.text(newVal);jQuery("#passengerCount_ow").val(newVal); }
									});
									$('#value-plus-rt').on('click', function(){
										
										var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)+1;
										if(newVal<=5){ divUpd.text(newVal); jQuery("#passengerCount_rt").val(newVal);}
									});

									$('#value-minus-rt').on('click', function(){
										var divUpd = $(this).parent().find('.value'), newVal = parseInt(divUpd.text(), 10)-1;
										if(newVal>=1){ divUpd.text(newVal);jQuery("#passengerCount_rt").val(newVal); }
									});
									</script>
								<!--//quantity-->
						<!--load more-->
								<script>
	$(document).ready(function () {
		size_li = $("#myList li").size();
		x=1;
		$('#myList li:lt('+x+')').show();
		$('#loadMore').click(function () {
			x= (x+1 <= size_li) ? x+1 : size_li;
			$('#myList li:lt('+x+')').show();
		});
		$('#showLess').click(function () {
			x=(x-1<0) ? 1 : x-1;
			$('#myList li').not(':lt('+x+')').hide();
		});
	});
</script>
<!-- //load-more -->



</body>
</html>