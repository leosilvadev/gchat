var HomeLoader = {
	
	load: function(){
		var logoutView = new LogoutView();
		logoutView.render();
		
		
		var roomList = new RoomList();
		var roomListView = new RoomListView({collection: roomList});
		roomListView.render();

		var $body = $('body');
		$body.append( logoutView.el );
		$body.append( roomListView.el );
	}
		
};

$(document).ready(function(){
	HomeLoader.load();
});