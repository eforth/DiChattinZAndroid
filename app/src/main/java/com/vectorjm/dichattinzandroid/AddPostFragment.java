package com.vectorjm.dichattinzandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddPostFragment extends Fragment {

    private ImageView imageView;
    private TextView titleVw;
    private EditText editText;
    private Button saveBtn;
    private Button galleryBtn;
    private Button cameraBtn;

    public AddPostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        saveBtn = view.findViewById(R.id.add_dialog_save_btn);
        cameraBtn = view.findViewById(R.id.camera_btn);
        galleryBtn = view.findViewById(R.id.gallery_btn);
        editText = view.findViewById(R.id.description_edit);
        titleVw = view.findViewById(R.id.add_dialog_title);
        imageView = view.findViewById(R.id.add_dialog_img_vw);
    }
}