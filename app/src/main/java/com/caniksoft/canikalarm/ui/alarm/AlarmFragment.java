package com.caniksoft.canikalarm.ui.alarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.caniksoft.canikalarm.databinding.FragmentAlarmBinding;
import com.caniksoft.canikalarm.entity.AlarmEntity;


public class AlarmFragment extends Fragment {

    private AlarmViewModel alarmViewModel;
    private FragmentAlarmBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAlarmBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        refresh();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void refresh() {
        alarmViewModel = new ViewModelProvider(this).get(AlarmViewModel.class);
        final TextView textView = binding.textAlarm;
        if (alarmViewModel.getText() != null) {
            alarmViewModel.getText().observe(getViewLifecycleOwner(), alarmEntity -> textView.setText("alarmEntity.getName()"));
        }
    }
}