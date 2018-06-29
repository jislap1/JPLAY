package jislap1.lenovo.com.jplay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import jislap1.lenovo.com.jplay.Objetos.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText txtNombres,txtCorreo,txtContraseña,txtConContraseña;
    private Button botonRegistrar;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombres = (EditText) findViewById(R.id.edtNombres);
        txtCorreo = (EditText) findViewById(R.id.edtCorreo);
        txtContraseña = (EditText) findViewById(R.id.edtContraseña);
        txtConContraseña = (EditText) findViewById(R.id.edtConContraseña);
        botonRegistrar = (Button) findViewById(R.id.btnRegistrar);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombre = txtNombres.getText().toString();
                final String correo = txtCorreo.getText().toString();
                final String contraseña = txtContraseña.getText().toString();
                progressDialog.setMessage("Realizando registro en linea...");
                progressDialog.show();
                if(isValidEmail(correo) && validarContraseña() && validarNombre(nombre)){
                    mAuth.createUserWithEmailAndPassword(correo, contraseña)
                            .addOnCompleteListener(RegistroActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegistroActivity.this, "Se registró correctamente.", Toast.LENGTH_SHORT).show();
                                        Usuario usuario = new Usuario();
                                        usuario.setNombre(nombre);
                                        usuario.setCorreo(correo);
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
                                        reference.setValue(usuario);
                                        Intent o = new Intent(getApplication(), MainActivity.class);
                                        startActivity(o);
                                        finish();
                                    } else {
                                        Toast.makeText(RegistroActivity.this, "Error al registrarse.", Toast.LENGTH_SHORT).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }else{
                    Toast.makeText(RegistroActivity.this, "Validaciones funcionando.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public boolean validarContraseña(){
        String contraseña,concontraseña;
        contraseña = txtContraseña.getText().toString();
        concontraseña = txtConContraseña.getText().toString();
        if(contraseña.equals(concontraseña)){
            if(contraseña.length()>=6 && contraseña.length()<=16){
                return true;
            }else return false;
        }else return false;
    }

    public boolean validarNombre(String nombre){
        return !nombre.isEmpty();
    }
}
