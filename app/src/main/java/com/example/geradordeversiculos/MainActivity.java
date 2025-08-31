package com.example.geradordeversiculos;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtVersiculo;
    Button btnGerar;
    ImageView imgIcone;
    Spinner spnCategoria;
    ProgressBar progressBar2;

    Map<String, String[]> versiculosPorCategoria = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtVersiculo = findViewById(R.id.txtVersiculo);
        btnGerar = findViewById(R.id.btnGerar);
        imgIcone = findViewById(R.id.imgIcone);
        spnCategoria = findViewById(R.id.spnCategoria);
        progressBar2 = findViewById(R.id.progressBar2);

        String[] categorias = {"Aleatório", "Fé", "Esperança", "Amor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categorias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnCategoria.setAdapter(adapter);

        versiculosPorCategoria.put("Aleatório", new String[]{
                "O Senhor é meu pastor, nada me faltará. - Salmo 23:1",
                "Posso todas as coisas naquele que me fortalece. - Filipenses 4:13",
                "Porque Deus amou o mundo de tal maneira que deu o seu Filho unigênito. - João 3:16",
                "Confia no Senhor de todo o teu coração. - Provérbios 3:5",
                "A paz deixo com vocês; a minha paz dou a vocês. - João 14:27",
                "Tudo posso naquele que me fortalece. - Filipenses 4:13",
                "A graça do Senhor Jesus seja com todos. Amém. - Apocalipse 22:21"
        });

        versiculosPorCategoria.put("Fé", new String[]{
                "Confia no Senhor de todo o teu coração. - Provérbios 3:5",
                "Tudo posso naquele que me fortalece. - Filipenses 4:13"
        });

        versiculosPorCategoria.put("Esperança", new String[]{
                "A paz deixo com vocês; a minha paz dou a vocês. - João 14:27"
        });

        versiculosPorCategoria.put("Amor", new String[]{
                "Porque Deus amou o mundo de tal maneira que deu o seu Filho unigênito. - João 3:16"
        });

        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar2.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        String categoriaSelecionada = spnCategoria.getSelectedItem().toString();
                        String[] listaVersiculos = versiculosPorCategoria.get(categoriaSelecionada);
                        if (categoriaSelecionada.equals("Aleatório")) {
                            listaVersiculos = versiculosPorCategoria.get("Aleatório");
                        }

                        Random random = new Random();
                        int index = random.nextInt(listaVersiculos.length);
                        String versiculo = listaVersiculos[index];
                        txtVersiculo.setText(versiculo);
                        progressBar2.setVisibility(View.GONE);
                    }
                }, 600);
            }
        });
    }
}
