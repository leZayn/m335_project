package m335.penz;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

public class PendencyViewHolder extends RecyclerView.ViewHolder {

    private TextView titel;
    private TextView completionDate;
    private TextView priority;

    private View itemView;

    public PendencyViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        titel = itemView.findViewById(R.id.titel);
        completionDate = itemView.findViewById(R.id.completiondate);
        priority = itemView.findViewById(R.id.priority);
    }



    public TextView getTitel() {
        return titel;
    }

    public TextView getCompletionDate() {
        return completionDate;
    }

    public TextView getPriority() {
        return priority;
    }

    public void setBackgroundColor(int color){
        itemView.setBackgroundColor(color);
    }
}