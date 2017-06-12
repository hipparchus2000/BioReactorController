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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.graphics.Color;
//import android.widget.Toast;
import android.text.Html;

public class aBioController extends Activity {

	Button cConnect;
	Button cCharts;
	Button cBatchManagement;
	Button cRecipieManagement;
    Button cSystem;
    TextView systemStatus;

    int connected=0;
    int currentUserLevel;
    
	SharedPreferences shared_preferences;
	SharedPreferences.Editor shared_preferences_editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bio_controller);
	    
		cConnect = (Button) findViewById(R.id.Connect);
		cConnect.setText("Connect");		    
	    cCharts = (Button) findViewById(R.id.Charts);
   	 	cBatchManagement = (Button) findViewById(R.id.bBatchManagement);
   	 	cRecipieManagement = (Button) findViewById(R.id.Recipies);
	    cSystem = (Button) findViewById(R.id.System);
		systemStatus = (TextView) findViewById(R.id.SystemStatus);

   	 	setUserLevel();

   	 	//TODO
   	 	//if Simulator selected then start sSimulator service if not started
		//and bind to it (to prevent it being destroyed)

   	 	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bio_controller, menu);
		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		//requestCode is for the BlueTooth connect Dialog
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {				
				setUserLevel();
			}
		}
	  
	}//onActivityResult
	
	
	
    
	public void mConnect(View view) {
		if (connected==1) {
			//@TODO disconnect from bluetooth
			//disconnect
			shared_preferences = getSharedPreferences("shared_preferences_Biocontroller",MODE_PRIVATE);
		    shared_preferences_editor = shared_preferences.edit();
		    shared_preferences_editor.putInt("Connected",0);
		    shared_preferences_editor.putString("username","");
		    shared_preferences_editor.putInt("AccessLevel",0);
		    shared_preferences_editor.putInt("Simulator",0);
		    shared_preferences_editor.putString("Controller","");
		    shared_preferences_editor.commit();
	    	setUserLevel();
		    //Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_SHORT).show();

		} else {
			//show dialog for connect
			Intent iConnect;
		    iConnect = new Intent(this, aConnect.class);
		    startActivityForResult(iConnect,1);
		}
	}

	public void mSystem(View view) {
		Intent iConnect;
	    iConnect = new Intent(this, aSystem.class);
	    startActivity(iConnect);
	}
	
	public void mCharts (View view) {
		Intent iConnect;
	    iConnect = new Intent(this, aCharts.class);
	    startActivity(iConnect);		
	}
	
	public void mRecipieManagement (View view) {
		Intent iConnect;
	    iConnect = new Intent(this, aRecipieManagement.class);
	    startActivity(iConnect);		
	}

	public void mBatchManagement (View view) {
		Intent iConnect;
	    iConnect = new Intent(this, aBatchManagement.class);
	    startActivity(iConnect);	
	    
		TextView systemstatus = (TextView) findViewById(R.id.SystemStatus);
	    systemstatus.setText("System Status:\nBatch N2345 Running\nRecipie X234 ");
	}

	public void setUserLevel(){
		 String userLevelString;
		 userLevelString="";   //default in case currentUserLevel is out of bounds
		 String username;
		 String controller;
		 
	 	 shared_preferences = getSharedPreferences("shared_preferences_Biocontroller",MODE_PRIVATE);			    
	     username = shared_preferences.getString("username","Default");
	     currentUserLevel = shared_preferences.getInt("AccessLevel",0);
	     connected = shared_preferences.getInt("Connected",0);
	     controller = shared_preferences.getString("Controller","");

	   	 switch (currentUserLevel) {
	   	 case 0:
			userLevelString=""; 
	   	 	 setButton(cCharts,false);
	   	 	 setButton(cBatchManagement,false);
	   	 	 setButton(cRecipieManagement,false);
	   	 	 setButton(cSystem,false);
		 	break;	   		 
		 case 1: 
			userLevelString="Viewer"; 
	   	 	 setButton(cCharts,true);
	   	 	 setButton(cBatchManagement,false);
	   	 	 setButton(cRecipieManagement,false);
	   	 	 setButton(cSystem,false);
		 	break;
		 case 2: 
			 userLevelString="Operator"; 
	   	 	 setButton(cCharts,true);
	   	 	 setButton(cBatchManagement,true);
	   	 	 setButton(cRecipieManagement,false);
	   	 	 setButton(cSystem,false);
		 	 break;
		 case 3: 
			 userLevelString="Recipie Manager"; 
	   	 	 setButton(cCharts,true);
	   	 	 setButton(cBatchManagement,true);
	   	 	 setButton(cRecipieManagement,true);
	   	 	 setButton(cSystem,false);
			 break;
		 case 4: 
			 userLevelString="Engineer"; 
	   	 	 setButton(cCharts,true);
	   	 	 setButton(cBatchManagement,true);
	   	 	 setButton(cRecipieManagement,true);
	   	 	 setButton(cSystem,true);
		 	 break;		 
	 }

     String s;
	 s="<b><u>System Status:</u></b><br/>";

	 if (connected==0) {
    	 s=s+"<i>Disconnected</i>";
    	 cConnect.setText("Connect");
     } else {
    	 s=s+     "<i>Connected to Controller: </i>"+controller+"<br/>"+
    			  "<i>Username:</i> "+username+"<br/>"+
    	          "<i>User Level:</i> "+userLevelString;
    	 cConnect.setText("Disconnect");
     }
	 
	 systemStatus.setText(Html.fromHtml(s));
	 

 	
	} //end of setUserLevel

	public void setButton(Button b,boolean on) {
  	 	 b.setClickable(on);
	   	 if (on==true) {
	   		 b.setTextColor(Color.BLACK);		
	   	 } else {
	   		 b.setTextColor(Color.WHITE);			 
	   	 }
	   		 
	}
	
}
