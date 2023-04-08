package edu.uw.tcss450.uiandnavigationlab.ui.blog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.uiandnavigationlab.R;
import edu.uw.tcss450.uiandnavigationlab.databinding.FragmentBlogPostBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlogPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlogPostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlogPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlogPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlogPostFragment newInstance(String param1, String param2) {
        BlogPostFragment fragment = new BlogPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BlogPostFragmentArgs args = BlogPostFragmentArgs.fromBundle(getArguments());

        FragmentBlogPostBinding binding = FragmentBlogPostBinding.bind(getView());

        binding.textPubdate.setText(args.getBlog().getPubDate());
        binding.textTitle.setText(args.getBlog().getTitle());

        final String preview =  Html.fromHtml(
                        args.getBlog().getTeaser(),
                        Html.FROM_HTML_MODE_COMPACT)
                .toString();
        binding.textPreview.setText(preview);

        //Note we are using an Intent here to start the default system web browser
        binding.buttonUrl.setOnClickListener(button ->
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(args.getBlog().getUrl()))));

    }

}