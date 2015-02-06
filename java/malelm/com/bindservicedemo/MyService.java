package malelm.com.bindservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    //Constructor
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  new LocalBinder();
    }

    //Binder class
    public class LocalBinder extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    //What this service will do
    public int findFact(int num){
        int fact = 1 ;
        for(int i = 1 ; i <= num ; i++){
            fact =fact * i;
        }
        return fact;
    }

}
