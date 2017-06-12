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
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import java.util.ArrayList;
import java.util.List;


public class cSimulator implements iController {
	
	SQLiteDatabase db;
	final String dbname="simulator.db";

	
	//=====================================================================================
	//private database functions
	private void initDatabase(){
		try {
			db=SQLiteDatabase.openDatabase(dbname, null, SQLiteDatabase.OPEN_READWRITE);
		} catch (SQLiteException e) {
			db=SQLiteDatabase.openOrCreateDatabase(dbname,  null);
			db.execSQL("DROP TABLE SETTINGS;");
			db.execSQL("DROP TABLE Users;");
		    db.execSQL("CREATE TABLE IF NOT EXISTS SETTINGS (NAME TEXT PRIMARY KEY, VALUE TEXT);");
		    db.execSQL("CREATE TABLE IF NOT EXISTS Users (USERNAME TEXT PRIMARY KEY, PASSWORD TEXT, ACCESSLEVEL);");
		    
		    //initialise 4 roles in the users table
		    WriteUserRecord("bill", "b", 4);
		    WriteUserRecord("steve", "s", 3);
		    WriteUserRecord("jim", "j", 2);
		    WriteUserRecord("bob", "b", 1);
		    
		    //initialise PIN
		    WriteSettings("PIN", "0000");
		}		
	}
	
	private void WriteSettings(String keyname, String value){
	    db.execSQL("INSERT OR REPLACE INTO SETTINGS (NAME,VALUE) VALUES ('"+keyname+"','"+value+"');");
	}
	
	private void WriteUserRecord(String Username, String Password, int AccessLevel) {
	    db.execSQL("INSERT OR REPLACE INTO Users (NAME,VALUE) VALUES ('"+Username+"','"+Password+"','"+String.valueOf(AccessLevel)+"');");
	}
	
	/*
	 //TODO
	
	private String ReadSettings(String keyname) {
		Cursor allrows  = db.rawQuery("SELECT * FROM SETTINGS", null);
		//allrows.getCount());
		Integer cindex = allrows.getColumnIndex("NAME");
		Integer cindex1 = allrows.getColumnIndex("VALUE");
		allrows.moveToFirst();
		allrows.getString(cindex);
		allrows.getString(cindex1);
		
		return ("bollocks");
		//TODO
	}
	*/
	
	
	//SYSTEM =============================================================

	public int fetchNumberOfUserRecords() {
		//TODO
		return 2;
	}
	
	//used by System - Edit User
	public dUser fetchUserRecord(int number) {
		//TODO
		dUser b=new dUser();
		if (number==1) {
			b.username="Fred";
			b.password="xyz";
			b.AccessLevel=4;
		} else {
			b.username="Joe";
			b.password="zzz";
			b.AccessLevel=2;	
		}
		return b;
	}
	
	public void sendUserRecord(int number,dUser ur) {
		WriteUserRecord(ur.username, ur.password, ur.AccessLevel);
	}
	
	//used by System - Change Controller Name
	//public String fetchControllerName() {
		//TODO
		//String s="Simulator";
		//return s;
	//}
	
	//public void sendControllerName(String newName) {
	//	WriteSettings("ControllerName", newName);
	//}
	
	//used by System - Change Controller PIN
	public void setControllerPIN(String newPIN) {
		WriteSettings("PIN", newPIN);
	}
	
	//Calibrations
	public void CalibratePH4() {
		//do nothing in sim
	}
	
	public void CalibratePH7() {
		//do nothing in sim
	}
	
	public void CalibratePH10() {
		//do nothing in sim
	}
	
	public void CalibrateDO() {
		//do nothing in sim
	}
	
	public void CalibrateVCD(float VCDsetting) {
		WriteSettings("VCDSetting", String.valueOf(VCDsetting));
	}
	
	//RECIPIE MANAGER =============================================================
	public List<String> fetchRecipieList() {
		List<String> list=new ArrayList<String>();
		list.add("R1234");
		list.add("R3333");
		return list;
		//TODO
	}
	
	public void CopyRecipie(String oldname,String newname) {
		//TODO
	}
	
	public void DeleteRecipie(String name) {
		//TODO
	}
	
	public int fetchSizeOfRecipie(String recipieName) {
		//TODO
		return 1;
	}
	
