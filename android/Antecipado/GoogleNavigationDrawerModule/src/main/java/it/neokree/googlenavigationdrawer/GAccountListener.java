package it.neokree.googlenavigationdrawer;

import android.support.v4.app.Fragment;

/**
 * Created by neokree on 11/12/14.
 */
public interface GAccountListener {

    public void onAccountOpening(GAccount account);

    public void openFragment(Fragment fragment);
}
