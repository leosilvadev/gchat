
var IndexApplication = new (Backbone.View.extend({
	
	Models:{},
	Collections:{},
	Views:{},
	
	initialize:function(){
		var router = new IndexRouter();
		this.start();
	},
	
	events: {
		'click a': function(e){
			e.preventDefault();
			console.log(e.currentTarget.pathname);
			Backbone.history.navigate(e.currentTarget.pathname, {trigger:true});
		}
	},

	start: function(){
		Backbone.history.start({pushState:true});
	}
	
}))({el:document.body});