package ru.example.remotetaskmanager.stopProcess;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.helpers.SelectPcHolder;
import ru.example.remotetaskmanager.helpers.UserHolder;
import ru.example.remotetaskmanager.interfaces.ApiListner;
import ru.example.remotetaskmanager.models.Process;
import ru.example.remotetaskmanager.models.Task;
import ru.example.remotetaskmanager.utills.APIUtils;
import ru.example.remotetaskmanager.utills.Message;
import ru.example.remotetaskmanager.view.LoadingDialog;

/**
 * Created by 95tox on 30.10.2016.
 */

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ProcessViewHolder> {

    private List<Process> mData;
    private Context mContext;

    public ProcessAdapter(List<Process>processes, Context context) {
        mData=processes;
        mContext=context;
    }

    @Override
    public ProcessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_process, parent, false);
        return new ProcessViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProcessViewHolder holder, int position) {
        final Process item=mData.get(position);
        String name=String.format(mContext.getString(R.string.process_name),item.getName(),item.getProcessId());
        final String memory=String.format(mContext.getString(R.string.process_memory),item.getMemory());
        String time=String.format(mContext.getString(R.string.process_time),item.getStartTimeString());

        holder.tvMemory.setText(memory);
        holder.tvName.setText(name);
        holder.tvTime.setText(time);

        holder.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final LoadingDialog loadingDialog=new LoadingDialog(mContext);
                LoadingDialog.dismiss(loadingDialog);
                Task task=new Task();
                task.setDate(new Date());
                task.setProcess(item.getName());
                task.setOwner(UserHolder.getInstance(mContext).getUser());
                task.setPc(SelectPcHolder.getInstance(mContext).getPc());
                task.setType(Task.TYPE_KILL);

                APIUtils.getInstance(mContext).addTask(task, new ApiListner() {
                    @Override
                    public void onLoad(Object obj) {
                        LoadingDialog.dismiss(loadingDialog);
                        Message.showToast("Success",mContext);
                    }

                    @Override
                    public void onError(String cause) {
                        LoadingDialog.dismiss(loadingDialog);
                        Message.showToast(cause,mContext);
                    }
                });
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    class ProcessViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvMemory;
        TextView tvTime;
        Button btnStop;

        public ProcessViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tvName);
            tvMemory=(TextView)itemView.findViewById(R.id.tvMemory);
            tvTime=(TextView)itemView.findViewById(R.id.tvTime);
            btnStop=(Button)itemView.findViewById(R.id.btnStop);

        }
    }
}
