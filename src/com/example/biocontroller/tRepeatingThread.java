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
import android.os.Handler;
import android.database.sqlite.*;

public class tRepeatingThread implements Runnable {

    private final Handler mHandler = new Handler();
    SQLiteDatabase db;
    dProcessData pd;

    public tRepeatingThread(SQLiteDatabase db1,dProcessData pd) {
    	db=db1;
    }

    @Override
    public void run() { 
    	//runs every minute
        mHandler.postDelayed(this, 60000);       
    }
}