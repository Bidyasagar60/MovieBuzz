package com.knight.moviebuzz.AdapterHouse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knight.moviebuzz.Model.Pojo.MovieCastAndCrew;
import com.knight.moviebuzz.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.Holder> {

    private Context context;
    private List<MovieCastAndCrew.CastBean> CastList;

    public CastAdapter(Context context, List<MovieCastAndCrew.CastBean> castList) {
        this.context = context;
        CastList = castList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.castview,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        MovieCastAndCrew.CastBean cast=CastList.get(position);

        holder.OriginalName.setText(cast.getName());
        holder.CharecterName.setText(cast.getCharacter());
        if (cast.getProfile_path()==null) {


            holder.CastImageView.setImageResource(R.drawable.default_image);
        }
        else {
            String image_url = "https://image.tmdb.org/t/p/w500/" + cast.getProfile_path();
            Picasso.get().load(image_url).into(holder.CastImageView);
        }


    }

    @Override
    public int getItemCount() {
        return CastList.size();
    }

    public class Holder extends  RecyclerView.ViewHolder {

        private ImageView CastImageView;
        private TextView OriginalName,CharecterName;
        public Holder(@NonNull View itemView) {
            super(itemView);

            CastImageView=itemView.findViewById(R.id.castimage);
            OriginalName=itemView.findViewById(R.id.CastOriginalName);
            CharecterName=itemView.findViewById(R.id.CastChatacterName);
        }
    }
}
