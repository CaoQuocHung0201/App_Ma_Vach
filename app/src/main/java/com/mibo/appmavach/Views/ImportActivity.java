package com.mibo.appmavach.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mibo.appmavach.Models.Product;
import com.mibo.appmavach.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImportActivity extends AppCompatActivity {

    DatabaseReference dataFireBase= FirebaseDatabase.getInstance().getReference();

    String Id="",Name="",dateImport="",dateExport="";

    int quantity=0, Inventory_number=0;

    EditText edtSeri,edtName,edtDateImport,edtQuantity;
    TextView btnImport,btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import);
        init();
    }

    private void init(){
        edtSeri = findViewById(R.id.edtSeri);
        edtName = findViewById(R.id.edtName);
        edtDateImport = findViewById(R.id.edtDateImport);
        edtQuantity = findViewById(R.id.edtQuantity);
        btnImport = findViewById(R.id.edtSeri);
        btnBack = findViewById(R.id.edtSeri);
    }


    private void get_time() {
        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                String currentHour = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
                dateImport = currentHour+"-"+currentDate;
//                txtDate.setText(currentDate);
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(r, 0);
    }
    private void get_DatabaseReference() {
        dataFireBase.child(Id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product product = snapshot.getValue(Product.class);
                Name = product.getName();
                dateImport=product.getDateImport();
                dateExport=product.getDateExport();
                quantity=product.getQuantity();
                Inventory_number=product.getInventory_number();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //finish();
                //startActivity(new Intent(Activity_Bo_dem_so.this, Activity_Setting.class));
            }
        });
    }
    private void insert_DatabaseReference(){
        Product product=new Product(Id,Name,dateImport,dateExport,quantity,Inventory_number);
        dataFireBase.child("Product:"+Id).setValue(product).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(ImportActivity.this, "Lưu thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void up_DataFirebase() {
        dataFireBase.child(Id).child("name").setValue(Name);

    }
}