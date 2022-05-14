package com.example.tipun_android_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.models.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomAdapter extends BaseAdapter implements Filterable {
    private List<Room> originalList;
    private List<Room> filteredList;
    private Context context;
    private ItemFilter mFilter = new ItemFilter();

    public RoomAdapter(Context context, List<Room> roomList) {
//        super(context, 0, roomList);
        this.filteredList = roomList;
        this.originalList = roomList;
        this.context = context;
    }

    public List<Room> getData(){
        return  filteredList;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Room getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return filteredList.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        Room room = getItem(position);
        View roomView;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_room, null);
        } else roomView = convertView;

        ImageView imgRoom =  convertView.findViewById(R.id.img_room);
        imgRoom.setImageResource(R.drawable.phongkhach1);

        TextView title = convertView.findViewById(R.id.tv_title);
        title.setText(room.getTitle());

        TextView address = convertView.findViewById(R.id.tv_address);
        String s = room.getBuilding()+ ", " + room.getDistrict() + ", " + room.getCity();
        address.setText(s);

        TextView price = convertView.findViewById(R.id.tv_price);
        String s1 = room.getPrice() + " vnđ";
        price.setText(s1);


        return convertView;
    }

    public Filter getFilter(){
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Room> list = originalList;

            int count = list.size();
            final List<Room> nlist = new ArrayList<>(count);

            String filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i).getTitle();
                if (filterableString.toLowerCase().contains(filterString)) {
                    nlist.add(list.get(i));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<Room>) results.values;
            notifyDataSetChanged();
        }

    }
}
