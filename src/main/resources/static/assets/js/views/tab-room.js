define(['backbone'], function(Backbone){
	
	var TabRoomView = Backbone.View.extend({
	
		template: _.template('<a href="#" class="btn-room-logout"><%= name %><span class="glyphicon glyphicon-remove close"></span></a>'),
		tagName: 'li',
		className: 'opened-room active-room',
		
		events: {
			'click .close': 'close'
		},
		
		close: function(){
			this.$el.remove();
			this.model.roomView.destroy();
			this.model.chatRoomView.destroy();
		},
		
		render: function(){
			this.$el.html(this.template(this.model.attributes));
			return this;
		}
	
	});
	
	return TabRoomView;

});