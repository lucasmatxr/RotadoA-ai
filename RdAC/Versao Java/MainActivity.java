package Corpo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton BotaoAzul, BotaoLaranja, BotaonVerde;
    ImageView imgRotaMDR, imgMDR, imgUfpa;
    // Home, Produtores, Coopeartiva, Empresa;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Home = findViewById(R.id.home);
        // Produtores = findViewById(R.id.produtores);
        // Coopeartiva = findViewById(R.id.cooperativas);

        BotaoAzul = (ImageButton) findViewById(R.id.ibtnazul2);
        BotaoLaranja = (ImageButton) findViewById(R.id.ibtnlaranja2);
        BotaonVerde = (ImageButton) findViewById(R.id.ibtnverde2);

        imgRotaMDR = (ImageView) findViewById(R.id.logo_rota_acai);
        imgMDR = (ImageView) findViewById(R.id.img_mdr);
        imgUfpa = (ImageView) findViewById(R.id.img_ufpa);

        imgRotaMDR.animate().alpha(1f).setDuration(3000);
        imgMDR.animate().alpha(1f).setDuration(2000);
        imgUfpa.animate().alpha(1f).setDuration(2000);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.home);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        // BotaoAzul.setOnClickListener(new View.OnClickListener() {
        // @Override
        // public void onClick(View v) {
        // if (v == BotaoAzul){
        // Intent intent = new Intent(MainActivity.this, Produtores.class);
        // startActivity(intent);
        // }
        // }
        // });

        // Coopeartiva.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        // {
        // @Override
        // public boolean onMenuItemClick(MenuItem item) {
        // if (item == Coopeartiva){
        // Intent intent = new Intent(MainActivity.this, Produtores.class);
        // startActivity(intent);
        // }
        // return true;
        // }
        // });

    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                break;
            case R.id.produtores:
                Intent intent = new Intent(MainActivity.this, Produtores.class);
                startActivity(intent);
                break;
            case R.id.cooperativas:
                intent = new Intent(MainActivity.this, Cooperativa.class);
                startActivity(intent);
                break;
            case R.id.empresa:
                intent = new Intent(MainActivity.this, Empresa.class);
                startActivity(intent);
                break;
            case R.id.Quem_Somos:
                Toast.makeText(this, "Quem Somos", Toast.LENGTH_SHORT).show();
                break;
            // case R.id.Cliente:
            // intent = new Intent(MainActivity.this, FormularioClientes.class);
            // startActivity(intent);
            // break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}