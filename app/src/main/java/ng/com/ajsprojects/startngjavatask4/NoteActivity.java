package ng.com.ajsprojects.startngjavatask4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NoteActivity extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    EditText txtTitle;
    EditText txtBody;
    DataModel note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        FirebaseUtil.openFbReference("notes");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        txtTitle = findViewById(R.id.txtTitle);
        txtBody = findViewById(R.id.txtBody);
        Intent intent = getIntent();
        DataModel note = (DataModel) intent.getSerializableExtra("Deal");
        if (note == null){
            note = new DataModel();
        }
        this.note = note;
        txtTitle.setText(note.getTitle());
        txtBody.setText(note.getBody());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_menu:
                saveNote();
                Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG).show();
                clean();
                backToList();
                return true;
            case R.id.delete_menu:
                deleteNote();
                Toast.makeText(this, "Note Deleted", Toast.LENGTH_LONG).show();
                backToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }


    // method to save notes
    private void saveNote(){
        note.setTitle(txtTitle.getText().toString());
        note.setBody(txtBody.getText().toString());
        if(note.getId() == null){
            mDatabaseReference.push().setValue(note);
        }
        else {
            mDatabaseReference.child(note.getId()).setValue(note);
        }
        mDatabaseReference.push().setValue(note);
    }

    private void deleteNote(){
        if (note == null){
            Toast.makeText(this, "Please save the note before deleting", Toast.LENGTH_SHORT).show();
            return;
        }
        mDatabaseReference.child(note.getId()).removeValue();
    }

    private void backToList(){
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    private void clean(){
        txtTitle.setText("");
        txtBody.setText("");
        txtTitle.requestFocus();
    }
}
