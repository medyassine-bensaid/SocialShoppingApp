package com.example.socialshoppingbeta;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.socialshoppingbeta.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    public static Uri uri;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.galerieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(iGallery, 1000);

            }
        });
        binding.cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                startActivityForResult(iGallery, 200);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (((requestCode==200)||(requestCode==1000))&&(resultCode==-1)) {
//            VideoView vv = getView().findViewById(R.id.videoView2);
//            vv.setVideoURI(data.getData());
//            vv.setVisibility(View.VISIBLE);
//            vv.start();
//            vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mediaPlayer) {
//                    vv.start();
//                }
//            });
            uri = data.getData();
            NavHostFragment.findNavController(SecondFragment.this)
                    .navigate(R.id.action_SecondFragment_to_addVideoFulledFragment);
        }


//        if (requestCode==1000) {
//            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//            AddVideoFulledFragment adff = new AddVideoFulledFragment();
//            Bundle bundle = new Bundle();
//            bundle.putString("URI", data.getData().toString());
//            adff.setArguments(bundle);
//            fragmentTransaction.add(adff.getId(),adff ).commit();
//        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}