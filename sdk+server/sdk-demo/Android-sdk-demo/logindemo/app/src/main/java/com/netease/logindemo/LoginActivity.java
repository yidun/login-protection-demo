package com.netease.logindemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.netease.mobsec.rjsb.watchman;
import com.netease.mobsec.rjsb.RequestCallback;

public class LoginActivity extends Activity {
    private EditText mEtUsername, mEtPsd;
    private Button mBtLogin;
    public statc String TAG= "TestLog";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        watchman.init(getApplicationContext(), "your productNumber",new RequestCallback(){
            @Override
            public void onResult(int code, String msg) {
                Log.e(TAG,"init,code = " + code + " msg = " + msg);
            }
        });
        setContentView(R.layout.activity_login);

        mEtUsername = (EditText)findViewById(R.id.et_username);
        mEtPsd = (EditText)findViewById(R.id.et_psd);
        mBtLogin = (Button)findViewById(R.id.bt_login);
        mBtLogin.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view == mBtLogin){
                String username = mEtUsername.getText().toString().trim();
                String psd = mEtPsd.getText().toString().trim();
                new LoginTask(LoginActivity.this){

                }.execute(username, psd);
            }
        }
    };
}
