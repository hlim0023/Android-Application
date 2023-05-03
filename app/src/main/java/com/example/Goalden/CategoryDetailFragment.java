package com.example.Goalden;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

public class CategoryDetailFragment extends Fragment {
    private static final String ARG_CATEGORY = "category";

    private String mCategory;

    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    public static CategoryDetailFragment newInstance(String category) {
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_category_detail, container, false);

        TextView categoryDescriptionTextView = rootView.findViewById(R.id.category_description);
        TextView titleTextView = rootView.findViewById(R.id.titleTextView);

        Typeface typeface = Typeface.createFromAsset(requireContext().getAssets(), "fonts/Rajdhani-Medium.ttf");

        categoryDescriptionTextView.setTypeface(typeface);
        titleTextView.setTypeface(typeface);

        // Set the description for the current category
        String description = getCategoryDescription(mCategory);
        categoryDescriptionTextView.setText(description);
        titleTextView.setText(mCategory);

        return rootView;
    }

    private String getCategoryDescription(String category) {
        String description;

        switch (category) {
            case "Committed Action":
                description = "Committed action involves setting meaningful goals and taking steps towards achieving them, even in the presence of obstacles or uncomfortable feelings. It's about staying focused on the values that guide your life and using them to inform your actions. Through committed action, you can cultivate a more fulfilling life and increase your resilience in the face of adversity.";
                break;
            case "Acceptance":
                description = "Acceptance is the process of recognizing and embracing thoughts, feelings, and sensations as they are, without trying to change or avoid them. This approach encourages a healthier relationship with difficult experiences, reducing the impact they have on your well-being. By accepting what you cannot control, you can create space for positive change and personal growth.";
                break;
            case "Cognitive Defusion":
                description = "Cognitive defusion is a technique that helps you observe your thoughts from a distance, rather than getting entangled in them. This practice allows you to see thoughts as transient mental events rather than concrete truths about yourself or the world. By defusing from your thoughts, you can reduce their power and influence over your emotions and behavior.";
                break;
            case "Being Present":
                description = "This category focuses on developing mindfulness and being fully present in each moment. By paying attention to your thoughts, feelings, and bodily sensations, you can increase your awareness of the present moment and make more intentional choices. This heightened awareness also fosters a deeper connection with your experiences, allowing you to savor positive moments and navigate challenges more effectively.";
                break;
            case "Values":
                description = "Values are the guiding principles that give your life meaning and direction. Identifying and clarifying your values can help you make decisions that align with your true self and create a more fulfilling life. In ACT, values serve as a foundation for setting goals and choosing actions that bring you closer to the person you want to be.";
                break;
            case "Self as Context":
                description = "Self-as-context refers to the idea that you are not defined by your thoughts, feelings, or experiences. Instead, you are the observer of these internal events, maintaining a constant sense of self throughout the ever-changing landscape of your experiences. Cultivating this perspective can help you foster self-compassion, reduce self-judgment, and better cope with difficult emotions and situations.";
                break;
            default:
                description = "Unknown category";
                break;
        }

        return description;
    }
}