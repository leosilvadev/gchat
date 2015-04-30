define(['backbone'], function(Backbone){
	
	var Room = Backbone.Model.extend({
		
		urlRoot: '/rooms',
		defaults: function(){
			return {
				name: 'Open room'	
			}
		}
			
	});

	return Room;
	
});