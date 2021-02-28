package ru.samsung.itschool.listofgoods;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList();
    ListView productList;
    Button incButton, decButton;
    EditText editText;
    String newGood;
    TextView toDel;
    ArrayList <Product> Chosen = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        incButton = findViewById(R.id.incButton);
        decButton = findViewById(R.id.decButton);
        editText = findViewById(R.id.newP);
        toDel = findViewById(R.id.toDel);


            if (products.size()==0){
                products.add(new Product("Картофель", "кг."));
                products.add(new Product("Молоко", "л."));
                products.add(new Product("Чай", "шт."));
            }



    productList = (ListView) findViewById(R.id.productList);
    ProductAdapter adapter = new ProductAdapter(this,
            R.layout.list_item,
            products);
productList.setAdapter(adapter);

incButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       try {
           newGood=editText.getText().toString();
           if (editText.equals("")) Toast.makeText(getApplicationContext(), "Пустое поле ввода", Toast.LENGTH_SHORT).show();
       }catch (Exception e){};
       if (!newGood.equals("")) {
           products.add(new Product(newGood));
           adapter.notifyDataSetChanged();
       }

    }
});
       productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @SuppressLint("SetTextI18n")
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               String pos = adapter.getItem(position).getName();
             if (productList.isItemChecked(position)) Chosen.add(adapter.getItem(position));
             else Chosen.remove(adapter.getItem(position));

             toDel.setText("Будет удалено:"+Chosen);
           }
       });

       decButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               for (int i=0; i<Chosen.size(); i++){
                   adapter.remove(Chosen.get(i));
               }
               productList.clearChoices();
               Chosen.clear();
               adapter.notifyDataSetChanged();
           }
       });
    }
}
