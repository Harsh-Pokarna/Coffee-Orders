package com.example.coffeorders;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.style.AlignmentSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Formatter;

public class MainActivity extends AppCompatActivity {
    TextView userNameInput, kitkatOrderCount, oreoOrderCount, coffeeOrderCount, orderDetails;
    Button viewOrderDetailsBtn, resetBtn, emailBtn;
    ImageButton incKt, incOr, incCf, decKt, decOr, decCf, delKt, delOr, delCf;
    int kt, or, cf;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialiseFields();
        incKt.setOnClickListener(v -> {
            kt+=1;
            kitkatOrderCount.setText(String.valueOf(kt));
        });
        incOr.setOnClickListener(v -> {
            or+=1;
            oreoOrderCount.setText(String.valueOf(or));
        });
        incCf.setOnClickListener(v -> {
            cf+=1;
            coffeeOrderCount.setText(String.valueOf(cf));
        });
        decKt.setOnClickListener(v -> {
           if (kt > 0){
               kt-=1;
           }
           else {
               kt = 0;
           }
            kitkatOrderCount.setText(String.valueOf(kt));
        });
        decOr.setOnClickListener(v -> {
           if (or > 0){
               or-=1;
           }
           else {
               or = 0;
           }
            oreoOrderCount.setText(String.valueOf(or));
        });
        decCf.setOnClickListener(v -> {
           if (cf > 0){
               cf-=1;
           }
           else {
               cf = 0;
           }
            coffeeOrderCount.setText(String.valueOf(cf));
        });
        delKt.setOnClickListener(v -> {
            kt = 0;
            kitkatOrderCount.setText(String.valueOf(kt));
        });
        delOr.setOnClickListener(v -> {
            or = 0;
            oreoOrderCount.setText(String.valueOf(or));
        });
        delCf.setOnClickListener(v -> {
            cf = 0;
            coffeeOrderCount.setText(String.valueOf(cf));
        });

        resetBtn.setOnClickListener(v -> {
            kt = 0;
            kitkatOrderCount.setText(String.valueOf(kt));
            or = 0;
            oreoOrderCount.setText(String.valueOf(or));
            cf = 0;
            coffeeOrderCount.setText(String.valueOf(cf));
            orderDetails.setVisibility(View.INVISIBLE);
            viewOrderDetailsBtn.setVisibility(View.VISIBLE);
        });

        viewOrderDetailsBtn.setOnClickListener(v -> {
            userName = userNameInput.getText().toString();
            if (TextUtils.isEmpty(userName)){
                Toast.makeText(this, "Please enter you name first", Toast.LENGTH_LONG).show();
            }
            else {
                makeList();
            }

        });

        emailBtn.setOnClickListener(v -> {
            userName = userNameInput.getText().toString();
            if (TextUtils.isEmpty(userName)){
                Toast.makeText(this, "Please enter you name first", Toast.LENGTH_LONG).show();
            }
            else {
                makeList();
                String details = orderDetails.getText().toString();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pokarnah@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Topping's Orders");
                intent.putExtra(Intent.EXTRA_TEXT, details);
                startActivity(intent);
            }

        });
    }

    private void makeList(){
        viewOrderDetailsBtn.setVisibility(View.INVISIBLE);
        orderDetails.setVisibility(View.VISIBLE);
        orderDetails.setText("Name: " + userName + "\n");
        Formatter formatter = new Formatter();
        formatter.format("%d", kt);
        orderDetails.append("Kit-Kat: " + formatter.toString() + "\n");
        Formatter formatter1 = new Formatter();
        formatter1.format("%d", or);
        orderDetails.append("Oreo: " + formatter1.toString() + "\n");
        Formatter formatter2 = new Formatter();
        formatter2.format("%d", cf);
        orderDetails.append("Coffee: " + formatter2.toString() + "\n");
        int sum = kt*20 + or*30 + cf*40;
        orderDetails.append("The net amount is: " + sum);

    }

    private void initialiseFields() {
        userNameInput = findViewById(R.id.user_name);
        orderDetails = findViewById(R.id.order_details);
        kitkatOrderCount = findViewById(R.id.kitkat_order_count);
        oreoOrderCount = findViewById(R.id.oreo_order_count);
        coffeeOrderCount = findViewById(R.id.coffee_order_count);
        viewOrderDetailsBtn = findViewById(R.id.view_order_button);
        resetBtn = findViewById(R.id.reset_button);
        emailBtn = findViewById(R.id.email_button);
        incKt = findViewById(R.id.add_kitkat_order);
        incOr = findViewById(R.id.add_oreo_order);
        incCf = findViewById(R.id.add_coffee_order);
        decKt = findViewById(R.id.subtract_kitkat_order);
        decOr = findViewById(R.id.subtract_oreo_order);
        decCf = findViewById(R.id.subtract_coffee_order);
        delKt = findViewById(R.id.delete_kitkat);
        delOr = findViewById(R.id.delete_oreo);
        delCf = findViewById(R.id.delete_coffee);
        kt = Integer.parseInt(kitkatOrderCount.getText().toString());
        or = Integer.parseInt(oreoOrderCount.getText().toString());
        cf = Integer.parseInt(coffeeOrderCount.getText().toString());
    }
}