package com.example.sqllite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,email,msg;
    Button insert,update,delete,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        msg=findViewById(R.id.msg);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        view=findViewById(R.id.view);

        DbHelper DB=new DbHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sname=name.getText().toString();
                String semail=email.getText().toString();
                String smsg=msg.getText().toString();

                Boolean checkInsertData=DB.inserUserData(sname,semail,smsg);
                if(checkInsertData==true)
                {
                    Toast.makeText(MainActivity.this, "New Entry Data Inserted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sname=name.getText().toString();
                String semail=email.getText().toString();
                String smsg=msg.getText().toString();

                Boolean checkInsertData=DB.updateUserData(sname,semail,smsg);
                if(checkInsertData==true)
                {
                    Toast.makeText(MainActivity.this, "New Entry Data Updated", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sname=name.getText().toString();

                Boolean checkInsertData=DB.deleteUserData(sname);
                if(checkInsertData==true)
                {
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res=DB.viewData();
                if(res.getCount()==0)
                {
                    Toast.makeText(MainActivity.this, "there is no entry", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("name :"+res.getString(0)+"\n");
                    buffer.append("gmail :"+res.getString(1)+"\n");
                    buffer.append("msg :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Detail");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });
    }
}