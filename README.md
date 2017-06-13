[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)

# android-dialog-library
Android library for creating custom dialogs

![one](https://user-images.githubusercontent.com/9627223/27083363-eefb4036-5065-11e7-96cd-1e2633dc080b.png)
![two](https://user-images.githubusercontent.com/9627223/27083375-f59fcc40-5065-11e7-901d-eee622d28f27.png)

## Setup Gradle

```groovy
dependencies {
    ...
	        compile 'com.github.RMgX:android-dialog-library:1.0'
}
```

## Examples

```code
new MicroInteraction.Builder(this)
                .headerImage(R.drawable.ic_success_green)
                .title(getString(R.string.title))
                .content(getString(R.string.content))
                .cancelable(false, false)
                .animation(AnimUtils.AnimDown)
                .typefaceTitle(ApplicationConstants.HEADING_FONT)
                .typefaceContent(ApplicationConstants.BODY_FONT)
                .typefacePositiveButton(ApplicationConstants.HEADING_FONT)
                .positiveButton("OKAY", new MicroInteraction.onPositiveListener() {
                    @Override
                    public void onPositive(Dialog dialog) {
                        dialog.cancel();
                    }
                })
                .negativeButton("CANCEL", new MicroInteraction.onNegativeListener() {
                    @Override
                    public void onNegative(Dialog dialog) {
                        dialog.cancel();
                    }
                })
                .show();
```

