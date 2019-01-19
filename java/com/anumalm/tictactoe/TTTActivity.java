package com.anumalm.tictactoe;

import android.support.v7.app.AppCompatActivity;

/**
 * Activity that gives all activities, that extend it, a TAG-String possibility.
 *
 * @author      Anu Malm <anu.m.malm(at)gmail.com>
 * @version     2019.0119
 * @since       2019.0113
 */
public class TTTActivity extends AppCompatActivity {
    // This TAG can be used when calling Debug.print-method.
    protected final String TAG = this.getClass().getName();
}
