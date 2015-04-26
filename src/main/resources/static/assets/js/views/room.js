define(
		['backbone', 
		 'templates/room', 
		 'views/chat-room', 
		 'views/tab-room', 
		 'collections/chat-messages',
		 'connectors/stomp-connector',
		 'models/chat-message'], 
		
		function(Backbone, RoomTemplate, ChatRoomView, TabRoomView, ChatMessageList, StompConnector, ChatMessage){
	
	var RoomView = Backbone.View.extend({
		
		template: _.template(RoomTemplate),
		className: 'row room',
		
		events: {
			'click .btn-enter-room': 'enter'
		},
		
		enter: function(event){
			var self = this;
			var roomCode = this.model.get('code');
			var roomName = this.model.get('name');
			
			$('.opened-room').removeClass('active-room');
			
			this.model.roomView = this;
			
			var tabRoomView = new TabRoomView({model: this.model});
			$('#navbar-active-rooms').append(tabRoomView.render().el);
			$('#modal-rooms').modal('hide');
			
			var chatRoomView = new ChatRoomView({model: this.model, collection: new ChatMessageList()});
			$('body').append(chatRoomView.render().el);
			this.model.chatRoomView = chatRoomView;
			
			this.messagesSubscription = StompConnector.getConnection().subscribe('/topic/rooms-'+roomCode, function(request){
				var chatMessage = new ChatMessage(JSON.parse(request.body));
				chatRoomView.showMessage(chatMessage);
			});
		},
		
		destroy: function(){
			this.messagesSubscription.unsubscribe();
			this.$el.remove();
		},
		
		render: function(){
			var name = this.model.get('name');
			var createdAt = new Date(this.model.get('createdAt')).parse();
			var users = this.model.get('users').length;
			
			this.$el.html(this.template({name:name, users:users, createdAt: createdAt}))
			return this
		}
		
	});
	
	return RoomView;

});