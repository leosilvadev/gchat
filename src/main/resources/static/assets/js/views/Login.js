define(['jquery', 'underscore', 'backbone', 'templates/Login', 'boostrap'], function($, _, Backbone, LoginTemplate){

	var LoginView = Backbone.View.extend({
		
		template: _.template(LoginTemplate),
		id : 'modal-login',
		className : 'modal fade',
		
		openModal: function(){
			this.$el.modal('show');
		},
		
		render: function(){
			this.$el.html(this.template());
		}
		
	});
	
	return LoginView;

});