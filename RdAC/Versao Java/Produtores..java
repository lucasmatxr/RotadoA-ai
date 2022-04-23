package corpo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;

public class Produtores extends AppCompatActivity {
    ImageButton BotaoAzul, BotaoLaranja, BotaonVerde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtores);
        BotaoAzul = findViewById(R.id.ibtnazul2);
        BotaoLaranja = (ImageButton) findViewById(R.id.ibtnlaranja2);
        BotaonVerde = (ImageButton) findViewById(R.id.ibtnverde2);
        //
        BotaoAzul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Produtores.this, LoginActivity.class);
                startActivity(intent);

                // FragmentTransaction fragmentTransaction =
                // getSupportFragmentManager().beginTransaction();
                // fragmentTransaction.replace(R.id.mainprodutor, new AzulFragment()).commit();

            }
        });

        BotaoLaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Produtores.this, LoginActivityLaranja.class);
                startActivity(intent);

                // FragmentTransaction fragmentTransaction =
                // getSupportFragmentManager().beginTransaction();
                // fragmentTransaction.replace(R.id.mainprodutor, new
                // LaranjaFragment()).commit();

            }
        });

        BotaonVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Liberar o setVisibily quando direcionar o frangment para a view da activity
                // produtores
                // BotaoAzul.setVisibility(View.GONE);
                // BotaoLaranja.setVisibility(View.GONE);
                // BotaonVerde.setVisibility(View.GONE);
                // FragmentTransaction fragmentTransaction =
                // getSupportFragmentManager().beginTransaction();
                // fragmentTransaction.replace(R.id.mainprodutor, new VerdeFragment()).commit();
                Intent intent = new Intent(Produtores.this, LoginActivityVerde.class);
                startActivity(intent);

            }
        });

    }

}