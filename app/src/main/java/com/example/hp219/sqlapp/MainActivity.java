package com.example.hp219.sqlapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    MyDBHelper dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        dbHandler=new MyDBHelper(this,null,null,1);
        printDatabase();

    }
    public void printDatabase()
    {
        String dbString=dbHandler.databaseToString();
        tv.setText(dbString);
        et.setText("");

    }
    public void onClickAdd(View view)
    {
        Products products=new Products(et.getText().toString());
        dbHandler.addProducts(products);
        printDatabase();
    }
    public void onClickDelete()
    {
        String inputText=et.getText().toString();
        dbHandler.delete(inputText);
        printDatabase();
    }

}
