package ir.blog.novintahlilgar.wakeup;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class LoginFragment extends Fragment {
	Activity activity;
	Button btLogin;
	private static final String usernameString = "username";
	private static final String passwordString = "password";
	private static final String sharedPrefName = "sharedPref";
	SharedPreferences sharedPref;
	EditText editTextUsername;
	EditText editTextPassword;
	final String username=null,password=null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		activity = (Main)this.getActivity();
		super.onCreate(savedInstanceState);
	}
	public View onCreateView(android.view.LayoutInflater inflater,
			android.view.ViewGroup container,
			android.os.Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_login, container, false);
		
		editTextUsername = (EditText) v.findViewById(R.id.username);
		editTextPassword = (EditText) v.findViewById(R.id.password);
		
		sharedPref = getActivity().getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
		if(sharedPref.contains(usernameString) && sharedPref.contains(passwordString))
			setTexts();
		
		
		btLogin = (Button) v.findViewById(R.id.button_login);
		btLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(checkUserPass()){
					saveInPref();
					((Main) activity).showMain();
				}else{
					
				}
					
				
			}

			
		});
		
		
		return v;
	};
	
	protected boolean checkUserPass() {
		
		return true;
	}
	public void setTexts(){
		editTextUsername.setText(sharedPref.getString(usernameString, null));
		editTextPassword.setText(sharedPref.getString(passwordString, null));
	}
	
	public void saveInPref() {
		Editor editor = sharedPref.edit();
		editor.putString(usernameString, editTextUsername.getText().toString());
		editor.putString(passwordString, editTextPassword.getText().toString());
		editor.commit();
	}
}
