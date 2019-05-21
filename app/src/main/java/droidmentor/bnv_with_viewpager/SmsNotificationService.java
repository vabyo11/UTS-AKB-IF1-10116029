package droidmentor.bnv_with_viewpager;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

/**
 * TANGGAL PENGERJAAN: 20 MEI 2019
 * NIM: 10116029
 * NAMA: VEBY VABYO
 * KELAS: IF-1
 */

public class SmsNotificationService extends IntentService {
    private final static String SERVICE_NAME = "smsService";
    private DBHandler db;

    public SmsNotificationService() {
        super(SERVICE_NAME);
        db = DBHandler.getInstance(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String contentTitle = (String) intent.getExtras().get(SmsReceiver.NOTIF_TITLE);
        String contentMessage = (String) intent.getExtras().get(SmsReceiver.NOTIF_MESSAGE);

        Contact contact = db.getContactByName(contentTitle);

        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder n  = new NotificationCompat.Builder(this)
                .setContentTitle(contact != null ? contact.getName() : contentTitle)
                .setContentText(contentMessage)
                .setSmallIcon(R.drawable.ic_message_white_24dp)
                .setLargeIcon(contact.getImage() != null ? BitmapUtility.getImage(contact.getImage()) : null)
                .setContentIntent(pIntent)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOngoing(false);

        Intent smsIntent = new Intent(this, ContactSmsActivity.class);
        smsIntent.putExtra("name", contact.getName());
        smsIntent.putExtra("phone", contact.getPhone());

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                smsIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        n.setContentIntent(contentIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, n.build());
    }

}
