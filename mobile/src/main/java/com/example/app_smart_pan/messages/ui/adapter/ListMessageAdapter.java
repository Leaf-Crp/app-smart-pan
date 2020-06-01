package com.example.app_smart_pan.messages.ui.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.app_smart_pan.R;
import com.example.services.beans.message.Message;
import org.jetbrains.annotations.NotNull;
import java.text.SimpleDateFormat;
import java.util.List;

public class ListMessageAdapter extends RecyclerView.Adapter{
    private List<Message> mMessageList;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private Context mContext;

    public ListMessageAdapter(Context context, List<Message> messages) {
        mContext = context;
        mMessageList = messages;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    /**
     * determine type de message à afficher entrant ou sortant
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        Log.d("SEND", "MERDE");
        Message message = (Message) mMessageList.get(position);
        int sessionId = 1;
        if (message.getId_user() == sessionId) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }


    /**
     * Charge la vue adapté au type de message
      * @param parent
     * @param viewType
     * @return
     */
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }
        return null;
    }


    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = (Message) mMessageList.get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }


   private static class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;
        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        }
        void bind(Message message) {
            messageText.setText(message.getContent());
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String date = formatter.format(message.getDate());
            timeText.setText(date);
        }
    }

   private static class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;
        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }
        void bind(Message message) {
            messageText.setText(message.getContent());
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            String date = formatter.format(message.getDate());
            timeText.setText(date);
            nameText.setText(String.valueOf(message.getUser().getFirstname() + " "+ message.getUser().getLastname()));
            // Utils.displayRoundImageFromUrl(mContext, message.getSender().getProfileUrl(), profileImage);
        }
    }
}


