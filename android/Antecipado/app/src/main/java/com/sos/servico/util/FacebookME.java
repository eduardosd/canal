package com.sos.servico.util;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.sos.servico.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by deivison on 5/2/15.
 */
public class FacebookME {

    public static void getFacebookEmail(final EmailRetrieve emailRetrieve){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken == null)  return;
        if(accessToken.getPermissions().contains("email")) {
            GraphRequest request=GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        emailRetrieve.user(object.getString("email"), object.getString("name"),object.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,email");
            request.setParameters(parameters);
            request.executeAsync();
        }

    }
    public static interface EmailRetrieve {
        void user(String email,String name,String id);
    }
}
