package pl.wasat.smarthma.ui.frags.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AppEventsLogger;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.LoginButton;
import com.facebook.widget.ProfilePictureView;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;

import java.util.Arrays;

import pl.wasat.smarthma.R;

/**
 * A simple {@link Fragment} subclass. Use the
 * {@link FacebookDialogFragment#newInstance} factory method to create an
 * instance of this fragment.
 */
public class FacebookDialogFragment extends DialogFragment implements Target {

    private static final String ARG_PARAM_QUICKLOOK_URL = "pl.wasat.smarthma.ARG_PARAM_QUICKLOOK_URL";

    private Button postPhotoButton;
    private ImageView imgViewQLook;

    private Bitmap quicklookImg;

    private boolean canPresentShareDialogWithPhotos;
    private GraphUser graphUser;
    private ProfilePictureView profilePictureView;
    private TextView userInfo;

    private UiLifecycleHelper uiHelper;
    private PendingAction pendingAction = PendingAction.NONE;

    private final String PENDING_ACTION_BUNDLE_KEY = "pl.wasat.smarthma:PendingAction";
    private static final String PERMISSION = "publish_actions";

    private enum PendingAction {
        NONE, POST_PHOTO
    }

    private final Session.StatusCallback callback = new Session.StatusCallback() {

        @Override
        public void call(Session session, SessionState state,
                         Exception exception) {
            onSessionStateChange(state, exception);
        }
    };

    private final FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
        @Override
        public void onError(FacebookDialog.PendingCall pendingCall,
                            Exception error, Bundle data) {
        }

