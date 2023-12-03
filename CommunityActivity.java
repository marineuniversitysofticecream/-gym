package kr.ac.mmu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import kotlin.jvm.internal.markers.KMappedMarker;

public class CommunityActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<CommunityData> chatList;
    private String nick="name1";

    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Button_send=findViewById(R.id.Button_send);
        EditText_chat=findViewById(R.id.EditText_chat);

        Button_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String message=EditText_chat.getText().toString();

               if(message!=null){
                   CommunityData chat=new CommunityData();
                   chat.setName(nick);
                   chat.setMessage(message);
                   myRef.setValue(chat);
               }
            }
        });

        mRecyclerView=findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        chatList=new ArrayList<>();

        mAdapter=new chatAdapter(chatList, CommunityActivity.this, nick);
        mRecyclerView.setLayoutManager(mLayoutManager);

        FirebaseDatabase  database=FirebaseDatabase.getInstance();
        myRef= database.getReference("message");
        //1. reclerView-반복(채팅방 스크롤)
        //1-1. reclerView-chatData
        //2. 디비 내용 넣기
        //3. 상대 휴대폰에 채팅 내용이 보임-디비에서 내용 가져오기(get)
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                CommunityData chat = snapshot.getValue(CommunityData.class);
                ((chatAdapter) mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

CommunityActivity.java
