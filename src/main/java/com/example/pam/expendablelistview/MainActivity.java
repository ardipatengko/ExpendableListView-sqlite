package com.example.pam.expendablelistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextAddress, editTextHobby;
    Button buttonAdd;
    ExpandableListView expandableListViewPerson;
    MyExpandableListAdapter adapter;
    final SparseArray<Group> groups = new SparseArray<Group>();
    List<Person> personList;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextHobby = (EditText) findViewById(R.id.editTextHobby);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        expandableListViewPerson = (ExpandableListView) findViewById(R.id.listViewPerson);

        dbHandler = new DBHandler(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHandler.addPerson(new Person(editTextName.getText().toString(),
                        editTextAddress.getText().toString(),
                        editTextHobby.getText().toString()));
                adapter.notifyDataSetChanged();
                Clear();
                GetAllData();
            }
        });

        GetAllData();
    }

    public void GetAllData(){
        personList = dbHandler.getAllPerson();
        for (int i = 0; i < personList.size(); i++){
            Group group = new Group("Person Data " + (i + 1));
            group.children.add(String.valueOf(personList.get(i).getId()));
            group.children.add(String.valueOf(personList.get(i).getName()));
            group.children.add(String.valueOf(personList.get(i).getAddress()));
            group.children.add(String.valueOf(personList.get(i).getHobby()));
            groups.append(i, group);
        }

        adapter = new MyExpandableListAdapter(this, groups);
        expandableListViewPerson.setAdapter(adapter);
    }

    public void Clear(){
        editTextName.setText("");
        editTextAddress.setText("");
        editTextHobby.setText("");
    }
}
