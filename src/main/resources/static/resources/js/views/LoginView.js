
var LoginView = Backbone.View.extend({
	
	template: _.template(loginTemplate),
	id : 'modal-login',
	className : 'modal fade',
	
	render: function(){
		this.$el.html(this.template());
	}
	
});