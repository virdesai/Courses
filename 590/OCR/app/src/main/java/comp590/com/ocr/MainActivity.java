package comp590.com.ocr;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    public static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/OCR/", lang = "eng";
    protected static final String PHOTO_TAKEN = "photo_taken";

    protected Button button;
    protected EditText editText;
    protected ImageView image;
    protected ProgressBar progress;
    protected String path;
    protected boolean taken;
    TessBaseAPI baseApi;

    @Override protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseApi = new TessBaseAPI();
        String[] paths = new String[] {DATA_PATH, DATA_PATH + "tessdata/"};

        for(String path: paths){
            File dir = new File(path);
            if(!dir.exists())
                if(!dir.mkdirs())
                    return;
        }

        if(!(new File(paths[1] + lang + ".traineddata")).exists()){
            try{
                AssetManager assetManager = getAssets();
                InputStream in = assetManager.open("tessdata/" + lang + ".traineddata");
                OutputStream out = new FileOutputStream(paths[1] + lang + ".traineddata");

                byte[] buf = new byte[1024];
                int len;

                while((len = in.read(buf)) > 0)
                    out.write(buf, 0, len);
                in.close();
                out.close();
            }catch (IOException e){
                System.err.println("Was unable to copy " + lang + " traineddata " + e.toString());
            }
        }

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.image);
        progress = (ProgressBar) findViewById(R.id.progressBar);
        button.setOnClickListener(new ButtonClickHandler());
        path = DATA_PATH + "/ocr.jpg";
    }

    public class ButtonClickHandler implements View.OnClickListener{
        public void onClick(View view){
            startCameraActivity();
        }
    }

    protected void startCameraActivity(){
        File file = new File(path);
        Uri outputFileUri = Uri.fromFile(file);
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(intent, 0);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == -1){
            editText.setTextColor(Color.RED);
            editText.setText("Working on it...");
            progress.setVisibility(View.VISIBLE);
            new PhotoTaken().execute();
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState){
        outState.putBoolean(MainActivity.PHOTO_TAKEN, taken);
    }

    @Override protected void onRestoreInstanceState(Bundle savedInstanceState){
        if (savedInstanceState.getBoolean(MainActivity.PHOTO_TAKEN))
            new PhotoTaken().execute();
    }

    private class PhotoTaken extends AsyncTask<Void, Void, Void>{
        String recognizedText;
        Bitmap original;

        @Override protected Void doInBackground(Void... params){
            onPhotoTaken();
            return null;
        }

        protected void onPhotoTaken(){
            taken = true;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            Bitmap bitmap = BitmapFactory.decodeFile(path);//, options);
            original = BitmapFactory.decodeFile(path, options);

            try{
                ExifInterface exif = new ExifInterface(path);
                int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                int rotate = 0;

                switch (exifOrientation){
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotate = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotate = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotate = 270;
                        break;
                }

                if (rotate != 0){
                    int w = bitmap.getWidth() * 5;  // Getting width & height of the given image
                    int h = bitmap.getHeight() * 5;
                    Matrix mtx = new Matrix(); // Setting pre rotate
                    mtx.preRotate(rotate);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, mtx, false); // Rotating Bitmap
                }
                bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true); // Convert to ARGB_8888, required by tess

            } catch (IOException e){
                System.err.println("Couldn't correct orientation: " + e.toString());
            }

            baseApi.init(DATA_PATH, lang);
            baseApi.setPageSegMode(TessBaseAPI.PageSegMode.PSM_SINGLE_BLOCK);
            baseApi.setImage(bitmap);
            recognizedText = baseApi.getUTF8Text();
            baseApi.end();
            recognizedText = (lang.equalsIgnoreCase("eng")) ? recognizedText.replaceAll("[^a-zA-Z0-9\n]+", " ") : recognizedText;
        }

        @Override protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            image.setImageBitmap(original);
            if (recognizedText.length() != 0){
                editText.setTextColor(Color.BLACK);
                editText.setText(recognizedText);
                editText.setSelection(editText.getText().toString().length());
                progress.setVisibility(View.GONE);
            }
        }
    }
}