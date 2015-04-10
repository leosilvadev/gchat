
var ChatMessageView = Backbone.View.extend({
	
	className: 'message',
	template: _.template('<header> <span class="sent-at">[<%= sentAt %>]</span><%= from %> said: </header>' +
			'<div><%= content %></div>'),
		
	render: function(){
		this.$el.html(this.template(this.model.attributes));
		return this;
	}
	
});