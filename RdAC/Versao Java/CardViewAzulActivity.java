package com.example.mynavapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynavapp.databinding.ActivityCardViewVerdeBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.DatabaseId;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.ViewHolder;

import java.text.BreakIterator;
import java.util.List;

public class CardViewAzulActivity extends AppCompatActivity {

    TextView Nome, Zona, Origem;
    ImageView imgFoto;

    private UserProdAz userProdAz;
    private GroupAdapter adapter;

    private AppBarConfiguration appBarConfiguration;
    private ActivityCardViewVerdeBinding binding;

    FirebaseFirestore dbUserAzul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_azul);

        adapter = new GroupAdapter();

        Intent intent = getIntent();
        userProdAz = intent.getParcelableExtra("User Item");
        getSupportActionBar().setTitle(userProdAz.getUsername());

        String ProfileUrl = userProdAz.getProfileUrl();
        String Username = userProdAz.getUsername();
        String Zone = userProdAz.getZone();
        String Local = userProdAz.getLocalidade();

        Nome = findViewById(R.id.tv_card_name);
        Zona = findViewById(R.id.tv_card_area);
        Origem = findViewById(R.id.tv_card_origem);
        imgFoto = findViewById(R.id.iv_card_photo);

        Nome.setText(Username);
        Zona.setText(Zone);
        Origem.setText(Local);
        imgFoto.setImageResource(Integer.parseInt(ProfileUrl));

        binding = ActivityCardViewVerdeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        // showAllUserData();

        // fetchUsersAz();

    }

    // private void showAllUserData() {
    //
    // Intent intent = getIntent();
    // userProdAz = intent.getParcelableExtra("User Item");
    // String username = intent.getStringExtra("username");
    // String zone = intent.getStringExtra("zone");
    // String localidade = intent.getStringExtra("localidade");
    // String ProfileUrl = intent.getStringExtra("imgFoto");
    //
    // Nome.setText(username);
    // Zona.setText(zone);
    // Origem.setText(localidade);
    // Nome.setText(ProfileUrl);
    //
    // }

    // private void fetchUsersAz() {
    // CollectionReference collectionReference = dbUserAzul.collection("/users");
    // collectionReference.get().addOnSuccessListener(new
    // OnSuccessListener<QuerySnapshot>() {
    // @Override
    // public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
    // if (queryDocumentSnapshots.equals(collectionReference)))
    // {
    // Nome.setText(queryDocumentSnapshots.getDocuments();
    // Zona.setText(documentSnapshot.getString("Nome"));
    // Origem.setText(documentSnapshot.getString("Nome"));
    // imgFoto.setImageResource(Integer.parseInt(documentSnapshot.getString("Nome")));
    // } else
    // Toast.makeText(getApplicationContext(), "Usuario nao encontardo",
    // Toast.LENGTH_LONG).show();
    //
    // }
    //
    // @Override
    // public void onSuccess(DocumentSnapshot documentSnapshot) {
    // if (documentSnapshot.exists())
    // {
    // Nome.setText(documentSnapshot.getString("Nome"));
    // Zona.setText(documentSnapshot.getString("Nome"));
    // Origem.setText(documentSnapshot.getString("Nome"));
    // imgFoto.setImageResource(Integer.parseInt(documentSnapshot.getString("Nome")));
    // } else
    // Toast.makeText(getApplicationContext(), "Usuario nao encontardo",
    // Toast.LENGTH_LONG).show();
    // }
    // })
    // .addOnFailureListener(new OnFailureListener() {
    // @Override
    // public void onFailure(@NonNull Exception e) {
    // Toast.makeText(getApplicationContext(), "Falha ao buscar o usuario",
    // Toast.LENGTH_LONG).show();
    // }
    // });
    // }

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
    // return R.layout.activity_card_view_azul;
    // }
    // }

}