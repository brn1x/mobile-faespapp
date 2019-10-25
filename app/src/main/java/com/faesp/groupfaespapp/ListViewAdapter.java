package com.faesp.groupfaespapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater inflater;
    List<Group> modelList;
    ArrayList<Group> arrayList;

    public ListViewAdapter(Context context, List<Group> modelList){
        mContext = context;
        this.modelList = modelList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Group>();
        this.arrayList.addAll(modelList);
    }


    public class ViewHolder{
        TextView mGroupType, mGroupName;
        ImageView mGroupIcon;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.fragment_list_row, null);

            // locate the view in fragment_list_row.xml
            holder.mGroupIcon = view.findViewById(R.id.group_icon);
            holder.mGroupType = view.findViewById(R.id.group_type);
            holder.mGroupName = view.findViewById(R.id.group_name);

            view.setTag(holder);
        } else{
            holder = (ViewHolder)view.getTag();
        }
        // set the results into components
        holder.mGroupType.setText(modelList.get(position).getTpGrupo());
        holder.mGroupName.setText(modelList.get(position).getNmGrupo());
        holder.mGroupIcon.setImageResource(modelList.get(position).getIcon());

        // listview item listener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GroupDescription.class);
                intent.putExtra("actionBarTitle", modelList.get(position).getNmGrupo());
                intent.putExtra("id", modelList.get(position).getId());
                intent.putExtra("descGroupName", modelList.get(position).getNmGrupo());
                intent.putExtra("descQtdMinP", modelList.get(position).getQtdMinP());
                intent.putExtra("descQtdMaxP", modelList.get(position).getQtdMaxP());
                intent.putExtra("descQtdEnc", modelList.get(position).getQtdEnc());
                intent.putExtra("descGroupType", modelList.get(position).getTpGrupo());
                intent.putExtra("descGroupDesc", modelList.get(position).getDescGrupo());
                intent.putExtra("descObjGroup", modelList.get(position).getObjGrupo());
                intent.putExtra("descSituacao", modelList.get(position).getSituacao());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    // filter for search component
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        modelList.clear();
        if(charText.length()==0){
            modelList.addAll(arrayList);
        }else{
            for(Group group : arrayList){
                if(group.getNmGrupo().toLowerCase(Locale.getDefault()).contains(charText)
                        || group.getTpGrupo().toLowerCase(Locale.getDefault()).contains(charText)){
                    modelList.add(group);
                }
            }
        }

        notifyDataSetChanged();
    }
}
