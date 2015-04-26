define(
		[
		 'jquery', 
		 'backbone',
		 'connectors/stomp-connector',
		 'views/logout',
		 'views/rooms'
		], function($, Backbone, StompConnector, LogoutView, RoomListView){

	var HomeRouter = Backbone.Router.extend({
		
		routes:{
			''			: 'index',
			'rooms'		: 'listRooms',
			'logout'	: 'logout'
		},
		
		initialize: function(){
			StompConnector.connect();
			
			this.initModals();
		},
		
		listRooms: function(){
			this.initModals();
			this.roomListView.openModal();
		},
		
		logout: function(){
			this.initModals();
			this.logoutView.openModal();
		},
		
		initModals: function(){
			var $body = $('body');
			
			if (this.logoutView) {
				this.logoutView.closeModal();
				
			} else {
				this.logoutView = new LogoutView();
				$body.append( this.logoutView.render().el );
			}
			
			if (this.roomListView) {
				this.roomListView.closeModal();
				
			} else {
				this.roomListView = new RoomListView();
				$body.append( this.roomListView.render().el );
			}
		}
	
	});
	
	return HomeRouter;

});