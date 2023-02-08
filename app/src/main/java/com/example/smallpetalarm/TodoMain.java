package com.example.smallpetalarm;

import android.app.AlarmManager;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TodoMain extends AppCompatActivity {
    private static final String TAG = "TodoMain";

    Context context;
    Fragment mainFragment;
    EditText inputTodo;

    public static NoteDatabase noteDatabase = null;

    private static final String NOTIFICATION_CHANNEL_ID = "primary_notification_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);


        mainFragment = new MainFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();

        Button saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDo();

                Toast.makeText(getApplicationContext(), "추가되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        openDatabase();
    }

    public void saveToDo() {

        inputTodo = findViewById(R.id.inputToDo);
        String todo = inputTodo.getText().toString();

        String sqlSave = "insert into " + NoteDatabase.TABLE_NOTE + " (TODO) values (" +
                "'" + todo + "')";

        NoteDatabase database = NoteDatabase.getInstance(context);
        database.execSQL(sqlSave);

        inputTodo.setText("");
    }

    public void openDatabase() {
        if(noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }

        noteDatabase = NoteDatabase.getInstance(this);
        boolean isOpen = noteDatabase.open();

        if(isOpen) {
            Log.d(TAG, "Database is open");
        }

        else {
            Log.d(TAG, "Database is not open");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(noteDatabase != null) {
            noteDatabase.close();
            noteDatabase = null;
        }
    }

    public void onTimeSet() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 17);
        c.set(Calendar.MINUTE, 31);
        c.set(Calendar.SECOND, 0);

        startAlarm(c);
    }

    private void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent,0);

        if(c.before(Calendar.getInstance())){
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }




}
