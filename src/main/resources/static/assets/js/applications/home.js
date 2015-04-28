define(
		['jquery', 
		 'backbone', 
		 'routers/routers', 
		 'navigator',
		 'client/notifications',
		 'bootstrap'], 
		 
		 function($, Backbone, Routers, navigator, notifications){
	
	var HomeApp = new (Backbone.View.extend({
		
		initialize:function(){
			var router = new Routers.Home();
			this.start();
			
			notifications.start();
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
	
	return HomeApp;

});