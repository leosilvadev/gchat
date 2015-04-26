define(['backbone'], function(Backbone){
	
	var ChatMessage = Backbone.Model.extend({
	
		urlRoot: function(){
			return '/chat/' + this.get('roomCode') + '/messages';
		}
		
	});
	
	return ChatMessage;
	
});