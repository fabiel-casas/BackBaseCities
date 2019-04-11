package com.casas.fabiel.backbasecities;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;

public class FileReader {
    private final String fileName;
    private final Context context;
    private final String TAG = FileReader.class.getSimpleName();

    public FileReader(String fileName, @NotNull Context context) {
        this.fileName = fileName;
        this.context = context;
    }

    public String read() {
        if (context != null) {
            try {
                AssetManager manager = context.getAssets();
                InputStream file = manager.open(fileName);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            } catch (IOException ex) {
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }
        return null;
    }
}
