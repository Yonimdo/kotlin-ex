package com.mdo.yoni.eshop.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mdo.yoni.eshop.R;
import com.mdo.yoni.eshop.WordsAdapter;
import com.mdo.yoni.eshop.data.SharedPreferencesManagerKt;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import java.util.ArrayList;

/**
 * Created by Yoni Mood on 23/05/2017.
 */

public class SearchWordsDialog {

    private ArrayList<String> selectedContacts;
    private WordsAdapter adapter;

    public interface onSelected {
        void onReturn();
    }

    private Activity ctx;

    public SearchWordsDialog(Activity ctx) {
        this.ctx = ctx;

    }



    public void open(final onSelected selected) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        View dialogView = ctx.getLayoutInflater().inflate(R.layout.spam_words_dialog, null);

        final EditText newText = (EditText) dialogView.findViewById(R.id.text);
        final Button save = (Button) dialogView.findViewById(R.id.add_to_spam);
        final ImageButton search = (ImageButton) dialogView.findViewById(R.id.search);
        final TextView title = (TextView) dialogView.findViewById(R.id.spam_title);
        RecyclerView recyclerView = (RecyclerView) dialogView.findViewById(R.id.asymmetric_grid_view);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        flowLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(flowLayoutManager);
        adapter = new WordsAdapter(ctx);
        recyclerView.setAdapter(adapter);
        title.setText(R.string.search);
        final Dialog dialog = builder.setView(dialogView).create();
        dialog.setCanceledOnTouchOutside(false);

//        try {
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        } catch (Exception e) {
//
//        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Spam s = new Spam(newText.getText().toString(), Spam.TYPE_TEXT);
//                if (s.getText().length() < 2) {
//                    return;
//                }
//                s.save(ctx);
//                adapter.setValues();

                ArrayList<String> words = new ArrayList<String>(SharedPreferencesManagerKt.getSearchWords(ctx));
                words.add(newText.getText().toString());
                SharedPreferencesManagerKt.setSearchWords(ctx, words);
                adapter.refresh();
                newText.setText("");
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected != null) {
                    selected.onReturn();
                }
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
