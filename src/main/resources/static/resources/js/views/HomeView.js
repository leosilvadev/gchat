var HomeView = function(){

	var self = this;
	self.stompClient = null;

	var activeRooms = [];
	
	self.render = function(){
		connect();
		registerEvents();
	};
	
	self.openRoom = function(){
		var $self = $(this);
		var roomCode = $self.attr('data-code');
		var roomName = $self.text();
		
		var room = self.findRoom(roomCode);
		if ( room ) {
			room.active();
			
		} else {
			room = new RoomView(roomCode, roomName, self);
			room.render();
			activeRooms.push(room);
			
		}
		
	};
	
	self.findRoom = function(roomCode){
		if ( activeRooms ){
			for ( var index in activeRooms ){
				var room = activeRooms[index];
				if (room.roomCode === roomCode) return room;
			}
		}
	};
	
	var connect = function() {
	    var socket = new SockJS('/messages');
	    self.stompClient = Stomp.over(socket);
	    self.stompClient.connect({}, function(frame){
	    	self.stompClient.subscribe('/topic/rooms', function(message){
	    		showNewRoom(JSON.parse(message.body));
			});
	    });
	};

	var disconnect = function() {
	    if (self.stompClient != null) {
	    	self.stompClient.disconnect();
	    }
	};
	
	var registerEvents = function(){
		$('.btn-connect-room').on('click', self.openRoom);
	};
	
	var showNewRoom = function(room){
		var roomsDivider = $('#dropdown-rooms>li.divider');
		var newRoom = 
			'<li>' +
				'<a href="#" data-code="'+room.code+'" class="btn-connect-room">' +
					'<span class="glyphicon glyphicon-share-alt"></span>' +
					'<span>'+room.name+'</span>' +
				'</a>' +
			'</li>';
		roomsDivider.before(newRoom);
		
		var roomView = self.findRoom(room.code);
		if ( roomView ) {
			roomView.active();
			
		} else {
			roomView = new RoomView(room.code, room.name, self);
			roomView.render();
			activeRooms.push(roomView);
			
		}
	};
	
};


$(document).ready(function(){
	var notifier = new Notifier();
	var homeView = new HomeView();
		
	var roomRegistrationView = new RoomRegistrationView(notifier);
	roomRegistrationView.render();
		
	homeView.render();
});

