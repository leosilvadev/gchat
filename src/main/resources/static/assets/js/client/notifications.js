define(['jquery',
        'underscore',
        'utils/events',
        'utils/template'], function($, _, events, template){

	var notifyClient = function(message, $container, type){
		var $container = $container || $('#global-notifications');
		
		var notification = {message: message, type: type};
		template.render('_notification', $container, notification);
	};
	
	var NotificationsView = {
		
		start: function(){
			events.on('notify:info', this.notifyInfo);
			events.on('notify:warning', this.notifyWarning);
		},
		
		notifyWarning: function(message, $container){
			notifyClient(message, $container, 'warning');
		},
		
		notifyInfo: function(message, $container){
			notifyClient(message, $container, 'info');
		}		
		
	};
	
	return NotificationsView;

});