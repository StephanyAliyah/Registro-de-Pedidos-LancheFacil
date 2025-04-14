package br.com.lanchefacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class ResumoActivity extends AppCompatActivity {

    TextView textValorNome, textValorPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resumo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Novos TextViews para exibir os valores
        textValorNome = findViewById(R.id.textValorNome);
        textValorPedido = findViewById(R.id.textValorPedido);

        // Recebe os dados da tela de pedidos
        Intent intent = getIntent();
        String nome = intent.getStringExtra("nomeCliente");
        String pedido = intent.getStringExtra("pedidoCliente");

        // Mostra os dados ao lado dos títulos
        textValorNome.setText(nome);
        textValorPedido.setText(pedido);
    }

    public void voltarInicio(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
