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
import android.app.Service;
import android.os.Binder;
import android.os.IBinder;
import android.content.Intent;
import android.app.IntentService;


public class sSimulator extends IntentService {

	//this class stores the current state of the Controller Hardware
	
	int State;  // 0 = awaiting connection to Controller Hardware  - Calibration can be done by
				//      engineer in this state
	      		// 1 = connected, no Batch Running
				// 2 = connected, Batch running, no recipie [ only possible for user level 2 or above]
	 			// 3 = connected, Batch running against Recipie

	Binder mBinder;
	Thread t;
	cSimulator s;
	dProcessData pd;

	//must be here for subclasses of IntentService
	public sSimulator() {
		super("sSimulator");
	}
	
	@Override
	protected void onHandleIntent(Intent arg0) {
		// TODO Auto-generated method stub
		
	}
	
    @Override
    public void onCreate() {
    	super.onCreate();
    }

    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
    	android.os.Debug.waitForDebugger();
    	//TODO
    	//Start a repeating thread every minute
    	//this runs the simulated Physics of the simulator
    	//and the Control loop of the simulated microcontroller
    	//with feedback loops for temperature control, pH control, DO control
    	s=new cSimulator(); //cSimulator wraps the SQLLite database
    	t = new Thread(new tRepeatingThread(s.db,pd));
    	t.start();
    	
    	return 1;
    }
    
    @Override
    public void onDestroy() {
    	//TODO
    	t.stop();
    	t.destroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
    	//TODO
        return mBinder;
    }	
	
}
