var HomeLoader = {

	load : function() {
		var logoutView = new LogoutView();
		logoutView.render();

		var roomList = new RoomList();
		var roomListView = new RoomListView({
			collection : roomList
		});
		roomListView.render();

		var $body = $('body');
		$body.append(logoutView.el);
		$body.append(roomListView.el);

		StompUtils.connect();
	}

};

$(document).ready(function() {
	HomeLoader.load();

	Date.prototype.parse = function() {
		var hours = this.getHours().toString();
		var minutes = this.getMinutes().toString();
		var seconds = this.getSeconds().toString();
		return (hours[1] ? hours : "0"+hours[0]) + ":" + (minutes[1] ? minutes : "0" + minutes[0]) + ":" + (seconds[1] ? seconds : "0" + seconds[0]);
	};
});