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

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class aSystem extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_system);
		//TODO
   	 	//if Simulator selected then start sSimulator service if not started
		//and bind to it (to prevent it being destroyed)
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.system, menu);
		return true;
	}

	public void mCalibratePH(View view) {
	    Intent iConnect = new Intent(this, aCalibratePH.class);
	    //TextView connectmenu = (TextView) findViewById(R.id.connectmenu);
	    //String message = editText.getText().toString();
	    //iConnect.putExtra(EXTRA_MESSAGE, message);
	    //connectmenu.setText("Hello");
	    startActivity(iConnect);
	}

	public void mEditUser(View view) {
	    Intent iConnect = new Intent(this, aEditUser.class);
	    startActivity(iConnect);
	}
	
}
