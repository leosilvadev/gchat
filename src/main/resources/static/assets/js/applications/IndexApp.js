define(['backbone', 'routers/IndexRouter'], function(Backbone, IndexRouter){
	
	var IndexApp = new (Backbone.View.extend({
		
		Models:{},
		Collections:{},
		Views:{},
		
		initialize:function(){
			var router = new IndexRouter();
			this.start();
		},
		
		
		//Workaround for navigation, when navigate to a modal twice it was not working
		//TODO: looking for a better implementation!
		events: {
			'click a': function(e){
				e.preventDefault();
				var href = e.currentTarget.pathname;
				var ret = Backbone.history.navigate(href, true);
				if (ret === undefined) {
				    Backbone.history.loadUrl(href);
				}
			}
		},
	
		start: function(){
			Backbone.history.start();
		}
		
	}))({el:document.body});
	
	return IndexApp;

});