        @Override
        public void onComplete(FacebookDialog.PendingCall pendingCall,
                               Bundle data) {
        }
    };

    /**
     * Use this factory method to create a new instance of this fragment using
     * the provided parameters.
     *
     * @param qUrl - URL to facebook service
     * @return A new instance of fragment FacebookFragment.
     */
    public static FacebookDialogFragment newInstance(String qUrl) {
        FacebookDialogFragment fragment = new FacebookDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_QUICKLOOK_URL, qUrl);
        fragment.setArguments(args);
        return fragment;
    }

    public FacebookDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        if (getArguments() != null) {
            String paramQLookUrl = getArguments().getString(ARG_PARAM_QUICKLOOK_URL);
        }*/

        if (savedInstanceState != null) {
            String name = savedInstanceState
                    .getString(PENDING_ACTION_BUNDLE_KEY);
            pendingAction = PendingAction.valueOf(name);
        }
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View rootView = inflater.inflate(R.layout.fragment_facebook_dialog,
                container, false);


        rootView.isInEditMode();
        //TODO INFOAPPS
        //getDialog().setTitle("Facebook Quicklook Share");

        profilePictureView = (ProfilePictureView) rootView
                .findViewById(R.id.facebook_dialog_img_user_profile);
        profilePictureView.isInEditMode();
        userInfo = (TextView) rootView.findViewById(R.id.facebook_dialog_tv_user_info);

        LoginButton loginButton = (LoginButton) rootView.findViewById(R.id.facebook_dialog_btn_login);
        loginButton.setFragment(this);
        loginButton.isInEditMode();
        loginButton
                .setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
                    @Override
                    public void onUserInfoFetched(GraphUser user) {
                        graphUser = user;
                        updateUI();
                        // It's possible that we were waiting for this.user to
                        // be populated in order to post a
                        // status update.
                        handlePendingAction();
                    }
                });

        imgViewQLook = (ImageView) rootView.findViewById(R.id.facebook_dialog_img_share_qlook);

        postPhotoButton = (Button) rootView.findViewById(R.id.facebook_dialog_btn_share_qlook);
        postPhotoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onClickPostPhoto();
            }
        });

        // Can we present the share dialog for photos?
        canPresentShareDialogWithPhotos = FacebookDialog.canPresentShareDialog(
                getActivity(), FacebookDialog.ShareDialogFeature.PHOTOS);

        //Target quicklookTarget = this;

        // Picasso.with(getActivity()).load(paramQLookUrl).into(quicklookTarget);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        uiHelper.onResume();

        // Call the 'activateApp' method to log an app event for use in
        // analytics and advertising reporting. Do so in
        // the onResume methods of the primary Activities that an app may be
        // launched into.
        AppEventsLogger.activateApp(getActivity());

        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);

        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();

        // Call the 'deactivateApp' method to log an app event for use in
        // analytics and advertising
        // reporting. Do so in the onPause methods of the primary Activities
        // that an app may be launched into.
        AppEventsLogger.deactivateApp(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    public void postOnActivityResult(int requestCode, int resultCode,
                                     Intent data) {
        // Session session = Session.getActiveSession();
        int sanitizedRequestCode = requestCode % 0x10000;
        uiHelper.onActivityResult(sanitizedRequestCode, resultCode, data,
                dialogCallback);
    }

    private void onSessionStateChange(SessionState state,
                                      Exception exception) {
        if (pendingAction != PendingAction.NONE
                && (exception instanceof FacebookOperationCanceledException || exception instanceof FacebookAuthorizationException)) {
            new AlertDialog.Builder(getActivity()).setTitle(R.string.cancelled)
                    .setMessage(R.string.permission_not_granted)
                    .setPositiveButton(R.string.ok, null).show();
            pendingAction = PendingAction.NONE;
        } else if (state == SessionState.OPENED_TOKEN_UPDATED) {
            handlePendingAction();
        }
        updateUI();
    }

    private void onClickPostPhoto() {
        performPublish(
                canPresentShareDialogWithPhotos);
    }

    private void handlePendingAction() {
        PendingAction previouslyPendingAction = pendingAction;
        // These actions may re-set pendingAction if they are still pending, but
        // we assume they
        // will succeed.
        pendingAction = PendingAction.NONE;

        switch (previouslyPendingAction) {
            case POST_PHOTO:
                postPhoto();
                break;
            case NONE:
                break;
            default:
                break;
        }
    }

    private void postPhoto() {
        if (canPresentShareDialogWithPhotos) {
            FacebookDialog shareDialog = createShareDialogBuilderForPhoto(
                    quicklookImg).build();
            uiHelper.trackPendingDialogCall(shareDialog.present());
        } else if (hasPublishPermission()) {
            Request request = Request.newUploadPhotoRequest(
                    Session.getActiveSession(), quicklookImg,
                    new Request.Callback() {
                        @Override
                        public void onCompleted(Response response) {
                            showPublishResult(getString(R.string.quicklook),
                                    response.getGraphObject(),
                                    response.getError());
                        }
                    });
            request.executeAsync();
        } else {
            pendingAction = PendingAction.POST_PHOTO;
        }
    }

    private FacebookDialog.PhotoShareDialogBuilder createShareDialogBuilderForPhoto(
            Bitmap... photos) {
        return new FacebookDialog.PhotoShareDialogBuilder(getActivity())
                .addPhotos(Arrays.asList(photos));
    }

    private boolean hasPublishPermission() {
        Session session = Session.getActiveSession();
        return session != null
                && session.getPermissions().contains("publish_actions");
    }

    private void updateUI() {
        Session session = Session.getActiveSession();
        boolean enableButtons = (session != null && session.isOpened());

        postPhotoButton.setEnabled(enableButtons
                || canPresentShareDialogWithPhotos);

        if (enableButtons && graphUser != null) {
            profilePictureView.setProfileId(graphUser.getId());
            userInfo.setText(getString(R.string.hello_user,
                    graphUser.getFirstName()));
        } else {
            profilePictureView.setProfileId(null);
            userInfo.setText(null);
        }
    }

    private void performPublish(boolean allowNoSession) {
        Session session = Session.getActiveSession();
        if (session != null) {
            pendingAction = PendingAction.POST_PHOTO;
            if (hasPublishPermission()) {
                // We can do the action right away.
                handlePendingAction();
                return;
            } else if (session.isOpened()) {
                // We need to get new permissions, then complete the action when
                // we get called back.
                session.requestNewPublishPermissions(new Session.NewPermissionsRequest(
                        this, PERMISSION));
                return;
            }
        }

        if (allowNoSession) {
            pendingAction = PendingAction.POST_PHOTO;
            handlePendingAction();
        }
    }

    private void showPublishResult(String message, GraphObject result,
                                   FacebookRequestError error) {
        String title;
        String alertMessage;
        Boolean isSuccess;
        if (error == null) {
            title = getString(R.string.success);
            String id = result.cast(GraphObjectWithId.class).getId();
            alertMessage = getString(R.string.successfully_posted_post,
                    message, id);
            isSuccess = true;
        } else {
            title = getString(R.string.error);
            alertMessage = error.getErrorMessage();
            isSuccess = false;
        }

        final Boolean isFinished = isSuccess;
        new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(alertMessage)
                .setPositiveButton(R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                                if (isFinished) {
                                    dismiss();
                                }
                            }
                        }).show();
    }

    private interface GraphObjectWithId extends GraphObject {
        String getId();
    }

    @Override
    public void onBitmapFailed(Drawable arg0) {
    }

    @Override
    public void onBitmapLoaded(Bitmap image, LoadedFrom arg1) {
        quicklookImg = image;

        Bitmap scaled = Bitmap.createScaledBitmap(image, 160, 160, true);
        imgViewQLook.setImageBitmap(scaled);

    }

    @Override
    public void onPrepareLoad(Drawable arg0) {
    }

}
