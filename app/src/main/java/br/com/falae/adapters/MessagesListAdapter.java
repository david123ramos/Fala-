package br.com.falae.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.falae.R;
import br.com.falae.echoserver.engine.Message;
import br.com.falae.models.User;

public class MessagesListAdapter extends ArrayAdapter<Message> {


    private List<Message> messages;
    public MessagesListAdapter(Context context, int resource, List<Message> msgs) {
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

            holder.message_sender = (TextView) listItem.findViewById(R.id.message_sender);
            holder.message_text = (TextView) listItem.findViewById(R.id.message_text);


            listItem.setTag(holder);

        } else {
            listItem = view;
            holder = (ViewHolder)view.getTag();
        }

        Message m = this.messages.get(position);
        holder.message_sender.setText(m.getNick() + " - " + m.getHour());
        holder.message_text.setText(m.getMsg());
        return listItem;
    }


    static class ViewHolder {
        TextView message_text;
        TextView message_sender;
    }
}


