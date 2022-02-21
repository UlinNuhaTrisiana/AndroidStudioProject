package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    int i=1;
    Context context;
    List<Model> listNegara;

    public Adapter(Context context, List<Model> listNegara) {
        this.context = context;
        this.listNegara = listNegara;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item,null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Model model=listNegara.get(position);
        if(i == 1){
            holder.kasus.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getKasus())));
        }
        else if(i ==2){
            holder.kasus.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getSembuh())));
        }
        else if(i ==3){
            holder.kasus.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getMeninggal())));
        }
        else{
            holder.kasus.setText(NumberFormat.getInstance().format(Integer.parseInt(model.getAktif())));
        }

        holder.negara.setText(model.getNegara());
    }

    @Override
    public int getItemCount() {
        return listNegara.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView kasus, negara;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            kasus=itemView.findViewById(R.id.kasusnegara);
            negara=itemView.findViewById(R.id.namanegara);
        }
    }

    public void filter(String charText){
        if (charText.equals("kasus")){
            i=1;
        }
        else if (charText.equals("sembuh")){
            i=2;
        }
        else if (charText.equals("meninggal")){
            i=2;
        }
        else{
            i=4;
        }
        notifyDataSetChanged();
    }
}
