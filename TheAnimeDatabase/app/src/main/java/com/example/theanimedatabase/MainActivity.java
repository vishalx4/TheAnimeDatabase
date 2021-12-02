package com.example.theanimedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.theanimedatabase.Fragments.AnimeListFragment;
import com.example.theanimedatabase.Fragments.SearchFragment;
import com.example.theanimedatabase.ViewModel.DataViewModel;

public class MainActivity extends AppCompatActivity {


    private DataViewModel dataViewModel;
    ProgressDialog progressDialog;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataViewModel = new ViewModelProvider(
                getViewModelStore(),
                ViewModelProvider
                        .AndroidViewModelFactory.
                        getInstance(getApplication())).get(DataViewModel.class);


        if(savedInstanceState == null)
        {
            attachFragment(new SearchFragment(),R.id.search_frame_layout);
            attachFragment(new AnimeListFragment(),R.id.anime_list_fragment);
        }

        handleData();

    }

    private void handleData() {
        dataViewModel.getReqStatus().observe(this, new Observer<DataViewModel.RequestStatus>() {
            @Override
            public void onChanged(DataViewModel.RequestStatus requestStatus) {
                switch (requestStatus)
                {
                    case IN_PROCESS:
                        showSpinner();
                        break;
                    case SUCCESS:
                        hideSpinner();
                        attachFragment(new AnimeListFragment(),R.id.anime_list_fragment);
                        break;
                    case ERROR:
                        showError();
                        break;
                }
            }
        });
    }


    private void attachFragment(Fragment fragment, int layout){
        getSupportFragmentManager().beginTransaction().replace(layout,fragment).commit();
    }


    private void showSpinner(){
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
            progressDialog.setTitle("Fetching list...");
            progressDialog.setMessage("loading ...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setIndeterminate(true);
        }
        progressDialog.show();
    }

    private AlertDialog getAlertDialog(){
        return new AlertDialog.Builder(this)
                .setTitle("Failed to fetch list")
                .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dataViewModel.fetchList();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .setCancelable(true)
                .create();
    }

    private void hideSpinner(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    private void showError(){
        hideSpinner();
        if(alertDialog!=null){
            alertDialog = getAlertDialog();
        }
        alertDialog.show();
    }

}