define(['backbone'], function(Backbone){
	
	var TabRoomView = Backbone.View.extend({
	
		template: _.template('<a href="#" class="btn-room-logout"><%= name %><span class="glyphicon glyphicon-remove close"></span></a>'),
		tagName: 'li',
		className: 'opened-room active-room',
		
		initialize: function(options){
			this.roomView = options.roomView;
			this.chatRoomView = options.chatRoomView;
			this.name = options.name;
		},
		
		events: {
			'click .close': 'destroy'
		},
		
		destroy: function(){
			console.log(this);
			this.roomView.destroy();
			this.chatRoomView.destroy();
			this.remove();
		},
		
		render: function(){
			this.$el.html(this.template({name: this.name}));
			return this;
		}
	
	});
	
	return TabRoomView;

});