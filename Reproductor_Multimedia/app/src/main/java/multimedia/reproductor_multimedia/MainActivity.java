package multimedia.reproductor_multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CardView viewMusica;
    private CardView viewVideo;
    private CardView viewMicrofono;
    private CardView viewGaleria;
    private CardView viewCamara;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewMusica = (CardView) findViewById(R.id.viewMusica);
        viewMicrofono = (CardView) findViewById(R.id.viewMicrofono);
        viewVideo = (CardView) findViewById(R.id.viewVideo);
        viewGaleria = (CardView) findViewById(R.id.viewGaleria);
        viewCamara = (CardView) findViewById(R.id.viewCamara);

        viewMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ReproductorMusica.class);
                startActivity(i);

            }
        });

        viewMicrofono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Microfono.class);
                startActivity(i);



            }
        });

        viewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Visualizar_video.class);
                startActivity(i);



            }
        });

        viewGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Galeria.class);
                startActivity(i);



            }
        });

        viewCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Camara.class);
                startActivity(i);

            }
        });

    }
}
