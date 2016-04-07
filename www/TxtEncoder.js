var exec = require('cordova/exec');

var TxtEncoder = {

    Encoder:function(filePath,successCallback, errorCallback) {
        exec(successCallback, errorCallback, "TxtEncoder", "Encoder", [filePath]);
    }
}

module.exports = TxtEncoder;