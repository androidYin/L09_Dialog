package com.example.android.l09_dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class MyDialogFragment extends DialogFragment {

    public interface CallBack {
        void call(CharSequence username, int which );
    }

    private EditText m_et_username;

    // 無參數建構子是必須的
    public MyDialogFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 透過 inflater， 讀取 fragment.xml 資源來生成 View

        // 取得 inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // 從 資源檔 fragment.xml 取得自定畫面
        //      inflate( resource , viewGroup )
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);

        // 初始化 edit_view
        m_et_username = (EditText)view.findViewById(R.id.et_username);

        // 建立 AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view) // 設定自定 view
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 稍後完成
                        CharSequence username = m_et_username.getText();
                        ((MyDialogFragment.CallBack)getActivity()).call(username, which);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 稍後完成
                        ((MyDialogFragment.CallBack)getActivity()).call(null, which);
                    }
                });
        return builder.create(); // 返回所建立的 Dialog
    }
}
