package br.com.falae.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import br.com.falae.R;
import br.com.falae.models.User;

public class UserListAdapter extends ArrayAdapter<User> {


    private List<User> users;
    public UserListAdapter(Context context, int resource, List<User> users) {
        super(context, resource, users);
        this.users = users;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View listItem = null;
        ViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listItem = inflater.inflate(R.layout.user_list, null, true);

            holder = new ViewHolder();

            holder.nicknameLabel = (TextView) listItem.findViewById(R.id.label_nickname);
            listItem.setTag(holder);

        } else {
            listItem = view;
            holder = (ViewHolder)view.getTag();
        }


        holder.nicknameLabel.setText(this.users.get(position).getNick()+" "+this.users.get(position).getIp());
        return listItem;
    }


    static class ViewHolder {
        TextView nicknameLabel;
    }
}


