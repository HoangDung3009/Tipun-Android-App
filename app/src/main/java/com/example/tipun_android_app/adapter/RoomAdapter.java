package com.example.tipun_android_app.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.models.Room;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.UserViewHolder>{

    private Context mContext;
    private List<Room> mRoomList;

    public RoomAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Room> rooms){
        this.mRoomList = rooms;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Room room = mRoomList.get(position);
        if(room == null){
            return;
        }
        holder.room_title.setText(room.getTitle());
    }

    @Override
    public int getItemCount() {
        if (mRoomList != null){
            return mRoomList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private ImageView room_thumbnail;
        private TextView room_title, room_address, room_price;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            room_thumbnail = itemView.findViewById(R.id.room_thumbnail);
            room_title = itemView.findViewById(R.id.room_title);
            room_address = itemView.findViewById(R.id.room_address);
            room_price = itemView.findViewById(R.id.room_price);


        }
    }
}
