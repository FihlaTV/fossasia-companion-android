package org.fossasia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;

import org.fossasia.R;
import org.fossasia.model.Speaker;
import org.fossasia.utils.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by Abhishek on 14/02/15.
 */
public class SpeakerAdapter extends BaseAdapter {

    private ArrayList<Speaker> mSpeakerList;
    private Context mContext;
    private LayoutInflater mInflater;

    public SpeakerAdapter(Context context, ArrayList<Speaker> speakerList) {
        this.mSpeakerList = speakerList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mSpeakerList.size();
    }

    @Override
    public Speaker getItem(int position) {
        return mSpeakerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (mInflater == null) {
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        View row;
        if (convertView == null) {
            row = mInflater.inflate(R.layout.item_speaker, parent, false);
        } else {
            row = convertView;
        }

        SpeakerHolder holder = new SpeakerHolder();
        holder.name = (TextView) row.findViewById(R.id.textView_speaker_name);
        holder.designation = (TextView) row.findViewById(R.id.textView_speaker_designation);
        holder.speakerImage = (NetworkImageView) row.findViewById(R.id.imageView_speaker_pic);
        holder.speakerImage.setDefaultImageResId(R.drawable.default_user);
        holder.information = (TextView) row.findViewById(R.id.textView_speaker_information);
        holder.linkedIn = (ImageView) row.findViewById(R.id.imageView_linkedin);
        holder.twitter = (ImageView) row.findViewById(R.id.imageView_twitter);
        Speaker speaker = getItem(position);
        holder.speakerImage.setImageUrl(speaker.getProfilePicUrl(), VolleySingleton.getImageLoader(mContext));
        holder.name.setText(speaker.getName());
        holder.designation.setText(speaker.getDesignation());
        holder.information.setText(speaker.getInformation());
        if (speaker.getLinkedInUrl().length() == 0) {
            holder.linkedIn.setVisibility(View.GONE);
        } else {
            holder.linkedIn.setVisibility(View.VISIBLE);
            holder.linkedIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "LinkedIn Clicked at position: " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (speaker.getTwitterHandle().length() == 0) {
            holder.twitter.setVisibility(View.GONE);
        } else {
            holder.twitter.setVisibility(View.VISIBLE);
            holder.twitter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Twitter Clicked at position: " + position, Toast.LENGTH_SHORT).show();
                }
            });
        }
        row.setTag(speaker);
        return row;
    }

//    @Override
//    public boolean isEnabled(int position) {
//        return false;
//    }

    public static class SpeakerHolder {
        TextView name;
        TextView designation;
        NetworkImageView speakerImage;
        TextView information;
        ImageView linkedIn;
        ImageView twitter;

    }
}