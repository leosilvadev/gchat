define(
		['backbone', 
		 'views/chat-room', 
		 'views/tab-room', 
		 'collections/chat-messages',
		 'connectors/stomp-connector',
		 'models/chat-message',
		 'utils/template'], 
		
		function(Backbone, ChatRoomView, TabRoomView, ChatMessageList, StompConnector, ChatMessage, template){
	
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
			
			var chatRoomView = new ChatRoomView({model: this.model, collection: new ChatMessageList()});
			chatRoomView.render();
			$('body').append(chatRoomView.el);
			

			var tabRoomView = new TabRoomView({roomView: this, chatRoomView: chatRoomView, name: this.model.get('name')});
			tabRoomView.render();
			$('#navbar-active-rooms').append(tabRoomView.el);
			$('#modal-rooms').modal('hide');
			
			
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
			template.render('_room', this.$el, this.model.attributes);
		}
		
	});
	
	return RoomView;

});