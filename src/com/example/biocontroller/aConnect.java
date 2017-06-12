/*    Copyright (C) 2013  Jeff Davies

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


package com.example.biocontroller;

import java.util.List;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.ComponentName;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.content.SharedPreferences;

public class aConnect extends Activity {

	
	EditText username;
	EditText password;
	EditText PIN;
	RadioGroup rg;
	List<String> ls;
	Intent iSimulator;
	
	SharedPreferences shared_preferences;
	SharedPreferences.Editor shared_preferences_editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect);

		//TODO
   	 	//if Simulator selected then start sSimulator service if not started
		//and bind to it (to prevent it being destroyed)
		
		//TODO
		//find bluetooth controllers and amend radio button group rgControllersList
		ls=listControllers();
		rg   = (RadioGroup)findViewById(R.id.rgControllerList);
		RadioButton b;
		for(int i = 0; i < ls.size(); i++) {
		    b = new RadioButton(this);
		    b.setText(ls.get(i));
		    rg.addView(b);
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.button3:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

 
	public void mBluetooth (View view) {

	    username   = (EditText)findViewById(R.id.name);
	    password   = (EditText)findViewById(R.id.password);
	    PIN   = (EditText)findViewById(R.id.BTPIN);
	    rg = (RadioGroup)findViewById(R.id.rgControllerList);

	    String s_username;
	    s_username=username.getText().toString();
		int AccessLevel;
		AccessLevel=0;  //user level or 0 for failure
		String s_controller;
		int i = rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));
		if (i==-1) {
			Toast t = Toast.makeText(getApplication(),"no controller selected" ,Toast.LENGTH_SHORT);
			t.show();
			return;
		}
		s_controller=ls.get(i);
		

		//TEST CODE ONLY 
		if (s_username.contains("a")) AccessLevel=4; //engineer
		if (s_username.contains("b")) AccessLevel=3; //engineer
		if (s_username.contains("c")) AccessLevel=2; //engineer
		if (s_username.contains("d")) AccessLevel=1; //engineer

		shared_preferences = getSharedPreferences("shared_preferences_Biocontroller",MODE_PRIVATE);


		if (s_controller.contains("Simulator")) {
			//or if Simulator selected then start sSimulator service
			//and bind to it (to prevent it being destroyed)
			//if Simulator deselected, stop service
			iSimulator = new Intent(this,sSimulator.class);
			startService(iSimulator); 
        	
		} else {
			//TODO connect to bluetooth
		}
		
		//Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
	    
	    shared_preferences_editor = shared_preferences.edit();
	    shared_preferences_editor.putString("username", s_username);
	    shared_preferences_editor.putInt("AccessLevel",AccessLevel);
	    shared_preferences_editor.putInt("Connected",1);
	    shared_preferences_editor.putInt("Simulator",0);
	    shared_preferences_editor.putString("Controller",s_controller);
	    shared_preferences_editor.commit();
	    
	    Intent intent = new Intent();
	    setResult(RESULT_OK, intent);
	    
	    finish();

	}

	public List<String> listControllers() {
		List<String> ls=new ArrayList<String>();
		ls.add("Simulator");
		ls.add("BCH0001:CellLab4Bioreactor04");
		ls.add("BCH0002:CellLab5Bioreactor12");
		//TODO enumerate Bluetooth devices and add them to the list
		
		return ls;
	}

}
