package com.example.user.myapplication;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnSystemUiVisibilityChangeListener;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.NativeExpressAdView;
import org.w3c.dom.Document;

public class MainActivity extends Activity {
    ImageButton button;
    ImageButton button1;
    private Runnable decor_view_settings = new Runnable() {
        public void run() {
            MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    };
    private Document document;
    boolean gone;
    int i;
    int j;
    private NativeExpressAdView mAdView;
    private InterstitialAd mInterstitialAd;
    private WebView mWebView;
    ProgressBar progressBar;

    private class HelloWebViewClient extends WebViewClient {
        private HelloWebViewClient() {
        }

        @JavascriptInterface
        public void onPageFinished(WebView paramWebView, String paramString) {
            if (MainActivity.this.mWebView.getUrl().equals("file:///android_asset/runnable.html")) {
                MainActivity.this.mAdView.setVisibility(0);
            }
            super.onPageFinished(paramWebView, paramString);
            MainActivity.this.progressBar.setVisibility(8);
        }

        public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
            super.onPageStarted(paramWebView, paramString, paramBitmap);
            MainActivity.this.progressBar.setVisibility(0);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            MainActivity.this.decor_view_settings.run();
            if (url.contains("google")) {
                view.loadUrl(url);
                MainActivity.this.mAdView.setVisibility(8);
            } else if (url.contains("bing")) {
                view.loadUrl(url);
                MainActivity.this.mAdView.setVisibility(8);
            } else {
                view.loadUrl(url);
                MainActivity.this.mAdView.setVisibility(8);
            }
            return true;
        }
    }

    public void onBackPressed() {
        Builder localBuilder = new Builder(this);
        localBuilder.setTitle("Exit");
        localBuilder.setMessage("Do you really want to exit?").setCancelable(false).setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                MainActivity.this.finish();
            }
        }).setNegativeButton("No", new OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.cancel();
            }
        });
        localBuilder.create().show();
    }

    protected void onCreate(Bundle paramBundle) {
        setContentView(R.layout.activity_main);
        onSystemUiVisibilityChange(66);
        this.i = 1;
        this.j = 1;
        this.mWebView = (WebView) findViewById(R.id.activity_main);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        this.decor_view_settings.run();
        this.mWebView.loadUrl("file:///android_asset/runnable.html");
        this.mAdView = (NativeExpressAdView) findViewById(R.id.adView);
        this.mAdView.loadAd(new AdRequest.Builder().build());
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-1839690726930086/2259447055");
        this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                Log.i(com.google.ads.AdRequest.LOGTAG, "onAdLoaded");
            }

            public void onAdClosed() {
                MainActivity.this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        this.button = (ImageButton) findViewById(2131165214);
        this.button1 = (ImageButton) findViewById(R.id.button1);
        this.button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (MainActivity.this.j <= 2 && MainActivity.this.j >= 1) {
                    if (MainActivity.this.mInterstitialAd.isLoaded()) {
                        MainActivity.this.mInterstitialAd.show();
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.j++;
                    } else {
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                }
                MainActivity.this.mWebView.loadUrl("file:///android_asset/runnable.html");
            }
        });
        this.button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (MainActivity.this.mWebView.canGoForward()) {
                    MainActivity.this.mWebView.goForward();
                    if (MainActivity.this.i <= 3 && MainActivity.this.i >= 1) {
                        if (MainActivity.this.mInterstitialAd.isLoaded()) {
                            MainActivity.this.mInterstitialAd.show();
                            MainActivity mainActivity = MainActivity.this;
                            mainActivity.i++;
                            return;
                        }
                        Log.d("TAG", "The interstitial wasn't loaded yet.");
                    }
                }
            }
        });
        WebSettings settings = this.mWebView.getSettings();
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        this.mWebView.setScrollBarStyle(0);
        this.mWebView.setVerticalScrollBarEnabled(false);
        this.mWebView.setHorizontalScrollBarEnabled(false);
        this.mWebView.getSettings().setPluginState(PluginState.ON);
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.mWebView.getSettings().setDomStorageEnabled(true);
        this.mWebView.getSettings().setAllowFileAccess(true);
        this.mWebView.getSettings().setAppCacheEnabled(true);
        this.mWebView.getSettings().setSaveFormData(true);
        this.mWebView.getSettings().setAppCacheMaxSize(5242880);
        this.mWebView.getSettings().setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        this.mWebView.getSettings().setCacheMode(-1);
        this.mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        this.mWebView.setWebViewClient(new HelloWebViewClient());
        this.mWebView.setInitialScale(1);
        this.mWebView.setWebChromeClient(new WebChromeClient());
        if (!isNetworkAvailable()) {
            this.mWebView.getSettings().setCacheMode(1);
        }
        this.mWebView.setDownloadListener(new DownloadListener() {
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
                Request request = new Request(Uri.parse(url));
                request.setMimeType(mimeType);
                request.addRequestHeader("cookie", CookieManager.getInstance().getCookie(url));
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setVisibleInDownloadsUi(true);
                request.setAllowedNetworkTypes(3);
                request.setNotificationVisibility(1);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(url, contentDisposition, mimeType));
                ((DownloadManager) MainActivity.this.getSystemService("download")).enqueue(request);
                Toast.makeText(MainActivity.this.getApplicationContext(), "Downloading File", 1).show();
            }
        });
        super.onCreate(paramBundle);
        setListeners()
    }
public void setListeners() {
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                webView.loadUrl("about:blank");

                view.clearHistory();
            }        
        });
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {

            }
            // For Android 3.0+
            public void openFileChooser(ValueCallback<Uri> uploadMsg) {

                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
            }
            // For Android 3.0+
            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(
                        Intent.createChooser(i, "File Browser"),
                        FILECHOOSER_RESULTCODE);
            }
            //For Android 4.1
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                mUploadMessage = uploadMsg;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("image/*");
                startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);

            }    
        });
            webView.loadUrl(url);    
final MyJavaScriptInterface myJavaScriptInterface
                = new MyJavaScriptInterface(this);
webView.addJavascriptInterface(myJavaScriptInterface, "AndroidFunction");
    
}
    private boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if (paramInt == 4 && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            this.decor_view_settings.run();
            return true;
        }
        if (paramInt == 93 || paramInt == 92 || paramInt == 66 || paramInt == 93) {
            this.decor_view_settings.run();
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public void onSystemUiVisibilityChange(int paramInt) {
        if (paramInt == 93 || paramInt == 92 || paramInt == 66 || paramInt == 0) {
            this.decor_view_settings.run();
        }
        this.decor_view_settings.run();
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        if (paramBoolean) {
            this.decor_view_settings.run();
        }
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener() {
            public void onSystemUiVisibilityChange(int paramInt) {
                MainActivity.this.decor_view_settings.run();
            }
        });
        this.button1.setVisibility(0);
    }
}