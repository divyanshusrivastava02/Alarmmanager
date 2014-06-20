package com.rakesh.alarmmanagerexample;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		System.out.println("R  Q  S::::::::"+Staqtic.alarm);
		Toast.makeText(arg0, "Alarm received!", Toast.LENGTH_LONG).show();
		
		Intent startIntent = new Intent(arg0, RingtonePlayingService.class); 
		arg0.startService(startIntent);

	}

	public void CancelAlarm(Context context,int RQS_1)
	{

		Intent stopIntent = new Intent(context, RingtonePlayingService.class);
		context.stopService(stopIntent);

		System.out.println("VBBBBBBBBBBBBBBBBBBBBBBBBB");
		Intent intent = new Intent(context, AlarmReceiver.class);
		PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(sender);
		System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCC");

	}

	public void SetAlarm(Context context,Calendar targetCal,int RQS_1)
	{


		ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

		AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(context, AlarmReceiver.class);
		// Loop counter `i` is used as a `requestCode`

		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, 0);
		// Single alarms in 1, 2, ..., 10 minutes (in `i` minutes)
		//				  alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, 
		//			                  SystemClock.elapsedRealtime() + 6000 * RQS_1, 
		//			                  pendingIntent); 
		alarmManager.set(AlarmManager.RTC_WAKEUP, 
				targetCal.getTimeInMillis(), 
				pendingIntent); 

		intentArray.add(pendingIntent);	

	}

}