package com.example.hairsalonbookingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Barberhome extends AppCompatActivity{
    TextView ebarber ;
    TextView date,time,name;
    TextView dateheading,timeheading;
    TextView counter;
    int count=0;
    Button accept,reject;
    ImageView image;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionRef = db.collection("Documents");
    CollectionReference collectionRef2 = db.collection("users");

    String namedata = "";
    String email = "";
    String imgurl = null;
    String phone = "";

    RelativeLayout relativeLayout;
    LinearLayout linearlayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberhome);
        db.collection("count").document("count").get().addOnSuccessListener(documentSnapshot->{
            if(documentSnapshot.exists()){
                Map<String, Object> map = documentSnapshot.getData();
                count = map.get("count")!=null?Integer.parseInt(map.get("count").toString()):0;
            }
            else
                count = 0;
            getCount();
        });



        if (getSupportActionBar() != null)  //remove top actionbar
        {
            getSupportActionBar().hide();
        }
        ebarber = findViewById(R.id.ebarber);
        linearlayout = findViewById(R.id.linearlayout);
        Bundle bundle = getIntent().getExtras();
        String uname = bundle.getString("username");
        ebarber.setText(uname);
        collectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot querySnapshot) {
                        List<DocumentSnapshot> documents = querySnapshot.getDocuments();

                        for (DocumentSnapshot document : documents) {
                            if (document.exists()) {
                                // Access the data of each document

                                String id2 =document.getString("id");
                                namedata = "";
                                String data = document.getString("date");
                                String timedata = document.getString("time");
                                imgurl = document.getString("imgurl");
                                String barberdata = document.getString("barber");
                                String accepted = document.getString("Accepted");

                                if(accepted.equals("pending")) {
                                    DocumentReference userDocRef = collectionRef2.document(id2);
                                    userDocRef.get()
                                            .addOnSuccessListener(documentSnapshot -> {
                                                if (documentSnapshot.exists()) {
                                                    // User document exists, retrieve the data
                                                    Map<String, Object> userData = documentSnapshot.getData();

                                                    // Access specific fields from the userData map
                                                    namedata = (String) userData.get("Name");
                                                    phone = (String) userData.get("Phone");
                                                    createRealativeLayout();
                                                    createTextView(data, 100, 375, date, 10);
                                                    createTextView(timedata + "", 160, 375, time, 10);
                                                    createTextView(barberdata + "", 220, 400, time, 10);
                                                    createTextView(phone + "", 280, 400, time, 10);
                                                    createTextView(id2 + "", 340, 375, time, 10);
                                                    createTextView("Date", 100, 300, dateheading, 10);
                                                    createTextView("Time", 160, 300, timeheading, 10);
                                                    createTextView("Barber", 220, 300, timeheading, 10);
                                                    createTextView("Phone", 280, 300, timeheading, 10);
                                                    createTextView("ID", 340, 300, timeheading, 10);
                                                    createTextView(namedata, 10, 350, timeheading, 16);
                                                    createButtonDone(100, 825, accept);
                                                    createButtonReject(100, 950, reject);
                                                    createImage(40, 10, image, "url");
                                                    linearlayout.addView(relativeLayout);
                                                } else {
                                                    namedata = null;
                                                    phone = null;
                                                }
                                            })
                                            .addOnFailureListener(e -> {
                                                namedata = null;
                                                phone = null;
                                            });
                                }


                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error occurred while retrieving data
                    }
                });

    }


    public void createRealativeLayout(){
        relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        relativeLayoutParams.height = 400;
        relativeLayout.setBackgroundColor(Color.GRAY);

        relativeLayout.setLayoutParams(relativeLayoutParams);
    }

    public void createTextView(String Date,int top,int left,TextView Text,int size){
        Text = new TextView(this);
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
//        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        int leftMargin = left; // Left margin in pixels
        int topMargin = top; // Top margin in pixels
        int rightMargin = 0; // Right margin in pixels
        int bottomMargin = 0; // Bottom margin in pixels
        textViewParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        Text.setText(Date);
        Text.setTextColor(Color.BLACK);
        Text.setTextSize(size);
        relativeLayout.addView(Text, textViewParams);

    }

    public void createTextViewId(String Date,int top,int left,TextView Text,int size){
        TextView id = new TextView(this);

        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
//        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        int leftMargin = left; // Left margin in pixels
        int topMargin = top; // Top margin in pixels
        int rightMargin = 0; // Right margin in pixels
        int bottomMargin = 0; // Bottom margin in pixels
        textViewParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        id.setText(Date);
        id.setTextColor(Color.BLACK);
        id.setTextSize(size);
        id.setTag("idbtn");

        relativeLayout.addView(id, textViewParams);

    }
    public void createButtonDone(int top,int left,Button btn){
        btn = new Button(this);
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                100,100
        );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup parentView = (ViewGroup) view.getParent();

                if(parentView!=null){
                        TextView childView =(TextView) parentView.getChildAt(4);
                        String text = childView.getText().toString();
                        parentView.removeViewAt(11);
                        parentView.removeViewAt(11);
                        count++;
                        HashMap<String,Object> map = new HashMap<String,Object>();
                        map.put("count",count);
                        CollectionReference cr = db.collection("Documents");
                        DocumentReference dc = cr.document(text);
                        HashMap<String,Object> updates = new HashMap<String,Object>();
                        updates.put("Accepted","Accepted");
                        dc.update(updates);
                        db.collection("count").document("count").set(map);
                        getCount();
                }
            }
        });


