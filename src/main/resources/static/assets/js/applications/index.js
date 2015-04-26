define(
		['backbone', 
		 'routers/routers', 
		 'navigator'],
		
		function(Backbone, Routers, navigator){
	
	var IndexApp = new (Backbone.View.extend({
				
		initialize:function(){
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