require.config({
	paths: {
		"jquery":"../libs/jquery/dist/jquery",
		"underscore":"../libs/underscore-amd/underscore",
		"backbone":"../libs/backbone-amd/backbone",
		"boostrap":"../libs/bootstrap/dist/js/bootstrap"
	}
});

require(['applications/IndexApp'], function(IndexApp){
});