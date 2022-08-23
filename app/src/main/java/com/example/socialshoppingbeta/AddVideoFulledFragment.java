package com.example.socialshoppingbeta;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddVideoFulledFragment extends Fragment {


    private VideoView videoView;
    private ArrayList<Product> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_video_fulled, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        videoView = view.findViewById(R.id.videoView);
        videoView.setVideoURI(SecondFragment.uri);
        videoView.start();
//        MediaController mc = new MediaController(getContext());
//        mc.setAnchorView(view);
//        videoView.setMediaController(mc);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
            }
        });
        ImageView btn = view.findViewById(R.id.addProductButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(iGallery, 300);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((resultCode==-1)&&(requestCode==300)) {
            View view = getView();
            EditText ed1 = view.findViewById(R.id.nomMarque);
            EditText ed2 = view.findViewById(R.id.nomProduit);
            EditText ed3 = view.findViewById(R.id.prixProduit);
            EditText ed4 = view.findViewById(R.id.lienProduit);
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");

            ImageView img = view.findViewById(R.id.imageView4);
            ConstraintLayout CL1 = view.findViewById(R.id.CL1);
            ConstraintLayout CL2 = view.findViewById(R.id.CL2);
            ConstraintLayout CL3 = view.findViewById(R.id.CL3);
            CL1.setVisibility(View.GONE);
            CL2.setVisibility(View.VISIBLE);
            img.setImageURI(data.getData());
            ImageView btn = view.findViewById(R.id.ajouterButton);
            LinearLayout products = view.findViewById(R.id.products);

                    btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (ed1.getText().toString().isEmpty()) {
                        ed1.setError("This field is obligatory");
                        ed1.requestFocus();
                        return;
                    }
                    if (ed2.getText().toString().isEmpty()) {
                        ed2.setError("This field is obligatory");
                        ed2.requestFocus();
                        return;
                    }
                    if (ed3.getText().toString().isEmpty()) {
                        ed3.setError("This field is obligatory");
                        ed3.requestFocus();
                        return;
                    }
                    if (ed4.getText().toString().isEmpty()) {
                        ed4.setError("This field is obligatory");
                        ed4.requestFocus();
                        return;
                    }
                    if (!Product.isValidURL(ed4.getText().toString())) {
                        ed4.setError("Invalid Link");
                        ed4.requestFocus();
                        return;
                    }

                    Product P = new Product();
                    P.setNomMarque(ed1.getText().toString());
                    P.setNomProduit(ed2.getText().toString());
                    P.setPrixProduit(ed3.getText().toString());
                    P.setLienProduit(ed4.getText().toString());
                    list.add(P);
                    ImageView ivn = new ImageView(getContext());
                    ivn.setLayoutParams(new LinearLayout.LayoutParams(240,240));
                    //ivn.getLayout
                    Picasso.with(getContext()).load(data.getData()).into(ivn);
                    products.addView(ivn);
                    CL2.setVisibility(View.GONE);
                    CL1.setVisibility(View.VISIBLE);
                    ImageView suivant = getView().findViewById(R.id.suivant);

                    suivant.setImageResource(R.drawable.suivant_full);
                    VideoView vv = view.findViewById(R.id.videoView);
                    videoView.setVideoURI(SecondFragment.uri);
                    videoView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CL2.setVisibility(View.GONE);
                            CL3.setVisibility(View.VISIBLE);
                            ImageView iv = view.findViewById(R.id.publish);
                            iv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    DatabaseReference dataref = FirebaseDatabase.getInstance().getReference();
                                    StorageReference sref = FirebaseStorage.getInstance().getReference();
                                    Map<String, Object> map = new HashMap<>();
                                    Map<String, Object> map2 = new HashMap<>();
                                    TextView tv = view.findViewById(R.id.description);
                                    Map<String, Object> map3 = new HashMap<>();
                                    map2.put("Description", tv.getText().toString());
//                                    product
//                                    map2.put("Images", )
                                    map.put(SecondFragment.uri.toString(), map2);
                                    dataref.updateChildren(map);
                                }
                            });
                        }
                    });
                }
            });
        }
    }
}