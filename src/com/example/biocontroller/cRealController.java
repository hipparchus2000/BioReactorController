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


public class cRealController implements iController{

	private List<String> operands=new ArrayList<String>();
	
	//in C you could set an ordinal value in a struct, but not Java.
	//engineer level commands
	private final String GETNUMBEROFUSERS="EA";
	private final String GETUSERRECORD="EB";
	private final String SETUSERRECORD="EC";
	private final String SETCONTROLLERPIN="ED";
	private final String CALIBRATEPH4="EE";
	private final String CALIBRATEPH7="EF";
	private final String CALIBRATEPH10="EG";
	private final String CALIBRATEDO="EH";
	private final String CALIBRATEVCD="EI";
	
	
	//RECIPIE MANAGER LEVEL COMMANDS ============================================
	private final String COPYRECIPIE="MA";
	private final String DELETERECIPIE="MB";
	private final String GETRECIPIESIZE="MC"; 
	private final String GETRECIPIERECORD="MD";
	private final String SETRECIPIERECORD="ME";
	private final String INSERTRECIPIERECORDBEFORE="MF";
	private final String DELETERECIPIERECORD="MG";
	private final String GETRECIPIELENGTH="MH";


	//SET SETPOINTS
	private final String SETPHSP="MI";
	private final String SETDPSP="MJ";
	private final String SETTEMPSP="MK";
	private final String SETIMPELLERSP="ML";  //note this can't be read
	private final String SETAIRSPARGESP="MN"; //0 off 1 on 2 auto
	private final String SETCO2SPAREGESP="MO"; //0 off 1 on
	private final String SETACIDPUMPSP="MP"; //0 off 1 on 2 auto
	private final String SETBASEPUMPSP="MQ"; //0 off 1 on 2 auto 
	private final String SETMEDIAPUMPSP="MR"; //0 off 1 on 
	private final String SETANTIFOAMPUMPSP="MS";  //0 off 1 on
	private final String SETHEATERSP="MT"; //0 off 1 on 2 auto

	
	//OPERATOR LEVEL COMMANDS====================================================
	private final String GETRECIPIELIST="OA"; //needed by operator to choose recipie
	private final String SETCURRENTBATCHNAME="OB"; //needed by operator(or is this recipie start)
	private final String STARTRECIPIE="OC";
	private final String RECORDCOMMENT="OD";


	//VIEWER LEVEL COMMANDS =====================================================
	private final String GETCURRENTRECIPIENAME="VA"; //needed by viewer
	private final String GETCURRENTBATCHNAME="VB"; //needed by viewer
    private final String GETISBATCHRUNNING="VC";
	private final String GETBATCHTIME="VD";

	//live SETPOINTS  - read only
	private final String GETPHSP="VE";
	private final String GETDOSP="VF";
	private final String GETTSP="VG";
	private final String GETIMPSP="VH";  //note this can't be read
	private final String GETAIRSPARGESP="VI"; //0 off 1 on 2 auto
	private final String GETCO2SPARGESP="VJ"; //0 off 1 on
	private final String GETACIDPUMPSP="VK"; //0 off 1 on 2 auto
	private final String GETBASEPUMPSP="VL"; //0 off 1 on 2 auto 
	private final String GETMEDIAPUMPSP="VM"; //0 off 1 on 
	private final String GETANTIFOAMPUMPSP="VN";  //0 off 1 on
	private final String GETHEATERSP="VO"; //0 off 1 on 2 auto
	//READ SETPOINTS history
	private final String GETPHSPAT="VP";
	private final String GETDOSPAT="VQ";
	private final String GETTEMPSPAT="VR";
	private final String GETIMPSPAT="VS";  //note this can't be read
	private final String GETAIRSPARGESPAT="VT"; //0 off 1 on 2 auto
	private final String GETCO2SPARGESPAT="VU"; //0 off 1 on
	private final String GETACIDPUMPSPAT="VV"; //0 off 1 on 2 auto
	private final String GETBASEPUMPSPAT="VW"; //0 off 1 on 2 auto 
	private final String GETMEDIAPUMPSPAT="VX"; //0 off 1 on 
	private final String GETANTIFOAMPUMPSPAT="VY";  //0 off 1 on
	private final String GETHEATERSPAT="VZ"; //0 off 1 on 2 auto
	
	
	//live READINGS
	private final String GETPH="Va";
	private final String GETDO="Vb";
	private final String GETTEMP="Vc";
	private final String GETVCD="Vd";  

