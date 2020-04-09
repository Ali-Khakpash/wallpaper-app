package com.example.g50.primary_wallpaper;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link tab_4.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link tab_4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab_4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private RequestQueue myque;
    private ArrayList<data_provider_1> data  ;
    private String url1;
    private String url2;
    private String url3;
    private String url4;

    // // // Recycler programming Section                      // // // Recycler programming Section

    RecyclerView recyclerView ;
    private ProgressBar mProgressBar;
    RecyclerView.Adapter  adapter ;
    RecyclerView.LayoutManager layoutManager ;



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public tab_4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab_4.
     */
    // TODO: Rename and change types and number of parameters
    public static tab_4 newInstance(String param1, String param2) {
        tab_4 fragment = new tab_4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //recyclerView= (RecyclerView) recyclerView.findViewById(R.id.recyclerview);
        View layout = inflater.inflate(R.layout.fragment_tab_4, container, false);
        mProgressBar = (ProgressBar) layout.findViewById(R.id.progress_bar);
        recyclerView= (RecyclerView)layout.findViewById(R.id.recyclerview4);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        mProgressBar.setVisibility(View.VISIBLE);
        data = new ArrayList<>();
        jsonparse();
        return layout;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    private void jsonparse(){

        final String url  = "https://www.jasonbase.com/things/Kjy1";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,    // // // this Could be a  JsonArrayRequest
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {

                            for (int i =0 ; i<response.length();i++) {

                                JSONObject subobjects = response.getJSONObject(i);
                                JSONArray preview_photo = subobjects.getJSONArray("preview_photos");
                                for (int j = 0 ; j<2; j++) {
                                    //  JSONObject subpreview_photo = preview_photo.getJSONObject(j)  ;

                                    if(j==0) {
                                        JSONObject subpreview_photo = preview_photo.getJSONObject(j)  ;
                                        JSONObject urls = subpreview_photo.getJSONObject("urls");
                                        url1 =urls.getString("regular");
                                        JSONObject subpreview_photo1 = preview_photo.getJSONObject(j+1)  ;
                                        JSONObject urls1 = subpreview_photo1.getJSONObject("urls");
                                        url2 =urls1.getString("regular");
                                        data .add(new data_provider_1(url1,url2));
                                        // data_provider data_provider = new data_provider();
                                        // data_provider.setImageurl(urls.getString("regular"));
                                        // data.add(data_provider);
                                    }

                                    if(j==1) {
                                        JSONObject subpreview_photo = preview_photo.getJSONObject(j+1)  ;
                                        JSONObject urls = subpreview_photo.getJSONObject("urls");
                                        url3 =urls.getString("regular");
                                        JSONObject subpreview_photo1 = preview_photo.getJSONObject(j+2);
                                        JSONObject urls1 = subpreview_photo1.getJSONObject("urls");
                                        url4=urls1.getString("regular");
                                        data .add(new data_provider_1(url3,url4));
                                    }





                                    // data .add(new data_provider(url1,url2));  /// don't get deceived by this fucking loop
                                }

                                // data .add(new data_provider(url1,url2));

                            }

                            adapter = new RecyclerAdapter_4(data,getContext());
                            mProgressBar.setVisibility(View.GONE);
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }


                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();     // // // to see what has happened

            }
        });

        myque  = Volley.newRequestQueue(getContext());
        myque.add(request);



    }




}
