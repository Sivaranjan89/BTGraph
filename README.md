# BTGraph
Showcase your data with a neat Bar Graph
Use this library to design a Bar Graph. Feel free to input any values while the library takes care of the percentage and other calculations for you.
Available Graphs are,
1) Single Bar Horizontal Graph
2) Multi Bar Horizontal Graph
1) Single Bar Vertical Graph
2) Multi Bar Vertical Graph

# How to Install Plugin
Add the below in your root build.gradle(project) at the end of repositories:<br />
<b>allprojects { </b><br />
<b>repositories { </b><br />
<b>... </b><br />
<b>maven { url 'https://jitpack.io' } </b><br />
<b>} </b><br />
<b>} </b><br />
            
Add the dependency in build.gradle(module) : <br />
<b>dependencies { </b><br />
<b>implementation 'com.github.Sivaranjan89:BTGraph:1.0'</b><br />
<b>}</b><br />


# Sample Screenshot
![three](https://user-images.githubusercontent.com/54542325/64919007-51a6ef00-d7c3-11e9-9631-074ca9763d23.png)


# Sample Usage
## Single Bar Horizontal
<com.droid.uigraph.BarGraph</br>
        android:id="@+id/bar"</br>
        android:layout_width="wrap_content"</br>
        android:layout_height="wrap_content"</br>
        android:layout_gravity="center"</br>
        android:layout_marginTop="20dp"</br>
        app:barWidth="250dp"</br>
        app:barHeight="50dp"</br>
        app:barRadius="5dp"</br>
        app:showLegend="true"</br>
        app:shouldBounceAnimate="true"</br>
        app:animationDuration="1500"</br>
        app:singleBar="true"</br>
        app:graphOrientation="horizontal"</br>
        /></br>
        
## Multi Bar Horizontal
<com.droid.uigraph.BarGraph</br>
        android:id="@+id/bar"</br>
        android:layout_width="wrap_content"</br>
        android:layout_height="wrap_content"</br>
        android:layout_gravity="center"</br>
        android:layout_marginTop="20dp"</br>
        app:barWidth="250dp"</br>
        app:barHeight="50dp"</br>
        app:barRadius="5dp"</br>
        app:showLegend="true"</br>
        app:shouldBounceAnimate="true"</br>
        app:animationDuration="1500"</br>
        app:singleBar="false"</br>
        app:graphOrientation="horizontal"</br>
        /></br>
        
## Single Bar Vertical
<com.droid.uigraph.BarGraph</br>
        android:id="@+id/bar"</br>
        android:layout_width="wrap_content"</br>
        android:layout_height="wrap_content"</br>
        android:layout_gravity="center"</br>
        android:layout_marginTop="20dp"</br>
        app:barWidth="250dp"</br>
        app:barHeight="50dp"</br>
        app:barRadius="5dp"</br>
        app:showLegend="true"</br>
        app:shouldBounceAnimate="true"</br>
        app:animationDuration="1500"</br>
        app:singleBar="true"</br>
        app:graphOrientation="vertical"</br>
        /></br>
        
## Multi Bar Vertical
<com.droid.uigraph.BarGraph</br>
        android:id="@+id/bar"</br>
        android:layout_width="wrap_content"</br>
        android:layout_height="wrap_content"</br>
        android:layout_gravity="center"</br>
        android:layout_marginTop="20dp"</br>
        app:barWidth="250dp"</br>
        app:barHeight="50dp"</br>
        app:barRadius="5dp"</br>
        app:showLegend="true"</br>
        app:shouldBounceAnimate="true"</br>
        app:animationDuration="1500"</br>
        app:singleBar="false"</br>
        app:graphOrientation="vertical"</br>
        /></br>
