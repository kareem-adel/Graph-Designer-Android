package com.kareem_adel.graphdesigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout MapControlsContainer;
    Designer designer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapControlsContainer = (LinearLayout) findViewById(R.id.MapControlsContainer);
        designer = (Designer) findViewById(R.id.designer);
        MapControlsContainer.addView(designer.NodeX);
        MapControlsContainer.addView(designer.NodeY);
        MapControlsContainer.addView(designer.NodeName);
        MapControlsContainer.addView(designer.NodeTag);
        MapControlsContainer.addView(designer.RemoveNode);
    }
}
