package com.axiagroups.localnotification;
// part 1 https://www.youtube.com/watch?v=ub4_f6ksxL0
// part 2 https://youtu.be/j6kQ9gikU-A?si=CkyF9UrAe1l_ObZd
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText titleET, bodyET;
    private Button channel1Btn, channel2Btn;
    private NotificationHelper mNotificationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        titleET = findViewById(R.id.titleET);
        bodyET = findViewById(R.id.bodyET);
        channel1Btn = findViewById(R.id.channel1Btn);
        channel2Btn = findViewById(R.id.channel2Btn);
        mNotificationHelper = new NotificationHelper(this);


        channel1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotificationOnChannel1(titleET.getText().toString(), bodyET.getText().toString());
            }
        });
        channel2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotificationOnChannel2(titleET.getText().toString(), bodyET.getText().toString());
            }
        });
    }

    private void sendNotificationOnChannel2(String title, String body) {
        NotificationCompat.Builder nb = mNotificationHelper.getChannel2Notification(title, body);
        mNotificationHelper.getManager().notify(2,nb.build());
    }

    private void sendNotificationOnChannel1(String title, String body) {
        NotificationCompat.Builder nb = mNotificationHelper.getChannel1Notification(title, body);
        mNotificationHelper.getManager().notify(1,nb.build());
    }
}