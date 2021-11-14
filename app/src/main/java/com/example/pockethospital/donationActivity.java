package com.example.pockethospital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pockethospital.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class donationActivity extends AppCompatActivity implements PaymentResultListener {
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(donationActivity.this, "Thanks for  Donation.",
                Toast.LENGTH_SHORT).show();
        startActivity(new Intent(donationActivity.this, MainActivity.class));
        finish();
        notification();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(donationActivity.this, "Payment Failed",
                Toast.LENGTH_SHORT).show();
    }
    public void Checkout() {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_niI4TeDGpuOjDT");
        //checkout.setImage(R.drawable.logo);
        final Activity activity = this;
        try {
            JSONObject options = new JSONObject();

            options.put("name", "CoviZOne");
            options.put("description", "Donation for needy People");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //   options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount", x);//pass amount in currency subunits
            options.put("prefill.email", "mayank0218.cse19@chitkara.edu.in");
            options.put("prefill.contact", "8168196670");
            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }

    public void Donate(View view) {
        EditText amount=findViewById(R.id.amount_Donate);
        if(amount.getText().toString().length()==0 ){
            Toast.makeText(getApplicationContext(),"Please Enter Amount",Toast.LENGTH_LONG).show();
        }
        else
        {
            x= Integer.parseInt(amount.getText().toString())*100;
            Checkout();
        }

    }
    private void notification() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"n")
                    .setContentText("Successfully Donated")
                    .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                    .setAutoCancel(true)
                    .setContentText("Successfully Donated");
            NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
            managerCompat.notify(999,builder.build());
        }
    }
}