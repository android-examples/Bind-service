package malelm.com.bindservicedemo;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    MyService mservice;
    MyService.LocalBinder binder;
    boolean serviceStatus;

    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.ed);
    }

    public  void bindService(View v){
        Intent i = new Intent(this , MyService.class);
        bindService(i , sc , Context.BIND_AUTO_CREATE);
        serviceStatus = true;
        Toast.makeText(this, "The service is binded successfully", Toast.LENGTH_LONG).show();

    }
    public void unbindService(View v){
        if(serviceStatus) {
            Intent i = new Intent(this, MyService.class);
            unbindService(sc);
            serviceStatus = false;
            Toast.makeText(this, "The service is unbinded successfully", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "The service is already unbinded", Toast.LENGTH_LONG).show();
        }
    }
    public  void findFact(View v){
        if(serviceStatus) {
            int ans = mservice.findFact(Integer.parseInt( et.getText().toString()) ) ;
            Toast.makeText(this, "The answer is "+ans , Toast.LENGTH_LONG ).show();
        }else{
            Toast.makeText(this, "Bind the service first" , Toast.LENGTH_LONG ).show();
        }
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (MyService.LocalBinder) service;
            mservice = binder.getService();
            serviceStatus = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
