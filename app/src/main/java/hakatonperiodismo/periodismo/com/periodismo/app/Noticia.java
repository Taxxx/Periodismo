package hakatonperiodismo.periodismo.com.periodismo.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import hakatonperiodismo.periodismo.com.periodismo.R;

public class Noticia extends Activity {

    File sdCard;

    ///////////////VOLVER
    ImageView volver;

    ///////////////Fotografía
    Button btn_foto;
    private String foto = "";
    boolean control_foto=false;
/////////////////////////////////VIDEO
    Button btn_video;
    String video="";
    //fecha

    private Calendar fechaYhora = Calendar.getInstance();
    SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String fecha_actual;
/////////////////////////////////VOZ
    Button btn_voz;



    /*
    Audio
     */
    Button btn_audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticia);
        volver = (ImageView) this.findViewById(R.id.btn_volver);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        fecha_actual=fecha.format(fechaYhora.getTime());
/*
SECCION FOTOGRAFÍAS
 */

        sdCard = Environment.getExternalStorageDirectory();


        btn_foto = (Button) findViewById(R.id.btn_img);
        btn_foto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg2)
            {


                File directory = new File(sdCard.getAbsolutePath()+ "/ChaskiReport/images");
                if(!directory.isDirectory())
                    directory.mkdirs();

                foto=Environment.getExternalStorageDirectory() + "/ChaskiReport/images/"+fecha_actual+".jpg";



                try
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File fot=new File(foto);
                    if(fot!=null)
                    {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fot));
                        startActivityForResult(intent, 1);
                        control_foto=true;
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Log.e("Fotografia", "ERROR");
                }
            }
        });

/*
SECCION VIDEO
 */

        btn_video=(Button)this.findViewById(R.id.btn_video);
        btn_video.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(Intent.createChooser(photoPickerIntent,"Take Video"),0);
            }
        });


        /*
        SECCION AUDIO
         */

        btn_audio=(Button )this.findViewById(R.id.btn_audio);
        btn_audio.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent recordIntent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
                startActivityForResult(recordIntent, 2);

            }
        });

        btn_voz=(Button)this.findViewById(R.id.btn_voz);
        btn_voz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Noticia.this,Voz.class);
                startActivity(intent);
            }
        });

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == Activity.RESULT_OK && resultCode != Noticia.this.RESULT_CANCELED)

                        {

                        }
                        else
                        {
System.out.print("no se tomo la fotografia");
                        }


                    break;


            case 0:

                if (resultCode == Activity.RESULT_OK && resultCode != Noticia.this.RESULT_CANCELED)
                {

                    try
                    {
                        AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                        FileInputStream fis = videoAsset.createInputStream();
                        File root=new File(Environment.getExternalStorageDirectory(),"/ChaskiReport/videos");
                        if (!root.exists()) {
                            System.out.println("No directory");
                            root.mkdirs();
                        }

                        File file;
                        file=new File(root,"android_"+System.currentTimeMillis()+".mp4" );

                        FileOutputStream fos = new FileOutputStream(file);

                        byte[] buf = new byte[1024];
                        int len;
                        while ((len = fis.read(buf)) > 0) {
                            fos.write(buf, 0, len);
                        }
                        fis.close();
                        fos.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }
                else

                {

                    System.out.print("no se tomo el video");
                }

            break;

            case  2:


                if (resultCode == Activity.RESULT_OK && resultCode != Noticia.this.RESULT_CANCELED) {



                    File folder = new File(sdCard.getAbsolutePath()+ "/ChaskiReport/audio");
                    if(!folder.isDirectory())
                        folder.mkdirs();



                    long folderModi = folder.lastModified();

                    FilenameFilter filter = new FilenameFilter()
                    {
                        public boolean accept(File dir, String name)
                        {
                            return (name.endsWith("3ga"));
                        }
                    };

                    File[] folderList = folder.listFiles(filter);

                    String recentName = "";

                    for(int i=0; i<folderList.length;i++)
                    {
                        long fileModi = folderList[i].lastModified();

                        if(folderModi == fileModi)
                        {
                            recentName = folderList[i].getName();
                        }
                    }
                }
                else
                {

                }

            break;
                }

        }


    }




