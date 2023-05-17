package com.qxy.douyin.ui.rank;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.qxy.douyin.MyApplication;
import com.qxy.douyin.R;
import com.qxy.douyin.databinding.FragmentFansListListBinding;
import com.qxy.douyin.databinding.FragmentRankBinding;
import com.qxy.douyin.databinding.FragmentRankListBinding;
import com.qxy.douyin.model.RankItem;
import com.qxy.douyin.model.RankVersion;
import com.qxy.douyin.ui.fans.FansListFragment;
import com.qxy.douyin.ui.rank.placeholder.PlaceholderContent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class RankFragment extends Fragment {
    private String type;
    private FragmentRankListBinding binding;
    private  View root;
    private RecyclerView recyclerView;
    private RankViewModel rankViewModel;
    private String[] versionlist=new String[20];
    private List<RankItem.DataBean.ListBean> itemlist;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter;



    public RankFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RankFragment newInstance(int type) {
        RankFragment fragment = new RankFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        type=String.valueOf(getArguments().getInt("type"));
        binding= FragmentRankListBinding.inflate(inflater, container, false);
        root = binding.getRoot();
        recyclerView=binding.list;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        rankViewModel=new ViewModelProvider(this).get(RankViewModel.class);
        spinner=binding.spinner;


        MyApplication.clientToken.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("RankFragment","acacaca");
                if(s!=null)
                {
                    rankViewModel.getRankVersionByType(type).observe(getViewLifecycleOwner(), new Observer<List<RankVersion.DataBean.ListBean>>() {
                        @Override
                        public void onChanged(List<RankVersion.DataBean.ListBean> list) {
                            if(list.size()>0) {
                                for(int i=0;i<list.size();i++)
                                {
                                    RankVersion.DataBean.ListBean listBean=list.get(i);
                                    versionlist[i]=("第"+listBean.getVersion()+"期 "+listBean.getStart_time()+" - "+listBean.getEnd_time());
                                }
                                adapter=new ArrayAdapter<String>(getContext(),R.layout.item_select,versionlist);
                                spinner.setAdapter(adapter);
                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if(MyApplication.clientToken.getValue()!="")
                                        MyApplication.getApiService().getrankitem("application/json",MyApplication.clientToken.getValue(),type,list.get(position).getVersion()).enqueue(new Callback<RankItem>() {
                                            @Override
                                            public void onResponse(Call<RankItem> call, Response<RankItem> response) {
                                                if(response.body().getData().getList()!=null)
                                                {
                                                    myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(response.body().getData().getList());
                                                    recyclerView.setAdapter(myItemRecyclerViewAdapter);
                                                }
                                            }
                                            @Override
                                            public void onFailure(Call<RankItem> call, Throwable t) {
                                            }
                                        });
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });

                            }
                        }
                    });
                    rankViewModel.getRankItemByTypeAndVersion(type, "141").observe(getViewLifecycleOwner(), new Observer<List<RankItem.DataBean.ListBean>>() {
                        @Override
                        public void onChanged(List<RankItem.DataBean.ListBean> listBeans) {
                            if(listBeans!=null) {
                                Log.d("RankFragment","hhhh");
                                myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(listBeans);
                                recyclerView.setAdapter(myItemRecyclerViewAdapter);
                            }

                        }
                    });
                    SharedPreferences.Editor editor=getContext().getSharedPreferences("user",MODE_PRIVATE).edit();
                    editor.putBoolean("hasrankcache",true);
                    editor.apply();

                }
            }
        });

        return root;
    }

}