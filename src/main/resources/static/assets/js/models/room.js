define(['backbone'], function(Backbone){
	
	var Room = Backbone.Model.extend({
		
		urlRoot: '/rooms'
			
	});

	return Room;
	
});