	//EditRecipie
	public dSettings fetchRecipieRecord(int recordnum,String recipieName) {
		//TODO
		dSettings b=new dSettings();
		b.currentUserLevel=2;
		b.currentUserName="test";
		b.BatchSettingNumber=23;
		b.MinutesSinceLastSetting=345;
		b.fTargetPH=(float)7.1;
		b.fTargetDO=(float)222;
		b.fTargetTemp=(float)22.1;
		b.uiAirSparge=2;
		b.bCO2Sparge=true;
		b.uiAcidPump=2;
		b.uiBasePump=2;
		b.uiHeater=2;
		b.bAntiFoamPump=false;
		b.bMediaPump=false;
		b.uiImpellerSpeed=30;
		
		return b;
	}
	
	public void sendRecipieRecord(int recordNumber,String recipieName,dSettings rr) {
		//TODO
		
	}
	
	public void insertRecipieRecordBefore(String recipieName,int recordNumber) {
		//TODO
	}
	
	public void deleteRecipieRecord(String recipieName,int recordNumber) {
		//TODO
	}
	
	//ViewRecipie
	//FetchRecipieCSV
	//SendRecipieCSV
	public int fetchRecipieLength(String recipieName) {
		//TODO
		return 100;
	}
	
	//BATCH MANAGER ===============================================================
	//batch details
	public String fetchCurrentRecipieName() {
		return "R1234";
		//TODO
	}
	
	public String fetchCurrentBatchName() {
		return "B0001";
		//TODO
	}
		
	public void startRecipie(String BatchName,String RecipieName) {
		//TODO
		WriteSettings("RecipieRunning", "1");
		WriteSettings("CurrentRecipeName", RecipieName);
		WriteSettings("CurrentBatchName",BatchName);
	}
	
	
	public int IsBatchRunning() { //1 for yes, 0 for no
		return 1;
		//TODO
	}
	
	public int fetchBatchTime() { //returns minutes from start of Batch
		return 12345;
		//TODO
	}
		
	//CHARTS =======================================================================
	//READ SETPOINTS
	public float fetchCurrentPHsetpoint() {
		return (float)7.0;
		//TODO
	}
	
	public float fetchCurrentDOsetpoint() {
		return 228;
		//TODO
	}
	
	public float fetchCurrentTemperatureSetpoint() {
		return (float)23.1;
		//TODO
	}
	
	public float fetchCurrentImpellerSetpoint() {  //note this can't be read
		return 30;
		//TODO
	}
	
	public int fetchAirSpargeSetting() { //0 off 1 on 2 auto
		return 2;
		//TODO
	}
	
	public int fetchCO2SpargeSetting() { //0 off 1 on
		return 1;
		//TODO
	}
	
	public int fetchAcidPumpSetting() { //0 off 1 on 2 auto
		return 2;
		//TODO
	}
	
	public int fetchBasePumpSetting(){ //0 off 1 on 2 auto
		return 2;
		//TODO
	}
	
	public int fetchMediaPumpSetting(){ //0 off 1 on
		//TODO
		return 0;
	}
	
	public int fetchAntifoamPumpSetting() {  //0 off 1 on
		//TODO
		return 0;
	}
	
	public int fetchHeaterSetting(){ //0 off 1 on 2 auto
		//TODO
		return 2;
	}
	
	// WRITE CURRENT SETTINGS FUNCTIONS===============================================
	//SET SETPOINTS
	public void setCurrentPHsetpoint(float newsetting) {
		WriteSettings("CurrentPHSetPoint", String.valueOf(newsetting));
	}
	
	public void setCurrentDOsetpoint(float newsetting) {
		WriteSettings("CurrentDOSetPoint", String.valueOf(newsetting));
	}
	
	public void setCurrentTemperatureSetpoint(float newsetting) {
		WriteSettings("CurrentTemperatureSetPoint", String.valueOf(newsetting));
	}
	
	public void setCurrentImpellerSetpoint(float newsetting) {  //note this can't be read
		WriteSettings("CurrentImpellerSetPoint", String.valueOf(newsetting));
	}
	
	public void setAirSpargeSetting(int newsetting) { //0 off 1 on 2 auto
		WriteSettings("CurrentAirSpargeSetting", String.valueOf(newsetting));
	}
	
