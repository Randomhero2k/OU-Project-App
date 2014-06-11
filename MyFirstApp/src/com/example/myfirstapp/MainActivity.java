package com.example.myfirstapp;

//Java imports
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.Thread;

//android imports
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;



public class MainActivity extends Activity {
    EditText arrowOneValue;
    private Socket socket;
    private static final int SERVERPORT = 5066;
    private static final String SERVER_IP = "192.168.1.89";
    Thread sendMessage = new Thread(new ClientThread());
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendMessage.start();
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the Submit button */
    public void sendMessage(View view) {
    	
    	arrowOneValue = (EditText) findViewById (R.id.arrowOne);
    	try{
    		
    		
    	String ArrowOneString = arrowOneValue.getText().toString();
		//int ArrowOne = Integer.parseInt(arrowOneValue.getText().toString());
		Log.w("myApp", ArrowOneString);
        PrintWriter out = new PrintWriter(new BufferedWriter(
        new OutputStreamWriter(socket.getOutputStream())),true);
        out.println(ArrowOneString);
        Log.w("myApp", "String sent");

    	
    	
    } catch (UnknownHostException e) {
    	Log.w("myApp", e);
	} catch (IOException e) {
		Log.w("myApp", e);
	} catch (Exception e){
    	Log.w("myApp", e);
    }
    
}
    class ClientThread implements Runnable {

		@Override
		public void run() {
		 

			try {
				InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

				socket = new Socket(serverAddr, SERVERPORT);

			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
}
}
