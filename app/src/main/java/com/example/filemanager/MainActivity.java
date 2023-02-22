package com.example.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);
        final Button b1 = findViewById(R.id.b1);
        final Button b2 = findViewById(R.id.b2);
        final Button b3 = findViewById(R.id.b3);
        final Button b4 = findViewById(R.id.b4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b1.setText("pressed");
            }


        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b2.setText("pressed");
            }
        });

        final ListView listView = findViewById(R.id.listView);
        final TextAdapter textAdapter1 = new TextAdapter();
        listView.setAdapter(textAdapter1);

        List<String> example = new ArrayList<>();
        for (int i = 0; i <= 100; i++){
            example.add(String.valueOf(i));
        }
    textAdapter1.setData(example);
    }

    class TextAdapter extends BaseAdapter{

        private List<String> data = new ArrayList<>();

        public void setData(List<String> data){
            if(data != null){
                this.data.clear();
                if(data.size() > 0){
                    this.data.addAll(data);
                }
            notifyDataSetChanged();
            }
        }


        @Override
        public int getCount(){
            return data.size();
        }

        @Override
        public String getItem(int position){
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
                convertView.setTag(new ViewHolder((TextView) convertView.findViewById(R.id.textItem)));
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            final String item = getItem(position);
            holder.info.setText(item);
            return convertView;
        }
        class ViewHolder {
            TextView info;
            ViewHolder(TextView info){
                this.info = info;
            }
        }
    }

    private static final int REQUEST_PERMISSIONS = 1234;

    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int PERMISSIONS_COUNT = 2;

    private boolean arePermissionDenied(){
        int p = 0;
        while (p < PERMISSIONS_COUNT) {
            if(checkSelfPermission(PERMISSIONS[p]) != PackageManager.PERMISSION_GRANTED){
                return true;
            }
            p++;
        }
        return false;
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(arePermissionDenied()){
            requestPermissions(PERMISSIONS, REQUEST_PERMISSIONS);
        }
    }
}