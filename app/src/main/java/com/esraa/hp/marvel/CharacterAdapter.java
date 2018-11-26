package com.esraa.hp.marvel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 2018-11-26.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.MyViewHolder> {
    ArrayList<MarvelCharacter> characters;
    MarvelCharacter character;
    Context context;

    public CharacterAdapter (ArrayList<MarvelCharacter> characters,Context context){
        this.characters=characters;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     character=characters.get(position);
     holder.name.setText(character.getName());
     holder.team.setText(character.getTeam());
     holder.bio.setText(character.getBio());
        Picasso.get().load(character.getImgUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return characters.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,team,bio;
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            team=itemView.findViewById(R.id.team);
            bio=itemView.findViewById(R.id.bio);
            imageView=itemView.findViewById(R.id.img);
        }
    }
}
