define(['backbone', 'underscore'], function(Backbone, _){
	
	var ChatUserView = Backbone.View.extend({
	
		className: 'chat-user',
		template : _.template('<span data-id="<%= id %>"><%= name %></span>'),
		
		destroy: function(){
			this.remove();
		},
		
		render: function(){
			this.$el.html(this.template(this.model.attributes));
			return this;
		}
	
	});
	
	return ChatUserView;

});