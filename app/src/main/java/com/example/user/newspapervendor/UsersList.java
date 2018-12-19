package com.example.user.newspapervendor;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class UsersList extends ArrayAdapter<User> {

    private Activity context;
    private List<User> userList;

    public UsersList( Activity context, int resource) {
        super(context, resource);
    }
}
