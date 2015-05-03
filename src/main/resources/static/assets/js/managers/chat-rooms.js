define(['backbone', 'views/chat-room', 'utils/events'], function(Backbone, ChatRoomView, events){
	
	var chats = [];
	
	var ChatRoomsManager = Backbone.View.extend({

		initialize: function(options){
			events.on('room:chat-new'	 , this.newChat, this);
			events.on('room:chat-switch' , this.switchChat, this);
			events.on('room:chat-destroy', this.destroy, this);
		},
		
		newChat: function(room, callback){
			var chat = new ChatRoomView({model: room});
			chat.render();
			$('body').append(chat.el);
			
			chats.push(chat);
			
			if(callback) callback();
		},
		
		isNotLoggedIn: function(room){
			for(var index in chats){
				var chatCode = chats[index].model.get('code');
				if(chatCode===room.get('code')) return false;
			}
			return true;
		},
		
		switchChat: function(room, callback){
			for(var it in chats){
				var chat = chats[it];
				chat.model.get('code') === room.get('code') ? chat.show() : chat.hide();
			}
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