//        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        int leftMargin = left; // Left margin in pixels
        int topMargin = top; // Top margin in pixels
        int rightMargin = 0; // Right margin in pixels
        int bottomMargin = 0; // Bottom margin in pixels
        textViewParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//        btn.setLayoutParams(new ViewGroup.LayoutParams(30,30));

        btn.setBackgroundResource(R.drawable.ok);
//        btn.setText(Date);
//        btn.setTextColor(Color.GRAY);
//        btn.setTextSize(size);
        relativeLayout.addView(btn, textViewParams);

    }

    public void createButtonReject(int top,int left,Button btn){
        btn = new Button(this);
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                100,100
        );
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup parentView = (ViewGroup) view.getParent();

                if(parentView!=null){
                    TextView childView =(TextView) parentView.getChildAt(4);

                    parentView.removeViewAt(11);
                    parentView.removeViewAt(11);

                    String text = childView.getText().toString();
                    HashMap<String,Object> map = new HashMap<String,Object>();
                    CollectionReference cr = db.collection("Documents");
                    DocumentReference dc = cr.document(text);
                    HashMap<String,Object> updates = new HashMap<String,Object>();
                    updates.put("Accepted","Rejected");
                    dc.update(updates);
                }
            }
        });
//        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        int leftMargin = left; // Left margin in pixels
        int topMargin = top; // Top margin in pixels
        int rightMargin = 0; // Right margin in pixels
        int bottomMargin = 0; // Bottom margin in pixels
        textViewParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        btn.setBackgroundResource(R.drawable.failed2);
//        btn.setText(Date);
//        btn.setTextColor(Color.GRAY);
//        btn.setTextSize(size);
        relativeLayout.addView(btn, textViewParams);

    }
    public void createImage(int top,int left,ImageView img,String url){
        img = new ImageView(this);
        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
                200,200
        );

//        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        int leftMargin = left; // Left margin in pixels
        int topMargin = top; // Top margin in pixels
        int rightMargin = 0; // Right margin in pixels
        int bottomMargin = 0; // Bottom margin in pixels
        textViewParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        img.setBackgroundResource(R.drawable.barber);
//        btn.setText(Date);
//        btn.setTextColor(Color.GRAY);
//        btn.setTextSize(size);
        relativeLayout.addView(img, textViewParams);

    }

    public void getCount(){
        counter = findViewById(R.id.counter);
        counter.setText("Successfull Appointment "+count);
    }

//    public void createTime(String Time){
//        time = new TextView(this);
//        RelativeLayout.LayoutParams textViewParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//        );
////        textViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        int leftMargin = 375; // Left margin in pixels
//        int topMargin = 160; // Top margin in pixels
//        int rightMargin = 0; // Right margin in pixels
//        int bottomMargin = 0; // Bottom margin in pixels
//        textViewParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
//
//        time.setText(Time);
//        time.setTextColor(Color.GRAY);
//        time.setTextSize(10);
//        relativeLayout.addView(time, textViewParams);
//
//    }
}
