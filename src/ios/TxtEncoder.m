/********* TxtEncoder.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface TxtEncoder : CDVPlugin {
  // Member variables go here.
}

- (void)Encoder:(CDVInvokedUrlCommand*)command;
@end

@implementation TxtEncoder

- (void)Encoder:(CDVInvokedUrlCommand*)command
{
   CDVPluginResult* pluginResult = nil;
   NSString* path = [command.arguments objectAtIndex:0];
   if(path != nil){
       NSString *ret = [self decodeDataType:path];

       if(ret){
             pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:ret];
       }
       else
       {
             pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
       }
   }
   else {
       pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
   }
   [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}


- (NSString *)decodeDataType:(NSString *)filepath
{
    NSString * encType = nil;

    NSStringEncoding *fileEncoding = nil;

    // 假如txt文件带编码头，则获取头
    NSString *txtContent = [NSString stringWithContentsOfFile:filepath usedEncoding:fileEncoding error:nil];

    if (txtContent)
    {
        return encType;
    }

    // GB18030解码
    NSStringEncoding enc = 0x80000631;
    txtContent = [NSString stringWithContentsOfFile:filepath usedEncoding:&enc error:nil];
    if (txtContent)
    {
        return @"GB18030";
    }

    // GBK解码
    enc = 0x80000632;
    txtContent = [NSString stringWithContentsOfFile:filepath usedEncoding:&enc error:nil];

    if (txtContent)
    {
         return @"GBK";
    }

    return encType;
}

@end
