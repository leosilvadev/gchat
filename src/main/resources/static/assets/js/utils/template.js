define(['jquery', 'underscore'], function($, _){
	
	var Template = function(){
		
		var object = object || {};
		
		this.html = function(templateName, $el, object){
			$.get('templates/' + templateName + ".html").success(function(html){
				var template = _.template(html);
				$el.html(template(object));
			});
		};
		
		this.append = function(templateName, $el, object){
			$.get('templates/' + templateName + ".html").success(function(html){
				var template = _.template(html);
				$el.append(template(object));
			});
		};
		
	};
	
	return new Template();
	
});