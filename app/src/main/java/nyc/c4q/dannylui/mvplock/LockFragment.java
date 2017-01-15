package nyc.c4q.dannylui.mvplock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dannylui on 1/14/17.
 */

/*
View of the lock
*/
public class LockFragment extends Fragment implements Lock.View {
    private View rootView;
    private LockPresenter presenter;
    private LockModel lockModel;
    private TextView lockDisplay;
    private EditText editCombination;
    private Button setCombinationBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_lock, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lockDisplay = (TextView) view.findViewById(R.id.tv_lock_display);
        editCombination = (EditText) view.findViewById(R.id.edit_combination);
        setCombinationBtn = (Button) view.findViewById(R.id.btn_set_combination);
        initButtons();
        lockModel = LockModel.getInstance();
        presenter = new LockPresenter(this);
    }

    private void initButtons() {
        for (int i = 0; i < 10; i++) { // Buttons 0-9
            String buttonId = "btn_" + i; // Buttons need to be named btn_1, btn_2, etc...
            int resID = getResources().getIdentifier(buttonId, "id", rootView.getContext().getPackageName());
            final Button button = (Button) rootView.findViewById(resID);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onKeyPressed(button.getText().toString().charAt(0));
                }
            });
        }

        setCombinationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetLockPressed();
            }
        });
    }


    public void onSetLockPressed() {
        presenter.newLockCombination(editCombination.getText().toString());
    }

    @Override
    public void onKeyPressed(char key) {
        presenter.nextKey(key);
    }

    @Override
    public void setLockDisplay(String input) {
        lockDisplay.setText(input);
        lockDisplay.setTextColor(Color.parseColor("#F57F17"));
    }

    @Override
    public void onUnlocked() {
        lockDisplay.setTextColor(Color.parseColor("#18FFFF"));
        Toast.makeText(rootView.getContext(), "Unlocked!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCombinationChanged(String newCombination) {
        Toast.makeText(rootView.getContext(), "Set lock to " + newCombination, Toast.LENGTH_SHORT).show();
    }
}
