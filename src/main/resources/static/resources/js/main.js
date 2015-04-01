
var postJSON = function(url, json, successCallback, errorCallback){
	$.ajax({
        type: "POST",
        url: url,
        contentType : 'application/json',
        data: json,
        success: successCallback,
        error: errorCallback
    });
};