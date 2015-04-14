define(['jquery','backbone', 'models/Alert', 'views/AlertView'], function($, Backbone, Alert, AlertView){

	var NotificationView = Backbone.View.extend({
		
		render: function(){
			var notification = this.model;
	
			//TODO:change to a new implementation to user notification
			alert(notification.get('message'));
		}
		
	});
	
	return NotificationView;

});