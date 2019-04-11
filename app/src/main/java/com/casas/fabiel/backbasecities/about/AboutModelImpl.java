package com.casas.fabiel.backbasecities.about;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.casas.fabiel.backbasecities.FileReader;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 */

public class AboutModelImpl implements About.Model {

    private static final String TAG = AboutModelImpl.class.getSimpleName();
    private final About.Presenter presenter;
    private final WeakReference<Context> context;
    private static final String FILE_NAME = "aboutInfo.json";

    public AboutModelImpl(@NonNull About.Presenter presenter, @NonNull Context context){
        this.presenter = presenter;
        this.context = new WeakReference<>(context);
    }

    @Override
    public void getAboutInfo() {
        String aboutInfoJson = new FileReader(FILE_NAME, context.get()).read();

        if(aboutInfoJson != null && !aboutInfoJson.isEmpty()){
    		AboutInfo aboutInfo = parseAboutInfo(aboutInfoJson);
    		if (aboutInfo != null){
        		presenter.onSuccess(aboutInfo);
        		return;
   		 	}
		}

		presenter.onFail();
    }

    private AboutInfo parseAboutInfo(String aboutInfoJson) {
        AboutInfo aboutInfo = null;
        try {
            JSONObject jsonObject = new JSONObject(aboutInfoJson);
            aboutInfo = new AboutInfo();
            aboutInfo.setCompanyName(jsonObject.getString("companyName"));
            aboutInfo.setCompanyAddress(jsonObject.getString("companyAddress"));
            aboutInfo.setCompanyCity(jsonObject.getString("city"));
            aboutInfo.setCompanyPostal(jsonObject.getString("postalCode"));
            aboutInfo.setAboutInfo(jsonObject.getString("details"));
        } catch (JSONException e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }
        return aboutInfo;
    }
}
