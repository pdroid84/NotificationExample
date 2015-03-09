package com.paul.debashis.notificationexample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void createNotification (View view){
        // Prepare intent which is triggered if the
        // notification is selected
        Intent mIntent = new Intent(this,NotificationReceiverActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this,0,mIntent,0);

        // Build notification
        // Actions are just fake
        Notification mNotification = new Notification.Builder(this)
                                    .setContentTitle("This is the title of Notification")
                                    .setContentText("This is content").setSmallIcon(R.mipmap.ic_launcher)
                                    .setContentIntent(mPendingIntent)
                                    .addAction(R.mipmap.ic_launcher,"Call",mPendingIntent)
                                    .addAction(R.mipmap.ic_launcher, "More", mPendingIntent)
                                    .addAction(R.mipmap.ic_launcher, "And More", mPendingIntent).build();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(0,mNotification);
    }
    public void createNotificationTwo (View view){
        // Sets an ID for the notification
        int mNotificationId = 001;
        Intent mIntent = new Intent(this,NotificationReceiverActivity.class);
        //Create Backstack
        TaskStackBuilder msTaskStackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack
        msTaskStackBuilder.addParentStack(NotificationReceiverActivity.class);
        // Adds the Intent to the top of the stack
        msTaskStackBuilder.addNextIntent(mIntent);
        // Gets a PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = msTaskStackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        //use NotificationCompat and its subclasses, particularly NotificationCompat.Builder, to provide the best notification support for a wide range of platforms
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My Notification Title")
                .setContentText("My Notification Context")
                .setContentIntent(resultPendingIntent);

        NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification mNotification = mBuilder.build();
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        // Builds the notification and issues it.
        mManager.notify(mNotificationId,mNotification);
    }
    public void createNotificationThree (View view) {
        // Instantiate a Builder object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My Special Notification Title")
                .setContentText("My Special Notification Context");
        // Creates an Intent for the Activity
        Intent notifyIntent = new Intent(this, SpecialActivity.class);
// Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

// Puts the PendingIntent into the notification builder
        builder.setContentIntent(notifyPendingIntent);
// Notifications are issued by sending them to the
// NotificationManager system service.
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
// Builds an anonymous Notification object from the builder, and
// passes it to the NotificationManager
        Notification mNotification = builder.build();
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotificationManager.notify(2, mNotification);
    }
    public void createNotificationFour (View view) {
        // Instantiate a Builder object.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("My Special Notification Title Updated")
                .setContentText("My Special Notification Context Updated");
        // Creates an Intent for the Activity
        Intent notifyIntent = new Intent(this, SpecialActivity.class);
// Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK);
// Creates the PendingIntent
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

// Puts the PendingIntent into the notification builder
        builder.setContentIntent(notifyPendingIntent);
// Notifications are issued by sending them to the
// NotificationManager system service.
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
// Builds an anonymous Notification object from the builder, and
// passes it to the NotificationManager
        Notification mNotification = builder.build();
        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
        // The sname id ensures the same notification gets updated
        mNotificationManager.notify(2, mNotification);
    }
    public void createNotificationFive (View view) {
        final int NOTIFICATION_ID = 3;
        final NotificationManager mNotifyManager =
                (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Picture Download")
                .setContentText("Download is in progress")
                .setSmallIcon(R.mipmap.ic_launcher);
// Start a lengthy operation in a background thread
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr+=5) {
                            // Sets the progress indicator to a max value, the
                            // current completion percentage, and "determinate"
                            // state
                            mBuilder.setProgress(100, incr, false);
                            // Displays the progress bar for the first time.
                            mNotifyManager.notify(NOTIFICATION_ID, mBuilder.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(5*1000);
                            } catch (InterruptedException e) {
                                Log.d("DEB", "sleep failure");
                            }
                        }
                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0,0,false);
                        mNotifyManager.notify(NOTIFICATION_ID, mBuilder.build());
                    }
                }
// Starts the thread by calling the run() method in its Runnable
        ).start();
    }
}
