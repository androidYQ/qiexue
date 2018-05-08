package com.xgj.qiaxue.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.xgj.qiaxue.R;

/**
 * Created by qi.yang on 2018/5/7 0007.
 */

public class MakeSureDialog {
    Context context;
    String hintText = "";
    String sureButtonText = "";
    public MakeSureDialog(Context context,String hintText ,String sureButtonText) {
        this.context = context;
        this.hintText = hintText;
        this.sureButtonText = sureButtonText;
        init();
    }
    
    Dialog makeSureDialog;
    TextView tv_sure;

    public void setListener(View.OnClickListener onClickListener){
        tv_sure.setOnClickListener(onClickListener);
    }

    private void init(){
        if (makeSureDialog != null) {
            makeSureDialog.dismiss();
        }
        makeSureDialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_choose_post_tip, null);
        makeSureDialog.setContentView(view);
        tv_sure = (TextView) view.findViewById(R.id.tv_giveUp);
        TextView tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);

        TextView tv_hint = (TextView) view.findViewById(R.id.tv_hint);


        tv_hint.setText(hintText);
        tv_sure.setText(sureButtonText);




        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeSureDialog.dismiss();
            }
        });

        Window win = makeSureDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
        makeSureDialog.show();
    }
    
    public void dismiss(){
        if(makeSureDialog != null){
            makeSureDialog.dismiss();
        }
    }
}
