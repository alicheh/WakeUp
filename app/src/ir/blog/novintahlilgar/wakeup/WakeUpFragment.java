package ir.blog.novintahlilgar.wakeup;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class WakeUpFragment extends Fragment{
	String username, password, rootUrl;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//TODO: get username and password from sharedpreferences
		username = "username";
		password = "username";
		rootUrl = "http://wake.huri.ir/call/?";
		final HttpHandler httpHandler = new HttpHandler() {
			
			@Override
			public void onResponse(String result) {
				Log.i("wakeResult",result);
				if(result != null){
					if(!result.equals("0")){
						String number = "tel:" + result;
						Intent call = new Intent(Intent.ACTION_CALL);
						Log.i("number",number);
						call.setData(Uri.parse(number));
						startActivity(call);
					}
				}else {
					Log.i("wakeResultNull",result);
				}
				
			}
			
			@Override
			public HttpUriRequest getHttpRequestMethod() {
				return new HttpGet(rootUrl+"user="+username+"&pass="+password);
			}
		};
		
		
		View v = inflater.inflate(R.layout.fragment_wake_up, container,false);
		
		Button btWakeUp = (Button) v.findViewById(R.id.button_wake_up);

		btWakeUp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("buttonWake", "clicked");
//				new HttpHandler() {
//					
//					@Override
//					public void onResponse(String result) {
//						Log.i("wakeResult",result);
//						if(result != null){
//							if(result != "0"){
//								Log.i("result", result);
//								String number = "tel:" + result;
//								Intent call = new Intent(Intent.ACTION_CALL);
//								Log.i("number",number);
//								call.setData(Uri.parse(number));
//								startActivity(call);
//							}
//						}else {
//							Log.i("wakeResultNull",result);
//						}
//						
//					}
//					
//					@Override
//					public HttpUriRequest getHttpRequestMethod() {
//						return new HttpGet(rootUrl+"user="+username+"&pass="+password);
//					}
//				}.execute();
				
				httpHandler.execute();
				//TODO: disable button until the request returns
			}
		});
		
		
		return v;
	}

}
