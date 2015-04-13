
var LoginView = Backbone.View.extend({
	
	template: _.template(loginTemplate),
	id : 'modal-login',
	className : 'modal fade',
	
	openModal: function(){
		this.$el.modal('show');
	},
	
	render: function(){
		this.$el.html(this.template());
	}
	
});