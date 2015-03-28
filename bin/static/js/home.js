var stompClient = null;
var activeRooms = [];

$(function(){
	
	connect();
	
	$('.btn-connect-room').on('click', function(){
		var $self = $(this);
		var roomCode = $self.attr('data-code');
		var roomName = $self.text();
		
		var room = findRoom(roomCode);
		if ( room ) {
			room.active();
			
		} else {
			room = new RoomView(roomCode, roomName, stompClient);
			room.render();
			activeRooms.push(room);
			
		}
		
	});
	
});

var findRoom = function(roomCode){
	if ( activeRooms ){
		for ( var index in activeRooms ){
			var room = activeRooms[index];
			if (room.roomCode === roomCode) return room;
		}
	}
};

var connect = function() {
    var socket = new SockJS('/messages');
    stompClient = Stomp.over(socket);
    stompClient.connect({});
}

var disconnect = function() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
}