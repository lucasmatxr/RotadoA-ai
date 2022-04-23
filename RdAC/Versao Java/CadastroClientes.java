
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.content.FileProvider;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class CadastroClientes extends AppCompatActivity {

    private EditText edtNome, edtZona, edtLocal, edtFone, edtEmail, edtSenha;
    private ImageView imgUser;
    private Button btnCad;
    private ImageButton btnCamera;

    // private FormContHelper helper;
    // private ContatoProdutores contatoProdutores;

    private String localArquivoFoto;
    private static final int TIRA_FOTO = 123;
    private boolean fotoResource = false;
    private ImageView ImgPhoto;

    private Bitmap bitmap;
    ImageView imagemContato;
    private Uri SelectedUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome = findViewById(R.id.et_nome);
        edtZona = findViewById(R.id.et_zona);
        edtLocal = findViewById(R.id.et_local);
        edtFone = findViewById(R.id.et_fone);
        edtEmail = findViewById(R.id.et_email);
        edtSenha = findViewById(R.id.et_Senha);
        imagemContato = findViewById(R.id.iv_cadcliente);
        ImgPhoto = findViewById(R.id.iv_foto_cliente);
        btnCad = findViewById(R.id.btn_cadastroform);
        btnCamera = findViewById(R.id.img_camera);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPhoto();

            }
        });

        btnCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
        //
        // this.helper = new FormContHelper(this);
        // final ImageButton imageButtonFoto = helper.getBotaoFoto();
        //
        // Intent intent = this.getIntent();
        // this.contatoProdutores = (ContatoProdutores)
        // intent.getSerializableExtra("contatoSelecionado");
        // if(this.contatoProdutores != null){
        // this.helper.colocaNoCadastro(this.contatoProdutores);
    }
    // if(null != toolbar){
    // toolbar.setTitle("CadastrarAgricultores");
    // toolbar.setNavigationIcon(R.drawable.image_voltar);
    // toolbar.setNavigationOnClickListener(new View.OnClickListener() {
    // @Override
    // public void onClick(View v) {
    // NavUtils.navigateUpFromSameTask(CadastroClientes.this);
    // }
    // });
    //// toolbar.inflateMenu(R.menu.menu);
    // }
    // imageButtonFoto.setOnClickListener(new View.OnClickListener() {
    // @Override
    // public void onClick(View v) {
    // alertaSourceImagem();
    // }
    // });
    // }

    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    private void createUser() {
        String nome = edtNome.getText().toString();
        String zona = edtZona.getText().toString();
        String local = edtLocal.getText().toString();
        String fone = edtFone.getText().toString();
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        if (email == null || email.isEmpty() || senha == null || senha.isEmpty() || nome == null || nome.isEmpty()
                || zona == null || zona.isEmpty() || fone == null || fone.isEmpty() || local == null
                || local.isEmpty()) {

            Toast.makeText(this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("Teste", task.getResult().getUser().getUid());
                            saveUserInFirebase();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Teste", e.getMessage());
                    }
                });
    }

    private void saveUserInFirebase() {
        String filename = UUID.randomUUID().toString();
        final StorageReference ref = FirebaseStorage.getInstance().getReference("/images/" + filename);
        ref.putFile(SelectedUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Log.i("Teste", uri.toString());
                                String uid = FirebaseAuth.getInstance().getUid();
                                String username = edtNome.getText().toString();
                                String zone = edtZona.getText().toString();
                                String localidade = edtLocal.getText().toString();
                                String phone = edtFone.getText().toString();
                                String emailto = edtEmail.getText().toString();
                                String profileUrl = uri.toString();

                                UserProdAz user = new UserProdAz(uid, username, zone, localidade, phone, emailto,
                                        profileUrl);
                                FirebaseFirestore.getInstance().collection("users")
                                        .document(uid)
                                        .set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Intent intent = new Intent(CadastroClientes.this,
                                                        AtvProdutoresAzul.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                                        | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                startActivity(intent);

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.i("Teste", e.getMessage());

                                            }
                                        });
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Teste", e.getMessage(), e);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro, menu);
        return true;
    }

    // @Override
    // public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    // switch (item.getItemId()){
    // case R.id.meu_cadastro_ok:
    //
    // ContatoProdutores contato = helper.pegaContatoDoCadastro();
    // ContatoDAO dao = new ContatoDAO(CadastroClientes.this);
    //
    // if(contato.getId() == null){
    // dao.inserirContato(contato);
    // }else{
    // dao.alterarContato(contato);
    // }
    // dao.close();
    // finish();
    // return false;
    // default:
    // return super.onOptionsItemSelected(item);
    // }
    // }
    // public void clicaTirarFoto(){
    // fotoResource = true;
    // localArquivoFoto =
    // getExternalFilesDir(null)+"/"+System.currentTimeMillis()+".jpg";
    //
    // Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT,
    // FileProvider.getUriForFile(this,
    // this.getApplicationContext().getPackageName() +
    // ".provider", new File(localArquivoFoto)));
    // startActivityForResult(irParaCamera, 123);
    // }
    // public void clicaCarregarImagem(){
    // fotoResource = false;
    // /*Intent intent = new Intent();
    // intent.setType("image/*");
    // intent.setAction(Intent.ACTION_GET_CONTENT);
    // startActivityForResult(Intent.createChooser(intent, "Selecione imagem de
    // contato"), 1);*/
    //
    // Intent intent = new Intent(Intent.ACTION_PICK);
    // intent.setType ("image/");
    //// android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    // startActivityForResult(intent, 1);
    // }
    //
    // public void alertaSourceImagem(){
    // AlertDialog.Builder builder = new AlertDialog.Builder(this);
    // builder.setTitle("Selecione a fonte da imagem:");
    // builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
    // public void onClick(DialogInterface dialog, int id) {
    // clicaTirarFoto();
    // }
    // });
    // builder.setNegativeButton("Biblioteca", new DialogInterface.OnClickListener()
    // {
    // public void onClick(DialogInterface dialog, int id) {
    // clicaCarregarImagem();
    // }
    // });
    // AlertDialog dialog = builder.create();
    // dialog.show();
    // }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            SelectedUri = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), SelectedUri);
                ImgPhoto.setImageDrawable(new BitmapDrawable(bitmap));
                imagemContato.setAlpha(1);

            } catch (IOException e) {
            }
        } else {
            if (requestCode == 123) {
                // helper.carregaImagem(this.localArquivoFoto);

                ImgPhoto.setImageDrawable(new BitmapDrawable(localArquivoFoto));
                imagemContato.setAlpha(1);
            } else {
                this.localArquivoFoto = null;
            }

        }

    }

}