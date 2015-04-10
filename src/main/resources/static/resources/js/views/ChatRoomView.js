
var ChatRoomView = Backbone.View.extend({
	
	template: _.template(chatRoomTemplate),
	
	initialize: function(){
		this.collection.on('add', this.saveMessage, this);
	},
	
	events: {
		'keyup .txt-message': 'checkMessage',
		'click .btn-send-message': 'sendMessage',
		'remove': 'remove'
	},
	
	render: function(){
		this.$el.html(this.template(this.model.attributes));
		return this;
	},
	
	close: function(){
		this.$el.remove();
	},
	
	checkMessage: function(event){
		if(event.which == 13 && this.isButtonSendMessageEnabled()) {
			var $txtMessage = $(event.target);
			var $btn = $txtMessage.parents('.chat-message').find('.btn-send-message');
			$btn.trigger('click');
			
	    } else {
			var $input = $(event.currentTarget);
			if ($input.val()) {
				this.ableButtonSendMessage();
			} else {
				this.disableButtonSendMessage();
			}
			
	    }
	},

	isButtonSendMessageEnabled: function(){
		return !this.$el.find('.btn-send-message').attr('disabled');
	},
	
	ableButtonSendMessage: function(){
		this.$el.find('.btn-send-message').removeAttr('disabled');
	},

	disableButtonSendMessage: function(){
		this.$el.find('.btn-send-message').attr('disabled', 'disabled');
	},
	
	sendMessage: function(){
		var roomCode = this.model.get('code');
		var $textMessage = this.$el.find('.txt-message');
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
		var $messages = this.$el.find('.chat[data-code="'+this.model.get('code')+'"] .chat-content');
		$messages.append( chatMessageView.render().el );
		this.focusLastMessages();
	},
	
	focusLastMessages: function(){
		var txtMessage = this.$el.find('.txt-message');
		txtMessage.attr('disabled', 'disabled');
		
		$(".chat-content").animate({ 
			scrollTop: $('.chat-content').height() + 500
		}, 1000, function(){
			txtMessage.removeAttr('disabled');
			txtMessage.focus();
		});
	}
	
});