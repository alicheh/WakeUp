package ir.blog.novintahlilgar.wakeup;

import org.apache.http.client.methods.HttpUriRequest;

import android.util.Log;

/**
 * Abstract class for handling http calls.
 */
public abstract class HttpHandler {

	/**
	 * @return HttpUriRequest
	 */
	public abstract HttpUriRequest getHttpRequestMethod();
	
	/**
	 * Abstract class for what to with the response of the http call.
	 */
	public abstract void onResponse(String result);
	
	public void execute(){
		Log.i("httpHandlerExec","execute");
		new HttpAsyncTask(this).execute();
	}
}
