package com.example.finalapps;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    Button play_pause;
    Button siguiente;
    Button atras;
    MediaPlayer mp;
    int posicion = 0;
    MediaPlayer vectormp [] = new MediaPlayer[3];

    public MusicaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MusicaFragment newInstance(String param1, String param2) {
        MusicaFragment fragment = new MusicaFragment();
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
        vista = inflater.inflate(R.layout.fragment_musica, container, false);

        play_pause = (Button) vista.findViewById(R.id.btn_play);
        siguiente = (Button) vista.findViewById(R.id.btn_atras);
        atras = (Button) vista.findViewById(R.id.btn_adelante);

        vectormp[0] = MediaPlayer.create(getContext(), R.raw.the_neighbourhood_daddy_issues);
        vectormp[1] = MediaPlayer.create(getContext(), R.raw.kali_uchis_telepatia);
        vectormp[2] = MediaPlayer.create(getContext(), R.raw.mark_ronson_miley_cyrus_nothing_breaks_like_a_heart);

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vectormp[posicion].isPlaying()) {
                    vectormp[posicion].pause();
                    play_pause.setBackgroundResource(R.drawable.play);
                    Toast.makeText(getContext(), "Pausa", Toast.LENGTH_SHORT).show();
                } else {
                    vectormp[posicion].start();
                    play_pause.setBackgroundResource(R.drawable.pausa);
                    Toast.makeText(getContext(), "Play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicion < vectormp.length - 1) {

                    if (vectormp[posicion].isPlaying()) {
                        vectormp[posicion].stop();
                        posicion++;
                        vectormp[posicion].start();
                    } else {
                        posicion++;
                    }

                } else {
                    Toast.makeText(getContext(), "No hay más canciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posicion >= 1) {

                    if (vectormp[posicion].isPlaying()) {
                        vectormp[posicion].stop();
                        vectormp[0] = MediaPlayer.create(getContext(), R.raw.kali_uchis_telepatia);
                        vectormp[1] = MediaPlayer.create(getContext(), R.raw.the_neighbourhood_daddy_issues);
                        vectormp[2] = MediaPlayer.create(getContext(), R.raw.mark_ronson_miley_cyrus_nothing_breaks_like_a_heart);
                        posicion--;

                        vectormp[posicion].start();

                    } else {
                        posicion--;
                    }

                } else {
                    Toast.makeText(getContext(), "No hay más canciones", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return vista;
    }

}