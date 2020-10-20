package device.docreaderlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import device.s.docreader.office.constant.MainConstant;
import device.s.docreader.office.officereader.AppActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPath(View view) {

        Intent intent = new Intent(this, AppActivity.class);
        intent.putExtra(MainConstant.INTENT_FILED_FILE_PATH, "/storage/emulated/0/Gold Rates App.docx");
        startActivity(intent);
    }
}