
var AlertView = Backbone.View.extend({
	
	template: _.template(
			"<div class='alert alert-<%= type %>'>" +
				"<button type='button' class='close' data-dismiss='alert' aria-label='Close'>" +
					"<span aria-hidden='true'>&times;</span>" +
				"</button> <%= message %>" +
			"</div>"),
	
	render: function(){
		this.$el.html( this.template(this.model.attributes) );
	},
	
	clear: function($alertsContainer){
		$alertsContainer.html('');
	}
	
});