	//CHART FUNCTIONS
	//historical readings
	private final String GETPHAT="Ve";
	private final String GETDOAT="Vf";
	private final String GETTEMPAT="Vg";
	private final String GETVCDAT="Vh";  
	

	
	//CONNECT =================================================================
	private final String CONNECT="Vx";
	private final String DISCONNECT="Vz";


	
//=============================================================	
	private void sendRequest(String command,String operands_int,int num_operands_expected) {
		//TODO
		//RETURNS THE RESPONSE FROM THE CONTROLLER
		//note -1 in num_operands_expected means no limit.
		operands.clear();
		operands.add("test");
		operands.add("xyz");
		if (operands.size()==num_operands_expected) {
			//ok
		} else {
			//throw exception
		}
	}
//=========================================================
	
	public int fetchNumberOfUserRecords() {
		//ask controller how many users there are;
		sendRequest(GETNUMBEROFUSERS,"",1);
		return Integer.valueOf(operands.get(0));
	}
	public dUser fetchUserRecord(int number) {
		sendRequest(GETUSERRECORD,String.valueOf(number),3);
		dUser d=new dUser();
		d.username=operands.get(0);
		d.password=operands.get(1);
		d.AccessLevel=Integer.valueOf(operands.get(2));
		return d;
	}
	
	public void sendUserRecord(int number,dUser ur) {
		String op=ur.username+","+ur.password+","+String.valueOf(ur.AccessLevel);
		sendRequest(SETUSERRECORD,op,0);
	}
	
	//used by System - Change Controller Name
	//public String fetchControllerName();
	//public void sendControllerName(String newName);
	//TODO  - or not?
	
	//used by System - Change Controller PIN
	public void setControllerPIN(String newPIN) {
		sendRequest(SETCONTROLLERPIN,newPIN,0);
	}
	
	//Calibrations
	public void CalibratePH4() {
		sendRequest(CALIBRATEPH4,"",0);
	}
	public void CalibratePH7() {
		sendRequest(CALIBRATEPH7,"",0);
	}
	public void CalibratePH10() {
		sendRequest(CALIBRATEPH10,"",0);
	}
	public void CalibrateDO() {
		sendRequest(CALIBRATEDO,"",0);
	}
	public void CalibrateVCD(float VCDsetting) {
		sendRequest(CALIBRATEVCD,String.valueOf(VCDsetting),0);
	}
	
	//RECIPIE MANAGER =============================================================
	public List<String> fetchRecipieList() {
		sendRequest(GETRECIPIELIST,"",-1);
		return operands;
	}
	
	public void CopyRecipie(String oldname,String newname) {
		sendRequest(COPYRECIPIE,oldname+","+newname,0);
	}
	
	public void DeleteRecipie(String name) {
		sendRequest(DELETERECIPIE,name,0);
	}
	
	//EditRecipie
	public int fetchSizeOfRecipie(String recipieName) {
		//output in records
		sendRequest(GETRECIPIESIZE,recipieName,1);
		return Integer.valueOf(operands.get(0));
	}
	
	public dSettings fetchRecipieRecord(int recordNumber,String recipieName) {
		sendRequest(GETRECIPIERECORD,recipieName+","+String.valueOf(recordNumber),-1);
		
		//this could be done in the class dSettings, but if it's in this file, this file then completely describes the ABI of the interface
		//to the real controller.
		
		dSettings d=new dSettings();
		//d.currentUserLevel;
		//d.currentUserName;
		//not used? d.BatchSettingNumber
		d.MinutesSinceLastSetting=Integer.valueOf(operands.get(0));
		d.fTargetPH=Float.valueOf(operands.get(1));
		d.fTargetDO=Float.valueOf(operands.get(2));
		d.fTargetTemp=Float.valueOf(operands.get(3));
		d.uiImpellerSpeed=Integer.valueOf(operands.get(4));
		d.uiAirSparge=Integer.valueOf(operands.get(5));
		d.bCO2Sparge=Integer.valueOf(operands.get(6))==1;
		//not used when not running d.MinutesSinceLastSetting
		d.bAntiFoamPump=Integer.valueOf(operands.get(7))==1;
		d.bMediaPump=Integer.valueOf(operands.get(8))==1;
		d.uiAcidPump=Integer.valueOf(operands.get(9));
		d.uiBasePump=Integer.valueOf(operands.get(10));
		d.uiHeater=Integer.valueOf(operands.get(11));
	
		return d;
	}
	
