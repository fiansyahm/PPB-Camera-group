package com.salmanarif.tugasppb.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.salmanarif.tugasppb.DetailAttendanceFragment;
import com.salmanarif.tugasppb.NavigationActivity;
import com.salmanarif.tugasppb.R;
import com.salmanarif.tugasppb.databinding.FragmentHomeBinding;
import com.salmanarif.tugasppb.ui.absent.AbsentformFragment;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

//                textView.setText(s);
                Button homefragment=root.findViewById(R.id.homefragmentabsent);
                homefragment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        homefragment.setVisibility(View.GONE);
                        ((NavigationActivity) getActivity()).Rid=R.id.homefragmentabsent;
                        Fragment fragment = new AbsentformFragment();
                        ((NavigationActivity) getActivity()).newFragment(fragment);
                    }
                });

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}