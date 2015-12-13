package hakatonperiodismo.periodismo.com.periodismo.db_model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract class Helper extends SQLiteOpenHelper {

	private Context context;
	private File dbFile;
	private String dbName;
	private boolean dbExist;
	private DatabaseCallback listener;

	public Helper(Context context, String dbName) {
		super(context, dbName, null, 1);
		this.context = context;
		this.dbName = dbName;
	}

	public void CreateDatabase() {
		dbFile = context.getDatabasePath(dbName);
		dbExist = checkDbExists(dbFile.getAbsolutePath());
		if (!dbExist) {
			try {
				File f = new File(dbFile.getAbsolutePath().replace(dbName, ""));
				f.mkdir();
				copyDataBase(dbFile);
				if (listener != null) {
					listener.OnCopied();
				}
			} catch (IOException e) {
				if (listener != null) {
					listener.OnErrorDb();
				}
				e.printStackTrace();
			}
		} else {
			if (listener != null) {
				listener.OnExists();
			}
		}
	}

	private boolean checkDbExists(String path) {
		SQLiteDatabase checkDB = null;
		try {
			checkDB = SQLiteDatabase.openDatabase(path, null,
					SQLiteDatabase.OPEN_READONLY);
		} catch (Exception e) {
			return checkDB != null;
		}
		if (checkDB != null) {
			checkDB.close();
		}
		return checkDB != null;
	}

	private void copyDataBase(File pathFile) throws IOException {
		InputStream myInput = context.getAssets().open(dbName);
		OutputStream myOutput = new FileOutputStream(pathFile);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	protected void setDatabaseListener(DatabaseCallback dbListener) {
		this.listener = dbListener;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	protected static class Table {




        public static class Usuarios {

            private Usuarios()
            {
            }
            public final static String TABLE_NAME = "usuario";
            public final static String ID_USUARIO = "id_usuario";
            public final static String USUARIO = "usuario";
            public final static String PASSWORD = "password";

        }

    }
	

	
}
