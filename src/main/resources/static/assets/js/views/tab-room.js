define(['backbone', 'utils/template', 'utils/events'], function(Backbone, template, events){
	
	var TabRoomView = Backbone.View.extend({
	
		tagName: 'li',
		className: 'opened-room active-room',
				
		events: {
			'click .close'			: 'destroy',
			'click .btn-tab-room' 	: 'switchChat'
		},
		
		switchChat: function(){
			events.trigger('room:chat-switch', this.model);
		},
		
		hide: function(){
			this.$el.removeClass('active-room');
		},
		
		show: function(){
			this.$el.addClass('active-room');
		},
		
		destroy: function(){
			events.trigger('room:tab-destroy', this.model);
			events.trigger('room:chat-destroy', this.model);
			this.remove();
		},
		
		render: function(){
			template.html('_tab_room', this.$el, this.model.attributes);
			return this;
		}
	
	});
	
	return TabRoomView;

});