var RoomView = function(roomCode, roomName, homeView){
	
	var self = this;
	self.homeView = homeView;
	self.roomCode = roomCode;
	self.roomName = roomName;
	
	self.render = function(){
		subscribe(self.roomCode, self.roomName, self.homeView.stompClient);
		applyListeners();
	};
	
	self.active = function(){
		$('.opened-room').removeClass('active-room');
		$('.opened-room[data-code="'+this.roomCode+'"]').addClass('active-room');

		$('.chat').addClass('invisible');
		$('.chat[data-code="'+this.roomCode+'"]').removeClass('invisible');
	};
	
	var focusLastMessage = function(){
		$(".chat-content").animate({ scrollTop: $('.chat-content').height() + 1000 }, 1000);
	};
	
	var applyListeners = function(){
		$('.txt-message').on('blur keyup', validateMessage);
		$('.txt-message').on('keypress', function(event){
			if(event.which == 13) {
				var $txtMessage = $(this);
				var $btn = $txtMessage.parents('.chat-message').find('.btn-send-message');
				$btn.trigger('click');
				return false;
		    }
		});
	};
	
	var validateMessage = function(){
		var $txtMessage = $(this);
		var $btn = $txtMessage.parents('.chat-message').find('.btn-send-message');
		if ( $txtMessage.val() ) {
			$btn.removeAttr('disabled');
			
		} else {
			$btn.attr('disabled', 'disabled');
			
		}
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
			var room = self.homeView.findRoom(roomCode);
			room.active();
		});
		
		create(roomCode, roomName);
	};
	
	var create = function(roomCode, roomName){
		var chatTemplate = 
			'<div class="chat" data-code="'+roomCode+'">' + 
				'<div class="chat-content"></div>' +
				'<div class="chat-message">' +
					'<div><textarea class="txt-message form-control"></textarea></div>' + 
					'<div>' +
						'<button class="btn-send-message btn btn-primary" disabled="disabled">' +
							'<span class="glyphicon glyphicon-send"><span> Send' + 
						'</button>' +
					'</div>' + 
				'</div>' +
				'<div class="chat-users"></div>' +
			'</div>';
		
		$('#opened-room').append(chatTemplate);
		
		$('.chat[data-code="' + roomCode + '"] button.btn-send-message').on('click', function(){
			var txtMessage = $(this).parents('.chat-message').find('textarea');
			var messageJSON = JSON.stringify({"from":"Leonardo", "content":txtMessage.val()});
			sendMessage(messageJSON);
			txtMessage.val('');
			txtMessage.focus();
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
				'<header> <span class="sent-at">['+message.sentAt+']</span>' + message.from + ' said: </header>' +
				'<div>' + message.content + '</div>' +
			'</div>';
		
		var $chat = $('.chat[data-code="'+roomCode+'"] .chat-content');
		$chat.append(messageHTML);
		focusLastMessage();
	};
	
};
