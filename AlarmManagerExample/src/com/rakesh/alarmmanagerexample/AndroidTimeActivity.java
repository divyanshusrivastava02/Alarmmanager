package com.rakesh.alarmmanagerexample;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class AndroidTimeActivity extends Activity {
 
 TimePicker myTimePicker;
 Button buttonstartSetDialog;
 TextView textAlarmPrompt;
 AlarmManager alarmManager;
 TimePickerDialog timePickerDialog;
 AlarmReceiver AlarmReceiver = new AlarmReceiver();
// static int val = 1;
 
  static int RQS_1 = Staqtic.alarm;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textAlarmPrompt = (TextView)findViewById(R.id.alarmprompt);
        System.out.println("R  Q  S:::::1:::"+RQS_1);
        
        buttonstartSetDialog = (Button)findViewById(R.id.startSetDialog);
        buttonstartSetDialog.setOnClickListener(new OnClickListener(){

   @Override
   public void onClick(View v) {
	  // RQS_1 += Staqtic.alarm ;
	   System.out.println("R  Q  S::::::::"+RQS_1);
    textAlarmPrompt.setText("");
    openTimePickerDialog(false);
    
    
   }});

    }

  
 private void openTimePickerDialog(boolean is24r){
  Calendar calendar = Calendar.getInstance();
  
  timePickerDialog = new TimePickerDialog(
    AndroidTimeActivity.this, 
    onTimeSetListener, 
    calendar.get(Calendar.HOUR_OF_DAY), 
    calendar.get(Calendar.MINUTE), 
    is24r);
  timePickerDialog.setTitle("Set Alarm Time");  
        
  timePickerDialog.show();

 }
    
    OnTimeSetListener onTimeSetListener
    = new OnTimeSetListener(){

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

   Calendar calNow = Calendar.getInstance();
   Calendar calSet = (Calendar) calNow.clone();

   calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
   calSet.set(Calendar.MINUTE, minute);
   calSet.set(Calendar.SECOND, 0);
   calSet.set(Calendar.MILLISECOND, 0);
   
   System.out.println("TIME SETED:::::::::"+hourOfDay +" :  "+minute);
   if(calSet.compareTo(calNow) <= 0){
    //Today Set time passed, count to tomorrow
    calSet.add(Calendar.DATE, 1);
   }
  RQS_1 += Staqtic.alarm ;
   setAlarm(calSet);
  }};

 private void setAlarm(Calendar targetCal){

  textAlarmPrompt.setText(
    "\n\n***\n"
    + "Alarm is set@ " + targetCal.getTime() + "\n"
    + "***\n");
 

  AlarmReceiver.SetAlarm(this, targetCal, RQS_1);

 }
 
 public void cancelRepeatingTimer(View view){
 	Context context = this.getApplicationContext();
 	System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA");
 	if(AlarmReceiver != null){
 		AlarmReceiver.CancelAlarm(context,RQS_1);
 	}else{
 		Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
 	}
 }
 
 
  
}
