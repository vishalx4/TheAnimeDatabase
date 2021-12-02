package com.example.theanimedatabase.ViewModel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.theanimedatabase.Model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class DataViewModel extends AndroidViewModel {

    private MutableLiveData<List<Anime>> listOfAnime = new MutableLiveData<>();
    private RequestQueue queue;
    private String API_URL = "https://api.jikan.moe/v3/search/anime?q=";
    private String anime_name_searched = "&order_by=members&sort=desc&page=1";


    private MutableLiveData<RequestStatus> reqStatus = new MutableLiveData<>();


    public DataViewModel(@NonNull Application application) {
        super(application);
        queue = Volley.newRequestQueue(getApplication());
        fetchList();
    }

    public void fetchList(){
        reqStatus.setValue(RequestStatus.IN_PROCESS);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,API_URL+anime_name_searched,null,
                response -> {

                    List<Anime> anime_list = new ArrayList<>();

                    try {
                        JSONArray list = response.getJSONArray("results");
                        for(int i=0;i<list.length();i++)
                        {
                            JSONObject anime_json_obj = new JSONObject(list.get(i).toString());
                            Anime anime = new Anime(anime_json_obj.getString("title"),
                                    anime_json_obj.getString("url"),
                                    anime_json_obj.getString("image_url"),
                                    anime_json_obj.getString("rated"),
                                    anime_json_obj.getString("synopsis"),
                                    anime_json_obj.getString("episodes")
                                    );
                            Log.d("vishal",anime.getName());
                            anime_list.add(anime);

                        }
                        listOfAnime.postValue(anime_list);
                        reqStatus.setValue(RequestStatus.SUCCESS);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        reqStatus.setValue(RequestStatus.ERROR);
                    }

                },
                error -> {
                    Log.d("vishal",error.getMessage());
                }
        );
        queue.add(jsonObjectRequest);
    }

    public MutableLiveData<List<Anime>> getListOfAnime() {
        return listOfAnime;
    }

    public void setListOfAnime(MutableLiveData<List<Anime>> listOfAnime) {
        this.listOfAnime = listOfAnime;
    }

    public String getAnime_name_searched() {
        return anime_name_searched;
    }

    public void setAnime_name_searched(String anime_name_searched) {
        this.anime_name_searched = anime_name_searched;
    }

    public enum RequestStatus{
        IN_PROCESS,SUCCESS,ERROR
    }

    public MutableLiveData<RequestStatus> getReqStatus() {
        return reqStatus;
    }

    public void setReqStatus(MutableLiveData<RequestStatus> reqStatus) {
        this.reqStatus = reqStatus;
    }
}
