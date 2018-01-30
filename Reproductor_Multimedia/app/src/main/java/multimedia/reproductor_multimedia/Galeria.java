package multimedia.reproductor_multimedia;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Galeria extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<String> nombresArchivos;
    private List<String> rutasArchivos;
    private ArrayAdapter<String> adaptador;
    private String directorioRaiz;
    private TextView carpetaActual;
    private ListView listas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        carpetaActual = (TextView) findViewById(R.id.rutaActual);
        listas = (ListView) findViewById(R.id.listView_Lista);

        directorioRaiz = Environment.getExternalStorageDirectory().getPath();

        listas.setOnItemClickListener(this);
        verDirectorio(directorioRaiz);

    }

    private void verDirectorio(String rutaDirectorio){
        nombresArchivos = new ArrayList<String>();
        rutasArchivos = new ArrayList<String>();
        int count = 0;
        File directorioActual = new File(rutaDirectorio);
        File [] listaArchivos = directorioActual.listFiles();

        if(!rutaDirectorio.equals(directorioRaiz)){
            nombresArchivos.add("../");
            rutasArchivos.add(directorioActual.getParent());
            count = 1;
        }

        for (File archivo : listaArchivos){
            rutasArchivos.add(archivo.getPath());
        }

        Collections.sort(rutasArchivos, String.CASE_INSENSITIVE_ORDER);

        for(int i = count; i < rutasArchivos.size(); i++){
            File archivo = new File(rutasArchivos.get(i));
            if(archivo.isFile()){
                nombresArchivos.add(archivo.getName());
            }else{
                nombresArchivos.add("/" + archivo.getName());
            }
        }

        if (listaArchivos.length < 1){
            nombresArchivos.add("No hay ningun archivo");
            rutasArchivos.add(rutaDirectorio);
        }

        adaptador = new ArrayAdapter<String>(this,R.layout.lista_archivos, nombresArchivos);
        listas.setAdapter(adaptador);

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        File archivo = new File(rutasArchivos.get(i));
        if(archivo.isFile()){
            Toast.makeText(this,"Has seleccionado el archivo: " + archivo.getName(),
                    Toast.LENGTH_LONG).show();
        }else{
            verDirectorio(rutasArchivos.get(i));
        }
    }
}
