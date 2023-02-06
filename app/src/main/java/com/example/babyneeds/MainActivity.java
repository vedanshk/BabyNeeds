package com.example.babyneeds;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class MainActivity extends AppCompatActivity {


    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private Button saveButton;
    private EditText babyItem , itemQuantity  , itemColor , itemSize;
  FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener( v->{


            createPopupItem();


        });


    }

    private void createPopupItem() {

        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup , null);

        babyItem = view.findViewById(R.id.edtTxtItem);
        itemQuantity = view.findViewById(R.id.edtTxtQty);
        itemColor  = view.findViewById(R.id.edtTxtColor);
        itemSize = view.findViewById(R.id.edtTxtSize);
        builder.setView(view);
        dialog = builder.create(); // creating dialog
        dialog.show(); // showing dialog

    }


}