package com.example.firstdbapp;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
String fname,lname,email;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=openOrCreateDatabase("MYDB1",MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student (id INTEGER  PRIMARY KEY  AUTOINCREMENT,fname VARCHAR,lname VARCHAR,email VARCHAR);");
       
    }    

    public void Adddata(View view)
    {
    	EditText edittext1=(EditText) findViewById(R.id.Textfirstname);
    	EditText edittext2=(EditText) findViewById(R.id.Textlastname);
    	EditText edittext3=(EditText) findViewById(R.id.Textemail);
    	fname=edittext1.getText().toString();
    	lname=edittext2.getText().toString();
    	email=edittext3.getText().toString();
    	db.execSQL("INSERT INTO student (fname,lname,email) VALUES ('"+fname+"','"+lname+"','"+email+"');");
    	
    	Toast.makeText(this, "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
    	
    	edittext1.setText("");
    	edittext2.setText("");
    	edittext3.setText("");
    }
    
    public void showdata(View view)
    {
        Cursor c=db.rawQuery("SELECT * from student", null);
         int count= c.getCount();
        c.moveToFirst();
        TableLayout tableLayout = new TableLayout(getApplicationContext());
        tableLayout.setVerticalScrollBarEnabled(true);
       TableRow tableRow;
       TextView textView,textView1,textView2,textView3,textView4,textView5;
       tableRow = new TableRow(getApplicationContext());
       textView=new TextView(getApplicationContext());
       textView.setText("Firstname");
       textView.setTextColor(Color.RED);
      	textView.setTypeface(null, Typeface.BOLD);
      	 textView.setPadding(20, 20, 20, 20);
      	tableRow.addView(textView);
        textView4=new TextView(getApplicationContext());
      	textView4.setText("LastName");
      	textView4.setTextColor(Color.RED);
      	textView4.setTypeface(null, Typeface.BOLD);
      	 textView4.setPadding(20, 20, 20, 20);
      	tableRow.addView(textView4);
        textView5=new TextView(getApplicationContext());
      	textView5.setText("Email");
      	textView5.setTextColor(Color.RED);
      	textView5.setTypeface(null, Typeface.BOLD);
      	textView5.setPadding(20, 20, 20, 20);
      	tableRow.addView(textView5);
       tableLayout.addView(tableRow);
         for (Integer j = 0; j < count; j++)
         {
             tableRow = new TableRow(getApplicationContext());
             textView1 = new TextView(getApplicationContext());
             textView1.setText(c.getString(c.getColumnIndex("fname")));
             textView1.setTextColor(Color.BLACK);
             textView2 = new TextView(getApplicationContext());
             textView2.setText(c.getString(c.getColumnIndex("lname")));
             textView2.setTextColor(Color.BLACK);
             textView3 = new TextView(getApplicationContext());
             textView3.setText(c.getString(c.getColumnIndex("email")));
             textView3.setTextColor(Color.BLACK);
             textView1.setPadding(20, 20, 20, 20);
             textView2.setPadding(20, 20, 20, 20);
             textView3.setPadding(20, 20, 20, 20);
             tableRow.addView(textView1);
             tableRow.addView(textView2);
             tableRow.addView(textView3);
             tableLayout.addView(tableRow);
             c.moveToNext() ;
         }
         setContentView(tableLayout);
    db.close();
    }
    public void close(View view)
    {
    System.exit(0);	
    }
}
