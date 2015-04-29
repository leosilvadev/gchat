define(['backbone', 'views/chat-room', 'utils/events'], function(Backbone, ChatRoomView, events){
	
	var chats = [];
	
	var ChatRoomsManager = Backbone.View.extend({

		initialize: function(options){
			events.on('room:chat-new', this.newChat);
			events.on('room:chat-destroy', this.destroy);
		},
		
		newChat: function(room, callback){
			console.log('newChat');
			var chat = new ChatRoomView({model: room});
			chat.render();
			$('body').append(chat.el);

			chats.push(chat);
			
			if(callback) callback();
		},

		destroy: function(room, callback){
			for(var it in chats){
				var chat = chats[it];
				if ( chat.model.get('code') === room.get('code') ){
					chats.splice(it, 1);
					chat.destroy();
				}
			}
		}
		
	});
	
	return new ChatRoomsManager();
	
});