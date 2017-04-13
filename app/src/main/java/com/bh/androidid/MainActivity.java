package com.bh.androidid;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private Context mContext = null;
    private TextView mValueOfDeviceId = null;
    private TextView mValueOfSubscriberId = null;
    private TextView mValueOfSerialId = null;
    private TextView mValueOfMACAddress = null;
    private TextView mVaueOfUUID = null;
    private TextView mValueOfAndroidId = null;
    private TextView mValueOfBluetoothAddress = null;


    private TelephonyManager mTelephonyMgr = null;
    private WifiManager mWifiMgr = null;
    private WifiInfo mWifiInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mContext = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mWifiMgr = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifiMgr.getConnectionInfo();

        mValueOfDeviceId = (TextView) findViewById(R.id.value_of_device_id);
        mValueOfSubscriberId = (TextView) findViewById(R.id.value_of_subscriber_id);
        mValueOfSerialId = (TextView) findViewById(R.id.value_of_serial_id);
        mValueOfMACAddress = (TextView) findViewById(R.id.value_of_mac_address);
        mVaueOfUUID = (TextView) findViewById(R.id.value_of_uuid);
        mValueOfAndroidId = (TextView) findViewById(R.id.value_of_android_id);
        mValueOfBluetoothAddress = (TextView) findViewById(R.id.value_of_bluetooth_address);

    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get Device ID
        mValueOfDeviceId.setText(mTelephonyMgr.getDeviceId());

        // Get Subscriber ID
        mValueOfSubscriberId.setText(mTelephonyMgr.getSubscriberId());

        // Get SERIAL NO.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            mValueOfSerialId.setText(Build.SERIAL);
        }


        // Get MAC Address
        mValueOfMACAddress.setText(mWifiInfo.getMacAddress());

        // Get Android ID
        mValueOfAndroidId.setText(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));

        // Get UUID
        mVaueOfUUID.setText(UUID.randomUUID().toString());

        // Get Bluetooth Address
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        String btAddress = "N/A";
        if (btAdapter != null) {
            btAddress = btAdapter.getAddress();
        }
        mValueOfBluetoothAddress.setText(btAddress);

    }
}
