var exec = require('cordova/exec');

exports.getQr = function(arg0, success, error) {
	if(typeof success == 'function' && typeof arg0 == 'function'){
		error = success;
		success = arg0;
		arg0 = null;
	}
	exec(success, error, "HttpBarcodeScanner", "getQr", [arg0]);
};

exports.startCamera = function(arg0, success, error) {
	if(typeof success == 'function' && typeof arg0 == 'function'){
		error = success;
		success = arg0;
		arg0 = null;
	}
    exec(success, error, "HttpBarcodeScanner", "startCamera", [arg0]);
};

exports.closeCamera = function(arg0, success, error) {
	if(typeof success == 'function' && typeof arg0 == 'function'){
		error = success;
		success = arg0;
		arg0 = null;
	}
    exec(success, error, "HttpBarcodeScanner", "closeCamera", [arg0]);
};

exports.stopCamera = function(arg0, success, error) {
	this.closeCamera(arg0, success, error);
};
