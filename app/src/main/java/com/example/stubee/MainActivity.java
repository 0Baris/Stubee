package com.example.stubee;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.stubee.notlar.NotlarFragment;
import com.example.stubee.todoo.ToDooFragment;
import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    SNavigationDrawer sNavigationDrawer;
    Class aClass;
    SplashScreen splashScreen;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sNavigationDrawer = findViewById(R.id.navigationDrawer);

        List<MenuItem> itemList = new ArrayList<>();

        itemList.add(new MenuItem("Notlar", R.drawable.notlar_bg));
        itemList.add(new MenuItem("Pomodoro", R.drawable.pomodro_bg));
        itemList.add(new MenuItem("To-Doo", R.drawable.todoo_bg));

        sNavigationDrawer.setMenuItemList(itemList);
        sNavigationDrawer.setAppbarTitleTV("Notlar");

        aClass = NotlarFragment.class;

        openFragment();

        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                switch (position){
                    case 0:
                        aClass = NotlarFragment.class;
                        break;
                    case 1:
                        aClass = PomodoroFragment.class;
                        break;
                    case 2:
                        aClass = ToDooFragment.class;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }
            }
        });

        sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {
            @Override
            public void onDrawerOpening() {

            }

            @Override
            public void onDrawerClosing() {
                openFragment();
            }

            @Override
            public void onDrawerOpened() {

            }

            @Override
            public void onDrawerClosed() {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        getWindow().setNavigationBarColor(Color.parseColor("#C46C00"));
        getWindow().setStatusBarColor(Color.parseColor("#C46C00"));

        sNavigationDrawer.isSoundEffectsEnabled();

    }

    private void openFragment() {
        try {
            Fragment fragment = (Fragment) aClass.newInstance();
            getSupportFragmentManager().
                    beginTransaction().
                    setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).
                    replace(R.id.frameLayout,fragment).
                    commit();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}