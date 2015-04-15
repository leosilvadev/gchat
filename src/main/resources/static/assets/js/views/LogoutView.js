define(['jquery', 'underscore', 'backbone', 'templates/LogoutTemplate'], function($, _, Backbone, LogoutTemplate){
	
	var LogoutView = Backbone.View.extend({
		
		template: _.template(LogoutTemplate),
		
		id: 'modal-logout',
		className: 'modal fade',

		closeModal: function(){
			this.$el.modal('hide');
		},

		openModal: function(){
			this.$el.modal('show');
		},
		
		render: function(){
			this.$el.html(this.template());
			return this;
		}
		
	});
	
	return LogoutView;

});