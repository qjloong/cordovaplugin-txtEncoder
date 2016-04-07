package cordova.plugin.TxtEncoder;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.mozilla.universalchardet.UniversalDetector;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * This class echoes a string called from JavaScript.
 */
public class TxtEncoder extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("Encoder")) {
            JSONObject options = args.getJSONObject(0);
            String pathStr = options.optString("path");
            String nameStr = options.optString("name");
            this.Encoder(pathStr,nameStr, callbackContext);
            return true;
        }
        return false;
    }

    public static String getFileIncode(File file) {

            if (!file.exists()) {
                System.err.println("getFileIncode: file not exists!");
                return null;
            }

            byte[] buf = new byte[4096];
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                // (1)
                UniversalDetector detector = new UniversalDetector(null);

                // (2)
                int nread;
                while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                    detector.handleData(buf, 0, nread);
                }
                // (3)
                detector.dataEnd();

                // (4)
                String encoding = detector.getDetectedCharset();
                if (encoding != null) {
                    System.out.println("Detected encoding = " + encoding);
                } else {
                    System.out.println("No encoding detected.");
                }

                // (5)
                detector.reset();
                fis.close();
                return encoding;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    public void Encoder(String path,String name, CallbackContext callbackContext) {

        if (path != null && path.length() > 0) {
         File file = new File(path,name);
         String ret = this.getFileIncode(file);
         if(ret != null){
            callbackContext.success(ret);
         }else{
            callbackContext.error("err");
         }
        } else {
            callbackContext.error("err");
        }
    }
}
