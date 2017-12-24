package id.fadhil.resepmama.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import id.fadhil.resepmama.DetailScrolling;
import id.fadhil.resepmama.R;
import id.fadhil.resepmama.model.Model;

/**
 * Created by riyan on 18/12/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    ArrayList<Model> list;
    Context context;

    public HomeAdapter(Context context, ArrayList<Model> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.with(context)
                .load("https://muhafadhil.000webhostapp.com/ResepMama/image/"+list.get(position).getGambar())
                .into(holder.iv);
        holder.tv.setText(list.get(position).getNamaResep());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DetailScrolling.class);
                i.putParcelableArrayListExtra("list",list);
                i.putExtra("position",position);
                context.startActivities(new Intent[]{i});
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        CardView card;
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_resep);
            card = (CardView) itemView.findViewById(R.id.card);
            tv = (TextView) itemView.findViewById(R.id.tv_judul);
        }
    }
}
