package pl.kapiz.yourfirehouse.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import pl.kapiz.yourfirehouse.R
import pl.kapiz.yourfirehouse.base.BaseActivity
import pl.kapiz.yourfirehouse.ui.main.MainActivity
import javax.inject.Inject

class LoginActivity : BaseActivity<LoginPresenter>(), LoginView {

    @Inject
    override lateinit var presenter: LoginPresenter

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.onAttachView(this, this)
    }

    override fun initView() {
        val editTextEmail: EditText = findViewById(R.id.loginEmail)
        val editTextPassword: EditText = findViewById(R.id.loginPassword)
        val buttonSubmit: Button = findViewById(R.id.loginSubmitButton)

        buttonSubmit.setOnClickListener {
            presenter.onSubmitButtonClicked(
                editTextEmail.text.toString(),
                editTextPassword.text.toString()
            )
        }
    }

    override fun showProgress(show: Boolean) {
        findViewById<View>(R.id.loginProgress).visibility = if (show) VISIBLE else GONE
    }

    override fun showLoginForm(show: Boolean) {
        findViewById<View>(R.id.loginFormContainer).visibility = if (show) VISIBLE else GONE
    }

    override fun openMainActivity() {
        startActivity(MainActivity.getStartIntent(this))
        finish()
    }
}
