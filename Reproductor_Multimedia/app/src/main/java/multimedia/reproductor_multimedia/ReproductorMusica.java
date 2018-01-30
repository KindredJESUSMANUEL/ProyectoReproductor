package multimedia.reproductor_multimedia;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReproductorMusica extends AppCompatActivity {

    private Button button_pause;
    private Button button_stop;
    private Button button_continue;
    private Button button_play_app;
    private Button button_play_alm;
    private Button button_play_int;


    private MediaPlayer mp;
    private MediaRecorder mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor_musica);

        button_pause = findViewById(R.id.button_pause);
        button_stop = findViewById(R.id.button_stop);



        button_pause.setEnabled(false);
        button_stop.setEnabled(false);
    }
    
}
