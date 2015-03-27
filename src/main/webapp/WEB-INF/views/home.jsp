<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<style type="text/css">
			@IMPORT url("static/flat-ui/css/vendor/bootstrap.min.css");
			@IMPORT url("static/flat-ui/css/flat-ui.min.css");
		</style>
	</head>
	<body>
		<header class="container">
			<nav class="navbar navbar-inverse navbar-embossed" role="navigation">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#navbar-collapse-01">
						<span class="sr-only">Toggle navigation</span>
					</button>
					<a class="navbar-brand" href="#">Flat UI</a>
				</div>
				<div class="collapse navbar-collapse" id="navbar-collapse-01">
					<ul class="nav navbar-nav navbar-left">
						<li>
							<a href="#fakelink">Menu Item<span class="navbar-unread">1</span></a>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">
								Rooms <b class="caret"></b>
							</a>
							<span class="dropdown-arrow"></span>
							<ul class="dropdown-menu">
								<c:forEach items="${rooms}" var="room">
									<li><a href="rooms/${room.id}">${room.name}</a></li>
								</c:forEach>
								<li class="divider"></li>
								<li><a href="#">Create a Room</a></li>
							</ul></li>
						<li><a href="#fakelink">About Us</a></li>
					</ul>
					<form class="navbar-form navbar-right" action="#" role="search">
						<div class="form-group">
							<div class="input-group">
								<input class="form-control" id="navbarInput-01" type="search"
									placeholder="Search"> <span class="input-group-btn">
									<button type="submit" class="btn">
										<span class="fui-search"></span>
									</button>
								</span>
							</div>
						</div>
					</form>
				</div>
			</nav>
		</header>
		<section class="container">
		</section>
		
		<script type="text/javascript" src="static/js/jquery-2.1.3.min.js"></script>
		<script type="text/javascript" src="static/flat-ui/js/flat-ui.min.js"></script>
		<script type="text/javascript" src="static/js/ws/sockjs-0.3.4.js"></script>
		<script type="text/javascript" src="static/js/ws/stomp.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function() {
				connect();
			});
			
			var stompClient = null;

	        function connect() {
	            var socket = new SockJS('/messages');
	            stompClient = Stomp.over(socket);
	            stompClient.connect({}, function(frame) {
	                stompClient.subscribe('/queue/messages-leonardo', function(message){
	                	console.log('Receiving a private message...');
	                    showMessage(JSON.parse(message.body));
	                });
	                
	                stompClient.subscribe('/topic/rooms-ABC', function(message){
	                	console.log('Receiving a message in ROOM...');
	                    showMessage(JSON.parse(message.body));
	                });
	            });
	        }

	        function disconnect() {
	            if (stompClient != null) {
	                stompClient.disconnect();
	            }
	        }

	        function showMessage(message) {
	            console.log(message.content);
	        }
		</script>
		
		
		<section id="container-poitings">
			<div class="header">
					
			</div>
			<div class="content">
			
			</div>
			<div class="footer">
				
			</div>
		</section>
		
		
		
	</body>
</html>