	public void setCO2SpargeSetting(int newsetting) { //0 off 1 on
		WriteSettings("CurrentCO2SpargeSetting", String.valueOf(newsetting));
	}
	
	public void setAcidPumpSetting(int newsetting) { //0 off 1 on 2 auto
		WriteSettings("CurrentAcidPumpSetting", String.valueOf(newsetting));
	}
	
	public void setBasePumpSetting(int newsetting) { //0 off 1 on 2 auto 
		WriteSettings("CurrentBasePumpSetting", String.valueOf(newsetting));
	}
	
	public void setMediaPumpSetting(int newsetting) { //0 off 1 on 
		WriteSettings("CurrentMediaPumpSetting", String.valueOf(newsetting));
	}
	
	public void setAntifoamPumpSetting(int newsetting) {  //0 off 1 on
		WriteSettings("CurrentAntifoamPumpSetting", String.valueOf(newsetting));
	}
	
	public void setHeaterSetting(int newsetting) { //0 off 1 on 2 auto
		WriteSettings("CurrentHeaterSetting", String.valueOf(newsetting));
	}
	
	//GET CURRENT READINGS FUNCTIONS ========================================================
	//READINGS
	public float getCurrentPH() {
		//TODO
		float f=(float)7.1;
		return f;
	}
	
	public float getCurrentDO() {
		//TODO
		float f=(float)225;
		return f;		
	}
	
	public float getCurrentTemperature() {
		//TODO
		float f=(float)22.7;
		return f;		
	}
	
	public float getCurrentVCD() {
		//TODO
		float f=(float)1e7;
		return f;		
	}

	//GET HISTORY FUNCTIONS (current batch) ===========================================================
	//CHART FUNCTIONS
	//readings
	public float getPHat(int batchTime){
		//TODO
		float f=(float)7.1;
		return f;		
	}
	
	public float getDOat(int batchTime){
		//TODO
		float f=(float)225;
		return f;				
	}
	
	public float getTemperatureAt(int batchTime){
		//TODO
		float f=(float)22.7;
		return f;				
	}
	
	public float getVCDAt(int batchTime) {
		//TODO
		float f=(float)1e7;
		return f;		
	}
	
	//settings
	//READ SETPOINTS
	public float fetchPHsetpointAt(int batchTime){
		//TODO
		float f=(float)7.2;
		return f;		
	}
	public float fetchDOsetpointAt(int batchTime){
		//TODO
		float f=(float)225;
		return f;		
	}
	public float fetchTemperatureSetpointAt(int batchTime){
		//TODO
		float f=(float)23.1;
		return f;		
	}
	public float fetchImpellerSetpointAt(int batchTime) {  //note this can't be read
		//TODO
		float f=(float)31;
		return f;		
	}
	
	public int fetchAirSpargeSettingAt(int batchTime){ //0 off 1 on 2 auto
		//TODO
		return 0;		
	}
	
	public int fetchCO2SpargeSettingAt(int batchTime){ //0 off 1 on
		//TODO
		return 0;		
	}
	
	public int fetchAcidPumpSettingAt(int batchTime){ //0 off 1 on 2 auto
		//TODO
		return 0;		
	}
	
	public int fetchBasePumpSettingAt(int batchTime){ //0 off 1 on 2 auto 
		//TODO
		return 0;				
	}
	
	public int fetchMediaPumpSettingAt(int batchTime){ //0 off 1 on 
		//TODO
		return 0;		
	}
	
	public int fetchAntifoamPumpSettingAt(int batchTime){  //0 off 1 on
		//TODO
		return 0;		
	}
	
	public int fetchHeaterSettingAt(int batchTime){ //0 off 1 on 2 auto
		//TODO
		return 0;		
	}
	
	//Mark event requests a comment to be entered
	public void recordComment(String comment) {
		WriteSettings("CurrentComment", comment);
	}

	
	//CONNECT =================================================================
	public int connectController(String Username,String Password,String PIN) {
	//returns access level granted - 0 if not connected.
		initDatabase();
	
		//lookup PIN
		
		//lookup User record based on username
		//fetch password and userlevel
		
		
		//TODO
		WriteSettings("CurrentUsername", Username);
		WriteSettings("CurrentAccessLevel", "4");
		return 4;		
	}
	
	
	public void disconnectController() {
		//TODO
        db.close();        
	}
		
	
	
	
}
