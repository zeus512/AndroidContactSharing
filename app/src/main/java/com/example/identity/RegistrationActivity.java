package com.example.identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity {
	EditText name, password, phone;
	String name1, password1, phoneno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		 if (android.os.Build.VERSION.SDK_INT > 9) {
			    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
			}
		name = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		phone = (EditText) findViewById(R.id.editText3);
		Button bt = (Button) findViewById(R.id.button1);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				name1 = name.getText().toString().trim();
				password1 = password.getText().toString().trim();
				phoneno = phone.getText().toString().trim();
				if (!name.equals("") && !password1.equals("")
						&& !phoneno.equals("")) {
					RegisterList rlist = new RegisterList();
					rlist.createDomain();
					rlist.AddToTable(name1, password1, phoneno);
					Toast.makeText(getBaseContext(), "Registration completed",
							Toast.LENGTH_LONG).show();
					Intent it = new Intent(RegistrationActivity.this,
							MainActivity.class);
					startActivity(it);
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please Enter All Fields", 90).show();
				}
			}
		});
	}

	public void register(View v) {
	}
}
