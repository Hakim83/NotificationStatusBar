package course.examples.notification.statusbar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationStatusBarActivity extends AppCompatActivity {
	
	// Notification ID to allow for future updates
	private static final int MY_NOTIFICATION_ID = 1;

	// Notification Count 
	private int mNotificationCount;
	
	// Notification Text Elements
	private final CharSequence tickerText = "This is a Really, Really, Super Long Notification Message!";
	private final CharSequence contentTitle = "Notification";
	private final CharSequence contentText = "You've Been Notified!";

	// Notification Action Elements
	private Intent mNotificationIntent;
	private PendingIntent mContentIntent;

	// Notification Sound and Vibration on Arrival  
	private Uri soundURI = Uri
			.parse("android.resource://course.examples.notification.statusbar/"
					+ R.raw.alarm_rooster);
	private long[] mVibratePattern = { 0, 200, 200, 300 };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		mNotificationIntent = new Intent(getApplicationContext(),
				NotificationSubActivity.class);
		mContentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
				mNotificationIntent,0);

		final Button button = (Button) findViewById(R.id.notify_button);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// Define the Notification's expanded message and Intent:
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
						.setTicker(tickerText)
						.setSmallIcon(android.R.drawable.stat_sys_warning)
						.setAutoCancel(true)
						.setContentTitle(contentTitle)
						.setContentText(contentText + " (" + ++mNotificationCount + ")")
						.setContentIntent(mContentIntent)
						.setSound(soundURI)
						.setVibrate(mVibratePattern);

				// Pass the Notification to the NotificationManager:
				NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				mNotificationManager.notify(MY_NOTIFICATION_ID,
						builder.build());
			}
		});

	}
}