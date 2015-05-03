define(['backbone', 'models/chat-message'], function(Backbone, ChatMessage){
	
	var ChatMessageList = Backbone.Collection.extend({
	
		model : ChatMessage
	
	});
	
	return ChatMessageList;

});