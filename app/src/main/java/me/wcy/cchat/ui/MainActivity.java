package me.wcy.cchat.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;
import me.wcy.cchat.AppCache;
import me.wcy.cchat.R;
import me.wcy.cchat.model.CMessage;
import me.wcy.cchat.model.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView terminal;
    private EditText etAccount;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (AppCache.getMyInfo() == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        terminal = findViewById(R.id.terminal);
        etAccount = findViewById(R.id.et_account);
        etMessage = findViewById(R.id.et_message);

        terminal.setMovementMethod(ScrollingMovementMethod.getInstance());

        AppCache.getService().setReceiveMsgCallback(receiveMsgCallback);
    }

    private Callback <CMessage> receiveMsgCallback = new Callback <CMessage>() {
        @Override
        public void onEvent(int code, String msg, CMessage message) {
            terminal.append("[接收]" + message.getFrom() + ":" + message.getContent());
            terminal.append("\n");
        }
    };

    @Override
    public void onClick(View v) {
        if (etAccount.length() == 0 || etMessage.length() == 0) {
            return;
        }

        String myAccount = AppCache.getMyInfo().getAccount();
        String toUser = etAccount.getText().toString();
        if (toUser.equals(myAccount)) {
            terminal.append("[发送失败]" + "你不能发送信息给自己" + "。");
            return;
        }

        CMessage message = CMessage.buildMessage(myAccount, toUser, etMessage.getText().toString());
        AppCache.getService().sendMsg(message, (code, msg, aVoid) -> {
            if (code == 200) {
                etMessage.setText(null);
                terminal.append("[发送]" + message.getContent());
            } else {
                terminal.append("[发送失败]" + message.getContent() + "," + msg);
            }
            terminal.append("\n");
        });
    }

    //Only For example
    public void jump(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    // only for example
    public void showToast(View view) {
        Toast.makeText(this, "Hello UT!", Toast.LENGTH_LONG).show();
    }

    // only for example
    public void showDialog(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("Hello UT！")
                .setTitle("提示")
                .create();
        alertDialog.show();
    }

    //UI组件状态验证
    public void inverse(View view) {
        //checkbox.setChecked(!checkbox.isChecked());
    }
}
