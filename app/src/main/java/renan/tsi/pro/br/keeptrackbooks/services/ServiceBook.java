package renan.tsi.pro.br.keeptrackbooks.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import renan.tsi.pro.br.keeptrackbooks.activities.BooksActivity;
import renan.tsi.pro.br.keeptrackbooks.activities.MainActivity;

/**
 * Created by renan on 26/11/17.
 */

public class ServiceBook extends IntentService {

    private boolean running = true;
    private PendingIntent resultPendingIntent;

    public ServiceBook() {
        super("Service Book");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("TESTE", "foi");
        notificate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent,flags,startId);
    }

    protected void notificate() {
        int notificationId = 27;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(android.R.drawable.ic_menu_info_details);
        mBuilder.setContentTitle("New book added!");
        mBuilder.setContentText("Create a status for track your notes!");
        mBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(this, BooksActivity.class);
        resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, 0);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationId, mBuilder.build());
    }

    @Override
    public void onDestroy() {
        running = false;
        super.onDestroy();
    }


}
