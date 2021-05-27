package m335.penz.pendency;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import m335.penz.R;

public class PendencyViewHolder extends RecyclerView.ViewHolder {

    private TextView titel;
    private TextView completionDate;
    private TextView priority;

    private CardView cardView;

    public PendencyViewHolder(@NonNull View itemView) {
        super(itemView);
        titel = itemView.findViewById(R.id.titel);
        completionDate = itemView.findViewById(R.id.completiondate);
        priority = itemView.findViewById(R.id.priority);
        cardView = itemView.findViewById(R.id.card);
    }

    public void setCardColor(int color){
        cardView.setCardBackgroundColor(color);
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
<<<<<<< Updated upstream
=======

<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
