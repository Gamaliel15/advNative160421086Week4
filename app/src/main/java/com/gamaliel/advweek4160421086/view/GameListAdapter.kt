package com.gamaliel.advweek4160421086.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gamaliel.advweek4160421086.databinding.GameListItemBinding
import com.gamaliel.advweek4160421086.model.Game

class GameListAdapter(val gameList:ArrayList<Game>)
    :RecyclerView.Adapter<GameListAdapter.GameViewHolder>(){
    class GameViewHolder(var binding: GameListItemBinding)
        :RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        var binding = GameListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GameViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.binding.txtGenre.text = gameList[position].genre
        holder.binding.txtTitle.text = gameList[position].title
        holder.binding.txtPlatforms.text = gameList[position].platforms?.joinToString (separator = ",")

    }

    fun updateGameList(newGameList:ArrayList<Game>){
        gameList.clear()
        gameList.addAll(newGameList)
        notifyDataSetChanged()
    }
}