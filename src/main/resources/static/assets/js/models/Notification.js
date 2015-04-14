define(['backbone'], function(Backbone){

	var Notification = Backbone.Model.extend({
		
		defaults: {
			message: '',
			type: 'warning',
			delay: 3000
		}
		
	});
	
	return Notification;

});