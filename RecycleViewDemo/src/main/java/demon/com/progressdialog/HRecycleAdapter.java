package demon.com.progressdialog;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by D&LL on 2017/5/12.
 */

public class HRecycleAdapter extends RecyclerView.Adapter<HRecycleAdapter.ViewHolder> {
    private List<Info> list;

    public HRecycleAdapter(List<Info> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hrecycle_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.infoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(parent.getContext(), WaterfallActivity.class);
                parent.getContext().startActivity(intent);
            }
        });

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                Info info = list.get(pos);
                Toast.makeText(v.getContext(), info.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Info info = list.get(position);
        holder.img.setImageResource(info.getId());
        holder.text.setText(info.getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.text)
        TextView text;
        View infoView;

        public ViewHolder(View view) {
            super(view);
            infoView = view;
            ButterKnife.bind(this, view);
        }
    }
}
