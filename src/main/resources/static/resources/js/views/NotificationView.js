
var NotificationView = Backbone.View.extend({
	
	render: function(){
		var delay = delay || 3000;
		var notification = this.model;

		$.notify({
			message: notification.get('message')
		}, this.buildMessageOptions(notification.get('type'), notification.get('delay')));
	},

	buildMessageOptions: function(type, delay){
		return {
			delay: delay,
			type: type,
			placement: {
				align: 'center'
			}
		};
	}
	
});