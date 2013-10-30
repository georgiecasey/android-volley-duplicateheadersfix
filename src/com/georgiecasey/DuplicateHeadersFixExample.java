package com.georgiecasey;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DuplicateHeadersFixExample  extends Activity {
    private RequestQueue queue;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);
        getFacebookHome();
    }

    public void getFacebookHome() {
    	String url = "http://www.facebook.com";
    	StringRequest postRequest = new StringRequest(Request.Method.GET, url, 
    			new Response.Listener<String>() 
    			{
    		@Override
    		public void onResponse(String response) {
    			// response. do some stuff
    		}
    			}, 
    			new Response.ErrorListener() 
    			{
    				@Override
    				public void onErrorResponse(VolleyError error) {
    					// TODO Auto-generated method stub
    				}
    			}
    			) {     

    		@Override
    		protected Response<String> parseNetworkResponse(NetworkResponse response) {
    			// we must override this to get headers. and with the fix, we should get all headers including duplicate names
    			// in an array of apache headers called apacheHeaders. everything else about volley is the same
    			for (int i = 0; i < response.apacheHeaders.length; i++) {
    				String key = response.apacheHeaders[i].getName();
    				String value = response.apacheHeaders[i].getValue();
    				Log.d("VOLLEY_HEADERFIX",key + " - " +value);
    			}

    			return super.parseNetworkResponse(response);
    		}
    	};
    	queue.add(postRequest);

    }

}
