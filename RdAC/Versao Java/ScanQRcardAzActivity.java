package Corpo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ScanQRcardAzActivity extends AppCompatActivity {
    TextView Nomeuser, Zonacode, Localcode;
    ImageView ImageUse;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcard_az);
        Nomeuser = findViewById(R.id.nomeuser);
        Zonacode = findViewById(R.id.zonacode);
        Localcode = findViewById(R.id.localcode);
        ImageUse = findViewById(R.id.imageperfil);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                Nomeuser.setText(value.getString("username"));
                Zonacode.setText(value.getString("zone"));
                Localcode.setText(value.getString("localidade"));

            }
        });

    }
}