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

public class dSettings {

	int BatchSettingNumber; //when settings change in a Batch or Recipie, the a new Settings record is
	                        //added to the Batch Settings or Recipie Settings array
	int MinutesSinceLastSetting; //tells the Recipie when to change to the next setting
								 //0 for the last reading
	
	String currentUserName;
	int currentUserLevel; //  0=Reader
						  //  1=Operator (can start stop batch with recipie)
	                      //  2=Recipie Manager (can override settings and run without a recipie)
						  //  3=Engineer (can calibrate sensors)
	
	float fTargetPH;
	float fTargetDO;
	float fTargetTemp;
	int uiImpellerSpeed;   //   ->  MotorController
	int uiAirSparge;       //  [0=Off/1=On/2=Auto]   -> Relay
	boolean bCO2Sparge;    //  [On/Off]  ->Relay
	int uiAcidPump;        //  [0=Off/1=On/2=Auto]   -> Relay
	int uiBasePump;        //  [0=Off/1=On/2=Auto]   -> Relay
	boolean bMediaPump;    //[On/Off]  ->Relay
	boolean bAntiFoamPump; // [On/Off]  ->Relay
	int uiHeater;          //[0=Off/1=On/2=Auto]   -> Relay
	
}
