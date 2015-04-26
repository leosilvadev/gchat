define(
		['jquery', 
		 'underscore', 
		 'backbone', 
		 'utils/template',
		 'bootstrap'], 
		 
		 function($, _, Backbone, template){

	var LoginView = Backbone.View.extend({
		
		id : 'modal-login',
		className : 'modal fade',

		openModal: function(){
			this.$el.modal('show');
		},

		closeModal: function(){
			this.$el.modal('hide');
		},
		
		render: function(){
			template.render('_login', this.$el, _);
		}
		
	});
	
	return LoginView;

});