require.config({
	paths: {
		"jquery"		: "../libs/jquery/dist/jquery",
		"underscore"	: "../libs/underscore-amd/underscore",
		"backbone"		: "../libs/backbone-amd/backbone",
		"bootstrap"		: "../libs/bootstrap/dist/js/bootstrap",
		"sockjs"		: "../libs/sockjs/sockjs.min",
		"stomp"			: "../libs/stomp-websocket/lib/stomp.min",
		"navigator"		: "utils/navigator"
	},

    shim: {
        "bootstrap": {
          deps: ["jquery"]
        }
    }
});

require(['applications/index'], function(IndexApp){
});