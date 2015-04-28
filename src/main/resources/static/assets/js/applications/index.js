define(
		['backbone', 
		 'routers/routers', 
		 'client/notifications',
		 'navigator'],
		
		function(Backbone, Routers, notifications, navigator){
	
	var IndexApp = new (Backbone.View.extend({
				
		initialize:function(){
			notifications.start();
			
			var router = new Routers.Index();
			this.start();
		},
		events: {
			'click a': function(event){
				navigator.to(event);
			}
		},
	
		start: function(){
			Backbone.history.start();
		}
		
	}))({el:document.body});
	
	return IndexApp;

});