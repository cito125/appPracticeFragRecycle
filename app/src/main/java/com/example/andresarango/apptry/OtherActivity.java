package com.example.andresarango.apptry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {
    private TextView startMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);
        startMain = (TextView) findViewById(R.id.start);

        startMain.setOnClickListener(onClick(this));

    }

    private View.OnClickListener onClick(final OtherActivity otherActivity) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(otherActivity,MainActivity.class);
                view.getContext().startActivity(intent);
            }
        };
    }
}
