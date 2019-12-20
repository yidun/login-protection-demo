package com.netease.logindemo;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.netease.mobsec.rjsb.watchman;
import com.netease.mobsec.rjsb.RequestCallback;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class LoginTask extends AsyncTask<String, Void, String> {
    private Context mContext;
    public static String TAG= "Login-getToken";
    public LoginTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String username = strings[0];
        String psd = strings[1];
        String token = watchman.getToken( "your BusinessId",new RequestCallback(){
            @Override
            public void onResult(int code, String msg) {
                Log.e(TAG,"Register, code = " + code + " msg = " + msg);
            }
        });
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", username);
        params.put("password", psd);
        params.put("token", token);
        return PostData(params);
    }

    @Override
    protected void onPostExecute(String result) {
        if(result!=null)
            Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "失败", Toast.LENGTH_SHORT).show();
    }

    public StringBuffer getRequestData(Map<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }


    private String showResponseResult(InputStream inptStream)
    {
        String result = "";
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inptStream));
            String line = "";
            while (null != (line = reader.readLine()))
            {
                result += line;
            }
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String PostData(Map<String, String> params) {
        byte[] data = getRequestData(params, "utf-8").toString().getBytes();
        String url = "http://localhost:8182/login.do";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);

            int response = httpURLConnection.getResponseCode();
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inptStream = httpURLConnection.getInputStream();
                return showResponseResult(inptStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
