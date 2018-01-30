package multimedia.reproductor_multimedia;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class Microfono extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private CardView viewGrabarAudio;
    private CardView viewDetenerGrabacion;
    private CardView viewReproducirAudio;
    private CardView viewDetenerAudio;
    private MediaRecorder recorder;
    private MediaPlayer player;
    private File archivo;
    private ImageView imageViewDisco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_microfono);

        viewGrabarAudio = (CardView) findViewById(R.id.viewGrabarAudio);
        viewDetenerGrabacion = (CardView) findViewById(R.id.viewDetenerGrabacion);
        viewReproducirAudio = (CardView) findViewById(R.id.viewReproducirAudio);
        viewDetenerAudio = (CardView) findViewById(R.id.viewDetenerAudio);
        imageViewDisco = findViewById(R.id.imageViewDisco);


    }

    public void grabar(View v) {
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        File path = new File(Environment.getExternalStorageDirectory()
                .getPath());
        try {
            archivo = File.createTempFile("audio", ".3gp", path);
        } catch (IOException e) {
        }
        recorder.setOutputFile(archivo.getAbsolutePath());
        try {
            recorder.prepare();
        } catch (IOException e) {
        }
        recorder.start();

        viewGrabarAudio.setEnabled(false);
        viewDetenerGrabacion.setEnabled(true);
        viewReproducirAudio.setEnabled(false);
        viewDetenerAudio.setEnabled(false);
    }

    public void detener(View v) {
        recorder.stop();
        recorder.release();
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        try {
            player.setDataSource(archivo.getAbsolutePath());
        } catch (IOException e) {
        }
        try {
            player.prepare();
        } catch (IOException e) {
        }
        viewGrabarAudio.setEnabled(true);
        viewDetenerGrabacion.setEnabled(false);
        viewReproducirAudio.setEnabled(true);
        viewDetenerAudio.setEnabled(false);

    }

    public void reproducir(View v) {
        player.start();
        viewGrabarAudio.setEnabled(false);
        viewDetenerGrabacion.setEnabled(false);
        viewReproducirAudio.setEnabled(false);
        viewDetenerAudio.setEnabled(true);
    }

    public void parar(View v){
        player.stop();
        viewGrabarAudio.setEnabled(false);
        viewDetenerGrabacion.setEnabled(false);
        viewReproducirAudio.setEnabled(true);
        viewDetenerAudio.setEnabled(false);
    }

    public void animacion(View v){
        Animation rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.rotate);
        rotateAnimation.reset();
        imageViewDisco.startAnimation(rotateAnimation);
    }

    public void onCompletion(MediaPlayer mp) {
        viewGrabarAudio.setEnabled(true);
        viewDetenerGrabacion.setEnabled(true);
        viewReproducirAudio.setEnabled(true);
        viewDetenerAudio.setEnabled(true);
    }

}
