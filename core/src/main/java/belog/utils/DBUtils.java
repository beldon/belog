package belog.utils;

import belog.annotation.Column;
import belog.annotation.Entity;
import belog.annotation.Id;
import belog.annotation.Table;
import org.springframework.util.StringUtils;


import java.lang.reflect.Field;

/**
 * Created by Beldon.
 * Copyright (c) 2016, All Rights Reserved.
 * http://beldon.me
 */
public abstract class DBUtils {

    /**
     * 插入语句
     */
    private static String INSERT = "INSERT INTO `:TABLE` (:COLUMN) VALUES (:VALUE)";

    /**
     * 更新语句
     */
    private static String UPDATE = "UPDATE `:TABLE` SET :SET_VALUE WHERE :WHERE";

    /**
     * 删除语句
     */
    private static String DELETE = "DELETE FROM `:TABLE` WHERE :WHERE";

    /**
     * 查找所有
     */
    private static String SELECT_ALL = "SELECT * FROM `:TABLE` WHERE :WHERE";

    /**
     * 查找部分字段
     */
    private static String SELECT_COLUMN = "SELECT :COLUMN FROM `:TABLE` WHERE :WHERE";

    /**
     * 获取Table名字
     *
     * @param clazz 持久层类
     * @return 表名
     */
    public static String getTableName(Class clazz) {
//        Entity entity = AnnotationUtils.findAnnotation(clazz, Entity.class);
        Entity entity = (Entity) clazz.getAnnotation(Entity.class);
        if (entity != null && StringUtils.hasText(entity.name())) {
            return entity.name();
        }

        Table table = (Table) clazz.getAnnotation(Table.class);
        if (table != null && StringUtils.hasText(table.name())) {
            return table.name();
        }
        return clazz.getSimpleName().toLowerCase();
    }

    /**
     * 获取主键
     *
     * @param clazz 持久层类
     * @return 主键名称
     */
    public static String getPrimaryKey(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                Column column = field.getAnnotation(Column.class);
                if (column != null && StringUtils.hasText(column.name())) {
                    return column.name();
                }
                return field.getName();
            }
        }
        return null;
    }


    /**
     * 获取某个字段名称
     *
     * @param clazz     持久层类
     * @param fieldName file
     * @return
     */
    public static String getColumn(Class clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            return getColumn(field);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getColumn(Field field) {
        Column column = field.getAnnotation(Column.class);
        if (column != null && StringUtils.hasText(column.name())) {
            return column.name();
        }
        return field.getName();
    }

    /**
     * 生成插入语句
     *
     * @param object 目标对象
     * @return sql
     */
    public static String insertSql(Object object) {
        return createSql(object, INSERT);
    }


    /**
     * 更新语句
     *
     * @param object
     * @return
     */
    public static String updateSql(Object object) {
        return createSql(object, UPDATE);
    }

    /**
     * 查找语句
     *
     * @param object
     * @return
     */
    public static String selectColumnSql(Object object) {
        return createSql(object, SELECT_COLUMN);
    }

    public static String selectAllSql(Object object) {
        return createSql(object, SELECT_ALL);
    }

    /**
     * 删除语句
     *
     * @param object
     * @return
     */
    public static String deleteSql(Object object) {
        return createSql(object, DELETE);
    }

    /**
     * 创建sql语句
     *
     * @param object 持久层类
     * @param sql    sql语句 INSERT|UPDATE|DELETE|SELECT_ALL|SELECT_COLUMN
     * @return
     */
    private static String createSql(Object object, String sql) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String tableName = getTableName(clazz); //table名称
        StringBuilder where = new StringBuilder();
        StringBuilder setValue = new StringBuilder();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        boolean firstWhere = true;
        String idName = ""; //主键名
        String idValue = ""; //主键值

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String column = getColumn(field);  //字段名称
                Object fieldValue = field.get(object); //字段值
                if (field.getAnnotation(Id.class) != null) {
                    idName = column;
                    idValue = fieldValue.toString();
                }

                if (fieldValue != null && !"0".equals(fieldValue.toString())) {
                    if (firstWhere) {
                        where.append("`" + column + "`='" + fieldValue + "'");
                        setValue.append("`" + column + "`='" + fieldValue + "'");
                        columns.append("`" + column + "`");
                        values.append("`" + fieldValue + "`");
                        firstWhere = false;
                    } else {
                        where.append(", `" + column + "`='" + fieldValue + "'");
                        setValue.append(", `" + column + "`='" + fieldValue + "'");
                        columns.append(",`" + column + "`");
                        values.append(", '" + fieldValue + "'");
                    }
                }
            }

            if (StringUtils.hasText(idValue) && !"0".equals(idValue)) {
                where = new StringBuilder("`" + idName + "` = '" + idValue + "'");
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sql.replaceAll(":TABLE", tableName)
                .replaceAll(":COLUMN", columns.toString())
                .replaceAll(":VALUE", values.toString())
                .replaceAll(":SET_VALUE", setValue.toString())
                .replaceAll(":WHERE", where.toString());
    }
}