require.config({
	paths: {
		"jquery":"../libs/jquery/dist/jquery",
		"underscore":"../libs/underscore-amd/underscore",
		"backbone":"../libs/backbone-amd/backbone",
		"bootstrap":"../libs/bootstrap/dist/js/bootstrap"
	},

    shim: {
        "bootstrap": {
          deps: ["jquery"]
        }
    }
});

require(['applications/IndexApp'], function(IndexApp){
});