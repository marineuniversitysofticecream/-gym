package kr.ac.mmu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.MyViewHolder> {
    private List<CommunityData> mDataset;
    private String mName;
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView TextView_name;
        public TextView TextView_message;
        public View rootView;
        public MyViewHolder(View v){
            super(v);
            TextView_name=v.findViewById(R.id.Textview_name);
            TextView_message=v.findViewById(R.id.Textview_msg);
            rootView=v;

            v.setClickable(true);
            v.setEnabled(true);
        }
    }
    public chatAdapter(List<CommunityData> mDataset, Context, CommunityActivity communityActivity, String mName){
        this.mName=mName;
    }
    @Override
    public chatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parnet, int viewType){
        LinearLayout v=(LinearLayout) LayoutInflater.from(parnet.getContext()).inflate(R.layout.row_chat, parnet, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        CommunityData chat=mDataset.get(position);

        holder.TextView_name.setText(chat.getName());
        holder.TextView_message.setText(chat.getMessage());

        if(chat.getName().equals(this.mName)){
            holder.TextView_message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        } else{
            holder.TextView_message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }
    }
    @Override
    public int getItemCount(){
        return mDataset==null?0:mDataset.size();
    }
    public CommunityData getChat(int position){
        return mDataset!=null?mDataset.get(position):null;
    }
    public void addChat(CommunityData chat){
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1);
    }

}
chatAdapter.java
