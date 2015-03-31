var HomeView = function(){

	var self = this;
	self.stompClient = null;

	var activeRooms = [];
	
	self.render = function(){
		connect();
		applyListeners();
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
	    self.stompClient.connect({});
	};

	var disconnect = function() {
	    if (self.stompClient != null) {
	    	self.stompClient.disconnect();
	    }
	};
	
	var applyListeners = function(){
		$('.btn-connect-room').on('click', self.openRoom);
	};
	
};


$(document).ready(function(){
	var view = new HomeView();
	view.render();
});

