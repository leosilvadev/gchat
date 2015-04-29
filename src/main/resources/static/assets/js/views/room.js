define(
		['backbone', 
		 'views/chat-room', 
		 'views/tab-room',
		 'collections/chat-messages',
		 'connectors/stomp-connector',
		 'models/chat-message',
		 'utils/template',
		 'utils/events',
		 'managers/tab-rooms',
		 'managers/chat-rooms'], 
		
		function(Backbone, ChatRoomView, TabRoomView, ChatMessageList, StompConnector, ChatMessage, template, events){
	
	var RoomView = Backbone.View.extend({
		
		className: 'row room',
				
		events: {
			'click .btn-enter-room': 'enter'
		},
		
		enter: function(event){
			var self = this;
			var roomCode = this.model.get('code');
			var roomName = this.model.get('name');
			
			$('.opened-room').removeClass('active-room');
			
			events.trigger('room:chat-new', this.model, function(){
				$('#modal-rooms').modal('hide');
			});

			events.trigger('room:tab-new', this.model, function(){
				$('#modal-rooms').modal('hide');
			});
			
			this.messagesSubscription = StompConnector.getConnection().subscribe('/topic/rooms-'+roomCode, function(request){
				var chatMessage = new ChatMessage(JSON.parse(request.body));
				chatRoomView.showMessage(chatMessage);
			});
		},
		
		destroy: function(){
			if (this.messagesSubscription) this.messagesSubscription.unsubscribe();
			this.remove();
		},
		
		render: function(){
			template.html('_room', this.$el, this.model.attributes);
		}
		
	});
	
	return RoomView;

});