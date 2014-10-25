package ir.blog.novintahlilgar.wakeup;

import java.util.Calendar;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CallMeFragment extends Fragment {
	private TextView mDateDisplay;
	private Button mPickDate;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;
	DatePicker mDatePicker;
	TimePicker mTimePicker;
	EditText mPhoneNumber;
	String username, password, rootUrl, date, time, phone;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO: get username and password from sharedpreferences
		username = "username";
		password = "username";
		rootUrl = "http://wake.huri.ir/call/?";
		final HttpHandler httpHandler = new HttpHandler() {

			@Override
			public void onResponse(String result) {
				Log.i("wakeResult", result);
				String message = "";
				//TODO: get message from strings!
				if (result != null) {
					switch (result) {
					case "1": {
						message = "OK";
						break;
					}
					case "2": {
						message = "Wrong Username or Password";
						break;
					}
					case "3": {
						message = "Unsuccessful!";
						break;
					}
					default:
						break;
					}
					
					Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
				} else {
					Log.i("wakeResultNull", result);
				}

			}

			@Override
			public HttpUriRequest getHttpRequestMethod() {
				return new HttpGet(rootUrl + "user=" + username + "&pass="
						+ password + "&d=" + date + "&t=" + time + "&p="
						+ phone);
			}
		};

		View v = inflater.inflate(R.layout.fragment_call_me, container, false);

		mDatePicker = (DatePicker) v.findViewById(R.id.date_picker);
		mTimePicker = (TimePicker) v.findViewById(R.id.time_picker);
		mPhoneNumber = (EditText) v.findViewById(R.id.text_phone_number);
		Button btCallMe = (Button) v.findViewById(R.id.button_call_me);
		btCallMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mYear = mDatePicker.getYear();
				mMonth = mDatePicker.getMonth();
				mDay = mDatePicker.getDayOfMonth();
				mHour = mTimePicker.getCurrentHour();
				mMinute = mTimePicker.getCurrentMinute();
				date = mYear + "-" + mMonth + "-" + mDay;
				time = mHour + "-" + mMinute;
				// TODO: check phone number here!
				phone = mPhoneNumber.getText().toString();
				Log.i("date:", date);
				Log.i("time:", time);
				Log.i("phone", phone);
				// TODO: dissable button.
				httpHandler.execute();

			}
		});

		return v;
	}

}
