package ru.example.remotetaskmanager.navdrawer;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.helpers.PreferenceHelper;
import ru.example.remotetaskmanager.helpers.SelectPcHolder;
import ru.example.remotetaskmanager.models.PC;
import ru.example.remotetaskmanager.models.ParentDrawer;
import ru.example.remotetaskmanager.models.User;

/**
 * Created by 95tox on 15.10.2016.
 */

public class DrawerAdapter extends ExpandableRecyclerAdapter<DrawerAdapter.ParentHolder, DrawerAdapter.ChildeHolder> {

    private LayoutInflater mInflator;

    public DrawerAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mInflator = LayoutInflater.from(context);
    }

    // onCreate ...
    @Override
    public ParentHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View recipeView = mInflator.inflate(R.layout.item_drawer_parent, parentViewGroup, false);
        return new ParentHolder(recipeView);
    }

    @Override
    public ChildeHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View ingredientView = mInflator.inflate(R.layout.item_drawer_child, childViewGroup, false);
        return new ChildeHolder(ingredientView);
    }

    // onBind ...
    @Override
    public void onBindParentViewHolder(ParentHolder viewHolder, int position, ParentListItem parentListItem) {
        ParentDrawer item=(ParentDrawer)parentListItem;
        viewHolder.tvTitle.setText(item.getTitle());
    }

    @Override
    public void onBindChildViewHolder(final ChildeHolder viewHolder, int position, Object childListItem) {
        if(childListItem instanceof User){
            User user=(User)childListItem;
            viewHolder.tvName.setText(user.getLogin());
            viewHolder.ivStatus.setVisibility(View.GONE);
        } else if(childListItem instanceof PC) {
            final PC pc=(PC)childListItem;
            viewHolder.tvName.setText(pc.getPcName());
            if(pc.isOnline()){
                viewHolder.ivStatus.setImageResource(R.drawable.ic_status_online);
            } else {
                viewHolder.ivStatus.setImageResource(R.drawable.ic_status_offline);
            }
            String id= PreferenceHelper.getIdSelectPc(mInflator.getContext());
            if(id.isEmpty()&&position==1){
               selectPc(viewHolder,pc);
            } else {
                viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);

                if(id.equals(pc.get_id())){
                    selectPc(viewHolder,pc);
                } else {
                    viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT);
                }
            }

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPc(viewHolder,pc);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            });

        }
    }

  private void selectPc(ChildViewHolder viewHolder, PC pc){
        viewHolder.itemView.setBackgroundResource(R.drawable.bg_item_nav_drawer);
        SelectPcHolder.getInstance(mInflator.getContext()).setPc(pc);
    }


     class ParentHolder extends ParentViewHolder {

        TextView tvTitle;

         ParentHolder(final View itemView) {
             super(itemView);
             tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
         }

    }

     class ChildeHolder extends ChildViewHolder {

         TextView tvName;
         ImageView ivStatus;

         ChildeHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivStatus=(ImageView)itemView.findViewById(R.id.ivStatus);
        }



    }

}