
var LogoutView = Backbone.View.extend({
	
	template: _.template(logoutTemplate),
	
	id: 'modal-logout',
	className: 'modal fade',
	
	render: function(){
		this.$el.html(this.template());
	}
	
});


