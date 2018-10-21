package roid.com.gyeongbokgung;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.et_userid)
    EditText mUserid;
    @BindView(R.id.et_username)
    EditText mUsername;
    @BindView(R.id.et_password)
    EditText mPassword;
    @BindView(R.id.btn_signup)
    Button mSignup;
    @BindView(R.id.tv_login)
    TextView mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signup)
    void signup(){
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        mSignup.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String userid = mUserid.getText().toString();
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        // TODO: Implement your own signup logic here.


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);

        mSignup.setEnabled(false);
    }

    public boolean validate() {
        boolean valid = true;

        String userid = mUserid.getText().toString();
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        if (userid.isEmpty() || userid.length() < 3) {
            mUserid.setError("at least 3 characters");
            valid = false;
        } else {
            mUserid.setError(null);
        }

        if (username.isEmpty() || username.length() < 3) {
            mUsername.setError("at least 3 characters");
            valid = false;
        } else {
            mUsername.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPassword.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        return valid;
    }

    public void onSignupSuccess() {
        mSignup.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Signup failed", Toast.LENGTH_LONG).show();
        mSignup.setEnabled(true);
    }

    @OnClick(R.id.tv_login)
    void login(){
        // Finish the registration screen and return to the Login activity
        finish();
    }
}
