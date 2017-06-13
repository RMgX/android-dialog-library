[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-8%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)

# android-dialog-library
Android library for creating custom dialogs

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