	public void sendRecipieRecord(int recordNumber,String recipieName,dSettings d) {
		
		String s=recipieName+",";
		s=s+String.valueOf(d.MinutesSinceLastSetting)+",";
		s=s+String.valueOf(d.fTargetPH)+",";
		s=s+String.valueOf(d.fTargetDO)+",";
		s=s+String.valueOf(d.fTargetTemp)+",";
		s=s+String.valueOf(d.uiImpellerSpeed)+",";
		s=s+String.valueOf(d.uiAirSparge)+",";
		s=s+String.valueOf(d.bCO2Sparge?1:0)+",";
		s=s+String.valueOf(d.bAntiFoamPump?1:0)+",";
		s=s+String.valueOf(d.bMediaPump?1:0)+",";
		s=s+String.valueOf(d.uiAcidPump)+",";
		s=s+String.valueOf(d.uiBasePump)+",";
		s=s+String.valueOf(d.uiHeater);

		sendRequest(SETRECIPIERECORD,s,0);
	}
	
	public void insertRecipieRecordBefore(String recipieName,int recordNumber) {
		sendRequest(INSERTRECIPIERECORDBEFORE,recipieName+","+String.valueOf(recordNumber),0);
	}
	
	public void deleteRecipieRecord(String recipieName,int recordNumber) {
		sendRequest(DELETERECIPIERECORD,recipieName+","+String.valueOf(recordNumber),0);
	}
	
	//ViewRecipie
	//FetchRecipieCSV
	//SendRecipieCSV
	public int fetchRecipieLength(String recipieName) {
		sendRequest(GETRECIPIELENGTH,recipieName,1);
		return Integer.valueOf(operands.get(0));
	}
	
	//BATCH MANAGER ===============================================================
	//batch details
	public String fetchCurrentRecipieName() {
		sendRequest(GETCURRENTRECIPIENAME,"",1);
		return operands.get(0);
	}
	
	public String fetchCurrentBatchName() {
		sendRequest(GETCURRENTBATCHNAME,"",1);
		return operands.get(0);
	}
	
	public void setCurrentBatchName(String bn) { //only userlevel recipie manager and above can start 
	             //a batch without a name set.
		sendRequest(SETCURRENTBATCHNAME,bn,0);
	}
	
