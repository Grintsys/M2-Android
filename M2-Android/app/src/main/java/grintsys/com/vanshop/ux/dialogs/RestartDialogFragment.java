package grintsys.com.vanshop.ux.dialogs;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import grintsys.com.vanshop.R;
import grintsys.com.vanshop.SettingsMy;
import grintsys.com.vanshop.entities.tenant.Tenant;
import grintsys.com.vanshop.utils.Analytics;
import grintsys.com.vanshop.ux.RestartAppActivity;
import timber.log.Timber;

public class RestartDialogFragment extends DialogFragment {

    private Tenant newTenantSelected;

    public static RestartDialogFragment newInstance(Tenant selectedTenant) {
        RestartDialogFragment frag = new RestartDialogFragment();
        frag.newTenantSelected = selectedTenant;
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setWindowAnimations(R.style.dialogFragmentAnimation);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Timber.d("%s - OnCreateView", this.getClass().getSimpleName());
        View view = inflater.inflate(R.layout.dialog_restart_app, container, false);

        Button okBtn = (Button) view.findViewById(R.id.dialog_restart_app_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Analytics.logShopChange(SettingsMy.getActualNonNullShop(getActivity()), newTenantSelected);
                Analytics.deleteAppTrackers();

                // Log out user, change actual shop and restart
                SettingsMy.setActiveUser(null);
                SettingsMy.setActualTenant(newTenantSelected);
                Intent i = new Intent(getActivity(), RestartAppActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
                getActivity().finish();
                dismiss();
            }
        });

        Button cancelBtn = (Button) view.findViewById(R.id.dialog_restart_app_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
