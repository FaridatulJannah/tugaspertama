package com.example.elektronik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etsetrika, etblender, etkulkas;
    private RadioGroup radioGroup;
    private Button btnpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etsetrika = findViewById(R.id.etsetrika);
        etblender = findViewById(R.id.etblender);
        etkulkas = findViewById(R.id.etkulkas);
        radioGroup = findViewById(R.id.radioGroup);
        btnpesan = findViewById(R.id.btnpesan);

        btnpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int jumlahSetrika = Integer.parseInt(etsetrika.getText().toString());
                int jumlahBlender = Integer.parseInt(etblender.getText().toString());
                int jumlahKulkas = Integer.parseInt(etkulkas.getText().toString());

                RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                String tipeMember = selectedRadioButton.getText().toString();

                // Harga per barang
                int hargaSetrika = 80000;
                int hargaBlender = 150000;
                int hargaKulkas = 3000000;

                int totalHargaAwal = (jumlahSetrika * hargaSetrika) + (jumlahBlender * hargaBlender) + (jumlahKulkas * hargaKulkas);

                // potongan harga
                int potonganHarga = 0;
                if (tipeMember.equals("silver")) {
                    potonganHarga = 100000;
                } else if (tipeMember.equals("gold")) {
                    potonganHarga = 500000;
                }

                //diskon
                double diskon;

                if (totalHargaAwal > 4000000) {
                    diskon = 0.1 * totalHargaAwal;
                } else {
                    diskon = 0;
                }

                double totalHarga = totalHargaAwal - potonganHarga - diskon;

                // bon pembelian
                showBonPembelian(jumlahSetrika, jumlahBlender, jumlahKulkas, totalHargaAwal, potonganHarga, diskon, totalHarga);
            }
        });
    }

    private void showBonPembelian(int jumlahSetrika, int jumlahBlender, int jumlahKulkas,
                                  int totalHargaAwal, int potonganHarga, double diskon, double totalHarga) {
        String bonPembelian = "Barang yang dibeli\n" +
                "Setrika: " + jumlahSetrika + "\n" +
                "Blender: " + jumlahBlender + "\n" +
                "Kulkas: " + jumlahKulkas + "\n\n" +
                "Total Harga Awal: Rp. " + totalHargaAwal + "\n" +
                "Potongan Harga: Rp. " + potonganHarga + "\n" +
                "Diskon: Rp. " + diskon + "\n" +
                "Total Harga yang harus dibayar: Rp. " + totalHarga + "\n" +
                "\n-----ELECTROKITA TERPERCAYA-----";

        // Membuat AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bon Pembelian")
                .setMessage(bonPembelian)
                .setPositiveButton("OK", null)
                .show();
    }
}

