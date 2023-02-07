package com.example.babyneeds;

import android.os.Bundle;

import com.example.babyneeds.data.DatabaseHandler;
import com.example.babyneeds.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.babyneeds.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Main Activity";
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText babyItem , itemQuantity  , itemColor , itemSize;

    private DatabaseHandler databaseHandler;
  FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHandler = new DatabaseHandler(this);
        List<Item> items = databaseHandler.getAllItems();

        for(Item item:items){

            Log.d(TAG, "onCreate: " + item.getItemName());

        }


        fab = findViewById(R.id.fab);

        fab.setOnClickListener( v->{


            createPopupItem();


        });
        


    }

    private void saveItem(View v) {
        Item item  = new Item();
        String newItem = babyItem.getText().toString().trim();
        String newColor = itemColor.getText().toString().trim();

        int quantity = Integer.parseInt(itemQuantity.getText().toString().trim());

        int size = Integer.parseInt(itemSize.getText().toString().trim());

        item.setItemName(newItem);
        item.setQuantity(quantity);
        item.setColor(newColor);
        item.setSize(size);
        databaseHandler.addItem(item);

        Snackbar.make(v , "Item Saved" , Snackbar.LENGTH_SHORT).show();

    }


    private void createPopupItem() {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup , null);


        babyItem = view.findViewById(R.id.edtTxtItem);
        itemQuantity = view.findViewById(R.id.edtTxtQty);
        itemColor  = view.findViewById(R.id.edtTxtColor);
        itemSize = view.findViewById(R.id.edtTxtSize);
        saveButton = view.findViewById(R.id.btnSave);
        builder.setView(view);
        dialog = builder.create(); // creating dialog
        dialog.show(); // showing dialog

        saveButton.setOnClickListener(v->{

            if(!babyItem.getText().toString().isEmpty() &&!itemSize.getText().toString().isEmpty()
                    &&!itemColor.getText().toString().isEmpty()&& !itemQuantity.getText().toString().isEmpty()
            ){
                saveItem(v);

            }else{
                Snackbar.make(v , "Empty Fields not allowed" ,Snackbar.LENGTH_SHORT).show();
            }
        });



    }


}