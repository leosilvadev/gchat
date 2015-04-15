define(['jquery', 'underscore', 'backbone', 'templates/Login', 'bootstrap'], function($, _, Backbone, LoginTemplate){

	var LoginView = Backbone.View.extend({
		
		template: _.template(LoginTemplate),
		id : 'modal-login',
		className : 'modal fade',

		openModal: function(){
			this.$el.modal('show');
		},

		closeModal: function(){
			this.$el.modal('hide');
		},
		
		render: function(){
			this.$el.html(this.template());
		}
		
	});
	
	return LoginView;

});