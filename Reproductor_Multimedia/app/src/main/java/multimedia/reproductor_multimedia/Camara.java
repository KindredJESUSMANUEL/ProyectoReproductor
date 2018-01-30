package multimedia.reproductor_multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Camara extends AppCompatActivity {

    private CardView viewFotos;
    private CardView viewVideos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        viewFotos = (CardView) findViewById(R.id.viewFotos);
        viewVideos = (CardView) findViewById(R.id.viewVideos);

        viewFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Camara.this, Camara_de_fotos.class);
                startActivity(i);

            }
        });

        viewVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Camara.this, Grabadora_de_videos.class);
                startActivity(i);



            }
        });
    }
}
