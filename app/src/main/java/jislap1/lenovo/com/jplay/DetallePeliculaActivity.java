package jislap1.lenovo.com.jplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetallePeliculaActivity extends AppCompatActivity {
    ImageButton BtnHobbit;
    TextView ptitulo, pedad, ptiempo, pdescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        ptitulo = (TextView) findViewById(R.id.titulo);
        pedad = (TextView) findViewById(R.id.edad);
        ptiempo = (TextView) findViewById(R.id.tiempo);
        pdescripcion = (TextView) findViewById(R.id.descripcion);

        BtnHobbit = (ImageButton) findViewById(R.id.btn_peli);
        BtnHobbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetallePeliculaActivity.this, VideoActivity.class);
                startActivity(i);
            }
        });

        ptitulo.setText("El Hobbit: Un Viaje Inesperado");
        pedad.setText("+14");
        ptiempo.setText("2h 49min");
        pdescripcion.setText("Precuela de la trilogía “El Señor de los Anillos”, obra de J.R.R. Tolkien. En compañía del mago Gandalf y de trece enanos, el hobbit Bilbo Bolsón emprende un viaje a través del país de los elfos y los bosques de los trolls, desde las mazmorras de los orcos hasta la Montaña Solitaria, donde el dragón Smaug esconde el tesoro de los Enanos. Finalmente, en las profundidades de la Tierra, encuentra el Anillo Único, hipnótico objeto que será posteriormente causa de tantas sangrientas batallas en la Tierra Media.");
    }
}
