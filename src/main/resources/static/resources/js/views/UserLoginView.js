
var UserLoginView = Backbone.View.extend({
	
	template: _.template(userLoginTemplate),
	id : 'modal-login',
	className : 'modal fade',
	
	render: function(){
		this.$el.html(this.template());
	}
	
});