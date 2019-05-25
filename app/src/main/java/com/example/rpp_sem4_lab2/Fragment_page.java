package com.example.rpp_sem4_lab2;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class Fragment_page extends Fragment {
    String graphic;
    String helmet;

    public Fragment_page() {}

    @SuppressLint("ValidFragment")
    public Fragment_page(String graphic, String helptext) {
        Bundle arguments = new Bundle();
        this.graphic = graphic;
        this.helmet = helptext;
        arguments.putString("helptext", helptext);
        arguments.putString("graphic", graphic);
        this.setArguments(arguments);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_view_pager, container, false);

        if(getArguments().getString("graphic") != null)
            graphic = getArguments().getString("graphic");
        if(getArguments().getString("helptext") != null)
            helmet = getArguments().getString("helptext");

        TextView desc = view.findViewById(R.id.hepltext);

        desc.setText(helmet);

        if(!graphic.equals("")) {
            ImageView image = view.findViewById(R.id.graphic);
            DownloadImageTask load_image_task = new DownloadImageTask(image);
            String base_url = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/";
            load_image_task.execute(base_url + graphic);
        }

        return view;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if(result != null) {
                bmImage.setImageBitmap(result);
            }
        }
    }
}