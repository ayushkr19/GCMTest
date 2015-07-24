package ayushkumar.gcmtest.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by ayush on 25/7/15.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService{

    private static final String TAG = "MyInstanceIDLS";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes.
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
