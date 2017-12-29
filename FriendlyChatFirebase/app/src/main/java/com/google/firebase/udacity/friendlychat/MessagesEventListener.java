package com.google.firebase.udacity.friendlychat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * Created by mshalabi on 28.12.17.
 *
 */

public class MessagesEventListener implements ChildEventListener {
    private MessageAdapter messageAdapter;
    public MessagesEventListener(MessageAdapter messageAdapter) {
        this.messageAdapter = messageAdapter;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        FriendlyMessage friendlyMessage = dataSnapshot.getValue(FriendlyMessage.class);
        messageAdapter.add(friendlyMessage);
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
