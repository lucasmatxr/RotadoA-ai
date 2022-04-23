package Corpo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ContatosAzul extends AppCompatActivity {

    // private ArrayList<UserProdAz> mListaContatos;
    //
    // private RecyclerView mRecyclerView;
    // private Adapter mAdapter;
    // private RecyclerView.LayoutManager mLayoutManager;

    private GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatos_azul);
        RecyclerView rv = findViewById(R.id.recyclerAzul);

        // criarListadeContatos();
        // construirRecyclerView();

        adapter = new GroupAdapter();
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(ContatosAzul.this, ScanQRcardAzActivity.class);
                UserProdAzItem userProdAzItem = (UserProdAzItem) item;
                intent.putExtra("User Item", userProdAzItem.userProdAz.getUuid());

                startActivity(intent);
                // REFAZER A CLASSE PRODUTADAPTER OU CRIAR OUTRA, BASEADO NOS VIDEOS:
                // Coding Café (Firebase Recycler Adapter onclick item listener - Handle item
                // clicks Android Studio Tutorial 31)
                // Coding in Flow (FirebaseUI Firestore RecyclerView Part 6 - ON CLICK INTERFACE
                // - Android Studio Tutorial)
            }
        });
        fetchUsers();

    }

    private void fetchUsers() {
        FirebaseFirestore.getInstance().collection("/users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                            @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.e("Teste", e.getMessage(), e);
                            return;
                        }
                        List<DocumentSnapshot> docs = queryDocumentSnapshots.getDocuments();
                        for (DocumentSnapshot doc : docs) {
                            UserProdAz userProdAz = doc.toObject(UserProdAz.class);
                            Log.d("Teste", userProdAz.getUsername());
                            adapter.add(new UserProdAzItem(userProdAz));
                        }
                    }
                });

    }

    private class UserProdAzItem extends Item<ViewHolder> {

        private final UserProdAz userProdAz;

        private UserProdAzItem(UserProdAz userProdAz) {
            this.userProdAz = userProdAz;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            TextView username = viewHolder.itemView.findViewById(R.id.tv_nomeprod);
            TextView zone = viewHolder.itemView.findViewById(R.id.tv_area);
            TextView localidade = viewHolder.itemView.findViewById(R.id.tv_origem);
            ImageView imgFoto = viewHolder.itemView.findViewById(R.id.imgPhotoAzul);

            username.setText(userProdAz.getUsername());
            zone.setText(userProdAz.getZone());
            localidade.setText(userProdAz.getLocalidade());

            Picasso.get()
                    .load(userProdAz.getProfileUrl())
                    .into(imgFoto);

        }

        @Override
        public int getLayout() {
            return R.layout.item_user_blue;
        }
    }

}

// private void criarListadeContatos() {
// mListaContatos = new ArrayList<>();
// mListaContatos.add(new UserProdAz(R.id.tv_nomeprod, R.id.tv_origem,
// R.id.tv_area));
//
// }
