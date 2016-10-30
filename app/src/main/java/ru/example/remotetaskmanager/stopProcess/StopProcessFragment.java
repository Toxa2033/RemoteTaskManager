package ru.example.remotetaskmanager.stopProcess;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.example.remotetaskmanager.R;
import ru.example.remotetaskmanager.helpers.SelectPcHolder;
import ru.example.remotetaskmanager.interfaces.SelectNavDrawerListner;
import ru.example.remotetaskmanager.models.PC;
import ru.example.remotetaskmanager.models.Process;
import ru.example.remotetaskmanager.models.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopProcessFragment extends Fragment implements SelectNavDrawerListner {

    private RecyclerView rvProcess;
    private ProcessAdapter adapter;
    private SelectPcHolder pcHolder;

    public StopProcessFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_stop_process, container, false);
        setupUI(view);
        pcHolder = SelectPcHolder.getInstance(getContext());
        if(pcHolder.getPc()!=null){
            setupAdapter(pcHolder.getPc().getStartedProcess());
        }
        SelectPcHolder.getInstance(getContext()).setSelectNavDrawListner(this);
        return view;
    }

    void setupUI(View view){
        rvProcess=(RecyclerView)view.findViewById(R.id.rvProcess);
        rvProcess.setLayoutManager(new LinearLayoutManager(getContext()));
        rvProcess.setHasFixedSize(true);
    }

    void setupAdapter(List<Process>processes){
        adapter=new ProcessAdapter(processes,getContext());
        rvProcess.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onSelectPc(PC pc) {
        setupAdapter(pc.getStartedProcess());
    }

    @Override
    public void onSelectUser(User user) {

    }
}
