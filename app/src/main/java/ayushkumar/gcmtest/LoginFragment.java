package ayushkumar.gcmtest;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private OnLogInListener mListener;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //hideLoginIfLoggedIn();
        Button login = (Button) getActivity().findViewById(R.id.login_bt);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateInputs();
            }
        });
    }

    private void validateInputs() {
        EditText username_et = (EditText) getActivity().findViewById(R.id.username_et);
        String username = username_et.getText().toString();

        EditText password_et = (EditText) getActivity().findViewById(R.id.password_et);
        String password = password_et.getText().toString();

        TextInputLayout username_til = (TextInputLayout) getActivity().findViewById(R.id.til1);
        TextInputLayout password_til = (TextInputLayout) getActivity().findViewById(R.id.til2);

        boolean validated = true;

        if(username.isEmpty()){
            username_til.setError("Username can't be empty!");
            validated = false;
        }else {
            username_til.setError(null);
        }
        if(password.isEmpty()){
            password_til.setError("Password can't be empty!");
            validated = false;
        }else {
            password_til.setError(null);
        }

        if(validated){
            saveUserInfo(username,password);
        }

    }


    private void saveUserInfo(String username, String password) {

        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("prefs.dat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
//        editor.putString("password", password);
        String authString = username + ":" + password;
        String token = Base64.encodeToString(authString.getBytes(), Base64.DEFAULT);
//        Toast.makeText(getActivity(),token,Toast.LENGTH_LONG).show();
        editor.putString("token", token);
        editor.apply();

        updateUIWithNewUser(username);
    }

    private void updateUIWithNewUser(String username) {
        TextView current_user_tv = (TextView) getActivity().findViewById(R.id.current_user_tv);
        current_user_tv.setText(" You are logged in as " + username);

        onLoginClick();
    }

    public void onLoginClick() {
        if (mListener != null) {
            mListener.onLoginClick();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnLogInListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnLogInListener {
         void onLoginClick();
    }

   /* private void hideLoginIfLoggedIn() {
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences("prefs.dat", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        boolean loggedIn = false;
        if(!username.isEmpty()){
            loggedIn = true;
        }

        if(loggedIn) {
            TextView current_user_tv = (TextView) getActivity().findViewById(R.id.current_user_tv);
            current_user_tv.setText(" You are logged in as " + username);

            LinearLayout login_ll = (LinearLayout) getActivity().findViewById(R.id.login_ll);
            login_ll.setVisibility(View.GONE);

            Button login_again_bt = (Button) getActivity().findViewById(R.id.login_again_bt);
            login_again_bt.setVisibility(View.VISIBLE);

            login_again_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout login_ll = (LinearLayout) getActivity().findViewById(R.id.login_ll);
                    login_ll.setVisibility(View.VISIBLE);
                    view.setVisibility(View.GONE);
                }
            });
        }else {
            Button login = (Button) getActivity().findViewById(R.id.login_bt);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validateInputs();
                }
            });

            Button login_again_bt = (Button) getActivity().findViewById(R.id.login_again_bt);
            login_again_bt.setVisibility(View.GONE);


        }

    }
*/
}
