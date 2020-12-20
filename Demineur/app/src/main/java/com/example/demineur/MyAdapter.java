package com.example.demineur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Cellule> arrayList;
    private ImageView cell_picture;

    public MyAdapter(Context context, ArrayList<Cellule> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.cellule_picture, parent, false);
        cell_picture = convertView.findViewById(R.id.imageView);
        if(arrayList.get(position).getClickOrNot()==1){
            if(arrayList.get(position).getBombeOrNot()==0){
                switch(arrayList.get(position).getBombeNextTo()){
                    case 0: cell_picture.setImageResource(R.drawable.case_vierge_cliquer);
                    break;
                    case 1: cell_picture.setImageResource(R.drawable.case_1);
                    break;
                    case 2: cell_picture.setImageResource(R.drawable.case_2);
                    break;
                    case 3: cell_picture.setImageResource(R.drawable.case_3);
                    break;
                    case 4: cell_picture.setImageResource(R.drawable.case_4);
                    break;
                    case 5: cell_picture.setImageResource(R.drawable.case_5);
                    break;
                    case 6: cell_picture.setImageResource(R.drawable.case_6);
                    break;
                    case 7: cell_picture.setImageResource(R.drawable.case_7);
                    break;
                    case 8: cell_picture.setImageResource(R.drawable.case_8);
                    break;
                }
            }
            else{
                cell_picture.setImageResource(R.drawable.bombe_m);
            }
        }
        else if(arrayList.get(position).getFlagOrNot()!=0 && arrayList.get(position).getClickOrNot() == 0){
            cell_picture.setImageResource(R.drawable.case_flag_yellow);
        }
        else{
            cell_picture.setImageResource(R.drawable.case_vierge);
        }
        return convertView;
    }
}
