package multimedia.reproductor_multimedia;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class Visualizar_video extends AppCompatActivity {

    private CardView viewElegirVideo;
    private CardView viewReproducirVideo;
    private EditText editTextExaminar;
    private VideoView videoViewVideo;

    private final int PICKER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_video);

        viewElegirVideo = (CardView) findViewById(R.id.viewElegirVideo);
        viewReproducirVideo = (CardView) findViewById(R.id.viewReproducirVideo);
        editTextExaminar = (EditText) findViewById(R.id.editTextExaminar);
        videoViewVideo = (VideoView) findViewById(R.id.videoViewVideo);

        viewElegirVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickFile();
            }
        });
        videoViewVideo.setMediaController(new MediaController(this));
        viewReproducirVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UrlFile = editTextExaminar.getText().toString();
                Log.e("ruta",UrlFile);
                videoViewVideo.setVideoPath(UrlFile);
                videoViewVideo.start();
                videoViewVideo.requestFocus();
            }
        });

    }

    private void PickFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try{
            startActivityForResult(Intent.createChooser(intent,"Selecciones un archivo de video"),PICKER);
        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(this, "Por favor, instale un administrador de archivos.", Toast.LENGTH_SHORT).show();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case PICKER:
                if(resultCode == RESULT_OK){
                    String FilePath = data.getData().getPath();

                    Log.e("f",data.toString());
                    editTextExaminar.setText(FilePath);
                }
                break;
        }
    }

}
