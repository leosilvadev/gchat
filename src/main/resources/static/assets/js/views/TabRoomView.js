
var TabRoomView = Backbone.View.extend({

	template: _.template('<a href="#" class="btn-room-logout"><%= name %><span class="glyphicon glyphicon-remove close"></span></a>'),
	tagName: 'li',
	className: 'opened-room active-room',
	
	events: {
		'click .close': 'close'
	},
	
	close: function(){
//		$('.chat[data-code="'+this.model.get('code')+'"]').remove();
		this.$el.remove();
		this.model.roomView.close();
		this.model.chatView.close();
	},
	
	render: function(){
		this.$el.html(this.template(this.model.attributes));
		return this;
	}

});