package org.techtown.courage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jisub on 2017-12-01.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    public List<NatureModel> objectList;
    public LayoutInflater inflater;

    public MyAdapter(Context context, List<NatureModel> objectList){
        inflater = LayoutInflater.from(context);
        this.objectList = objectList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NatureModel current = objectList.get(position);
        holder.setData(current, position);
        holder.setListeners();

    }



    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView title;
        public ImageView imgThumb, imgDelete, imgCopy;
        public int position;
        public NatureModel currentObject;

        public int getposition(){
            return this.position;
        }
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            imgThumb = (ImageView) itemView.findViewById(R.id.img_thumb);
            imgDelete = (ImageView) itemView.findViewById(R.id.img_delete);
            imgCopy = (ImageView) itemView.findViewById(R.id.img_copy);
        }

        public void setData(NatureModel currentObject, int position) {
            this.title.setText(currentObject.getTitle());
            this.imgThumb.setImageResource(currentObject.getImageID());
            this.position = position;
            this.currentObject = currentObject;

        }

        public void setListeners(){
            imgDelete.setOnClickListener(MyViewHolder.this);
            imgCopy.setOnClickListener(MyViewHolder.this);
            imgThumb.setOnClickListener(MyViewHolder.this);
        }

        @Override
        public void onClick(View v){
            switch (v.getId()){
                case R.id.img_delete:
                    removeItem(position);
                    break;

                case R.id.img_copy:
                    addItem(position, currentObject);
                    break;
            }
        }

    }

    public void removeItem(int position){
        objectList.remove(position);
        notifyItemRemoved(position);

    }

    public void addItem(int position, NatureModel currentObject){
        objectList.add(position, currentObject);
        notifyItemInserted(position);
    }
}

