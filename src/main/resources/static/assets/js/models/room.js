define(['backbone'], function(Backbone){
	
	var Room = Backbone.Model.extend({
		
		urlRoot: '/rooms',
		defaults: function(){
			return {
				name: 'Open room',
				createdAt: new Date(),
				users: 0				
			}
		}
			
	});

	return Room;
	
});