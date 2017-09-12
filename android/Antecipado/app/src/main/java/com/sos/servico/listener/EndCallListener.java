package com.sos.servico.listener;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by deivison on 5/2/15.
 */
public class EndCallListener extends PhoneStateListener {
    private boolean callStatus;
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
        if(TelephonyManager.CALL_STATE_RINGING == state) {
            Log.i("CALL", "RINGING, number: " + incomingNumber);
        }
        if(TelephonyManager.CALL_STATE_OFFHOOK == state) {
            //wait for phone to go offhook (probably set a boolean flag) so you know your app initiated the call.
            callStatus = true;
            Log.i("CALL", "OFFHOOK");
        }
        if(TelephonyManager.CALL_STATE_IDLE == state) {
            //when this state occurs, and your flag is set, restart your app
            Log.i("CALL", "IDLE callStatus=>"+callStatus+", incomingNumber=>"+incomingNumber);
        }
    }
}