package com.dbhelperdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by D&LL on 2017/3/26.
 */

public class SQLiteUtil {
    /**
     * 创建名为name的数据库
     *
     * @param context
     * @param name
     */
    public static SQLiteDatabase createSQLite(Context context, String name) {
        SQLiteDatabase sdb = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir().toString() + "/" + name + ".db3", null);
        return sdb;
    }

    /**
     * 在SD卡以程序命名的文件夹下创建名为name的数据库
     *
     * @param context
     * @param name
     */
    public static SQLiteDatabase createSDSQLite(Context context, String name) {
        SQLiteDatabase sdb = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = null;
            try {
                file = new File(Environment.getExternalStorageDirectory().getCanonicalPath() + "/" +
                        context.getResources().getString(R.string.app_name));
                if (!file.exists()) {//判读文件夹是否存在
                    file.mkdirs();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sdb = SQLiteDatabase.openOrCreateDatabase(file.toString() + "/" + name + ".db3", null);
        }
        return sdb;
    }

    /**
     * 判断某张表是否存在
     *
     * @param tableName 表名
     * @return
     */
    public static boolean isTableExist(SQLiteDatabase sdb, String tableName) {
        boolean result = false;
        if (tableName == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + tableName.trim() + "' ";
            cursor = sdb.rawQuery(sql, null);
            if (cursor.moveToNext()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    result = true;
                }
            }

        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 判断某表里某字段是否存在
     *
     * @param db
     * @param tableName
     * @param fieldName
     * @return
     */
    public static boolean isFieldExist(SQLiteDatabase db, String tableName, String fieldName) {
        String queryStr = "select sql from sqlite_master where type = 'table' and name = '%s'";
        queryStr = String.format(queryStr, tableName);
        Cursor c = db.rawQuery(queryStr, null);
        String tableCreateSql = null;
        try {
            if (c != null && c.moveToFirst()) {
                tableCreateSql = c.getString(c.getColumnIndex("sql"));
            }
        } finally {
            if (c != null)
                c.close();
        }
        if (tableCreateSql != null && tableCreateSql.contains(fieldName))
            return true;
        return false;
    }

    /**
     * 创建数据表
     *
     * @param sdb
     * @param table 表名
     * @param sql   表的内容
     */
    public static void createTable(SQLiteDatabase sdb, String table, String sql) {
        if (!SQLiteUtil.isTableExist(sdb, table)) {//判断表是否存在
            sdb.execSQL("create table " + sql);
        }
    }

    /**
     * 删除数据表
     *
     * @param sdb
     * @param table
     */
    private void dropTable(SQLiteDatabase sdb, String table) {
        String sql = "DROP TABLE " + table;
        sdb.execSQL(sql);
    }

    /**
     * 语句操纵，插入的方法
     *
     * @param sdb
     * @param sql
     * @param columns 列对应值
     */
    public static void insertData(SQLiteDatabase sdb, String sql, String[] columns) {
        sdb.execSQL(sql, columns);
    }

    /**
     * 语法操纵
     *
     * @param sqb
     * @param where
     */

    public static void grammarDo(SQLiteDatabase sqb, String where) {
        sqb.execSQL(where);
    }

    /**
     * 方法操纵，插入数据
     * 返回插入的行值，发送错误返回-1
     *
     * @param sdb
     * @param table
     * @param values
     * @return
     */
    private static long insertSDB(SQLiteDatabase sdb, String table, ContentValues values) {
        long rowId = sdb.insert(table, null, values);
        return rowId;
    }

    /**
     * 方法操纵，更新
     *
     * @param sdb
     * @param table
     * @param values
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public static int updateSDB(SQLiteDatabase sdb, String table, ContentValues values, String whereClause, String[] whereArgs) {
        int result = sdb.update(table, values, whereClause, whereArgs);
        return result;
    }


    /**
     * 方法操纵
     *
     * @param sdb
     * @param table
     * @param columns
     * @param whereClause
     * @param args
     * @return
     */
    public static Cursor querySDB(SQLiteDatabase sdb, String table, String[] columns, String whereClause, String[] args) {
        Cursor cursor = sdb.query(table, columns, whereClause, args, null, null, null);
        return cursor;
    }

    /**
     * 语句操纵select
     *
     * @param sdb
     * @param where
     * @param sql
     * @return
     */
    public static Cursor queryData(SQLiteDatabase sdb, String sql, String[] where) {
        Cursor cursor = sdb.rawQuery(sql, where);
        return cursor;
    }

    /**
     * 方法操纵，删除
     *
     * @param sdb
     * @param table
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public static int deleteSDB(SQLiteDatabase sdb, String table, String whereClause, String[] whereArgs) {
        int result = sdb.delete(table, whereClause, whereArgs);
        return result;
    }

    /**
     * 关闭数据库
     *
     * @param sdb
     */
    public static void closeSDB(SQLiteDatabase sdb) {
        if (sdb != null && sdb.isOpen()) {
            sdb.close();
        }
    }

}
