define(
		['jquery', 
		 'backbone', 
		 'routers/routers', 
		 'navigator', 
		 'bootstrap'], 
		 
		 function($, Backbone, Routers, navigator){
	
	var HomeApp = new (Backbone.View.extend({
		
		initialize:function(){
			console.log(Routers);
			var router = new Routers.Home();
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
	
	return HomeApp;

});