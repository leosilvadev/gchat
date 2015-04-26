define(['jquery','backbone'], function($, Backbone){

	var NotificationView = Backbone.View.extend({
		
		render: function($el){
			var notification = this.model;
	
			//TODO:change to a new implementation to user notification
			alert(notification.get('message'));
		}
		
	});
	
	return NotificationView;

});