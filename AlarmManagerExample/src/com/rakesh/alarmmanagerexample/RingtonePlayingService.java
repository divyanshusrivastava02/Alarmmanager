package com.rakesh.alarmmanagerexample;

import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

public class RingtonePlayingService extends Service
{
    private Ringtone ringtone;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
    	
    	Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	  	
       // Uri ringtoneUri = Uri.parse(intent.getExtras().getString("ringtone-uri"));

        this.ringtone = RingtoneManager.getRingtone(this, notification);
        ringtone.play();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        ringtone.stop();
    }
}