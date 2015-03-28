var RoomView = function(roomCode, roomName, stompClient){
	
	this.stompClient = stompClient;
	this.roomCode = roomCode;
	this.roomName = roomName;
	
	this.render = function(){
		subscribe(this.roomCode, this.roomName, this.stompClient);
	};
	
	var subscribe = function(roomCode, roomName, stompClient){
		console.log('Subscribing... '+'/topic/rooms-'+roomCode);
		stompClient.subscribe('/topic/rooms-'+roomCode, function(message){
			console.log('Receiving a message in ROOM...');
		});

		$('.opened-room').removeClass('active-room');
		var $roomTab = $('<li class="opened-room active-room" data-code="'+roomCode+'"><a href="#">'+roomName+'</a></li>');
		$('#navbar-active-rooms').append($roomTab);
		
		$roomTab.on('click', function(){
			var roomCode = $(this).attr('data-code');
			var room = findRoom(roomCode);
			room.active();
		});
		
		create(roomCode, roomName);
	};
	
	var create = function(roomCode, roomName){
		var chatTemplate = 
			'<div class="chat" data-code="'+roomCode+'">' + 
				'<div class="chat-content">'+roomCode+'</div>' +
				'<div class="chat-message">' +
					'<div><textarea class="txt-message"></textarea></div>' + 
					'<div><button class="btn-send-message btn btn-primary">Send</button></div>' + 
				'</div>' +
				'<div class="chat-users"></div>' +
			'</div>';
		
		$('#opened-room').append(chatTemplate);
	};
	
	this.active = function(){
		$('.opened-room').removeClass('active-room');
		$('.opened-room[data-code="'+this.roomCode+'"]').addClass('active-room');

		$('.chat').addClass('invisible');
		$('.chat[data-code="'+this.roomCode+'"]').removeClass('invisible');
	};
	
};
