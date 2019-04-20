package com.example.cv.select;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.constraint.solver.widgets.Helper;


public class Lister {

	private Context context;

	public Lister(Context context){
		this.context = context;
		createAndOpenDB();
	}

	private DatabaseHelperRP help;
	private SQLiteDatabase sq_db;


	public SQLiteDatabase createAndOpenDB()
	{
		help = new DatabaseHelperRP(context);
		sq_db = help.getWritableDatabase();
		return sq_db;
	}

	public boolean executeNonQuery(String command)
	{
		try
		{
			sq_db.execSQL(command);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

	public String[][] executeReader(String command)
	{
		Cursor cursor = sq_db.rawQuery(command, null);
		int col = cursor.getColumnCount();
		int row = cursor.getCount();
		String[][] str_arry = new String[row][col];
		int j = 0, i = 0;

		cursor.moveToFirst();
		if(row != 0)
		{
			for(i = 0 ; i < row ; i++)
			{
				for(j = 0 ; j < col ; j++)
				{
					str_arry[i][j] = cursor.getString(j);
				}
				cursor.moveToNext();
			}
			cursor.close();

			return str_arry;
		}

		else
			return null;
	}


	public void closeDB()
	{
		sq_db.close();
	}


}