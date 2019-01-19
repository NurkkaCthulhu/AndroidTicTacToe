package com.anumalm.tictactoe;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Debug-class for printing debug messages easier into logcat or toast.
 *
 * @author      Anu Malm <anu.m.malm(at)gmail.com>
 * @version     2019.0119
 * @since       2019.0113
 */
public class Debug {

    private static int DEBUG_LEVEL;
    private static Context myHost;

    /**
     * Printing method.
     *
     * @param className         class that calls this method (or TAG for the print)
     * @param methodName        name of the method that this print is used in
     * @param message           message that we want to display related to the method
     * @param level             how many curly brackets deep this print is
     * @param printToToast      is the debug message printed to toast (=true) or logcat (=false)
     */
    public static void print(String className, String methodName, String message, int level, boolean printToToast) {
        if(level <= DEBUG_LEVEL) {
            if(printToToast) {
                Toast.makeText(myHost,
                        className + ": " + methodName + ", " + message, Toast.LENGTH_SHORT).show();
            } else {
                Log.d(className, methodName + ", " + message);
            }
        }
    }

    /**
     * Loads the Debug class.
     *
     * @param host              Context that is used for getting the debug_level from xml and printing toast
     */
    public static void loadDebug(Context host) {
        myHost = host;
        DEBUG_LEVEL = host.getResources().getInteger(R.integer.debug_level);
    }
}
