package com.example.smallpetalarm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class Vaccine_info extends AppCompatActivity {

    Button calBtn, mapBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.vaccine_info);


        calBtn = findViewById(R.id.calBtn);
        mapBtn = findViewById(R.id.mapBtn);


        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EB%B0%98%EB%A0%A4%EB%8F%99%EB%AC%BC+%E" +
                                "C%98%88%EB%B0%A9+%EC%A0%91%EC%A2%85%EC%9D%BC+%EA%B3%84%EC%82%B0%EA%B8%B0&oquery=%EA%B0%95%EC%95%84%EC%A7%80+%EC%98%88%EB%B0%A9%EC%A0%91%EC%A2%85&tqi=hjTxXsp0JXosslw74Wossssst28-400188"));
                startActivity(intent);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=동물병원"));
                startActivity(intent);
            }

        });



    }

}