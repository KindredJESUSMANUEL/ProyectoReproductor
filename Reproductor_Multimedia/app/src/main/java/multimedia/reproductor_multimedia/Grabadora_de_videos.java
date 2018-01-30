package multimedia.reproductor_multimedia;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Grabadora_de_videos extends AppCompatActivity {

    private static String APP_DIRECTORY = "MyVideApp/";
    private static String MEDIA_DIRECTORY = APP_DIRECTORY + "VideoApp";

    private final int MY_PERMISSIONS = 100;
    private final int VIDEO_CODE = 200;
    private final int SELECT_VIDEO = 300;

    private CardView viewHacerVideo;
    private VideoView videoViewGrabacion;
    private LinearLayout mRlView2;
    private MediaController mc;

    private String mPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabadora_de_videos);

        viewHacerVideo = (CardView) findViewById(R.id.viewHacerVideo);
        videoViewGrabacion = (VideoView) findViewById(R.id.videoViewGrabacion);
        mRlView2 = (LinearLayout) findViewById(R.id.mRlView2);

        if(mayRequestStoragePermission()){
            viewHacerVideo.setEnabled(true);
        }else{
            viewHacerVideo.setEnabled(false);
        }


        viewHacerVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOptions();
            }
        });
    }

    private boolean mayRequestStoragePermission() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                && (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED)){
            return true;
        }

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(mRlView2, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        }

        return false;

    }

    private void showOptions(){
        final CharSequence[] option = {"Grabar video", "Grabar video duracion establecida", "Grabar video tamaño establecido", "Elegir de galeria", "Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(Grabadora_de_videos.this);
        builder.setTitle("Elige una opción");
        builder.setItems(option, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(option[i] == "Grabar video") {
                    openCamera();
                }if(option[i] == "Grabar video duracion establecida") {
                    openCamera2();
                }if(option[i] == "Grabar video tamaño establecido"){
                    openCamera3();
                }if(option[i] == "Elegir de galeria") {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("video/*");
                    startActivityForResult(intent.createChooser(intent, "Selecciona app de video"), SELECT_VIDEO);
                }else{
                    dialogInterface.dismiss();
                }
            }
        });

        builder.show();
    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated){
            isDirectoryCreated = file.mkdirs();
        }

        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() /1000;
            String videoName = timestamp.toString() + ".mp4";

            mPath = Environment.getExternalStorageDirectory() +File.separator + MEDIA_DIRECTORY + File.separator+ videoName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            startActivityForResult(intent, VIDEO_CODE);

        }
    }

    private void openCamera2() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated){
            isDirectoryCreated = file.mkdirs();
        }

        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() /1000;
            String videoName = timestamp.toString() + ".mp4";

            mPath = Environment.getExternalStorageDirectory() +File.separator + MEDIA_DIRECTORY + File.separator+ videoName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 15);
            startActivityForResult(intent, VIDEO_CODE);

        }
    }

    private void openCamera3() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isDirectoryCreated = file.exists();

        if(!isDirectoryCreated){
            isDirectoryCreated = file.mkdirs();
        }

        if(isDirectoryCreated){
            Long timestamp = System.currentTimeMillis() /1000;
            String videoName = timestamp.toString() + ".mp4";

            mPath = Environment.getExternalStorageDirectory() +File.separator + MEDIA_DIRECTORY + File.separator+ videoName;

            File newFile = new File(mPath);

            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
            intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, "1048576");
            startActivityForResult(intent, VIDEO_CODE);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("file_path", mPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mPath = savedInstanceState.getString("file_path", mPath);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mc = new MediaController(this);

        if(resultCode == RESULT_OK){
            switch (requestCode){
                case VIDEO_CODE:
                    MediaScannerConnection.scanFile(this, new String[]{mPath}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("ExternalStorage", "Scanned" +path+":");
                            Log.i("ExternalStorage", "-> Uri = " + uri);
                        }
                    });

                    Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(mPath,MediaStore.Images.Thumbnails.MINI_KIND);

                    BitmapDrawable bitmapDrawable = new BitmapDrawable(thumbnail);
                    videoViewGrabacion.setBackgroundDrawable(bitmapDrawable);
                    videoViewGrabacion.setMediaController(mc);
                    videoViewGrabacion.setVideoPath(mPath);
                    videoViewGrabacion.requestFocus();

                    break;
                case SELECT_VIDEO:
                    Uri path = data.getData();
                    videoViewGrabacion.setVideoURI(path);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(Grabadora_de_videos.this, "Permisos aceptados", Toast.LENGTH_SHORT).show();
                viewHacerVideo.setEnabled(true);
            }
        }else{
            showExplanation();
        }
    }

    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Grabadora_de_videos.this);
        builder.setTitle("Permisos denegados");
        builder.setMessage("Para usar las funciones de la app necesitas aceptar los permisos");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });

        builder.show();
    }

}
