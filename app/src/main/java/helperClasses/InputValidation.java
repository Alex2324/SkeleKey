package helperClasses;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class InputValidation {

    private Context context;
    /**
     * constructor
     *
     * @param c
     */
    public InputValidation(Context c){
        this.context = c;
    }

    /**
     * method to check InputEditText filled .
     *
     * @param tIET
     * @param tIL
     * @param message
     * @return
     */
    public boolean isTextFieldFilled(EditText tIET,
                                     TextInputLayout tIL,
                                     String message){
        //remove leading and trailing spaces
        String textField = tIET.getText().toString().trim();

        if (textField.isEmpty()){
            tIL.setError(message);
            hideKeyboard(tIET);
            return false;
        }
        else tIL.setErrorEnabled(false);

        return true;
    }

    /**
     * method to check InputEditText is filled .
     *
     * @param tIET
     * @param tIL
     * @param message
     * @return
     */
    public boolean isTextFieldEmail(EditText tIET,
                                    TextInputLayout tIL,
                                    String message){
        //remove leading and trailing spaces
        String textField = tIET.getText().toString().trim();

        if (textField.isEmpty() ||
                !android.util.Patterns.EMAIL_ADDRESS.matcher(textField).matches()){
            tIL.setError(message);
            hideKeyboard(tIET);
            return false;
        }
        else tIL.setErrorEnabled(false);

        return true;
    }

    /**
     * method to check if fields match.
     *
     * @param tIET
     * @param tIET2
     * @param tIL
     * @param message
     * @return
     */

    public boolean isEachTextFieldsSame(EditText tIET,
                                        EditText tIET2,
                                        TextInputLayout tIL,
                                        String message){
        //remove leading and trailing spaces
        String value1 = tIET.getText().toString().trim();
        String value2 = tIET2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            tIL.setError(message);
            hideKeyboard(tIET2);
            return false;
        }
        else tIL.setErrorEnabled(false);

        return true;
    }
    private void hideKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(view.getWindowToken(),
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
