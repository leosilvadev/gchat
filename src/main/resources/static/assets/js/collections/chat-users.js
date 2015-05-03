define(['backbone',
        'underscore', 
        'models/chat-user'], function(Backbone, _, ChatUser){
	
	var ChatUsers = Backbone.Collection.extend({
		
		model: ChatUser,
		
		initialize: function(options){
			this.room = options.room;
		},
		
		url: function(){
			return '/chat/'+this.room+'/users'
		},

		parse: function(response) {
			console.log(response);
			return response;
		}
		
	});
	
	return ChatUsers;

});