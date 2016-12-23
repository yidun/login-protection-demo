package com.netease.logindemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.netease.mobrcsec.rjsb.RjsbHandler;


public class LoginActivity extends Activity {
    private EditText mEtUsername, mEtPsd;
    private Button mBtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RjsbHandler.setBusinessId(getApplicationContext(), "YOUR_BUSINESS_ID");
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
