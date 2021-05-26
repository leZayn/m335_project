package m335.penz;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PendencyViewHolder extends RecyclerView.ViewHolder {

    private TextView titel;
    private TextView completionDate;
    private TextView priority;

    public PendencyViewHolder(@NonNull View itemView) {
        super(itemView);
        titel = itemView.findViewById(R.id.titel);
        completionDate = itemView.findViewById(R.id.completiondate);
        priority = itemView.findViewById(R.id.priority);
        setBackgroundColor();
    }

    public void setBackgroundColor(){
        switch (priority.getText().toString()){
            case "1":
                itemView.setBackgroundColor((int)R.color.low_priority);
                break;
            case "2":
                itemView.setBackgroundColor((int)R.color.standard_priority);
                break;
            case "3":
                itemView.setBackgroundColor((int)R.color.high_priority);
                break;
        }
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
}
