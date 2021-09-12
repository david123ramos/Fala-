package br.com.falae.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.falae.R;
import br.com.falae.models.User;

public class MessagesListAdapter extends ArrayAdapter<String> {


    private List<String> messages;
    public MessagesListAdapter(Context context, int resource, List<String> msgs) {
        super(context, resource, msgs);
        this.messages = msgs;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View listItem = null;
        ViewHolder holder = null;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listItem = inflater.inflate(R.layout.messages_list, null, true);

            holder = new ViewHolder();

            holder.message_text = (TextView) listItem.findViewById(R.id.message_text);
            listItem.setTag(holder);

        } else {
            listItem = view;
            holder = (ViewHolder)view.getTag();
        }


        holder.message_text.setText(this.messages.get(position));
        return listItem;
    }


    static class ViewHolder {
        TextView message_text;
    }
}


