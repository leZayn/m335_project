package m335.penz.pendency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import m335.penz.R;
import m335.penz.model.Pendency;

/**
 * @author Severin Baur
 * @author Phearum Svay
 *
 * This class, MainActivity, is responsible for the functionality of {@link R.layout.activity_create}
 */
public class PendencyAdapter extends RecyclerView.Adapter<PendencyViewHolder> {

    private List<Pendency> pendencies;

    private Context context;

    public PendencyAdapter(List<Pendency> pendencies) {
        this.pendencies = pendencies;
    }

    @NonNull
    @Override
    public PendencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.pendency_card, parent, false);
        return new PendencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendencyViewHolder holder, int position) {
    holder.getTitel().setText(pendencies.get(position).getTitle());
    if(pendencies.get(position).getCompletionDate() != null){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        holder.getCompletionDate().setText(pendencies.get(position).getCompletionDate().format(formatter));
        if(pendencies.get(position).getCompletionDate().isBefore(LocalDate.now())) {
            holder.getCompletionDate().setTextColor(context.getColor(R.color.error));
        }
    }
        setBackgroundColor(holder, pendencies.get(position));
        holder.getPriority().setText(getPriorityString(pendencies.get(position).getPriority()));
    }

    @Override
    public int getItemCount() {
        return pendencies.size();
    }

    private String getPriorityString(int priority){
        switch(priority){
            case 1:
                return context.getString(R.string.low);
            case 2:
                return context.getString(R.string.standard);
            case 3:
                return context.getString(R.string.high);
        }
        return null;
    }

    private void setBackgroundColor(PendencyViewHolder pendencyViewHolder, Pendency pendency){
        switch (pendency.getPriority()){
            case 1:
                pendencyViewHolder.setCardColor(context.getColor(R.color.low_priority));
                break;
            case 2:
                pendencyViewHolder.setCardColor(context.getColor(R.color.standard_priority));
                break;
            case 3:
                pendencyViewHolder.setCardColor(context.getColor(R.color.high_priority));
                break;
        }
    }
}
