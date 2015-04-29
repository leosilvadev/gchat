define(['jquery', 
        'underscore', 
        'backbone', 
        'utils/template'], function($, _, Backbone, template){
	
	var LogoutView = Backbone.View.extend({
		
		id: 'modal-logout',
		className: 'modal fade',

		closeModal: function(){
			this.$el.modal('hide');
		},

		openModal: function(){
			this.$el.modal('show');
		},
		
		render: function(){
			template.html('_logout', this.$el, _);
			return this;
		}
		
	});
	
	return LogoutView;

});