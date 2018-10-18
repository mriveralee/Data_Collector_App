package yunzhil.buttontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    private String userDefaultCaller;
    private final static String FILE_NAME = "userDefaultDailer.txt";
    //private TelecomManager manger= (TelecomManager) getSystemService(TELECOM_SERVICE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button defaultBtn = findViewById(R.id.defaultBtn);
        Button returnBtn = findViewById(R.id.returnBtn);
        TelecomManager manger= (TelecomManager) getSystemService(TELECOM_SERVICE);
        userDefaultCaller=manger.getDefaultDialerPackage();
        maintainUserDefaultCaller(userDefaultCaller);
        defaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDefaultCaller();
            }
        });

        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnDefaultCaller();
            }
        });
    }
    private void returnDefaultCaller(){
        StringBuffer sb = read();

        Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
        intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
                sb.toString());
        startActivity(intent);
        Toast.makeText(this,"Already return!",Toast.LENGTH_SHORT).show();

    }



    private void changeDefaultCaller(){

        //userDefaultCaller=manger.getDefaultDialerPackage();
        Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
        intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
                this.getPackageName());
        startActivity(intent);
        Toast.makeText(this,"Already done!",Toast.LENGTH_SHORT).show();

    }

    private void maintainUserDefaultCaller(String caller){
        StringBuffer sb = read();
        if (sb.length()<=0) {
            save(caller);
        }
    }


    private StringBuffer read() {
        FileInputStream in = null;
        Scanner s = null;
        StringBuffer sb = new StringBuffer();
        try {
            in = super.openFileInput(FILE_NAME);
            s = new Scanner(in);
            while (s.hasNext()) {
                sb.append(s.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb;
    }

    private void save(String data) {
        FileOutputStream out = null;
        PrintStream ps = null;
        try {
            out = super.openFileOutput(FILE_NAME, Activity.MODE_PRIVATE);
            ps = new PrintStream(out);
            ps.println(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                    ps.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}


