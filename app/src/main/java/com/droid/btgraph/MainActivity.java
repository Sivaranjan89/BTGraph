package com.droid.btgraph;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.droid.uigraph.BarGraph;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarGraph graph = findViewById(R.id.bar);
        graph.setValues(250, Color.GREEN, "Green");
        graph.setValues(400, Color.YELLOW, "Yellow");
        graph.setValues(900, Color.RED, "Red");
        graph.render();


        BarGraph graph2 = findViewById(R.id.bar2);
        graph2.setValues(250, Color.GREEN, "Green");
        graph2.setValues(400, Color.YELLOW, "Yellow");
        graph2.setValues(900, Color.RED, "Red");
        graph2.render();


        BarGraph graph3 = findViewById(R.id.bar3);
        graph3.setValues(250, Color.GREEN, "Green");
        graph3.setValues(400, Color.YELLOW, "Yellow");
        graph3.setValues(900, Color.RED, "Red");
        graph3.render();


        BarGraph graph4 = findViewById(R.id.bar4);
        graph4.setValues(250, Color.GREEN, "Green");
        graph4.setValues(400, Color.YELLOW, "Yellow");
        graph4.setValues(900, Color.RED, "Red");
        graph4.render();
    }
}
