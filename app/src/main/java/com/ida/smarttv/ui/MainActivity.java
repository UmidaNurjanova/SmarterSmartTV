package com.ida.smarttv.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.ida.smarttv.R;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    Toolbar toolbar;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);


        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.login).build();
        NavigationUI.setupWithNavController(
                toolbar, navController, appBarConfiguration);

    }
}
