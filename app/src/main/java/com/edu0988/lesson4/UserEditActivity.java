package com.edu0988.lesson4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.edu0988.lesson4.databinding.ActivityUserEditBinding;


public class UserEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserEditBinding binding = ActivityUserEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        User user;
        if (getIntent().hasExtra("user")) {
            user = (User) getIntent().getSerializableExtra("user");
            setTitle("Редактирование пользователя");
        }
        else {
            user = new User();
            setTitle("Новый пользователь");
        }

        binding.nameEt.setText(user.getName());
        binding.lastnameEt.setText(user.getLastname());
        binding.phoneEt.setText(user.getPhone());

        binding.saveBtn.setOnClickListener(v -> {
                    user.setName(binding.nameEt.getText().toString());
                    user.setLastname(binding.lastnameEt.getText().toString());
                    user.setPhone(binding.phoneEt.getText().toString());
                    finish();
                }
        );

        binding.deleteBtn.setOnClickListener(v -> {
                    finish();
                }
        );

    }
}