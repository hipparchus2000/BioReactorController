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

public class dBatch {

	String BatchName;
	String BatchNotes;
	String usingRecipieName;
	String BatchBy;
	String StartDateTime;
	String StopDateTime;
	int currentMinutesIntoBatch; //only used for a batch that is currently running
	int sizeOfBatch; //number of readings in a completed batch
	dReadings[] batchReadings;    //records readings
	dSettings[] batchSettings;    //records changes to settings
	
	public dBatch () {
		batchReadings=new dReadings[10000];
		batchSettings=new dSettings[10000];
	}
	
}
