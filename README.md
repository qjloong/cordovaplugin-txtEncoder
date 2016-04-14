# cordovaplugin-txtEncoder


install $: cordova plugin add cordova.plugin.TxtEncoder


getTxtEncoder 


                    获取android txt 编码类型 
                    var param = {'path':fileDirPath,'name':filename};
                    navigator.TxtEncoder.Encoder(param,function(res){
                       var encodeType  = res // 返回编码类型
                           }.function (err){
                       console.log("err",err)
                    }));
