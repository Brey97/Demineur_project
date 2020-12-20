package com.example.demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Cellule> celluleArrayList = new ArrayList<>();


        for (int i=0; i<90; i++) {
            if(i<20){
                celluleArrayList.add(new Cellule(0,1));
            }
            else{
                celluleArrayList.add(new Cellule(0,0));
            }
        }
        Collections.shuffle(celluleArrayList);

        Log.e("my tagidi tag",String.valueOf(celluleArrayList.size()));

        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MyAdapter(this, celluleArrayList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int bombe =0;
                celluleArrayList.get(position).setClickOrNot(1);
                bombe = NeighboursBombe(celluleArrayList,position);
                celluleArrayList.get(position).setBombeNextTo(bombe);
                gridView.setAdapter(adapter);
            }
        });
    }
    public Cellule[][] monoToBidi(ArrayList<Cellule> array, final int rows, final int cols ) {

        Cellule[][] bidi = new Cellule[rows][cols];
        for ( int i = 0; i < rows; i++ ) {
            for (int j = 0; j < cols; j++) {
                bidi[i][j] = array.get(i*9+j);
            }
        }
        return bidi;
    }

    public int NeighboursBombe(ArrayList<Cellule> array,int position){
        int bombe = 0;
        Cellule[][] bibi = monoToBidi(array,9,10);
        for(int i = 0; i<bibi.length;i++){
            for(int j =0;j<bibi[0].length;j++){
                if( i == position/9 && j == (position%9)){
                    if(i-1>=0 && j-1>=0){
                        bombe += bibi[i-1][j-1].getBombeOrNot();
                    }
                    if(i-1>=0){
                        bombe += bibi[i-1][j].getBombeOrNot();
                    }
                    if(i-1>=0 && j+1< bibi[0].length){
                        bombe += bibi[i-1][j+1].getBombeOrNot();
                    }
                    if(j-1>=0){
                        bombe += bibi[i][j-1].getBombeOrNot();
                    }
                    if(j+1< bibi[0].length){
                        bombe += bibi[i][j+1].getBombeOrNot();
                    }
                    if(i+1<bibi.length && j-1>=0){
                        bombe += bibi[i+1][j-1].getBombeOrNot();
                    }
                    if(i+1<bibi.length ){
                        bombe += bibi[i+1][j].getBombeOrNot();
                    }
                    if(i+1<bibi.length && j+1< bibi[0].length){
                        bombe += bibi[i+1][j+1].getBombeOrNot();
                    }
                }
            }
        }
        return bombe;
    }
}