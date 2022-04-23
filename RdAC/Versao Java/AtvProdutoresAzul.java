
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.zxing.Result;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.util.List;

public class AtvProdutoresAzul extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton ibtnscan;
    CodeScanner codeScanner;
    CodeScannerView scannerView;
    TextView resultData;

    private UserProdAz userProdAz;

    // private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atv_produtores_azul);
        ibtnscan = (ImageButton) findViewById(R.id.ibtn_scan);
        // RecyclerView rv = findViewById(R.id.recyclerAzul);

        // resultData = findViewById(R.id.resultOfQr);

        // adapter = new GroupAdapter();
        // rv.setAdapter(adapter);
        // rv.setLayoutManager(new LinearLayoutManager(this));

        verifyAuthentication();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ibtnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v == ibtnscan) {
                    Intent intent = new Intent(AtvProdutoresAzul.this, ScannerFirst.class);
                    startActivity(intent);
                }

            }
        });

        // fetchUsersAz();
    }

    // @Override
    // protected void onActivityResult(int requestCode, int resultCode, @Nullable
    // Intent data) {
    // Intent intent = getIntent();
    //
    // if(data != null){
    // userProdAz = intent.getParcelableExtra("User Item");
    // if(codeScanner != null){
    // resultData.setText(userProdAz.getUuid());
    // String Uuid = userProdAz.getUuid();
    // intent = new Intent(AtvProdutoresAzul.this, CardViewAzulActivity.class);
    // intent.putExtra("User Item", Uuid).getData();
    // }
    //
    //
    // }
    //
    // super.onActivityResult(requestCode, resultCode, data);
    // }

    private void verifyAuthentication() {

        if (FirebaseAuth.getInstance().getUid() == null) {
            Intent intent = new Intent(AtvProdutoresAzul.this, LoginActivity.class);

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_blue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.produtores:
                Intent intent = new Intent(AtvProdutoresAzul.this, ContatosAzul.class);
                startActivity(intent);
                break;
            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                verifyAuthentication();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // private void fetchUsersAz() {
    // FirebaseFirestore.getInstance().collection("/users")
    // .addSnapshotListener(new EventListener<QuerySnapshot>() {
    // @Override
    // public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable
    // FirebaseFirestoreException e) {
    // if(e != null){
    // Log.e("Teste", e.getMessage(), e);
    // return;
    // }
    // List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
    // for(DocumentSnapshot doc: docs){
    // UserProdAz userProdAz = doc.toObject(UserProdAz.class);
    // Log.d("Teste", userProdAz.getUsername());
    // adapter.add(new UserAtvItem(userProdAz));
    // }
    // }
    // });
    //
    // }
    //
    // private class UserAtvItem extends Item<ViewHolder> {
    //
    // private final UserProdAz userProdAz;
    //
    // private UserAtvItem(UserProdAz userProdAz){
    // this.userProdAz = userProdAz;
    // }
    //
    //
    // @Override
    // public void bind(@NonNull ViewHolder viewHolder, int position) {
    // TextView username = viewHolder.itemView.findViewById(R.id.tv_nomeprod);
    // TextView zone = viewHolder.itemView.findViewById(R.id.tv_area);
    // TextView localidade = viewHolder.itemView.findViewById(R.id.tv_origem);
    // ImageView imgFoto = viewHolder.itemView.findViewById(R.id.imgPhotoAzul);
    //
    // username.setText(userProdAz.getUsername());
    // zone.setText(userProdAz.getZone());
    // localidade.setText(userProdAz.getLocalidade());
    //
    //
    // Picasso.get()
    // .load(userProdAz.getProfileUrl())
    // .into(imgFoto);
    //
    //
    // }
    //
    // @Override
    // public int getLayout() {
    // return R.layout.item_card_blue;
    // }
    // }

}