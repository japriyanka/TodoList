package com.example.hp.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText et;
    private Button btn;
    private ListView lv;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.edit_text);
        btn = findViewById(R.id.add_btn);
        lv = findViewById(R.id.list_item);
        items = FileHelper.readData(this);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        lv.setAdapter(adapter);
        btn.setOnClickListener(this);
        lv.setOnItemClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.add_btn:
                String itemEntered = et.getText().toString();
                adapter.add(itemEntered);
               FileHelper.writeData(items,this);
                et.setText("");


                Toast.makeText(this,"Item Added", Toast.LENGTH_SHORT).show();
                break;


        }


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
       FileHelper.writeData(items,this);
        Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();

    }
}
