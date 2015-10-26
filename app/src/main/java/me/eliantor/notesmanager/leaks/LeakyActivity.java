package me.eliantor.notesmanager.leaks;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aktor on 23/10/15.
 */
public class LeakyActivity extends Activity {

    private TextView mTv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //...
        mTv = (TextView)findViewById(0);


       new MyThread().start();

    }

    private static class MyThread  extends Thread{
        public void run() {
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {

            }

//                final String result = "finally";
            // mTv.setText(result); -> Exception ui update from non Main Thread
//                LeakyActivity.this.runOnUiThread(new Runnable(){
//                    public void run(){
////                        LeakyActivity.this.mTv.setText(result);
//                    }
//                });
        }
    }


}
