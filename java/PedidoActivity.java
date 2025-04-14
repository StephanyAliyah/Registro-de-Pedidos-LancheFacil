package br.com.lanchefacil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PedidoActivity extends AppCompatActivity {

    EditText editTextNome;
    CheckBox cbXBurguer, cbXSalada, cbXBacon, cbCheeseBurger, cbVegetariano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pedido);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextNome = findViewById(R.id.editTextNome);
        cbXBurguer = findViewById(R.id.cbXBurguer);
        cbXSalada = findViewById(R.id.cbXSalada);
        cbXBacon = findViewById(R.id.cbXBacon);
        cbCheeseBurger = findViewById(R.id.cbCheeseBurger);
        cbVegetariano = findViewById(R.id.cbVegetariano);
    }

    public void confirmarPedido(View view) {
        String nome = editTextNome.getText().toString().trim();

        if (nome.isEmpty()) {
            editTextNome.setError("Digite seu nome");
            editTextNome.requestFocus();
            return;
        }

        boolean algumItemMarcado = cbXBurguer.isChecked() ||
                cbXSalada.isChecked() ||
                cbXBacon.isChecked() ||
                cbCheeseBurger.isChecked() ||
                cbVegetariano.isChecked();

        if (!algumItemMarcado) {
            cbXBurguer.setError("Marque pelo menos um item");
            return;
        }

        // Monta o texto com os itens escolhidos
        StringBuilder pedido = new StringBuilder();
        if (cbXBurguer.isChecked()) pedido.append("X-Burguer\n");
        if (cbXSalada.isChecked()) pedido.append("X-Salada\n");
        if (cbXBacon.isChecked()) pedido.append("X-Bacon\n");
        if (cbCheeseBurger.isChecked()) pedido.append("Cheese Burguer\n");
        if (cbVegetariano.isChecked()) pedido.append("Vegetariano\n");

        // Envia os dados com putExtra
        Intent intent = new Intent(this, ResumoActivity.class);
        intent.putExtra("nomeCliente", nome);
        intent.putExtra("pedidoCliente", pedido.toString());
        startActivity(intent);
    }
}
