define(['backbone', 
        'underscore',
        'models/chat-message',
        'models/chat-user',
        'collections/chat-messages',
        'views/chat-message',
        'views/chat-users',
		'connectors/stomp-connector',
        'utils/template',
        'utils/events'], function(Backbone, _, ChatMessage, ChatUser, ChatMessageList, ChatMessageView, ChatUsersView, StompConnector, template, events){
	
	var ChatRoomView = Backbone.View.extend({
		
		initialize: function(){
			this.collection = new ChatMessageList();
			this.collection.on('add', this.saveMessage, this);
			this.subscribe(this.model);
		},
		
		events: {
			'keyup .txt-message': 'checkMessage',
			submit: 'sendMessage'
		},
		
		checkMessage: function(event){
			event.preventDefault();
			
			var message = this.$('.txt-message').val().trim();
			if (message) {
				this.ableButtonSendMessage();
				if (event.keyCode===13) {
					this.sendMessage(event);
					this.$('.txt-message').val('');
				}
				
			} else {
				this.disableButtonSendMessage();
			
			}
			
		},
	
		isButtonSendMessageEnabled: function(){
			return !this.$('.btn-send-message').attr('disabled');
		},
		
		ableButtonSendMessage: function(){
			this.$('.btn-send-message').removeAttr('disabled');
		},
		
		disableButtonSendMessage: function(){
			this.$('.btn-send-message').attr('disabled', 'disabled');
		},
		
		sendMessage: function(e){
			e.preventDefault();
			var roomCode = this.model.get('code');
			var $textMessage = this.$('.txt-message');
			var content = $textMessage.val();
			
			if ( content ) {
				var chatMessage = new ChatMessage({content:content, roomCode: roomCode});
				this.collection.add( chatMessage );
				$textMessage.val('');
				this.disableButtonSendMessage();
			}
		},
		
		saveMessage: function(chatMessage){
			chatMessage.save();
		},
		
		showMessage: function(chatMessage){
			var chatMessageView = new ChatMessageView({model:chatMessage});
			var $messages = this.$('.chat[data-code="'+this.model.get('code')+'"] .chat-content');
			$messages.append( chatMessageView.render().el );
			this.focusLastMessages();
		},
		
		focusLastMessages: function(){
			var txtMessage = this.$('.txt-message');
			txtMessage.attr('disabled', 'disabled');
			
			this.$(".chat-content").animate({ 
				scrollTop: this.$('.chat-content').height() + 500
				
			}, 1000, function(){
				txtMessage.removeAttr('disabled');
				txtMessage.focus();
				
			});
		},

		subscribe: function(room){
			var view = this;
			var roomCode = room.get('code');
			
			this.messagesSubscription = StompConnector.getConnection().subscribe('/topic/rooms-'+roomCode, function(request){
				var chatMessage = new ChatMessage(JSON.parse(request.body));
				view.showMessage(chatMessage);
				if(chatMessage.has('user')){
					view.usersView.addUser(new ChatUser(chatMessage.get('user')));
				}
				
			}, {roomCode: roomCode});
		},
		
		hide: function(){
			this.$el.hide();
		},
		
		show: function(){
			this.$el.show();
		},
		
		destroy: function(){
			if (this.messagesSubscription) this.messagesSubscription.unsubscribe(null, {code:this.model.get('code')});
			this.usersView.destroy();
			this.remove();
		},
		
		render: function(){
			var view = this;
			$.get('templates/_chat_room.html').success(function(html){
				var template = _.template(html);
				view.$el.html(template(view.model.attributes));
				
				view.usersView = new ChatUsersView({roomCode: view.model.get('code')});
				view.$('.chat-users').html( view.usersView.render().el );
			
			});
			return this;
		}
		
	});
	
	return ChatRoomView;

});