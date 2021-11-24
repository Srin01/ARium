package com.example.secondar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.example.secondar.expert.FurnitureExpert;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;


public class MainActivity extends AppCompatActivity {


    private ArFragment arFragment;
    AnchorNode anchorNode;
    private Button btnRemove;
    FurnitureExpert furnitureExpert;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        int n = getIntent().getIntExtra("number", 0);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        btnRemove = (Button) findViewById(R.id.remove);
        getImages();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this, Uri.parse(Common.model))
                    .build()
                    .thenAccept(modelRenderable -> MainActivity.this.addModelToScene(anchor, modelRenderable));

        });


        btnRemove.setOnClickListener(view -> removeAnchorNode(anchorNode));
    }

    private void getImages() {
        furnitureExpert = FurnitureExpert.getInstance();
    }


    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {

        anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();

    }

    public void removeAnchorNode(AnchorNode nodeToremove) {
        if (nodeToremove != null) {
            arFragment.getArSceneView().getScene().removeChild(nodeToremove);
            nodeToremove.getAnchor().detach();
            nodeToremove.setParent(null);
            nodeToremove = null;
        }
    }

//    private void takePhoto() {
//
//        // Create a bitmap the size of the scene view.
//        final Bitmap bitmap = Bitmap.createBitmap(surfaceView.getWidth(), surfaceView.getHeight(),
//                Bitmap.Config.ARGB_8888);
//
//
//
//        // Create a handler thread to offload the processing of the image.
//        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
//        handlerThread.start();
//        // Make the request to copy.
//        PixelCopy.request(holder.videoView, bitmap, (copyResult) -> {
//            if (copyResult == PixelCopy.SUCCESS) {
//                Log.e(TAG,bitmap.toString());
//                String name = String.valueOf(System.currentTimeMillis() + ".jpg");
//                imageFile = ScreenshotUtils.store(bitmap,name);
//
//            } else {
//                Toast toast = Toast.makeText(getViewActivity(),
//                        "Failed to copyPixels: " + copyResult, Toast.LENGTH_LONG);
//                toast.show();
//            }
//            handlerThread.quitSafely();
//        }, new Handler(handlerThread.getLooper()));
//    }
//
//    private void takeScreenshot(){
//        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
//            @Override
//            public void onSnapshotReady(Bitmap bitmap) {
//                Bitmap b = bitmap;
//                String timeStamp = new SimpleDateFormat(
//                        "yyyyMMdd_HHmmss", Locale.getDefault())
//                        .format(new java.util.Date());
//
//                String filepath = timeStamp + ".jpg";
//
//                try{
//                    OutputStream fout = null;
//                    fout = openFileOutput(filepath,MODE_WORLD_READABLE);
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout);
//                    fout.flush();
//                    fout.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                saveImage(filepath);
//            }
//        };
////        mMap.snapshot(callback);
//    }

//    /**
//     * Method to save the screenshot image
//     * @param filePath  the file path
//     */
//    public void saveImage(String filePath)
//    {
//        File file = this.getFileStreamPath(filePath);
//
//        if(!filePath.equals(""))
//        {
//            final ContentValues values = new ContentValues(2);
//            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
//            values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
//            final Uri contentUriFile = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//            Toast.makeText(MainActivity.this,"Code Saved to files!",Toast.LENGTH_LONG).show();
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
////            Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", imageFile);
//            intent.setDataAndType(contentUriFile, "image/*");
//            startActivity(intent);
//        }
//        else
//        {
//            System.out.println("ERROR");
//        }
//    }

//    private void ss(){
//        ScreenShot screenShot = new ScreenShot();
//        screenShot.take();
//    }
//
//    private void plstakeScreenshot() {
//        Date now = new Date();
//        String mPath;
//        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
//
//        try {
//            // image naming and path  to include sd card  appending name you choose for file
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
//                mPath= this.getExternalFilesDir(Environment.DIRECTORY_DCIM) + "/" + now + ".jpeg";
//            }
//            else
//            {
//                mPath= Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpeg";
//            }
//
//
//            // create bitmap screen capture
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(false);
//
//            File imageFile = new File(mPath);
//
//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//            openScreenshot(imageFile);
//        } catch (Throwable e) {
//            // Several error may come out with file handling or DOM
//            e.printStackTrace();
//        }
//    }

//    private void openScreenshot(File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        Uri uri = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", imageFile);
//        intent.setDataAndType(uri, "image/*");
//        startActivity(intent);
//    }
}
