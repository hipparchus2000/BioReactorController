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

public class dRecipie {

	String Name;
	String Notes;  //TODO don't forget to add this to other classes!
	
	dSettings[] Settings;   //an array of settings, at the appropriate time after Batch Start
	                       //using a recipie, the Settings change as indicated in the 
						   //current point in the Settings array
	public dRecipie() {
		Settings=new dSettings[1000];
	}
}
