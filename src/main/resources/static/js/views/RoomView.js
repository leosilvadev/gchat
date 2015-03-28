var RoomView = function(roomCode, roomName, stompClient){
	
	this.stompClient = stompClient;
	this.roomCode = roomCode;
	this.roomName = roomName;
	
	this.render = function(){
		subscribe(this.roomCode, this.roomName, this.stompClient);
	};
	
	var subscribe = function(roomCode, roomName, stompClient){
		stompClient.subscribe('/topic/rooms-'+roomCode, function(message){
			console.log('message: '+message.body);
			showMessage(roomCode, JSON.parse(message.body));
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
				'<div class="chat-content"></div>' +
				'<div class="chat-message">' +
					'<div><textarea class="txt-message"></textarea></div>' + 
					'<div><button class="btn-send-message btn btn-primary">Send</button></div>' + 
				'</div>' +
				'<div class="chat-users"></div>' +
			'</div>';
		
		$('#opened-room').append(chatTemplate);
		
		$('.chat[data-code="' + roomCode + '"] button.btn-send-message').on('click', function(){
			var message = $(this).parents('.chat-message').find('textarea').val();
			var messageJSON = JSON.stringify({"from":"Leonardo", "content":message});
			sendMessage(messageJSON);
		});
	};
	
	var sendMessage = function(messageJSON){
		var url = 'chat/'+roomCode+'/messages';
		$.ajax({
	        type: "POST",
	        url: url,
	        contentType : 'application/json',
	        data: messageJSON
	    });
	};
	
	var showMessage = function(roomCode, message){
		var messageHTML = 
			'<div class="message">' +
				'<header>' + message.from + ' said: </header>' +
				'<div>' + message.content + '</div>' +
			'</div>';
		
		var $chat = $('.chat[data-code="'+roomCode+'"] .chat-content');
		$chat.append(messageHTML);
		focusLastMessage();
	};
	
	this.active = function(){
		$('.opened-room').removeClass('active-room');
		$('.opened-room[data-code="'+this.roomCode+'"]').addClass('active-room');

		$('.chat').addClass('invisible');
		$('.chat[data-code="'+this.roomCode+'"]').removeClass('invisible');
	};
	
	var focusLastMessage = function(){
		$(".chat-content").animate({ scrollTop: $('.chat-content').height() + 1000 }, 1000);
	};
	
};
