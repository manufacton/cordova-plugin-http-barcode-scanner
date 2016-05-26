package com.manufacton.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.iki.elonen.NanoHTTPD;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.manufacton.cordova.MyCameraManager;

/**
 * This class echoes a string called from JavaScript.
 */
public class ServerRunner extends NanoHTTPD {
    private static int PORT = 8085;

    public ServerRunner() throws IOException {
        super(PORT);
        start();
        // System.out.println( "\nRunning! Point your browers to http://localhost:8085/ \n" );
    }

    // public static byte[] lastFrameBytes;

    @Override
    public Response serve(IHTTPSession session) {
        // String method = session.getMethod();
        // Map<string, string> params = session.getParms();
        if(MyCameraManager.lastFrameBytes == null){
            return newFixedLengthResponse("");
        }
        InputStream is = new ByteArrayInputStream(MyCameraManager.lastFrameBytes);
        return newFixedLengthResponse(Response.Status.OK, "image/jpg", is, MyCameraManager.lastFrameBytes.length);
    }
    
}
