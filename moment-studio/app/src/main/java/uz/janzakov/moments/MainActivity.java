package uz.janzakov.moments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class MainActivity extends Activity {
    private static final int PICK_IMAGE = 401;
    private WebView webView;
    private ValueCallback<android.net.Uri[]> pendingFiles;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(Color.rgb(10, 16, 19));
        getWindow().setNavigationBarColor(Color.rgb(10, 16, 19));
        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(10, 16, 19));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onShowFileChooser(WebView view,
                    ValueCallback<android.net.Uri[]> callback, FileChooserParams params) {
                if (pendingFiles != null) pendingFiles.onReceiveValue(null);
                pendingFiles = callback;
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                try {
                    startActivityForResult(Intent.createChooser(intent, "Rasm tanlang"), PICK_IMAGE);
                    return true;
                } catch (Exception error) {
                    pendingFiles = null;
                    callback.onReceiveValue(null);
                    return false;
                }
            }
        });
        setContentView(webView);
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (request == PICK_IMAGE && pendingFiles != null) {
            android.net.Uri[] resultUris = result == RESULT_OK
                    ? WebChromeClient.FileChooserParams.parseResult(result, data) : null;
            pendingFiles.onReceiveValue(resultUris);
            pendingFiles = null;
        }
    }

    @Override public void onBackPressed() {
        webView.evaluateJavascript("window.appBack && window.appBack()", value -> {
            if ("\"exit\"".equals(value)) finish();
        });
    }

    @Override protected void onDestroy() {
        if (pendingFiles != null) pendingFiles.onReceiveValue(null);
        webView.destroy();
        super.onDestroy();
    }
}
