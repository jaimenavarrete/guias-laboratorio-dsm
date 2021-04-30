package com.udb.dsm.decimasegundaapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private StudentsAdapter adaptador;

    Button btnAddStudent;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adaptador = new StudentsAdapter(this);

        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), AddStudentActivity.class);
            startActivity(intent);
        });

        list = findViewById(R.id.list);
        list.setAdapter(adaptador);

        retrieveStudents();
    }

    @Override
    protected void onResume() {
        super.onResume();

        retrieveStudents();
    }

    public void retrieveStudents() {
        String URL = "content://com.udb.dsm.decimasegundaapp/students";

        Uri students = Uri.parse(URL);
        Cursor c = getContentResolver().query(students, null, null, null, null);

        adaptador.swapCursor(c);
        adaptador.notifyDataSetChanged();
    }

    protected void activityAddStudent() {
        Intent intent = new Intent(getBaseContext(), AddStudentActivity.class);
        startActivity(intent);
    }
}

//public class MainActivity extends ListActivity implements
//        LoaderCallbacks<Cursor> {
//    private StudentsAdapter adaptador;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        adaptador = new StudentsAdapter(this);
//        setListAdapter(adaptador);
//        getLoaderManager().initLoader(0, null, this);
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        return new CursorLoader(
//                this,
//                StudentsContract.CONTENT_URI,
//                null, null, null, null);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        adaptador.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        adaptador.swapCursor(null);
//    }
//}