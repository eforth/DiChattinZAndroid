package com.vectorjm.dichattinzandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplashFragment extends Fragment {

    private final String TAG = "SplashFragment";

    // Get relevant columns for use later.
    private  final String[] projection = {
        MediaStore.Files.FileColumns._ID,
        MediaStore.Files.FileColumns.DATA,
        MediaStore.Files.FileColumns.DATE_ADDED,
        MediaStore.Files.FileColumns.MEDIA_TYPE,
        MediaStore.Files.FileColumns.MIME_TYPE,
        MediaStore.Files.FileColumns.TITLE
    };

    // Register the permissions callback, which handles the user's response to the
    // system permissions dialog. Save the return value, an instance of
    // ActivityResultLauncher, as an instance variable.
    private final ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
        new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your
                // app.
                getMedia();
            } else {
                // Explain to the user that the feature is unavailable because the
                // features requires a permission that the user has denied. At the
                // same time, respect the user's decision. Don't link to system
                // settings in an effort to convince the user to change their
                // decision.
                Log.i(TAG, "requires permission");
            }
        });

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

/*        final int hasPermission = ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE);

        if (hasPermission == PackageManager.PERMISSION_GRANTED) {
            getMedia();
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            Log.i(TAG, "should permission");
        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
        }*/

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_mainFragment);
            }
        }, 2000);
    }

    private void getMedia() {
        // Return only video and image metadata.
        String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE
                + " OR "
                + MediaStore.Files.FileColumns.MEDIA_TYPE + "="
                + MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

        Uri queryUri = MediaStore.Files.getContentUri("external");

        CursorLoader cursorLoader = new CursorLoader(
            getContext(),
            queryUri,
            projection,
            selection,
            null,
            MediaStore.Files.FileColumns.DATE_ADDED + " DESC"
        );

        Cursor cursor = cursorLoader.loadInBackground();

        while (cursor != null && cursor.moveToNext()) {
            String mediaID       = cursor.getString(cursor.getColumnIndexOrThrow(projection[0]));
            String data          = cursor.getString(cursor.getColumnIndexOrThrow(projection[1]));
            String dateAdded     = cursor.getString(cursor.getColumnIndexOrThrow(projection[2]));
            String mediaType     = cursor.getString(cursor.getColumnIndexOrThrow(projection[3]));
            String mimeType      = cursor.getString(cursor.getColumnIndexOrThrow(projection[4]));
            String title         = cursor.getString(cursor.getColumnIndexOrThrow(projection[5]));

            Log.i(TAG, " ------------------------ Media Start ------------------------- ");
            Log.i(TAG, "Media ID: " + mediaID);
            Log.i(TAG, "Data: " + data);
            Log.i(TAG, "Date added: " + dateAdded);
            Log.i(TAG, "MediaType: " + mediaType);
            Log.i(TAG, "MIME Type: " + mimeType);
            Log.i(TAG, "Title: " + title);
            Log.i(TAG, " ------------------------ Media End ------------------------- ");
        }
    }
}