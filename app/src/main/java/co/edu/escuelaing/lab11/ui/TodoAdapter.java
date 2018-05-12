package co.edu.escuelaing.lab11.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.escuelaing.lab11.R;
import co.edu.escuelaing.lab11.model.todo;



public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>    {


    private final List<todo> todosList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView description;
        public TextView priority;
        public TextView completed;
        public ViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.description);
            priority = (TextView) view.findViewById(R.id.priority);
            completed = (TextView) view.findViewById(R.id.completed);

        }
    }

    public TodoAdapter(List<todo> moviesList) {
        this.todosList = moviesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_todos, parent, false);

        return new ViewHolder(itemView);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        todo todo = todosList.get(position);
        holder.description.setText(todo.getDescription());
        holder.priority.setText(Integer.toString(todo.getPriority()));
        holder.completed.setText(todo.isCompleted());

    }

    @Override
    public int getItemCount() {
        return todosList.size();
    }
}
