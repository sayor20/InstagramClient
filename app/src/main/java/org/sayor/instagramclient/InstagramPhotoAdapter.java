package org.sayor.instagramclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sayor on 6/16/2015.
 */
public class InstagramPhotoAdapter extends ArrayAdapter<InstagramPhoto> {
    // what data we need from adapter
    // Context, Data Source
    public InstagramPhotoAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, android.R.layout.simple_list_item_1 , objects);
    }

    // What the item looks like
    // Use the template to display each photos
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for this position
        InstagramPhoto photo = getItem(position);
        // Check if we are using a recycled view. If not, we can inflate
        if(convertView == null){
            // create a new view from template
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photos, parent, false);
        }
        // Lookup the views for populating the data (image, caption)
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhotos = (ImageView) convertView.findViewById(R.id.ivPhotos);
        // Insert the model data into each of the view items
        tvCaption.setText(photo.caption);
        ivPhotos.setImageResource(0);
        Picasso.with(getContext()).load(photo.imageURL).into(ivPhotos);
        // Return the created item as a view
        return convertView;
    }
}
