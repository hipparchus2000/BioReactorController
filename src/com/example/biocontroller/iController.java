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

public interface iController {

	//SYSTEM =============================================================
	
	//used by System - Edit User
	public int fetchNumberOfUserRecords();
	public dUser fetchUserRecord(int number);
	public void sendUserRecord(int number,dUser ur);
	
	//used by System - Change Controller Name
	//public String fetchControllerName();
	//public void sendControllerName(String newName);
	//TODO  - or not?
	
	//used by System - Change Controller PIN
	public void setControllerPIN(String newPIN);
	
	//Calibrations
	public void CalibratePH4();
	public void CalibratePH7();
	public void CalibratePH10();
	public void CalibrateDO();
	public void CalibrateVCD(float VCDsetting);
	
	//RECIPIE MANAGER =============================================================
	public List<String> fetchRecipieList();
	public void CopyRecipie(String oldname,String newname);
	public void DeleteRecipie(String name);
	//EditRecipie
	public int fetchSizeOfRecipie(String recipieName); //output in records
	public dSettings fetchRecipieRecord(int recordNumber, String recipieName);
	public void sendRecipieRecord(int recordNumber,String recipieName,dSettings rr);
	public void insertRecipieRecordBefore(String recipieName,int recordNumber);
	public void deleteRecipieRecord(String recipieName,int recordNumber);
	//ViewRecipie
	//FetchRecipieCSV
	//SendRecipieCSV
	public int fetchRecipieLength(String recipieName);
	
	//BATCH MANAGER ===============================================================
	//batch details
	public String fetchCurrentRecipieName();
	public String fetchCurrentBatchName();
	
	public void startRecipie(String BatchName,String RecipieName);
	public int IsBatchRunning(); //1 for yes, 0 for no
	public int fetchBatchTime(); //returns minutes from start of Batch

	//CHARTS =======================================================================
	//READ SETPOINTS
	public float fetchCurrentPHsetpoint();
	public float fetchCurrentDOsetpoint();
	public float fetchCurrentTemperatureSetpoint();
	public float fetchCurrentImpellerSetpoint();  //note this can't be read

	public int fetchAirSpargeSetting(); //0 off 1 on 2 auto
	public int fetchCO2SpargeSetting(); //0 off 1 on
	
	public int fetchAcidPumpSetting(); //0 off 1 on 2 auto
	public int fetchBasePumpSetting(); //0 off 1 on 2 auto 
	public int fetchMediaPumpSetting(); //0 off 1 on 
	public int fetchAntifoamPumpSetting();  //0 off 1 on

	public int fetchHeaterSetting(); //0 off 1 on 2 auto
	
	//SET SETPOINTS
	public void setCurrentPHsetpoint(float newsetting);
	public void setCurrentDOsetpoint(float newsetting);
	public void setCurrentTemperatureSetpoint(float newsetting);
	public void setCurrentImpellerSetpoint(float newsetting);  //note this can't be read

	public void setAirSpargeSetting(int newsetting); //0 off 1 on 2 auto
	public void setCO2SpargeSetting(int newsetting); //0 off 1 on
	
	public void setAcidPumpSetting(int newsetting); //0 off 1 on 2 auto
	public void setBasePumpSetting(int newsetting); //0 off 1 on 2 auto 
	public void setMediaPumpSetting(int newsetting); //0 off 1 on 
	public void setAntifoamPumpSetting(int newsetting);  //0 off 1 on

	public void setHeaterSetting(int newsetting); //0 off 1 on 2 auto
	
	//READINGS
	public float getCurrentPH();
	public float getCurrentDO();
	public float getCurrentTemperature();
	public float getCurrentVCD();  

	//CHART FUNCTIONS
	//readings
	public float getPHat(int batchTime);
	public float getDOat(int batchTime);
	public float getTemperatureAt(int batchTime);
	public float getVCDAt(int batchTime);  
	
	//settings
	//READ SETPOINTS
	public float fetchPHsetpointAt(int batchTime);
	public float fetchDOsetpointAt(int batchTime);
	public float fetchTemperatureSetpointAt(int batchTime);
	public float fetchImpellerSetpointAt(int batchTime);  //note this can't be read

	public int fetchAirSpargeSettingAt(int batchTime); //0 off 1 on 2 auto
	public int fetchCO2SpargeSettingAt(int batchTime); //0 off 1 on
	
	public int fetchAcidPumpSettingAt(int batchTime); //0 off 1 on 2 auto
	public int fetchBasePumpSettingAt(int batchTime); //0 off 1 on 2 auto 
	public int fetchMediaPumpSettingAt(int batchTime); //0 off 1 on 
	public int fetchAntifoamPumpSettingAt(int batchTime);  //0 off 1 on

	public int fetchHeaterSettingAt(int batchTime); //0 off 1 on 2 auto

	//Mark event requests a comment to be entered
	public void recordComment(String comment);
	
	//CONNECT =================================================================
	public int connectController(String Username,String Password,String PIN);
	//returns access level granted - 0 if not connected.
	
	public void disconnectController();
	
}
