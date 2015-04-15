define(['jquery', 'backbone', 'routers/HomeRouter', 'bootstrap'], function($, Backbone, HomeRouter){
	
	var HomeApp = new (Backbone.View.extend({
		
		Models:{},
		Collections:{},
		Views:{},
		
		initialize:function(){
			var router = new HomeRouter();
			this.start();
		},
		
		events: {
			'click a': function(e){
				var $target = $(e.currentTarget);
				var href = $target.attr('href');
				var external = $target.attr('external');
				
				if ( !external ) {
					e.preventDefault();
					var ret = Backbone.history.navigate(href, true);
					if (ret === undefined) {
					    Backbone.history.loadUrl(href);
					}
				}
			}
		},
	
		start: function(){
			Backbone.history.start();
		}
		
	}))({el:document.body});
	
	return HomeApp;

});