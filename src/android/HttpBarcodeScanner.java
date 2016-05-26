package com.manufacton.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import fi.iki.elonen.NanoHTTPD;

import com.manufacton.cordova.MyCameraManager;
import com.manufacton.cordova.ServerRunner;

import java.io.IOException;

/**
 * This class echoes a string called from JavaScript.
 */
public class HttpBarcodeScanner extends CordovaPlugin {

    private MyCameraManager cameraManager;
    private ServerRunner server;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("startCamera")) {
            String message = args.getString(0);
            this.startCamera(message, callbackContext);
            
            return true;
        }
        if (action.equals("getQr")) {
            String message = args.getString(0);
            this.getQr(message, callbackContext);
            return true;
        }
        if (action.equals("closeCamera")) {
            String message = args.getString(0);
            this.closeCamera(message, callbackContext);        
            return true;
        }
        return false;
    }

   

    private void startCamera(String message, CallbackContext callbackContext) {
        try {
            server = new ServerRunner();
            cameraManager = new MyCameraManager(cordova.getActivity());
            cameraManager.openCamera(640, 480);
            callbackContext.success("http://localhost:8085/1.jpg");
        } catch (IOException e){
            e.printStackTrace();
            callbackContext.error(e.toString());
        }
    }

    private void closeCamera(String message, final CallbackContext callbackContext) {
        cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {    
                    server.stop();
                    cameraManager.onPause();
                    callbackContext.success("camera server stopped");
                } catch (Exception e){
                    e.printStackTrace();
                    callbackContext.error(e.toString());
                }
            }
        });
    }

    private void getQr(String message, CallbackContext callbackContext) {
        if(cameraManager != null)
            callbackContext.success(cameraManager.qrCode);
        else 
            callbackContext.error("Camera server not initialized");
    }
}
