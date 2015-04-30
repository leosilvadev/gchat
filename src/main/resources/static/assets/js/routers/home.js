define(
		['jquery', 
		 'backbone',
		 'connectors/stomp-connector',
		 'views/logout',
		 'views/rooms',
		 'views/room-registration'], function($, Backbone, StompConnector, LogoutView, RoomsView, RoomRegistrationView){

	var HomeRouter = Backbone.Router.extend({
		
		routes:{
			''			: 'index',
			'rooms'		: 'listRooms',
			'logout'	: 'logout',
			'rooms/new'	: 'newRoom'
		},
		
		initialize: function(){
			StompConnector.connect();
			
			this.initModals();
		},
		
		listRooms: function(){
			this.initModals();
			this.roomsView.openModal();
		},
		
		newRoom: function(){
			this.roomsView.closeModal();
			this.roomRegistrationView.openModal();
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

			if (this.roomsView) {
				this.roomsView.closeModal();
				
			} else {
				this.roomsView = new RoomsView();
				$body.append( this.roomsView.render().el );
			}

			if (this.roomRegistrationView) {
				this.roomRegistrationView.closeModal();
				
			} else {
				this.roomRegistrationView = new RoomRegistrationView();
				$body.append( this.roomRegistrationView.render().el );
			}
			
		}
	
	});
	
	return HomeRouter;

});