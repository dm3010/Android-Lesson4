package com.edu0988.lesson4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edu0988.lesson4.databinding.ActivityMainBinding;
import com.edu0988.lesson4.databinding.SingleItemBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserAdapter userAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        userAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Список пользователей");

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setName("Пользователь "+i);
            user.setLastname("Фамилия "+i);
            userList.add(user);
        }

        userAdapter = new UserAdapter(userList);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(userAdapter);

        binding.fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserEditActivity.class);
            startActivity(intent);
        });

    }

    public static class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

        private List<User> users;

        View.OnClickListener onClickListener = itemView -> {
            User user = (User) itemView.getTag();
            Intent intent = new Intent(itemView.getContext(), UserEditActivity.class);
            intent.putExtra("user", user);
            itemView.getContext().startActivity(intent);
        };

        public UserAdapter(List<User> contacts) {
            this.users = contacts;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            SingleItemBinding binding =
                    SingleItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.itemView.setTag(users.get(position));
            holder.itemView.setOnClickListener(onClickListener);
            holder.itemTextView.setText(
                    users.get(position).getName() + "\n" + users.get(position).getLastname()
            );
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        private class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView itemTextView;

            public ViewHolder(SingleItemBinding binding) {
                super(binding.getRoot());
                itemTextView = binding.itemTextView;
            }
        }
    }
}


