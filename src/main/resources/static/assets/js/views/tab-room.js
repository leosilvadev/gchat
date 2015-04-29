define(['backbone', 'utils/template', 'utils/events'], function(Backbone, template, events){
	
	var TabRoomView = Backbone.View.extend({
	
		tagName: 'li',
		className: 'opened-room active-room',
				
		events: {
			'click .close': 'destroy'
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