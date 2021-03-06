package net.gsantner.markor.activity.openeditor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import net.gsantner.markor.R;
import net.gsantner.markor.activity.DocumentActivity;
import net.gsantner.markor.util.DocumentIO;
import net.gsantner.opoc.util.ActivityUtils;
import net.gsantner.opoc.util.FileUtils;
import net.gsantner.opoc.util.PermissionChecker;

import java.io.File;

public class OpenEditorActivity extends AppCompatActivity {
    protected void openEditorForFile(File file) {
        try {
            PermissionChecker permc = new PermissionChecker(this);
            if (permc.doIfExtStoragePermissionGranted(getString(R.string.error_need_storage_permission_to_save_documents))) {
                if (!file.exists()){
                    FileUtils.writeFile(file, "");
                }
                Context c = getApplicationContext();
                ActivityUtils au = new ActivityUtils(this);
                Intent openIntent = new Intent(c, DocumentActivity.class)
                        .setAction(Intent.ACTION_CALL_BUTTON)
                        .putExtra(DocumentIO.EXTRA_PATH, file)
                        .putExtra(DocumentIO.EXTRA_PATH_IS_FOLDER, false);
                au.animateToActivity(openIntent, true, 1);
            }
        } catch (Exception ignored) {
            finish();
        }
    }
}
