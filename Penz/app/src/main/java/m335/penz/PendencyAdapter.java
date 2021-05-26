package m335.penz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import m335.penz.model.Pendency;

public class PendencyAdapter extends RecyclerView.Adapter<PendencyViewHolder> {

    private List<Pendency> pendencies;

    public PendencyAdapter(List<Pendency> pendencies) {
        this.pendencies = pendencies;
    }

    @NonNull
    @Override
    public PendencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.pendency_card, parent, false);
        return new PendencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendencyViewHolder holder, int position) {
    holder.getTitel().setText(pendencies.get(position).getTitle());
    if(pendencies.get(position).getCompletionDate() != null){
        holder.getCompletionDate().setText(pendencies.get(position).getCompletionDate().toString());
    }
        holder.getCompletionDate().setText(pendencies.get(position).getPriority());
    }

    @Override
    public int getItemCount() {
        return pendencies.size();
    }
}
