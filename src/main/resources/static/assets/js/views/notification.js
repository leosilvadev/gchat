define(['jquery','backbone'], function($, Backbone){

	var NotificationView = Backbone.View.extend({
	
		template: _.template(
				'<div class="alert alert-<%= type %>">' +
					'<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
					  	'<span aria-hidden="true">&times;</span>' +
					'</button>' +
					'<%= message %>' +
				'</div>'),
		
		events: {
			'click button.close': 'destroy'
		},
		
		destroy: function(){
			this.remove();
		},
		
		render: function(){
			var notification = this.model;
			
			this.$el.html(this.template(this.model.attributes));
			return this;
		}
		
	});
	
	return NotificationView;

});