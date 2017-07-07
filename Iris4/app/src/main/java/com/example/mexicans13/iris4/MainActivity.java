package com.example.mexicans13.iris4;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{

    Button BotonFoto;
    ImageView Imagen;
    android.graphics.Bitmap Bitmap;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BotonFoto = (Button) findViewById(R.id.button1);
        Imagen = (ImageView) findViewById(R.id.imageView);

        if(!hasCamera()) //Desaparece el boton cuando se toma la imagen
            BotonFoto.setEnabled(false);
    }

    //Checa si el usuario captura la imagen
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Capturar imagen con el boton al darle click
    private void captureImage(View view){
        Intent pantalla = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Tomar la foto y el resultado de esta
        startActivityForResult(pantalla, REQUEST_IMAGE_CAPTURE);
    }

    //Si queremos retornar la imagen

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //Obtenemos la foto
            Bundle extra = data.getExtras();
            Bitmap bitmap = (Bitmap) extra.get("data");
            Imagen.setImageBitmap(bitmap);
        }
    }
}
