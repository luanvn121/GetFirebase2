package romz.luannguyen.getfirebase.fragment;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import romz.luannguyen.getfirebase.R;
import romz.luannguyen.getfirebase.adapter.CustomAdapter;
import romz.luannguyen.getfirebase.model.DuLieu;

/**
 * A simple {@link Fragment} subclass.
 */
public class Thang7Fragment extends Fragment {

    Firebase root;
    private ArrayList<DuLieu> mDulieu = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CustomAdapter customAdapter;
    View rootView;


    public Thang7Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_thang7, container, false);
        Firebase.setAndroidContext(getActivity());

        mRecyclerView= (RecyclerView)rootView.findViewById(R.id.recycler7_view);

        root = new Firebase("https://kitestquocte.firebaseio.com/2016/7");
        if (isNetworkAvailable(getActivity())) {
            // code here
            Toast.makeText(getActivity(), "Connect", Toast.LENGTH_SHORT).show();
        } else {
            // code
            Toast.makeText(getActivity(), "NotConnect", Toast.LENGTH_SHORT).show();

        }
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("Tag", "onChildAdded: "+dataSnapshot.getKey().toString());

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d("child", "onDataChange: "+child.getValue().toString());

                    /*  DataObject post = dataSnapshot.getValue(DataObject.class);
                       mang.add(post);*/

                    DuLieu post=new DuLieu();
                    post.setContent(child.getValue(DuLieu.class).getContent());
                    post.setTitle(child.getValue(DuLieu.class).getTitle());
                    post.setUrl(child.getValue(DuLieu.class).getUrl());
                    mDulieu.add(post);
                }
                customAdapter = new CustomAdapter(getActivity(),mDulieu);
                RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        return rootView;
    }
    private boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
