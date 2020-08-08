package com.example.recyclerviewss;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Build;
import android.os.Bundle;

import com.example.recyclerviewss.Adapters.SingerAdapter;
import com.example.recyclerviewss.Models.SingerModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ArrayList>  list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        ArrayList<SingerModel> list = new ArrayList<>();

        list.add(new SingerModel(R.drawable.a1, "Raman Negi"));
        list.add(new SingerModel(R.drawable.a2, "Drumer"));
        list.add(new SingerModel(R.drawable.a3, "Guitarist"));
        list.add(new SingerModel(R.drawable.a4, "SantKabir"));
        list.add(new SingerModel(R.drawable.a5, "SantKabir"));
        list.add(new SingerModel(R.drawable.a6, "SantKabir"));

        SingerAdapter adapter = new SingerAdapter(list, this);
        recyclerView.setAdapter(adapter);

        //StaggeredGridLayoutManager staggeredGridLayoutManager =new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(staggeredGridLayoutManager);

        GridLayoutManager gridLayoutManager =new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        //LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

        //LinearLayoutManager layoutManager =new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //recyclerView.setLayoutManager(layoutManager);  // for horizontal scroller

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN
             | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            /*int frompostion = viewHolder.getAdapterPosition();
            int toposition = target.getAdapterPosition();*/

            Collections.swap(Collections.singletonList(viewHolder.getItemId()),
                    viewHolder.getAdapterPosition(),
                    target.getAdapterPosition());
            Objects.requireNonNull(recyclerView.getAdapter()).notifyItemMoved( viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return false;
        }
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        }
    };
}
