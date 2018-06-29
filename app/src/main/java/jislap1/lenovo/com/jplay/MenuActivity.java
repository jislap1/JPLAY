package jislap1.lenovo.com.jplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import jislap1.lenovo.com.jplay.Objetos.Usuario;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView nombre;
    private TextView correo;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private String NOMBRE_USUARIO;
    private String CORREO_USUARIO;
    ImageButton BotonHobbit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BotonHobbit = (ImageButton) findViewById(R.id.p_elhobbit);
        BotonHobbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity.this, DetallePeliculaActivity.class);
                startActivity(i);
            }
        });

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        final View navView = navigationView.inflateHeaderView(R.layout.nav_header_menu);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            DatabaseReference reference = database.getReference("Usuarios/"+currentUser.getUid());
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    NOMBRE_USUARIO = usuario.getNombre();
                    CORREO_USUARIO = usuario.getCorreo();
                    nombre = (TextView) navView.findViewById(R.id.nombre);
                    correo = (TextView) navView.findViewById(R.id.correo);
                    nombre.setText(NOMBRE_USUARIO);
                    correo.setText(CORREO_USUARIO);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else{
            returnLogin();
        }
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void returnLogin(){
        startActivity(new Intent(MenuActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else if (id == R.id.nav_accion) {

        } else if (id == R.id.nav_comedia) {

        } else if (id == R.id.nav_drama) {

        } else if (id == R.id.nav_terror) {

        } else if (id == R.id.nav_cerrar) {
            FirebaseAuth.getInstance().signOut();
            returnLogin();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