	public void startRecipie(String BatchName,String RecipieName) {
		sendRequest(STARTRECIPIE,BatchName+","+RecipieName,0);
	}
	
	
	public int IsBatchRunning() { //1 for yes, 0 for no
		sendRequest(GETISBATCHRUNNING,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchBatchTime() { //returns minutes from start of Batch
		sendRequest(GETBATCHTIME,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	//CHARTS =======================================================================
	//READ SETPOINTS
	public float fetchCurrentPHsetpoint() {
		sendRequest(GETPHSP,"",1);
		return Float.valueOf(operands.get(0));
	}
	public float fetchCurrentDOsetpoint() {
		sendRequest(GETDOSP,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float fetchCurrentTemperatureSetpoint() {
		sendRequest(GETTSP,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float fetchCurrentImpellerSetpoint() {
		//note this can't be read
		sendRequest(GETIMPSP,"",1);
		return Float.valueOf(operands.get(0));
	}

	public int fetchAirSpargeSetting() {
		//0 off 1 on 2 auto
		sendRequest(GETAIRSPARGESP,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchCO2SpargeSetting() { //0 off 1 on
		sendRequest(GETCO2SPARGESP,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchAcidPumpSetting() { //0 off 1 on 2 auto
		sendRequest(GETACIDPUMPSP,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchBasePumpSetting() { //0 off 1 on 2 auto
		sendRequest(GETBASEPUMPSP,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchMediaPumpSetting() { //0 off 1 on
		sendRequest(GETMEDIAPUMPSP,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchAntifoamPumpSetting() {  //0 off 1 on
		sendRequest(GETANTIFOAMPUMPSP,"",1);
		return Integer.valueOf(operands.get(0));
	}

	public int fetchHeaterSetting() { //0 off 1 on 2 auto
		sendRequest(GETHEATERSP,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	//SET SETPOINTS
	public void setCurrentPHsetpoint(float newsetting) {
		sendRequest(SETPHSP,String.valueOf(newsetting),0);
	}
	
	public void setCurrentDOsetpoint(float newsetting) {
		sendRequest(SETDPSP,String.valueOf(newsetting),0);
	}
	
	public void setCurrentTemperatureSetpoint(float newsetting) {
		sendRequest(SETTEMPSP,String.valueOf(newsetting),0);
	}
	
	public void setCurrentImpellerSetpoint(float newsetting) {  // note this can't be read
		sendRequest(SETIMPELLERSP,String.valueOf(newsetting),0);
	}
	
	public void setAirSpargeSetting(int newsetting) { // 0 off 1 on 2 auto
		sendRequest(SETAIRSPARGESP,String.valueOf(newsetting),0);
	}
	
	public void setCO2SpargeSetting(int newsetting) { // 0 off 1 on
		sendRequest(SETCO2SPAREGESP,String.valueOf(newsetting),0);
	}
	
	public void setAcidPumpSetting(int newsetting) { // 0 off 1 on 2 auto
		sendRequest(SETACIDPUMPSP,String.valueOf(newsetting),0);
	}
	
	public void setBasePumpSetting(int newsetting) { //0 off 1 on 2 auto
		sendRequest(SETBASEPUMPSP,String.valueOf(newsetting),0);
	}
	
	public void setMediaPumpSetting(int newsetting) { //0 off 1 on
		sendRequest(SETMEDIAPUMPSP,String.valueOf(newsetting),0);
	}
	
	public void setAntifoamPumpSetting(int newsetting) {  //0 off 1 on
		sendRequest(SETANTIFOAMPUMPSP,String.valueOf(newsetting),0);
	}
	
	public void setHeaterSetting(int newsetting) { //0 off 1 on 2 auto
		sendRequest(SETHEATERSP,String.valueOf(newsetting),0);
	}
	
	//READINGS
	public float getCurrentPH() {
		sendRequest(GETPH,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float getCurrentDO() {
		sendRequest(GETDO,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float getCurrentTemperature() {
		sendRequest(GETTEMP,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float getCurrentVCD() {
		sendRequest(GETVCD,"",1);
		return Float.valueOf(operands.get(0));
	}

	//CHART FUNCTIONS
	//readings
	public float getPHat(int batchTime) {
		sendRequest(GETPHAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float getDOat(int batchTime) {
		sendRequest(GETDOAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float getTemperatureAt(int batchTime) {
		sendRequest(GETTEMPAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float getVCDAt(int batchTime) {
		sendRequest(GETVCDAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	//settings
	//READ SETPOINTS
	public float fetchPHsetpointAt(int batchTime) {
		sendRequest(GETPHSPAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float fetchDOsetpointAt(int batchTime) {
		sendRequest(GETDOSPAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float fetchTemperatureSetpointAt(int batchTime) {
		sendRequest(GETTEMPSPAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public float fetchImpellerSetpointAt(int batchTime) {  //note this can't be read
		sendRequest(GETIMPSPAT,"",1);
		return Float.valueOf(operands.get(0));
	}
	
	public int fetchAirSpargeSettingAt(int batchTime) { //0 off 1 on 2 auto
		sendRequest(GETAIRSPARGESPAT,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchCO2SpargeSettingAt(int batchTime) { //0 off 1 on
		sendRequest(GETCO2SPARGESPAT,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchAcidPumpSettingAt(int batchTime) { //0 off 1 on 2 auto
		sendRequest(GETACIDPUMPSPAT,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchBasePumpSettingAt(int batchTime) { //0 off 1 on 2 auto
		sendRequest(GETBASEPUMPSPAT,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchMediaPumpSettingAt(int batchTime) { //0 off 1 on
		sendRequest(GETMEDIAPUMPSPAT,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchAntifoamPumpSettingAt(int batchTime) {  //0 off 1 on
		sendRequest(GETANTIFOAMPUMPSPAT,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	public int fetchHeaterSettingAt(int batchTime) { //0 off 1 on 2 auto
		sendRequest(GETHEATERSPAT,"",1);
		return Integer.valueOf(operands.get(0));
	}
	
	//Mark event requests a comment to be entered
	public void recordComment(String comment) {
		sendRequest(RECORDCOMMENT,comment,0);
	}
	
	//CONNECT =================================================================
	public int connectController(String Username,String Password,String PIN) {
		sendRequest(CONNECT,Username+","+Password+","+PIN,1);
		return Integer.valueOf(operands.get(0));
	}
	//returns access level granted - 0 if not connected.
	
	public void disconnectController() {
		sendRequest(DISCONNECT,"",0);		
	}

}
