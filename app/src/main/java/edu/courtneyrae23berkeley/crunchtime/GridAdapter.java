package edu.courtneyrae23berkeley.crunchtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

/**
 * Created by court_000 on 2/1/2016.
 */


public class GridAdapter extends BaseAdapter {
    private Context context;
    private Integer[] imageIds = {
            R.drawable.jog, R.drawable.situps, R.drawable.pushup, R.drawable.squats,
            R.drawable.walking, R.drawable.plank,R.drawable.jumpingjacks, R.drawable.pullups,
            R.drawable.cycling, R.drawable.leglifts, R.drawable.swimming, R.drawable.stairs
    };
    static final String[] exercises = new String[] {
            "jogging", "situps", "pushups",
            "squats", "walking", "plank",
            "jumping jacks", "pullups", "cycling",
            "leg-lifts", "swimming", "stair-climbing"};

    public GridAdapter(Context c) {
        context = c;
    }

    public int getCount() {
        return imageIds.length;
    }

    public Object getItem(int position) {
        return imageIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if(convertView==null){
            v = LayoutInflater.from(context).inflate(R.layout.image_text_view,null);
            v.setLayoutParams(new GridView.LayoutParams(210, 210));

            ImageView imageView = (ImageView)v.findViewById(R.id.image);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
            imageView.setImageResource(imageIds[position]);

            TextView textView = (TextView) v.findViewById(R.id.title);
            textView.setText(exercises[position]);
            textView.setAllCaps(true);

            String[] reps = new String[] {"situps", "pushups", "pullups", "squats"};

            TextView equivalent = (TextView) v.findViewById(R.id.equivalent);
            double rep = MainActivity.startReps[position];
            rep = ((double)((int) (10*rep)) / 10);
            if (Arrays.asList(reps).contains(exercises[position])) {
                equivalent.setText(String.valueOf(rep) +" reps");
            } else {
                equivalent.setText(String.valueOf(rep) + " mins");
            }
        }
        else
        {
            v = convertView;
        }


        return v;
    }
}

