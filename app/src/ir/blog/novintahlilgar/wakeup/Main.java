package ir.blog.novintahlilgar.wakeup;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Main extends Activity {
	
	private static final String usernameString = "username";
	private static final String passwordString = "password";
	private static final String sharedPrefName = "sharedPref";
	FragmentManager fragmentManager;
	CallMeFragment callMeF;
	WakeUpFragment wakeUpF;
	LoginFragment loginF;
	
	SharedPreferences sharedPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sharedPref = getSharedPreferences(sharedPrefName, MODE_PRIVATE);
		fragmentManager = getFragmentManager();
		
		if(sharedPref.contains(usernameString) && sharedPref.contains(passwordString))
			showMain();
		else {
			showLogin();
		}
		
		
	}
	
	
	public void showMain(){
		callMeF = new CallMeFragment();
		wakeUpF = new WakeUpFragment();
		
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_2x, callMeF);
		fragmentTransaction.replace(R.id.fragment_wake_up, wakeUpF);
		fragmentTransaction.commit();
	}
	
	public void showLogin(){
		loginF = new LoginFragment();
		
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_2x, loginF);
		fragmentTransaction.commit();
	}
}
