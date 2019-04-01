package grintsys.com.vanshop.ux.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import grintsys.com.vanshop.R;
import grintsys.com.vanshop.ux.MainActivity;
import grintsys.com.vanshop.ux.fragments.BannersFragment;
import timber.log.Timber;

/**
 * Dialog informs user about session timeout.
 * User is redirected to {@link BannersFragment}.
 */
public class LoginExpiredDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Timber.d("%s - OnCreateView", this.getClass().getSimpleName());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.myAlertDialogStyle);
        builder.setTitle(R.string.Oops_login_expired);
        builder.setMessage(R.string.Your_session_has_expired_Please_log_in_again);

        builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (getActivity() instanceof MainActivity)
                    ((MainActivity) getActivity()).onDrawerBannersSelected();
                dialog.dismiss();
            }
        });

        return builder.create();
    }
}
