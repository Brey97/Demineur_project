package com.example.demineur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    MyAdapter adapter;
    TextView textView;
    private Button buttonNewGame;
    private Button buttonFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int rows = 10;
        int cols = 10;

        ArrayList<Cellule> celluleArrayList = new ArrayList<>();

        initializeGrid(celluleArrayList);

        Log.e("my tagidi tag",String.valueOf(celluleArrayList.size()));

        initializeGame(celluleArrayList);
        ArrayList<Integer> bombeClick = new ArrayList<>();
        ArrayList<Integer> flagClick = new ArrayList<>();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int bombe =0;
                if(flagClick.size() !=0){
                    if(celluleArrayList.get(position).getFlagOrNot() ==0){
                        celluleArrayList.get(position).setFlagOrNot(1);
                    }
                    else{
                        celluleArrayList.get(position).setFlagOrNot(0);
                    }
                    gridView.setAdapter(adapter);
                }
                else{
                    if(bombeClick.size()==0){
                        if(celluleArrayList.get(position).getFlagOrNot() == 0){
                            celluleArrayList.get(position).setClickOrNot(1);
                            bombe = NeighboursBombe(celluleArrayList,position,rows,cols);
                            celluleArrayList.get(position).setBombeNextTo(bombe);
                            gridView.setAdapter(adapter);
                            if(celluleArrayList.get(position).getBombeOrNot() == 1){
                                bombeClick.add(1);
                            }
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, "You Lose, press New Game", Toast.LENGTH_SHORT).show();
                    }
                }
                countFlag(celluleArrayList);
            }
        });

        buttonFlag = findViewById(R.id.buttonFlag);
        buttonFlag.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(bombeClick.size()==0){
                    if(flagClick.size() == 0){
                        flagClick.add(1);

                        Toast.makeText(MainActivity.this, "Press on a tile to put or remove a Flag", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        flagClick.clear();
                        Toast.makeText(MainActivity.this, "Press on a tile to reveal it", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "You Lose, press New Game", Toast.LENGTH_SHORT).show();
                }
                countFlag(celluleArrayList);
            }
        });

        buttonNewGame = findViewById(R.id.buttonNewGame);
        buttonNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                bombeClick.clear();
                celluleArrayList.clear();
                initializeGrid(celluleArrayList);
                initializeGame(celluleArrayList);
                countFlag(celluleArrayList);
            }
        });
        countFlag(celluleArrayList);


    }
    public void countFlag(ArrayList<Cellule> celluleArrayList){
        int flag=0;
        for(int i =0; i <100;i++){
            if(celluleArrayList.get(i).getFlagOrNot()==1){
                flag++;
            }
        }
        TextView tv1 = (TextView)findViewById(R.id.textView2);
        if(20-flag >=0){
            tv1.setText(String.valueOf(20-flag) + " flag to put");
        }

    }
    public void initializeGrid(ArrayList<Cellule> celluleArrayList){
        int rows = 10;
        int cols = 10;

        for (int i=0; i<100; i++) {
            if(i<20){
                celluleArrayList.add(new Cellule(0,1));
            }
            else{
                celluleArrayList.add(new Cellule(0,0));
            }
        }
        Collections.shuffle(celluleArrayList);

    }
    public void initializeGame(ArrayList<Cellule> celluleArrayList){
        gridView = (GridView) findViewById(R.id.gridView);
        adapter = new MyAdapter(this, celluleArrayList);
        gridView.setAdapter(adapter);

    }
    public Cellule[][] monoToBidi(ArrayList<Cellule> array, int rows, int cols ) {

        Cellule[][] bidi = new Cellule[rows][cols];
        for ( int i = 0; i < rows; i++ ) {
            for (int j = 0; j < cols; j++) {
                bidi[i][j] = array.get(i*10+j);
            }
        }
        return bidi;
    }

    public int NeighboursBombe(ArrayList<Cellule> array,int position,int rows,int cols){
        int bombe = 0;
        Cellule[][] bibi = monoToBidi(array,rows,cols);
        for(int i = 0; i<bibi.length;i++){
            for(int j =0;j<bibi[0].length;j++){
                if( i == position/cols && j == (position%cols)){
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