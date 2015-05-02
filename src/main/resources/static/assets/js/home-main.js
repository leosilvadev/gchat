require.config({
	paths: {
		"jquery"		: "../libs/jquery/dist/jquery",
		"underscore"	: "../libs/underscore-amd/underscore",
		"backbone"		: "../libs/backbone-amd/backbone",
		"bootstrap"		: "../libs/bootstrap/dist/js/bootstrap",
		"sockjs"		: "../libs/sockjs/sockjs.min",
		"stomp"			: "../libs/stomp-websocket/lib/stomp",
		"navigator"		: "utils/navigator"
	},

    shim: {
        "bootstrap": {
          deps: ["jquery"]
        }
    }
});

require(['applications/home'], function(HomeApp){
	Date.prototype.parse = function() {
		var hours = this.getHours().toString();
		var minutes = this.getMinutes().toString();
		var seconds = this.getSeconds().toString();
		return (hours[1] ? hours : "0"+hours[0]) + ":" + (minutes[1] ? minutes : "0" + minutes[0]) + ":" + (seconds[1] ? seconds : "0" + seconds[0]);
	};
});