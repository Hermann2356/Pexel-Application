package adapter;

import android.app.DownloadManager;
import android.util.Log;
import android.view.LayoutInflater;

import com.bumptech.glide.Glide;
import com.example.pexelapplication.model.Photo;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pexelapplication.databinding.ItemPhotoBinding;
import com.example.pexelapplication.model.Src;
import com.example.pexelapplication.view.QueryActivity;
import com.example.pexelapplication.viewmodel.QueryViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

/**
 * Step 1: Extend RecyclerView.Adapter<>
 * Step 2: Create custom ViewHolder class and extend RecyclerView.ViewHolder
 * Step 3: Create constructor matching super [use Alt + Enter] for our CustomViewHolder
 * Step 4: Pass custom ViewHolder into the RecyclerView.Adapter<PASS_IT_HERE>
 * Step 5: Implement the Adapter methods: onCreateViewHolder, onBindViewHolder, getItemCount [use Alt + Enter]
 * Step 6: Pass list to the Adapter using Constructor or any other way
 * Step 7: Pass the list size to getItemCount as the return value
 * Step 8: setup onCreateViewHolder by inflating the layout using LayoutInflater or ViewBinding
 * Ex: LayoutInflater.from(parent.getContext()).inflate(R.layout.my_layout_name, parent, false);
 * Ex: MyItemBinding.inflate(layoutInflater, parent, false);
 * Step 9: Finish onCreateViewHolder setup by passing the inflated view to the customViewHolder class and returning it
 * Step 10: Setup views in your custom view holder class
 * Step 11 Setup onBindViewHolder, retrieve the item from list using the provided position and
 * use it to load the ItemView
 */
public class PexelAdapter extends RecyclerView.Adapter<PexelAdapter.PexelViewHolder> {
    private final List<Photo> photos;
    private PixelClickListener listener;

    public PexelAdapter(List<Photo>  photos , PixelClickListener listener) {
        this.photos = photos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PexelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPhotoBinding binding = ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new PexelViewHolder(binding, listener) ;
    }

    @Override
    public void onBindViewHolder(@NonNull PexelViewHolder holder, int position) {
        Photo photo = photos.get(position);
        holder.setOnClick(photo);
        holder.setImage(photo);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    static class PexelViewHolder extends RecyclerView.ViewHolder {

        private ItemPhotoBinding binding;
        private PixelClickListener listener;

        public PexelViewHolder(@NonNull ItemPhotoBinding binding, PixelClickListener listener) {
            super(binding.getRoot());
            this.binding = binding;
            this.listener = listener;
        }

        private void setImage(@NotNull Photo photo) {

            Glide.with(binding.getRoot())
                    .load(photo.getSrc().getOriginal())
                    .centerCrop()
                    .into(binding.ivPhoto);
        }

        private void setOnClick(Photo photo) {
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.itemClicked(photo);
                    }
                }
            });
        }

    }
}
