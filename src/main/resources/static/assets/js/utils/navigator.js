define(['jquery', 'backbone'], function($, Backbone){
	
	var navigator = {

		//Workaround for navigation, when navigate to a modal twice it was not working
		//TODO: looking for a better implementation!
		to: function(event){
			var $target = $(event.currentTarget);
			var href = $target.attr('href');
			var external = $target.attr('external');
			
			if ( !external ) {
				event.preventDefault();
				var ret = Backbone.history.navigate(href, true);
				if (ret === undefined) {
				    Backbone.history.loadUrl(href);
				}
			}
		}			
	};
	
	return navigator;
	
});